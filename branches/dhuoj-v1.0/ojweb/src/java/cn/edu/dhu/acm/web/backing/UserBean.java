package cn.edu.dhu.acm.web.backing;

import javax.servlet.http.HttpSession;

import cn.edu.dhu.acm.persistence.entity.User;
import cn.edu.dhu.acm.web.util.Util;

/**
 * Created on Jan 14, 2009
 * @author yhu
 */

public class UserBean implements java.io.Serializable {

    private boolean logonMode = false;
    private User user;

    public UserBean() {
    }

    public void setLogonMode(boolean logonMode) {
        this.logonMode = logonMode;
    }

    public boolean isLogonMode() {
        logonMode = (user != null);
        return logonMode;
    }

    public void setUser(User user) {
        logonMode = true;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String logout() {
        HttpSession session = (HttpSession) Util.getExternalContext().getSession(false);
        session.invalidate();
        return "success";
    }
}
