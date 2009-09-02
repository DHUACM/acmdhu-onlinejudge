package cn.edu.dhu.acm.oj.web.listener;

import java.util.Timer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import cn.edu.dhu.acm.oj.config.ServerConfig;

public class ContestListener implements ServletContextListener
{
    private Timer timer = null;

    public void contextInitialized(ServletContextEvent event)
    {
        timer = new Timer(true);
        loadServerConfig(event.getServletContext());
        timer.schedule(new RankTask(event.getServletContext()), 0, 2000);
        event.getServletContext().log("Add update ranklist task.");
    }

    public void contextDestroyed(ServletContextEvent event)
    {
        timer.cancel();
        event.getServletContext().log("cancel timer");
    }

    private void loadServerConfig(ServletContext ctx) {
        String url = ctx.getInitParameter("dhuoj/PrimeServerURL");
        ServerConfig.PRIME_SERVER_URL = url;
        System.out.println("primer server url = " + url);
    }
}  
