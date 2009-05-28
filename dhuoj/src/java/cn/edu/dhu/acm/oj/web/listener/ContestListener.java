package cn.edu.dhu.acm.oj.web.listener;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContestListener implements ServletContextListener
{
    private Timer timer = null;

    public void contextInitialized(ServletContextEvent event)
    {
        timer = new Timer(true);
        timer.schedule(new RankTask(event.getServletContext()), 0, 10000);
        event.getServletContext().log("Add update ranklist task.");
        timer.schedule(new ContestTask(event.getServletContext()), 0, 2000);
        event.getServletContext().log("Add update contest status task.");
    }

    public void contextDestroyed(ServletContextEvent event)
    {
        timer.cancel();
        event.getServletContext().log("cancel timer");
    }

}  
