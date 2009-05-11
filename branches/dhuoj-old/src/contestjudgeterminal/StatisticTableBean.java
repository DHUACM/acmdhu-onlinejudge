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

final public class StatisticTableBean {
  public Element root = null;
  private int problemCount = 0;
  private String filename = "";

  public StatisticTableBean(String filePath) throws Exception{
    try{
      root = Element.unmarshal(filePath);
      filename = filePath;
    }catch(Exception e){
      throw e;}
      }
  public StatisticTableBean(Element x){
    root = x;}
  public boolean hasLogin(String studentNo){
    Element person = getPerson(studentNo);
    if (person.getChildText("Login").equals("Yes")){
      return true;}
    return false;}
  public boolean Login(String studentID,String studentNo,String computerNo,String gradeclass,String IP) throws Exception{
    if (isExist(studentID,studentNo)){
      Element person = getPerson(studentNo);
      person.setChildText("Login","Yes");
      person.setChildText("ComputerNo",computerNo);
      person.setChildText("Class",gradeclass);
      person.setChildText("IPAddress",IP);
      try{
      save();
      }catch(Exception ee){
        throw ee;}
      return true;}
    return false;}
  public boolean Login(String studentID,String studentNo){
  if (isExist(studentID,studentNo)){
    Element person = getPerson(studentNo);
    person . setChildText("Login","Yes");
    person . setChildText("ComputerNo","Unknown");
    person . setChildText("Class","Unknown");
    try{
    save();
    }catch(Exception ee){
    }
    return true;}
  return false;}
  private boolean isExist(String studentID,String studentNo){
    Element temp;
    temp = getPerson(studentNo);
    if (temp == null ){
    return false;}
    if (temp.getChildText("StudentNo").equals(studentNo)){
    return true;}
    return false;}
  private Element getPerson(String studentNo){
    Iterator it = root.getChild("Persons").getChildren("PersonInformation").iterator();
    while(it.hasNext()){
    Element temp = (Element)it.next();
    if (temp.getChildText("StudentNo").equals(studentNo)){
    return temp;
    }
    }
    return null;
    }
  private boolean checkProblem(Element person,String problemID){
    Iterator it = person.getChild("Problems").getChildren("SingleProblem").iterator();
    while(it.hasNext()){
      Element temp = (Element)it.next();
      if (temp.getChildText("ProblemID").equals(Integer.toString(Integer.parseInt(problemID)))){
        return true;
      }
    }
    return false;
  }
  private Element getProblemNode(Element person,String problemID){
    Iterator it = (person.getChild("Problems").getChildren("SingleProblem")).iterator();
    while(it.hasNext()){
      Element temp = (Element)it.next();
      if (temp.getChildText("ProblemID").equals(Integer.toString(Integer.parseInt(problemID)))){
        return temp;
      }
    }
    return null;
  }
  private String getPunishTime(Element problemNode){
    return problemNode.getChildText("PunishTime");
  }
  private Element setPunishTime(Element problemNode,String newTime){
    return problemNode.getChild("PunishTime").setText(newTime);
  }
  private String getStatus(Element problemNode){
    return problemNode.getChildText("Status");
  }
  private Element setStatus(Element problemNode,String newStatus){
    return problemNode.getChild("Status").setText(newStatus);
  }
  public void setClassName(String studentNo,String classname){
    Element opnode = getPerson(studentNo);
    opnode.setChildText("Class",classname);
  }
  public void setComputerNo(String studentNo,String computerNo){
    Element opnode = getPerson(studentNo);
    opnode.setChildText("ComputerNo",computerNo);
  }
  private String getSubmitAmount(Element problemNode){
    return problemNode.getChildText("SubmitAmount");
  }
  private Element setSubmitAmount(Element problemNode,String newAmount){
    return problemNode.getChild("SubmitAmount").setText(newAmount);
  }
  public void setProblemCount(int amount){
    problemCount = amount;
  }
  private Element getProblemFormat(){
    return (Element)root.getChild("Format").getChild("Problem").clone();
  }
  private Element getSingleProblemFormat(){
    return (Element)root.getChild("Format").getChild("SingleProblem").clone();
  }
  private void aBP(int howMany){
    int  c = 0;
    int number =0;
    Element addWhere = root.getChild("ProblemStatus");
    for(c=0;c<howMany;c++){
      Element add = getProblemFormat();
      number = c;
      add.setChildText("Number",Integer.toString(number));
      add.setChildText("Yes","0");
      add.setChildText("Whole","0");
      addWhere.addContent(add);
    }
  }
  private void aBS(Element a,int h){
    int c = 0;
    int problemID =0;
    for ( c = 0 ; c < h ; c++ ) {
      Element add = getSingleProblemFormat();
      problemID = c ;
      add.setChildText ( "ProblemID" , Integer.toString( problemID ) );
      add.setChildText ( "Status" , "No" );
      add.setChildText ( "PunishTime" , "0" );
      add.setChildText ( "SubmitAmount" , "0" );
      a.addContent ( add );
    }
  }
  private void changeWholeProblemStatus(String problemNumber,int yes,int whole){
    Element start = root.getChild("ProblemStatus");
    Iterator it = start.getChildren("Problem").iterator();
    while(it.hasNext()){
      Element temp = (Element)it.next();
      if (temp.getChildText("Number").equals(Integer.toString(Integer.parseInt(problemNumber)))){
        start = temp;
        break;
      }
    }
    start.getChild("Yes").setText(String.valueOf(Integer.parseInt(start.getChildText("Yes"))+yes));
    start.getChild("Whole").setText(String.valueOf(Integer.parseInt(start.getChildText("Whole"))+whole));
  }
  public void changeSingleProblemStatus(String msg) throws Exception{
    String studentNo = "";
    String problemID = "";
    boolean yesorno  = false;
    String howlong   = "" ;
    studentNo = msg.substring(0,8);
    problemID = msg.substring(8,10);
    if (msg.substring(10,11).equals("1")){
      yesorno = true;
    }
    howlong = msg.substring(11,msg.length());
    Element personnode = getPerson(studentNo);
    Element opnode = getProblemNode(personnode,problemID);
    String status = opnode.getChildText("Status");
    System.out.println("status"+status);
    if (status == "Yes"){
      return;}
    if (yesorno) {
      opnode.getChild("Status").setText("Yes");
      opnode.setChildText("PunishTime",Integer.toString(Integer.parseInt(opnode.getChildText("SubmitAmount"))*20+Integer.parseInt(howlong)));
      opnode.setChildText("SubmitAmount",Integer.toString(Integer.parseInt(opnode.getChildText("SubmitAmount"))+1));
      personnode.setChildText("AcceptAmount",Integer.toString(Integer.parseInt(personnode.getChildText("AcceptAmount"))+1));
      personnode.setChildText("PunishTimeAll", Integer.toString(Integer.parseInt(personnode.getChildText("PunishTimeAll"))+Integer.parseInt(opnode.getChildText("PunishTime"))));
      personnode.setChildText("SubmitCount", Integer.toString(Integer.parseInt(personnode.getChildText("SubmitCount")) + 1));
      changeWholeProblemStatus(problemID,1,1);}
    else {
      opnode.getChild("Status").setText("No");
      opnode.setChildText("SubmitAmount",Integer.toString(Integer.parseInt(opnode.getChildText("SubmitAmount"))+1));
      opnode.getChild("PunishTime").setText("0");
      personnode.setChildText("SubmitCount", Integer.toString(Integer.parseInt(personnode.getChildText("SubmitCount"))+1));
      changeWholeProblemStatus(problemID,0,1);
    }
  }
    public boolean init() throws Exception{
    boolean result = false;
    try{
      aBP(problemCount);
    }catch(Exception EXini){
      System.err.println("init fail");
      throw EXini;
    }
    return result;
  }
  public boolean save() throws Exception{
    System.err.print("Save");
    boolean success = false;
    try{
      root.marshal(filename);
      success = true;
      System.out.println("Success");
    }catch(Exception EXsave){
      System.err.println("fail");
      throw EXsave;
    }
    return success;
  }
  public Element getRoot(){
    return root;
  }

}


