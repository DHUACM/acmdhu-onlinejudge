package com.zxp;

import java.io.*;
import java.util.*;
import org.jdom.*;
import com.dyf.*;

public class PaperDetail {
      private Element detailRoot;

      /**
       * 试卷信息类的构造函数
       **/
      public PaperDetail(){
         detailRoot = new Element("PaperDetail",true);

         detailRoot.addContent(new Element("PaperVersion"));
         detailRoot.addContent(new Element("PaperID"));
         detailRoot.addContent(new Element("PaperName"));
         detailRoot.addContent(new Element("PaperAuthor"));

         Element elem = new Element("PaperTotalTime");
         elem.setAttribute("unit", "hour");
         detailRoot.addContent(elem);

         elem = new Element("PaperStartTime");
         elem.setAttribute("zone", "+8");
         detailRoot.addContent(elem);

         detailRoot.addContent(new Element("PaperProblemSum"));
         detailRoot.addContent(new Element("PublicKey"));
         detailRoot.addContent(new Element("Signature"));

         detailRoot.addContent(new Element("PaperReference"));
         detailRoot.addContent(new Element("PaperDescribe"));
       }
       /**
        * 试卷信息类的构造函数，root是试卷信息类对象要复制的外界的一个元素
       **/
       public PaperDetail( Element root ){
         detailRoot = root;
         if( detailRoot.getChildrenCount("Signature") == 0 ){
           detailRoot.addContent(new Element("PublicKey"));
           detailRoot.addContent(new Element("Signature"));
         }
       }
       /**
        * 得到试卷信息类的根节点
       **/
        public Element getRootElement(){
          return detailRoot;
        }
        /**
         * 设置电子程序题试卷的版本号为version
       **/
        public void setVersion( String version ){
          detailRoot.setChildText("PaperVersion", version );
        }
        /**
         * 得到电子程序题试卷的版本号
       **/
        public String getVersion(){
          return detailRoot.getChildText("PaperVersion");
        }
        /**
         * 设置试卷的ID
       **/
        public void setID( String id ){
          detailRoot.getChild("PaperID").setText( id );
        }
        /**
         * 得到试卷ID
       **/
        public String getID(){
          return detailRoot.getChild("PaperID").getText();
        }
        /**
         *设置试卷信息类内设置的试卷的总题目
       **/
        public void setProblemSum( String sum ){
          detailRoot.getChild("PaperProblemSum").setText( sum );
        }
        /**
         * 得到试卷信息类内设置的试卷的总题数
       **/
        public String getProblemSum(){
          return detailRoot.getChild("PaperProblemSum").getText();
        }
        /**
         * 设置试卷比赛开始时间或使用时间
       **/
        public void setStartTime( String startTime ){
          detailRoot.getChild("PaperStartTime").setText( startTime );
        }
        /**
         * 得到试卷比赛开始时间或使用时间
       **/
        public String getStartTime(){
          return detailRoot.getChild("PaperStartTime").getText();
        }
        /**
         * 设置试卷涉及的知识点
       **/
        public void setReference( String reference ){
          detailRoot.getChild("PaperReference").setText( "" );
          detailRoot.getChild("PaperReference").addContent(new CDATA(reference));
        }
        /**
         * 得到试卷涉及的知识点
       **/
        public String getReference(){
          return detailRoot.getChild("PaperReference").getText();
        }
        /**
         * 得到试卷描述
       **/
        public String getDecribe(){
          return detailRoot.getChild("PaperDescribe").getText();
        }
        /**
         * 设置试卷描述
       **/
        public void setDecribe( String decribe ){
          detailRoot.getChild("PaperDescribe").setText( "" );
          detailRoot.getChild("PaperDescribe").addContent(new CDATA(decribe));
        }
        /**
         * 设置试卷名称
       **/
        public void setName( String name ){
          detailRoot.getChild("PaperName").setText( name );
        }
        /**
         *得到试卷名称
       **/
        public String getName(){
          return detailRoot.getChild("PaperName").getText();
        }
        /**
         * 设置试卷作者
       **/
        public void setAuthor( String author ){
          detailRoot.getChild("PaperAuthor").setText( author );
        }
        /**
         * 得到试卷作者
       **/
        public String getAuthor(){
          return detailRoot.getChild("PaperAuthor").getText();
        }
        /**
         * 设置试卷考试的总运行时间
       **/
        public void setTotalTime( String totalTime ){
          detailRoot.getChild("PaperTotalTime").setText( totalTime );
        }
        /**
         * 得到试卷考试的总运行时间
       **/
        public String getTotalTime(){
          return detailRoot.getChild("PaperTotalTime").getText();
        }
        /**
         * 设置试卷公钥
       **/
        public void setPublicKey( String publicKey ){
          detailRoot.setChildText( "PublicKey", publicKey );
        }
        /**
         * 得到试卷公钥
       **/
        public String getPublicKey(){
          return detailRoot.getChildText( "PublicKey" );
        }
        /**
         * 设置试卷数字签名
       **/
        public void setSignature( String signature ){
          detailRoot.setChildText( "Signature", signature );
        }
        /**
         * 得到试卷数字签名
       **/
        public String getSignature(){
          return detailRoot.getChildText( "Signature" );
        }
}