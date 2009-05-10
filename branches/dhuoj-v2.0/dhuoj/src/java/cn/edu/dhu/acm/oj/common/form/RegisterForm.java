package cn.edu.dhu.acm.oj.common.form;

public class RegisterForm {

    private String userID = null;
    private String password = null;
    private String email = null;
    private String nickName = null;
    private String school = null;

    public RegisterForm() {
    }

    public RegisterForm(String uid, String pass, String email, String nick, String school) {
        this.userID = uid;
        this.password = pass;
        this.email = email;
        this.nickName = nick;
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
