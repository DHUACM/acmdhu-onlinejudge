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


final class MsgClient{
  String ServerIP = "localhost";

  public MsgClient() {
  }

  public void setServerIP(String str){
      ServerIP = str;
  }

//------------------------------------------------------------------------------
  public MessageType getMessage(String add) throws Exception{
      String res = "";

      //System.out.println("Connecting MSGService to get@"+ServerIP);

      String EndPoint = "http://"+ServerIP+":8080/axis/services/MsgServer";
      Service service = new Service();
      try{
          Call  call = (Call)service.createCall();
          call.setTargetEndpointAddress(new java.net.URL(EndPoint));
          call.setOperationName("getMessage");
          res = (String)call.invoke(new Object[] { add });
      }catch(Exception e){
          System.err.println(e.toString()+" ::  Msg getting processs error.");
          throw e;
      }

      MessageType result = new MessageType(res);
      if (!result.getContext().equals(""))
          System.out.println("Message  <" + result.getContext()+">  Received.\n");

      return new MessageType(res);
  }

//------------------------------------------------------------------------------

  public boolean sendMessage(MessageType m) throws Exception{

      Boolean res = new Boolean(false);

      //System.out.println("connecting MsgService to SEND@"+ServerIP);

      String EndPoint = "http://"+ServerIP+":8080/axis/services/MsgServer";
      Service service = new Service();
      try{
          Call  call = (Call)service.createCall();
          call.setTargetEndpointAddress(new java.net.URL(EndPoint));
          call.setOperationName("addMessage");
          res = (Boolean)call.invoke(new Object[] { m.toString() });
      }catch(Exception e){
          System.err.println(e.toString()+" ::  Msg sending processs error.");
          throw e;
      }

      System.out.println("Message <"+m.getContext()+"> Sent Out.");
      return res.booleanValue();
  }

//******************************************************************************
  public static void main(String[] args) {
    MsgClient msgClient1 = new MsgClient();
  }
}