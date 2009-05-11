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
import java.lang.*;
import java.io.*;

final public class JudgeNet {
  private TimeClient Time;
  private MsgClient  Message;

  private String     ServerIP = "localhost";
  private String     PrimeServerIP = "localhost";

  public JudgeNet() {
      Message = new MsgClient();

      Time    = new TimeClient();
      Time.start();
  }

//------------------------------------------------------------------------------
/**Send one Message to the Client PC
 * Fist para is the Destination IP, and second the msg context.
 */

  public void sendMessage(String IP, String msg) throws Exception {
      Message.sendMessage(  new MessageType( "JUDGE",IP,msg)  );
  }

  /**Get a Message from server
   * if there isn't any , return context of ""
   */

  public MessageType getMessage() throws Exception{
      return Message.getMessage("JUDGE");
  }

//------------------------------------------------------------------------------

/**This Method receive the Answer which the Clients submit
 *
 */
  public Element getAnswer(String str) throws Exception{
      String res = "";

      Service service = new Service();
      String endpoint =
                "http://"+ServerIP+":8080/axis/services/Server";

      try{
          Call     call    = (Call) service.createCall();
          call.setTargetEndpointAddress( new java.net.URL(endpoint) );
          call.setOperationName("sendTask");
          res  = (String) call.invoke( new Object[] {""}  );
      }
      catch(Exception e){
          System.err.println(e.toString()+"\n");
          System.err.println("Error:  While receiving Judge Task.\n");
          throw e;
      }


      //SAXBuilder   ix  =  new SAXBuilder();
      Element elm = null;
      res = res.substring( 9 , res.length()-3 );

      try{
          elm = Element.unmarshal(new StringReader(res));
      }catch(Exception e){e.toString();}

      return elm;
  }

  public String getTime(){
      return Time.getTime();
  }

//------------------------------------------------------------------------------
  public void setServerIP(String add){
      ServerIP = add;
      Time.setServerIP(add);
      Message.setServerIP(add);
  }

  public void setPrimeServerIP(String add){
      PrimeServerIP = add;
  }

  public boolean updateRanklist(String info) throws Exception{
      Boolean res = new Boolean(false);

      Service service = new Service();
      String endpoint =
                "http://"+PrimeServerIP+":8080/axis/services/PrimeServer";

      try{
          Call     call    = (Call) service.createCall();
          call.setTargetEndpointAddress( new java.net.URL(endpoint) );
          call.setOperationName("getJudgeInfo");
          res  = (Boolean) call.invoke( new Object[] { info }  );
      }
      catch(Exception e){
          System.err.println(e.toString()+"\n");
          System.err.println("Error:  While Sending Problem Solve Status.");
          throw e;
      }

      return res.booleanValue();
  }

  public int getTaskCount() throws Exception{
      Integer res = null;

      Service service = new Service();
      String endpoint =
                "http://"+ServerIP+":8080/axis/services/Server";

      try{
          Call     call    = (Call) service.createCall();
          call.setTargetEndpointAddress( new java.net.URL(endpoint) );
          call.setOperationName("taskCount");
          res  = (Integer) call.invoke( new Object[] { "  " }  );
      }
      catch(Exception e){
          System.err.println(e.toString()+"\n");
          System.err.println("Error:  While getting Task Count from Server.");
          throw e;
      }
      return res.intValue();
  }

  public boolean testNetwork() throws Exception{
      try{
          if (!testServer()) return false;
          if (!testPrimeServer()) return false;
      }catch(Exception e){
          System.err.println("Error:  While Testing Network");
          throw e;
      }
      return true;
  }

  private boolean testServer() throws Exception{
      Boolean r = new Boolean(false);
      String endpoint =
                      "http://"+ServerIP+":8080/axis/services/Server";
      Service  service = new Service();

      try{
          Call     call    = (Call) service.createCall();
          call.setTargetEndpointAddress( new java.net.URL(endpoint) );
          call.setOperationName("testNetwork");
          r = (Boolean) call.invoke( new Object[] {"invoke"}  );
      }
      catch(Exception e){
          System.err.println(e.toString());
          System.err.println("Testing Server Error. @"+endpoint);
          throw e;
    }
    return r.booleanValue();
  }

  private boolean testPrimeServer() throws Exception {
      Boolean r = new Boolean(false);
      String endpoint =
                      "http://"+PrimeServerIP+":8080/axis/services/PrimeServer";
      Service  service = new Service();

      try{
          Call     call    = (Call) service.createCall();
          call.setTargetEndpointAddress( new java.net.URL(endpoint) );
          call.setOperationName("testNetwork");
          r = (Boolean) call.invoke( new Object[] {"invoke"}  );
      }
      catch(Exception e){
          System.err.println(e.toString());
          System.err.println("Testing PrimeServer Error. @"+endpoint);
          throw e;
    }
    return r.booleanValue();
  }


  public void setInterval(long i){
      Time.setInterval(i);
  }

  public static void main(String[] args) {
      JudgeNet n = new JudgeNet();

      n.setServerIP("202.120.159.24");
      n.setPrimeServerIP("202.120.159.29");

      while(true)
      try{

          //n.getTestPaper("");
      }catch(Exception e){
          System.err.println("dfd");
      }
  }
}