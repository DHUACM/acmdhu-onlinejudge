package cn.edu.dhu.acm.oj.wsclient;

import java.util.List;
import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.buslogic.facade.contest.*;

public class WSContestFacadeClient {

    final private QName qName = new QName("http://contest.facade.buslogic.oj.acm.dhu.edu.cn/", "WSContestFacadeService");
    private URL url;

    public WSContestFacadeClient(String host) {
        try {
            url = new URL(WSContestFacadeService.class.getResource("."), "http://" + host + "/primeserver/WSContestFacade?wsdl");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public int submitCode(SubmitCodeForm scForm) throws Exception {
        WSContestFacadeService service = new WSContestFacadeService(url, qName);
        WSContestFacade port = service.getWSContestFacadePort();
        return port.submitCode(scForm);
    }

    public SolutionBean querySubmitStatus(int solutionID) throws Exception {
        WSContestFacadeService service = new WSContestFacadeService(url, qName);
        WSContestFacade port = service.getWSContestFacadePort();
        return port.querySubmitStatus(solutionID);
    }

    public List<ContestBean> getContestByStatus(int status) throws Exception {
        WSContestFacadeService service = new WSContestFacadeService(url, qName);
        WSContestFacade port = service.getWSContestFacadePort();
        return port.getContestByStatus(status);
    }

    public ContestBean getContestDetail(String userID, int contestID) throws Exception {
        WSContestFacadeService service = new WSContestFacadeService(url, qName);
        WSContestFacade port = service.getWSContestFacadePort();
        return port.getContestDetail(userID, contestID);
    }
}
