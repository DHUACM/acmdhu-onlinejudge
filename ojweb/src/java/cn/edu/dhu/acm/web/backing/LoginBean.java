package cn.edu.dhu.acm.web.backing;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputSecret;

import cn.edu.dhu.acm.persistence.entity.User;
import cn.edu.dhu.acm.persistence.dbao.UserDBAO;
import cn.edu.dhu.acm.web.backing.UserBean;
import cn.edu.dhu.acm.web.util.Util;

/**
 * Created on Jan 14, 2009
 * @author yhu
 */

public class LoginBean {

    private HtmlInputText username;
    private HtmlInputSecret password;

    public LoginBean() {
    }

    public HtmlInputText getUsername() {
        return username;
    }

    public void setUsername(HtmlInputText name) {
        username = name;
    }

    public HtmlInputSecret getPassword() {
        return password;
    }

    public void setPassword(HtmlInputSecret pass) {
        password = pass;
    }

    public String login_action() {
        UserDBAO dbao = new UserDBAO();
        String name = (String) username.getValue();
        String pass = (String) password.getValue();
        try {
            User user = dbao.findByUserIdAndPassword(name, pass);
            UserBean ub = (UserBean)Util.getBean("UserBean");
            ub.setUser(user);
            return "success";
        } catch(Exception e) {
            return "fail";
        }
    }
}
