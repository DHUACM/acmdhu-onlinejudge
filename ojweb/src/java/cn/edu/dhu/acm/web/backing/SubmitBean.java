package cn.edu.dhu.acm.web.backing;

import cn.edu.dhu.acm.web.backing.UserBean;
import cn.edu.dhu.acm.web.util.Util;
import cn.edu.dhu.acm.persistence.entity.User;
import cn.edu.dhu.acm.persistence.entity.Solution;
import cn.edu.dhu.acm.persistence.entity.SourceCode;
import cn.edu.dhu.acm.persistence.dbao.SolutionDBAO;
import cn.edu.dhu.acm.persistence.dbao.SourceCodeDBAO;

/**
 * Created on Jan 16, 2009
 * @author yhu
 */

public class SubmitBean {

    private int cid = 0;
    private int pid;
    private short lang;
    private String code;
    private User user;

    public SubmitBean() {
        
    }

    public int getContestID() {
        return cid;
    }

    public void setContestID(int cid) {
        this.cid = cid;
    }

    public int getProblemID() {
        return pid;
    }

    public void setProblemID(int pid) {
        this.pid = pid;
    }

    public short getLanguage() {
        return lang;
    }

    public void setLanguage(short lang) {
        this.lang = lang;
    }

    public String getSourceCode() {
        return code;
    }

    public void setSourceCode(String code) {
        this.code = code;
    }

    public String submit_action() {
        UserBean ub = (UserBean) Util.getBean("UserBean");
        user = ub.getUser();

        Solution sln = new Solution();
        sln.setContestId(cid);
        sln.setProblemId(pid);
        sln.setUserId(user.getUserId());
        sln.setSubmitDate(new java.util.Date());
        sln.setLanguage(lang);
        sln.setCodeLength(code.length());

        SolutionDBAO slnDbao = new SolutionDBAO();
        slnDbao.create(sln);

        SourceCode source = new SourceCode();
        source.setSolutionId(sln.getSolutionId());
        source.setSource(code);

        try {
            SourceCodeDBAO srcDbao = new SourceCodeDBAO();
            srcDbao.create(source);
        } catch(Exception e) {

        }
        return "success";
    }
}
