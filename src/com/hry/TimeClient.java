package com.hry;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.lang.*;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import java.util.*;

final class TimeClient extends Thread {

    private int sec = 0;
    private int min = 0;
    private int hour = 0;
    String ServerIP = "localhost";
    private long ADJUST_INTERVAL = 0;
    private long count = 0;
    private long INITIAL_TIME;
    private long PRE_TIME;
    private long ADJUSTMENT;
    public boolean UPDATED;

    public TimeClient() {
        ADJUSTMENT = 0;
        INITIAL_TIME = 0;
    }

    public void setServerIP(String add){
        ServerIP = add;
    }

    public void run(){
        Calendar cal = Calendar.getInstance();
        INITIAL_TIME = cal.getTimeInMillis();
        PRE_TIME = INITIAL_TIME;

        while(true){
            try{
                sleep(1000);
            }catch(Exception e){}

            if ( ADJUST_INTERVAL == 0 ) {
                //System.out.println("Time Synchronization Cancelled.");
                continue;
            }

            count++;
            if( count % ADJUST_INTERVAL == 0){
                try{
                    adjust();
                    UPDATED = true;
                }catch(Exception e){
                    UPDATED = false;
                    e.printStackTrace();
                    System.out.println("Remote Time Service Error.\n");
                }
                System.out.println("Adjust with server finished.");
            }
        }
    }

//connect the server;
    public void adjust() throws Exception{

        if ( ServerIP == "localhost" ) return ;

        //System.out.println("Adjusting time with the server.\n");

        String endpoint =
                        "http://"+ServerIP+":8080/axis/services/Server";
        Service  service = new Service();

        try{
            Call     call    = (Call) service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            call.setOperationName("timeService");
            java.lang.Long res = (java.lang.Long) call.invoke( new Object[] {"invoke"}  );

            Calendar cal = Calendar.getInstance();

            long elapse = cal.getTimeInMillis()-INITIAL_TIME;

            ADJUSTMENT = res.longValue() - elapse;
        }
        catch(Exception e){
            //System.err.println(e.toString()+"-Register & TestPaper Process Error.");
            //UPDATED_TIME = false;
            throw e;
        }
        //UPDATED_TIME = true;
    }

    public long getTimeInMillis(){
        Calendar cal = Calendar.getInstance();

        long CURRENT_TIME = cal.getTimeInMillis();
        if (CURRENT_TIME < PRE_TIME){
            INITIAL_TIME = INITIAL_TIME - ( PRE_TIME -  CURRENT_TIME);
        }

        return cal.getTimeInMillis() - INITIAL_TIME + ADJUSTMENT;
    }

    public String getTime(){
        Calendar cal = Calendar.getInstance();

        //IF the time of the SYSTEM have been adjust by the USER, then
        long CURRENT_TIME = cal.getTimeInMillis();

        if ( CURRENT_TIME <  PRE_TIME ) {
            INITIAL_TIME = INITIAL_TIME - (PRE_TIME - CURRENT_TIME);
        }

        long elapse = CURRENT_TIME - INITIAL_TIME;
        elapse += ADJUSTMENT;

        //convert the time into HH:MM:SS format.
        elapse =  (int)(elapse/1000);
        hour   =  (int)(elapse/3600);
        elapse =  elapse % 3600;
        min    =  (int)(elapse/60);
        sec    =  (int)elapse % 60 ;

        //FORMAT the time into String kind;
        String result;
        result = Integer.toString(hour);
        result +=":";

        String str = Integer.toString(min);
        result += str.length()==1 ? "0"+str :str;
        result +=":";

        str = Integer.toString(sec);
        result += str.length()==1 ? "0"+str :str;

        PRE_TIME = CURRENT_TIME;

        return result;
    }

    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return min;
    }
    public int getSecond(){
        return sec;
    }
    public void setHour(int h){
        hour = h;
    }
    public void setMin(int m){
        min = m;
    }
    public void setSec(int s){
        sec = s;
    }

    public String serveTime(String str){
        return "";
    }

    public void setInterval(long inter){
        ADJUST_INTERVAL = inter;
    }
/*
    public static void main(String[] args) {
        TimeClient TimeClient1 = new TimeClient();

        TimeClient1.start();
        try{
            Thread.sleep(1000);
        }catch(Exception e){}

        TimeClient1.setHour(12);
        TimeClient1.setMin(2);
        System.out.println(TimeClient1.getTime());
        TimeClient1.stop();
    }
*/
}