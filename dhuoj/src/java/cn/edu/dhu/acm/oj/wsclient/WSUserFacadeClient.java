package cn.edu.dhu.acm.oj.wsclient;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.buslogic.facade.user.*;

public class WSUserFacadeClient {

    final private QName qName = new QName("http://user.facade.buslogic.oj.acm.dhu.edu.cn/", "WSUserFacadeService");
    private URL url;

    public WSUserFacadeClient(String host) {
        try {
            url = new URL(WSUserFacadeService.class.getResource("."), "http://" + host + "/primeserver/WSUserFacade?wsdl");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(LoginForm loginForm) throws Exception {
        WSUserFacadeService service = new WSUserFacadeService(url, qName);
        WSUserFacade port = service.getWSUserFacadePort();
        return port.login(loginForm);
    }

    public boolean register(RegisterForm regForm) throws Exception {
        WSUserFacadeService service = new WSUserFacadeService(url, qName);
        WSUserFacade port = service.getWSUserFacadePort();
        return port.register(regForm);
    }
    
}
