package cn.edu.dhu.acm.oj.logic.facade;

import cn.edu.dhu.acm.oj.common.form.LoginForm;
import cn.edu.dhu.acm.oj.persistence.beans.UserBean;
import cn.edu.dhu.acm.oj.persistence.dao.UserDAO;

public class UserFacade {

    public static boolean login(LoginForm uf) {
        UserDAO udao = new UserDAO();
        String username = uf.getUsername();
        String password = uf.getPassword();
        UserBean ubean = udao.chkLogin(username, password);
        return ubean == null ? false : true;
    }
}
