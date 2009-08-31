package cn.edu.dhu.acm.oj.common.form;

public class MessageForm implements java.io.Serializable{

    private int messageID = 0;
    private String userID = null;
    private String question = null;
    private String response = null;

    public MessageForm() {
    }

    public MessageForm(int mid, String uid, String question, String response) {
        this.messageID = mid;
        this.userID = uid;
        this.question = question;
        this.response = response;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
