/*
 * RunSubmit.java
 * 
 * Created on 2007-6-5, 9:30:30
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author 孙辞海
 */
class RunSubmit implements Runnable{
    String paperNo;
    String problemNo;
    public RunSubmit(String paperNo,String problemNo){
        this.paperNo = paperNo;
        this.problemNo = problemNo;
    }
    public void run(){
        Control.Submit(paperNo, problemNo);
    }
}

