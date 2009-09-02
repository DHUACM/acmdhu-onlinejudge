package cn.edu.dhu.acm.oj.wsclient;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.buslogic.facade.message.*;

public class WSMessageFacadeClient {

    final private QName qName = new QName("http://message.facade.buslogic.oj.acm.dhu.edu.cn/", "WSMessageFacadeService");
    private URL url;

    public WSMessageFacadeClient(String host) {
        try {
            url = new URL(WSMessageFacadeService.class.getResource("."), "http://" + host + "/primeserver/WSMessageFacade?wsdl");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public int submitMessage(MessageForm msgForm) throws Exception {
        WSMessageFacadeService service = new WSMessageFacadeService(url, qName);
        WSMessageFacade port = service.getWSMessageFacadePort();
        return port.submitMessage(msgForm);
    }

    public MessageBean queryMessageStatus(int msgID) throws Exception {
        WSMessageFacadeService service = new WSMessageFacadeService(url, qName);
        WSMessageFacade port = service.getWSMessageFacadePort();
        return port.queryMessageStatus(msgID);
    }
}
