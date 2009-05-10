package cn.edu.dhu.acm.oj.logic.facade;

import java.util.Calendar;
import cn.edu.dhu.acm.oj.common.form.LoginForm;
import cn.edu.dhu.acm.oj.common.form.RegisterForm;
import cn.edu.dhu.acm.oj.persistence.beans.UserBean;
import cn.edu.dhu.acm.oj.persistence.dao.UserDAO;
import cn.edu.dhu.acm.oj.exception.*;

public class UserFacade {

    public static boolean login(LoginForm uf) {
        UserDAO udao = new UserDAO();
        String username = uf.getUsername();
        String password = uf.getPassword();
        UserBean ubean = udao.chkLogin(username, password);
        return ubean == null ? false : true;
    }

    public static boolean register(RegisterForm regForm) throws UserRegisterFailException {
        UserDAO udao = new UserDAO();
        UserBean ubean = udao.findUser(regForm.getUserID());
        // user already exist, register failed.
        if (ubean != null) throw new UserRegisterFailException("UserID " + regForm.getUserID() + " already exist, register failed.");
        ubean = new UserBean(regForm.getUserID(), 
                                           regForm.getPassword(),
                                           regForm.getEmail(),
                                           regForm.getNickName(),
                                           regForm.getSchool(),
                                           "127.0.0.1",
                                           Calendar.getInstance().getTime(),
                                           Calendar.getInstance().getTime(),
                                           0);
        udao.addUser(ubean);
        return true;
    }
}
