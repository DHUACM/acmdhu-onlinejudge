package cn.edu.dhu.acm.oj.logic.facade;

import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.common.util.Util;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.persistence.beans.SourceCodeBean;
import cn.edu.dhu.acm.oj.persistence.dao.SolutionDAO;
import cn.edu.dhu.acm.oj.persistence.dao.SourceCodeDAO;


public class ContestFacade {

    public static int submitCode(SubmitCodeForm scf) {
        SolutionBean sbean = new SolutionBean(scf.getUserID(), scf.getContestID(),
                scf.getProblemID(), 0, 0, Util.getTime(), (short)0, scf.getLanguage());
        SolutionDAO sdao = new SolutionDAO();
        sdao.addSolution(sbean);

        SourceCodeBean scbean = new SourceCodeBean(sbean.getSolutionId(), scf.getSource());
        SourceCodeDAO scdao = new SourceCodeDAO();
        scdao.addSourceCode(scbean);

        return sbean.getSolutionId();
    }

    public static SolutionBean querySubmitStatus(int solutionId) {
        SolutionDAO sdao = new SolutionDAO();
        return sdao.findSolution(solutionId);
    }

    public static void main(String[] args) {
        // public SubmitCodeForm(String uid, int pid, int cid, byte lang, String src)
        SubmitCodeForm scf = new SubmitCodeForm("hyj", 1003, 1, (byte)1, "hello.test");
        int sid = ContestFacade.submitCode(scf);
        System.out.println("sid = " + sid);
    }
}
