package cn.edu.dhu.acm.web.backing;

import javax.faces.application.FacesMessage;

import cn.edu.dhu.acm.persistence.entity.User;
import cn.edu.dhu.acm.persistence.dbao.UserDBAO;

/**
 * Created on Jan 15, 2009
 * @author yhu
 */

public class RegisterBean {

    String username;
    String password;
    String repassword;
    String email;
    String nickname;
    String school;

    public RegisterBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String submit_action() {
        User user = new User(username, password, email, nickname, school);
        try {
            UserDBAO dbao = new UserDBAO();
            dbao.create(user);
            return "success";
        } catch (Exception e) {
            return null;
        }
    }
}
