package cn.edu.dhu.acm.oj.logic.rank;

import java.io.*;
import cn.edu.dhu.acm.oj.persistence.beans.ContestBean;

public class RanklistClientThread extends Thread {

    private String outputDir;
    private static final long sleepTime = 5000L;
    private RanklistClient rank;
    private ContestBean contest;

    public RanklistClientThread(int cid) {
        this.rank = new RanklistClient(cid);
        File dir = new File("html");
        if ((!(dir.exists())) && (!(dir.mkdir()))) {
            System.out.println("BoardFrame:" + dir.getName() + " could not be created.");
        } else {
            this.outputDir = "html" + File.separator;
        }
    }

    private FileOutputStream createFile(String fileName) {
        FileOutputStream fout = null;
        try {
            if (outputDir != null) {
                fout = new FileOutputStream(outputDir + fileName);
            } else {
                fout = new FileOutputStream(fileName);
            }
        } catch (Exception e) {
        }
        return fout;
    }

    public static void main(String args[]) {
        RanklistClientThread rct = new RanklistClientThread(1);
        rct.start();

    }

    public void run() {
        while (true) {
            try {
                rank.updateBoardTimely(new PrintWriter(createFile("summary.html")));
                sleep(sleepTime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}