package cn.edu.dhu.acm.oj.buslogic.facade.user;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.edu.dhu.acm.oj.common.util.Util;
import cn.edu.dhu.acm.oj.common.form.LoginForm;
import cn.edu.dhu.acm.oj.common.form.RegisterForm;
import cn.edu.dhu.acm.oj.persistence.beans.UserBean;
import cn.edu.dhu.acm.oj.persistence.dao.UserDAO;
import cn.edu.dhu.acm.oj.exception.UserRegisterFailException;

@WebService()
public class WSUserFacade {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "loginForm") LoginForm loginForm) {
        UserDAO udao = new UserDAO();
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        UserBean ubean = udao.chkLogin(username, password);
        return ubean == null ? false : true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public boolean register(@WebParam(name = "regForm") RegisterForm regForm) throws UserRegisterFailException {
        UserDAO udao = new UserDAO();
        UserBean ubean = udao.findUser(regForm.getUserID());
        // user already exist, register failed.
        if (ubean != null) {
            throw new UserRegisterFailException("UserID " + regForm.getUserID() + " already exist, register failed.");
        }
        ubean = new UserBean(regForm.getUserID(),
                regForm.getPassword(),
                regForm.getEmail(),
                regForm.getNickName(),
                regForm.getSchool(),
                "127.0.0.1",
                Util.getTime(),
                Util.getTime(),
                0);
        udao.addUser(ubean);
        return true;
    }
}
