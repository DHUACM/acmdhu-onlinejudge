package dhuoj.appclient.webservice.client;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import dhuoj.webservice.*;

public class WSUserAccountClient {

    final private QName qName = new QName("http://webservice.dhuoj/", "WSUserAccountService");
    private URL url;

    public WSUserAccountClient(String host) {
        try {
            url = new URL("http://" + host + "/dhuoj/WSUserAccountService?WSDL");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(LoginForm loginForm) throws Exception {
        WSUserAccountService service = new WSUserAccountService(url, qName);
        WSUserAccount port = service.getWSUserAccountPort();
        return port.login(loginForm);
    }

    public boolean register(RegisterForm regForm) throws Exception {
        WSUserAccountService service = new WSUserAccountService(url, qName);
        WSUserAccount port = service.getWSUserAccountPort();
        return port.register(regForm);
    }
}
