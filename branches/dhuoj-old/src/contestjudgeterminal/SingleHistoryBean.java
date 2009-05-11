package contestjudgeterminal;

/**
 * <p>Title: ContestJudgeTerminal</p>
 * <p>Description: ContestJudgeTerminal</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: DHU</p>
 * @author DHU
 * @version 1.0
 */

import java.util.*;
import java.io.*;
import org.jdom.*;
import com.lrf.*;

final public class SingleHistoryBean {
  public Element root;
  public String fileName;
  public String storePath;
  public String ebPath;

  public SingleHistoryBean(){
    root = new Element("SingleHistory");
    Document doc = new Document(root);
  }

  public void init(Element RegisterInfo , String EnvironmentBeanFilePath){
    ebPath = EnvironmentBeanFilePath;
    root.addContent((Element)(RegisterInfo.getChild("StudentNo").clone()));

    fileName = RegisterInfo.getChildText("StudentNo")+".xml";
    root.addContent(new Element("FileName").setText(fileName));

    root.addContent((Element)(RegisterInfo.clone()));
    storePath = new EnvironmentBean(ebPath).getHistory();
  }
  public void setEBPath(String path){
    ebPath = path;
    storePath = new EnvironmentBean(ebPath).getHistory();
  }
  public void addanswer(Element answerInformation){
    root.addContent((Element) answerInformation.clone());
  }
  public void setroot(Element target){
    root = target;
  }
  public boolean save(){
    try {
          File storedir = new File(storePath);
          if (storedir.exists()==false) storedir.mkdir();
          String fo = getfileName();
          root.marshal(fo);
          return true;
        } catch (java.io.IOException eio) {
          System.err.println(eio.toString());
        }
    return false;
  }
  public void setfileName(String name){
    root.setChildText("FileName",name);
  }
  public String getfileName(){
    return root.getChildText("FileName");
  }
  public void setstudentid(String id){
    root.getChild("StudentID").setText(id);
  }
  public Element getRoot(){
    return root;
  }
  public String getstudentid(){
    return root.getChildText("StudentID");
  }
  public Iterator getallanswer(){
    Iterator it = root.getChildren("AnswerInformation").iterator();
    return it;
  }
}