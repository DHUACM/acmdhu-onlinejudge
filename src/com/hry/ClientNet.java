package com.hry;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import java.io.*;
import java.net.InetAddress;
import java.lang.*;
import com.sjn.*;

final public class ClientNet {
  private TimeClient    time;
  private String        ServerIP ="localhost";
  private MsgClient     Message;
  private String        LocalIP ;
  Element TestPaper;

  public ClientNet() {
      Message = new MsgClient();
      time  =  new TimeClient();

      try{
          LocalIP = (String)InetAddress.getLocalHost().getHostAddress();
      }catch(Exception e){
          System.err.println(e.toString());
      }

      time.start();
  }


  public boolean submit(SingleAnswerDoc doc) throws Exception{
      doc.setSubmitTime(Long.toString( time.getTimeInMillis() ));

      if ( doc.getLanguage().equals("") )
          try{
              XMLOutputter xo = new XMLOutputter();
              String str = xo.outputString(doc.getDocument().getRootElement());

              str = "<![CDATA["+ ServerIP + "+" + str + "]]>";

              register(str);
          }catch(Exception e){
              System.err.println("ERROR: while registering");
              throw e;
          }
          else
              try{
              submitAnswer(doc.getDocument());
          }catch(Exception e){
              System.err.println("ERROR: while Submitting Answer");
              throw e;
          }

          return true;
  }

//------------------------------------------------------------------------------
//get TestPaper

  public void receivePaper() throws Exception {

    String endpoint = "http://"+ServerIP+":8080/axis/services/Server";
    Service  service = new Service();
    String   res = "";

    try{
        Call     call    = (Call) service.createCall();
        call.setTargetEndpointAddress( new java.net.URL(endpoint) );
        call.setOperationName("sendPaper");
        res = (String) call.invoke( new Object[] {"invoke"}  );
    }
    catch(Exception e){
        System.err.println(e.toString()+"\n");
        System.err.println("Error:  While Receiving TestPaper.");
        throw e;
    }

    if (res.equals("") ) return ;

    //Convert the String into Document Type;
    res = res.substring( 9 , res.length()-3 );
    StringReader ox= new StringReader(res);

    try{
        TestPaper = Element.unmarshal(ox);
    }catch(Exception JdomE){
        System.err.println(JdomE.toString()+"\n");
        System.err.println("Error: While Converting TestPaper.\n");
        throw JdomE;
    }
    //time.start();
  }

  public Element getTestPaper() throws Exception {
      return TestPaper;
  }

//------------------------------------------------------------------------------
//Submit the Register information, keep blocked until contest is started.
//at the end of this method, REAL-TIME Clock will start.

  private boolean submitAnswer(Document doc) throws Exception {
      Boolean res = new Boolean(false);

      //transform Document type into String FORMAT.
      XMLOutputter xo = new XMLOutputter();
      String str = "";
      try{
          str = xo.outputString(doc);
      }catch(Exception e){}

      str = "<![CDATA["+str +"]]>";

      String endpoint = "http://"+ServerIP+":8080/axis/services/Server";
      Service  service = new Service();

      try{
            Call     call    = (Call) service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            call.setOperationName("getTask");
            res = (Boolean) call.invoke( new Object[] {str}  );
      }
      catch(Exception e){
            System.err.println(e.toString()+ "\n");
            System.err.println("Error:  While Submitting ");
            throw e;
      }

      return res.booleanValue();
  }
//------------------------------------------------------------------------------

  private boolean register(String str) throws Exception {
      Boolean res = new Boolean(false);

      //transform Document type into String FORMAT.

      String endpoint = "http://"+ServerIP+":8080/axis/services/Server";
      Service  service = new Service();

      try{
            Call     call    = (Call) service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            call.setOperationName("doRegister");
            res = (Boolean) call.invoke( new Object[] {str}  );
      }
      catch(Exception e){
            System.err.println(e.toString()+ "\n");
            System.err.println("Error:  While Submitting LOGIN Info.");
            throw e;
      }

      receivePaper();

      return res.booleanValue();
  }

  public boolean isStarted() throws Exception {
      Boolean res = new Boolean(false);

      String endpoint = "http://"+ServerIP+":8080/axis/services/Server";
      Service  service = new Service();

      try{
            Call     call    = (Call) service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            call.setOperationName("isStarted");
            res = (Boolean) call.invoke( new Object[] {" "}  );
      }
      catch(Exception e){
            System.err.println(e.toString()+ "\n");
            System.err.println("Error:  While Submitting LOGIN Info.");
            throw e;
      }

      return res.booleanValue();
  }

//get Current Time.
  public String getTime(){
      return time.getTime();
  }

  public void setServerIP(String str){
      ServerIP = str;
      Message.setServerIP(str);
      time.setServerIP(str);
  }

  public boolean sendMessage(String msg) throws Exception{
      Message.sendMessage( new MessageType( LocalIP , "JUDGE" , msg ) );

      return true;
  }

  public MessageType getMessage() throws Exception{
      return Message.getMessage(LocalIP);
  }

  public boolean testNetwork() throws Exception{
      String endpoint =
                      "http://"+ServerIP+":8080/axis/services/Server";
      Service  service = new Service();
      Boolean res = null;

      try{
          Call     call    = (Call) service.createCall();
          call.setTargetEndpointAddress( new java.net.URL(endpoint) );
          call.setOperationName("testNetwork");
          res = (Boolean) call.invoke( new Object[] {"invoke"}  );
      }
      catch(Exception e){
          System.err.println(e.toString());
          System.err.println("Testing Network Error.");
          throw e;
      }

      return res.booleanValue();
    }

  public void setInterval(int i){
      time.setInterval(i);
  }

  /*public void start(){
      time.start();
  } */

  public static void main(String[] args) {
    ClientNet clientNet1 = new ClientNet();

    clientNet1.setServerIP("202.120.159.24");

    /*for (int i=0;i<300000;i++)
    try{
      clientNet1.sendMessage("³Å±©µôÄã");

        //System.out.println( clientNet1.getMessage().getContext());

    }catch(Exception e){System.err.print("dfd");}
*/
    SingleAnswerDoc doc = new SingleAnswerDoc();
    doc.setFilename("p1");
    doc.setIPAddress("202.120.159.30");
    doc.setName("hry");
    doc.setTestID("0051");
    doc.setStdNo("12233344");
    doc.setSubmitTime("900000000");

    doc.setSourceCode("int main() \n{return 0}\n");
    doc.setLanguage("");


    XMLOutputter xo = new XMLOutputter();
    String str = xo.outputString(doc.getDocument());
    //for (int  i =1 ; i<30000 ; i++)
    clientNet1.setInterval(0);
    try{
        System.out.println(clientNet1.isStarted());
    }catch(Exception e){}
  }
}
