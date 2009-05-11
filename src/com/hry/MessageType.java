package com.hry;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

final public class MessageType {

    String StartPoint  = "";
    String Context     = "";
    String Destination = "";

    public MessageType() {
        StartPoint = "";
        Context ="";
        Destination = "";
    }

    public MessageType(String s,String d,String c){
        StartPoint  = s;
        Context     = c;
        Destination = d;
    }

    public MessageType(String str){
        int i;
        if (str==""){
            StartPoint = "";
            Context ="";
            Destination = "";
            return ;
        }

        for(i=0;i<str.length();i++)
            if (str.charAt(i)=='+') break;

        StartPoint = str.substring(0,i);
        str = str.substring(i+1,str.length());

        for(i=0;i<str.length();i++)
            if (str.charAt(i)=='+') break;
        Destination = str.substring(0,i);
        Context = str.substring(i+1,str.length());
    }

    public String getStartPoint(){
        return StartPoint;
    }
    public String getContext(){
        return Context;
    }
    public String getDestination(){
        return Destination;
    }

    public String toString(){
        return StartPoint+"+"+Destination+"+"+Context;
    }

    public static void main(String[] args) {
        MessageType m = new MessageType();

        m = new MessageType("202.120.159.27","localhost","IBM TExt");

        m = new MessageType();

        m = new MessageType("202.120.159.27+localhost+IBM");

        System.out.println(m.getStartPoint());
        System.out.println(m.getContext());
        System.out.println(m.getDestination());

        m = new MessageType("");
        System.out.println(m.toString());
    }
}