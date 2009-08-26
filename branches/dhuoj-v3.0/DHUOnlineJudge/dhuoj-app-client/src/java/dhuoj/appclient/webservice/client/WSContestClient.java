package dhuoj.appclient.webservice.client;

import java.util.List;
import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import dhuoj.webservice.*;

public class WSContestClient {

    final private QName qName = new QName("http://webservice.dhuoj/", "WSContestService");
    private URL url;

    public WSContestClient(String host) {
        try {
            url = new URL("http://" + host + "/dhuoj/WSContestService?WSDL");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer submitCode(SubmitCodeForm submitCodeForm) throws Exception {
        WSContestService service = new WSContestService(url, qName);
        WSContest port = service.getWSContestPort();
        return port.submitCode(submitCodeForm);
    }

    public Solution querySubmitStatus(int solutionID) throws Exception {
        WSContestService service = new WSContestService(url, qName);
        WSContest port = service.getWSContestPort();
        return port.querySubmitStatus(solutionID);
    }

    public List<Contest> getContestList(int first, int max) throws Exception {
        WSContestService service = new WSContestService(url, qName);
        WSContest port = service.getWSContestPort();
        return port.getContestList(first, max);
    }
}
