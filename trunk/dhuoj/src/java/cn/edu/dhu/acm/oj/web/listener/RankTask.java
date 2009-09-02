package cn.edu.dhu.acm.oj.web.listener;

import java.net.URL;
import java.net.URLConnection;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import cn.edu.dhu.acm.oj.config.ServerConfig;
import cn.edu.dhu.acm.oj.wsclient.WSContestFacadeClient;
import cn.edu.dhu.acm.oj.buslogic.facade.contest.ContestBean;

public class RankTask extends TimerTask {

    private ServletContext context = null;
    private String rootPath = null;

    public String getRootPath(ServletContext ctx) {
        return ctx.getRealPath("/");
    }

    public RankTask(ServletContext context) {
        this.context = context;
        rootPath = getRootPath(context);
    }

    @Override
    public void run() {
        try {
            WSContestFacadeClient client = new WSContestFacadeClient(ServerConfig.PRIME_SERVER_URL);
            List<ContestBean> list = client.getContestByStatus(1);
            list.addAll(client.getContestByStatus(0));

            for (ContestBean cbean : list) {
                int cid = cbean.getContestId();
                /*
                long end = cbean.getEndTime().getTime();
                long now = java.util.Calendar.getInstance().getTimeInMillis();
                if(cid == 0 || end-now<=3600*1000)
                {
                // the board is locked in the last hour.
                continue;
                }*/
                if (cid == 0) {
                    continue;
                }

                String rank_path = "http://" + ServerConfig.PRIME_SERVER_URL + "/primeserver/rank/contest" + cid + ".html";
                int bytesum = 0, byteread = 0;
                URL url = new URL(rank_path);
                URLConnection conn = url.openConnection();
                InputStream inStream = conn.getInputStream();

                //File fout = new File(rootPath + "rank" + File.separator + "contest" + cid + ".html");
                String filename = rootPath + "rank" + File.separator + "contest" + cid + ".html";
                FileOutputStream fos = new FileOutputStream(filename);

                byte[] buffer = new byte[1024];

                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fos.write(buffer, 0, byteread);
                }
                System.out.println("sync rank path " + rank_path + " from primerserver success!");
            }
        } catch (Exception e) {
            context.log("Update ranklist exception: " + e.getMessage());
        }
    }
}
