package cn.edu.dhu.acm.oj.web.listener;

import cn.edu.dhu.acm.oj.logic.rank.*;
import java.io.PrintWriter;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;
import cn.edu.dhu.acm.oj.common.config.Const;

public class RankTask extends TimerTask
{
    private ServletContext context = null;
    private String rootPath = null;

    public String getRootPath(ServletContext ctx)
    {
        return ctx.getRealPath("/");
    }

    public RankTask(ServletContext context)
    {
        this.context = context;
        rootPath = getRootPath(context);
    }

    @Override
    public void run()
    {
        try
        {
            ContestDAO cdao = new ContestDAO();
            List<ContestBean> list = cdao.findContestByStatus(Const.CONTEST_RUNNING);
            for(ContestBean cbean:list)
            {
                int cid = cbean.getContestId();
                long end = cbean.getEndTime().getTime();
                long now = java.util.Calendar.getInstance().getTimeInMillis();
                if(cid == 0 || end-now<=3600*1000)
                {
                    // the board is locked in the last hour.
                    continue;
                }

                File fout = new File(rootPath+"rank" + File.separator + "contest"+cid+".html");
                System.out.println("rank path = " + fout.toString());
                RanklistClient rank = new RanklistClient(cid);
                rank.updateBoardTimely(new PrintWriter(fout));
            }
        }
        catch(Exception e)
        {
            context.log("Update ranklist exception: "+e.getMessage());
        }
    }

}
