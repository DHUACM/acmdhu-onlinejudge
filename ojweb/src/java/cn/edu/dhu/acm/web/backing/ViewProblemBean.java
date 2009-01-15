package cn.edu.dhu.acm.web.backing;

import cn.edu.dhu.acm.persistence.entity.Problem;
import cn.edu.dhu.acm.persistence.dbao.ProblemDBAO;

/**
 * Created on Jan 13, 2009
 * @author yhu
 */

public class ViewProblemBean {

    private int contestID;
    private int problemID;


    public ViewProblemBean() {
    }


    public int getContestID() {
        return contestID;
    }

    public void setContestID(int cid) {
        contestID = cid;
    }

    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(int pid) {
        problemID = pid;
    }

    public Problem getProblem() {
        ProblemDBAO dbao = new ProblemDBAO();
        return dbao.findProblem(problemID);
    }
}
