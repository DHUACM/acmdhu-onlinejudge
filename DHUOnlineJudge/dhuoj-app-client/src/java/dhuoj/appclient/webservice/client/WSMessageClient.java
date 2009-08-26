package dhuoj.appclient.webservice.client;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import dhuoj.webservice.*;

public class WSMessageClient {

    final private QName qName = new QName("http://webservice.dhuoj/", "WSMessageService");
    private URL url;

    public WSMessageClient(String host) {
        try {
            url = new URL("http://" + host + "/dhuoj/WSMessageService?WSDL");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer submitMessage(MessageForm msgForm) throws Exception {
        WSMessageService service = new WSMessageService(url, qName);
        WSMessage port = service.getWSMessagePort();
        return port.submitMessage(msgForm);
    }

    public Message queryMessageStatus(int msgID) throws Exception {
        WSMessageService service = new WSMessageService(url, qName);
        WSMessage port = service.getWSMessagePort();
        return port.queryMessageStatus(msgID);
    }
}
