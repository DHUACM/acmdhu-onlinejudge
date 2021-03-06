package cn.edu.dhu.acm.oj.persistence.beans;
// Generated May 4, 2009 10:16:51 PM by Hibernate Tools 3.2.1.GA



/**
 * ContestProblem generated by hbm2java
 */
public class ContestProblemBean  implements java.io.Serializable {

    private int id;
    private int contestId;
    private int problemId;
    private String title;
    private int accepted;
    private int submit;
    private int sequence;

    public ContestProblemBean() {
    }

    public ContestProblemBean(int id, int contestId, int problemId, String title, int accepted, int submit, int sequence) {
       this.id = id;
       this.contestId = contestId;
       this.problemId = problemId;
       this.title = title;
       this.accepted = accepted;
       this.submit = submit;
       this.sequence = sequence;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getContestId() {
        return this.contestId;
    }
    
    public void setContestId(int contestId) {
        this.contestId = contestId;
    }
    public int getProblemId() {
        return this.problemId;
    }
    
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public int getAccepted() {
        return this.accepted;
    }
    
    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
    public int getSubmit() {
        return this.submit;
    }
    
    public void setSubmit(int submit) {
        this.submit = submit;
    }
    public int getSequence() {
        return this.sequence;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}


