package cn.edu.dhu.acm.oj.judge.server;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;


public class DBService implements Runnable
{
    private final LinkedList<SolutionBean> unjudge_queue = new LinkedList();
    
    private final LinkedList<SolutionBean> judged_queue = new LinkedList();

    private final LinkedList<MessageBean> unanswered_queue = new LinkedList();

    private final LinkedList<MessageBean> answered_queue = new LinkedList();

    private SolutionDAO sol_dao = null;
    private SourceCodeDAO src_dao = null;
    private MessageDAO msg_dao = null;

    public DBService()
    {
        sol_dao = new SolutionDAO();
        src_dao = new SourceCodeDAO();
        msg_dao = new MessageDAO();
    }

    public void run()
    {
        while(true)
        {
            try
            {
                List<SolutionBean> list = sol_dao.findUnjudgedSolutionsInRange(0, 20);
                for(SolutionBean sbean : list)
                {
                    sbean.setResult(Const.QUEUE);
                    sol_dao.updateSolution(sbean);
                    SourceCodeBean scbean = src_dao.findSourceCode(sbean.getSolutionId());
                    sbean.setSourceCode(scbean);
                    synchronized(unjudge_queue)
                    {
                        unjudge_queue.add(sbean);
                    }
                }
                
                synchronized(judged_queue)
                {
                    while (!judged_queue.isEmpty()) {
                        SolutionBean sbean = judged_queue.removeFirst();
                        sol_dao.updateSolution(sbean);
                    }
                }

                List<MessageBean> msgList = msg_dao.findNewMessagesInRange(0, 20);
                for (MessageBean msg : msgList) {
                    msg.setStatus(Const.MSG_IN_QUEUE);
                    msg_dao.updateMessage(msg);
                    synchronized(unanswered_queue) {
                        unanswered_queue.add(msg);
                    }
                }

                synchronized(answered_queue) {
                    while (!answered_queue.isEmpty()) {
                        MessageBean msg = answered_queue.removeFirst();
                        msg_dao.updateMessage(msg);
                    }
                }
                
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch(Exception e)
            {
                System.err.println(e);
                e.printStackTrace();
            }
        }
    }

    public SolutionBean getUnjudgeFirst()
    {
        synchronized(unjudge_queue)
        {
            if(unjudge_queue.isEmpty())
            {
                return null;
            }
            else
            {
                return unjudge_queue.getFirst();
            }
        }
    }

    public MessageBean getUnansweredFirst()
    {
        synchronized(unanswered_queue)
        {
            if(unanswered_queue.isEmpty())
            {
                return null;
            }
            else
            {
                return unanswered_queue.getFirst();
            }
        }
    }

    public void removeUnjudgeFirst()
    {
        synchronized(unjudge_queue)
        {
            unjudge_queue.removeFirst();
        }
    }

    public void removeUnansweredFirst()
    {
        synchronized(unanswered_queue)
        {
            unanswered_queue.removeFirst();
        }
    }
    
    public void addJudged(SolutionBean b)
    {
        synchronized(judged_queue)
        {
            judged_queue.add(b);
        }
    }

    public void addAnswered(MessageBean mb)
    {
        synchronized(answered_queue)
        {
            answered_queue.add(mb);
        }
    }

}
