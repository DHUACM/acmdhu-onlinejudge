package cn.edu.dhu.acm.web.backing;

/**
 * Created on Jan 11, 2009
 * @author yhu
 */

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import cn.edu.dhu.acm.persistence.entity.Problem;
import cn.edu.dhu.acm.persistence.dbao.ProblemDBAO;
import cn.edu.dhu.acm.web.util.Util;

public class ProblemListBean implements java.io.Serializable {

    // problem entity data base access object.
    private ProblemDBAO dbao;

    // data model for problems.
    private DataModel problems;

    public ProblemListBean() {
        dbao = new ProblemDBAO();
        problems = new ListDataModel();

        // dump problems data from db.
        problems.setWrappedData(dbao.findProblemEntities());
    }

    /**
     * List of Problems in the system.
     *
     * @return Returns the problemss.
     */
    public DataModel getProblems() {
        return problems;
    }


    /**
     * View the specified problem detail.
     *
     * @return outcome
     */
    public String viewProblem() {
        Problem problem = (Problem) problems.getRowData();
        int cid = 0;
        int pid = problem.getProblemId();

        ViewProblemBean vpb = (ViewProblemBean) Util.getBean("ViewProblemBean");
        vpb.setContestID(cid);
        vpb.setProblemID(pid);
        
        return "success";
    }
}
