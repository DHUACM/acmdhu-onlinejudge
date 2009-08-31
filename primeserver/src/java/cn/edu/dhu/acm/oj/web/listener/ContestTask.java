package cn.edu.dhu.acm.oj.web.listener;

import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;
import cn.edu.dhu.acm.oj.common.util.Util;
import cn.edu.dhu.acm.oj.common.config.Const;

public class ContestTask extends TimerTask
{
    private int counter;
    private ContestDAO cdao = null;
    private ServletContext context = null;

    public ContestTask(ServletContext context)
    {
        this.counter = 0;
        this.context = context;
        this.cdao = new ContestDAO();
    }


    @Override
    public void run()
    {
        try
        {
            this.updateContestStatus(Const.CONTEST_RUNNING);
            this.updateContestStatus(Const.CONTEST_PENDING);
            if (counter >= 50) {
                counter = 0;
                this.updateContestStatus(Const.CONTEST_ENDED);
            }
            counter++;
        }
        catch(Exception e)
        {
            context.log("Error occurs when update contest: "+e.getMessage());
        }
    }

    private void updateContestStatus(int status) throws Exception {
        try {
            List<ContestBean> contests = cdao.findContestByStatus(status);
            for (ContestBean contest : contests) {
                long start = contest.getStartTime().getTime();
                long end = contest.getEndTime().getTime();
                if (start > end) {
                    long tmp = start; start = end; end = tmp;
                }
                long now = Util.getTimeLong();
                if (now < start) contest.setStatus(Const.CONTEST_PENDING);
                else if (now > end) contest.setStatus(Const.CONTEST_ENDED);
                else contest.setStatus(Const.CONTEST_RUNNING);
            }
        } catch(Exception e) {
            throw e;
        }
    }
    
}
