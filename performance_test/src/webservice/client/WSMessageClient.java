package webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;
import cn.edu.dhu.acm.oj.buslogic.facade.message.MessageForm;
import cn.edu.dhu.acm.oj.buslogic.facade.message.MessageBean;

public class WSMessageClient {

        final private QName qName = new QName("http://webservice.oj.acm.dhu.edu.cn/", "MessageServiceService");
        private URL url;

        public WSMessageClient(String host) throws java.lang.Exception {
                url = new URL("http://" + host + "/dhuoj/MessageService?wsdl");
        }

        public Integer submitMessage(MessageForm msgForm) throws java.lang.Exception {
                MessageServiceService service = new MessageServiceService(url, qName);
                MessageService port = service.getMessageServicePort();
                return port.postMessage(msgForm);
        }

        public MessageBean queryMessageStatus(int msgID) throws java.lang.Exception {
                MessageServiceService service = new MessageServiceService(url, qName);
                MessageService port = service.getMessageServicePort();
                return port.queryMessageStatus(msgID);
        }
}

