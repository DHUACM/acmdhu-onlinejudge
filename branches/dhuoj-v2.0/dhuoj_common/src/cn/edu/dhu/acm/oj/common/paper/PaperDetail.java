package cn.edu.dhu.acm.oj.common.paper;

import java.io.*;
import java.util.*;
import org.jdom.*;
import problem.*;

public class PaperDetail {
      private Element detailRoot;

      /**
       * �Ծ���Ϣ��Ĺ��캯��
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
        * �Ծ���Ϣ��Ĺ��캯��root���Ծ���Ϣ�����Ҫ���Ƶ�����һ��Ԫ��
       **/
       public PaperDetail( Element root ){
         detailRoot = root;
         if( detailRoot.getChildrenCount("Signature") == 0 ){
           detailRoot.addContent(new Element("PublicKey"));
           detailRoot.addContent(new Element("Signature"));
         }
       }
       /**
        * �õ��Ծ���Ϣ��ĸ�ڵ�
       **/
        public Element getRootElement(){
          return detailRoot;
        }
        /**
         * ���õ��ӳ������Ծ�İ汾��Ϊversion
       **/
        public void setVersion( String version ){
          detailRoot.setChildText("PaperVersion", version );
        }
        /**
         * �õ����ӳ������Ծ�İ汾��
       **/
        public String getVersion(){
          return detailRoot.getChildText("PaperVersion");
        }
        /**
         * �����Ծ��ID
       **/
        public void setID( String id ){
          detailRoot.getChild("PaperID").setText( id );
        }
        /**
         * �õ��Ծ�ID
       **/
        public String getID(){
          return detailRoot.getChild("PaperID").getText();
        }
        /**
         *�����Ծ���Ϣ�������õ��Ծ������Ŀ
       **/
        public void setProblemSum( String sum ){
          detailRoot.getChild("PaperProblemSum").setText( sum );
        }
        /**
         * �õ��Ծ���Ϣ�������õ��Ծ��������
       **/
        public String getProblemSum(){
          return detailRoot.getChild("PaperProblemSum").getText();
        }
        /**
         * �����Ծ����ʼʱ���ʹ��ʱ��
       **/
        public void setStartTime( String startTime ){
          detailRoot.getChild("PaperStartTime").setText( startTime );
        }
        /**
         * �õ��Ծ����ʼʱ���ʹ��ʱ��
       **/
        public String getStartTime(){
          return detailRoot.getChild("PaperStartTime").getText();
        }
        /**
         * �����Ծ��漰��֪ʶ��
       **/
        public void setReference( String reference ){
          detailRoot.getChild("PaperReference").setText( "" );
          detailRoot.getChild("PaperReference").addContent(new CDATA(reference));
        }
        /**
         * �õ��Ծ��漰��֪ʶ��
       **/
        public String getReference(){
          return detailRoot.getChild("PaperReference").getText();
        }
        /**
         * �õ��Ծ�����
       **/
        public String getDecribe(){
          return detailRoot.getChild("PaperDescribe").getText();
        }
        /**
         * �����Ծ�����
       **/
        public void setDecribe( String decribe ){
          detailRoot.getChild("PaperDescribe").setText( "" );
          detailRoot.getChild("PaperDescribe").addContent(new CDATA(decribe));
        }
        /**
         * �����Ծ����
       **/
        public void setName( String name ){
          detailRoot.getChild("PaperName").setText( name );
        }
        /**
         *�õ��Ծ����
       **/
        public String getName(){
          return detailRoot.getChild("PaperName").getText();
        }
        /**
         * �����Ծ�����
       **/
        public void setAuthor( String author ){
          detailRoot.getChild("PaperAuthor").setText( author );
        }
        /**
         * �õ��Ծ�����
       **/
        public String getAuthor(){
          return detailRoot.getChild("PaperAuthor").getText();
        }
        /**
         * �����Ծ?�Ե�������ʱ��
       **/
        public void setTotalTime( String totalTime ){
          detailRoot.getChild("PaperTotalTime").setText( totalTime );
        }
        /**
         * �õ��Ծ?�Ե�������ʱ��
       **/
        public String getTotalTime(){
          return detailRoot.getChild("PaperTotalTime").getText();
        }
        /**
         * �����Ծ?Կ
       **/
        public void setPublicKey( String publicKey ){
          detailRoot.setChildText( "PublicKey", publicKey );
        }
        /**
         * �õ��Ծ?Կ
       **/
        public String getPublicKey(){
          return detailRoot.getChildText( "PublicKey" );
        }
        /**
         * �����Ծ�����ǩ��
       **/
        public void setSignature( String signature ){
          detailRoot.setChildText( "Signature", signature );
        }
        /**
         * �õ��Ծ�����ǩ��
       **/
        public String getSignature(){
          return detailRoot.getChildText( "Signature" );
        }
}