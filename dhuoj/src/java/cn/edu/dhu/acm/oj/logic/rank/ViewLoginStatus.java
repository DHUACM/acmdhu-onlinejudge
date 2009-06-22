package cn.edu.dhu.acm.oj.logic.rank;

import java.io.PrintWriter;
import java.util.*;
import java.lang.StringBuffer;

import cn.edu.dhu.acm.oj.persistence.beans.ContestBean;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.persistence.beans.UserBean;
import cn.edu.dhu.acm.oj.persistence.beans.ContestProblemBean;
import cn.edu.dhu.acm.oj.logic.facade.ContestFacade;
import cn.edu.dhu.acm.oj.common.util.Util;

public class ViewLoginStatus {

    private Object printingLock;
//    private int numProblems;
//    private TreeMap<Integer, ContestProblemBean> problem_map;
    private TreeMap<String, UserBean> user_map;
    private ArrayList<SolutionBean> allRuns;
    private int cntTotalUser, cntLoginUser;
//    private long[] psdAccepted;
//    private long[] psdAttempts;
    
    private ContestBean contest;

    public ViewLoginStatus(int cid) {
        this.contest = ContestFacade.getContest(cid);
        // get contest problems
//        problem_map = ContestFacade.getProblemsByContest(cid);
        // get all runs
        this.allRuns = (ArrayList)ContestFacade.queryContestLoginStatus(cid);
        // private contest get users by reservation.
        if (contest.getPrivate_() != 0) {
            this.user_map = ContestFacade.getContestReservation(cid);
        } else {
            // get users who submit codes in this contest.
            this.user_map = ContestFacade.getUsersByRank(allRuns);
        }
//        this.numProblems = problem_map.size();
//        this.psdAccepted = new long[numProblems+1];
//        this.psdAttempts = new long[numProblems+1];
        this.printingLock = new Object();
        this.cntLoginUser = 0;
        this.cntTotalUser = user_map.size();
    }

    private void printHeader(PrintWriter os) {
        if (os == null) {
            os = new PrintWriter(System.out, true);
        }
        os.println("<html>");
        os.println("<head><title>" + contest.getTitle() + " Login Status</title></head>");
        os.println("<body>");
        os.print("<div align='center'>");
        os.println("<h1>" + contest.getTitle() + " Login Status</h1>");
        os.println("<h4>contest time: " + contest.getStartTime() + " ----- " + contest.getEndTime() + "</h4>");
        os.println("<h4>last update: " + Util.getTimeString() + "</h4>");

        int border = 1;
        os.println("<table border=\"" + border + "\">");
        os.print("<tr>");
        os.print("<th><strong><u>UserID</u></strong></th>");
        os.print("<th><strong><u>UserName</u></strong></th>");
        os.print("<th><strong><u>Login</u></strong></th>");
//        os.print("<th><strong><u>Time</u></strong></th>");
//        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        for (int j = 0; j < this.numProblems; ++j) {
//            os.print("<th>&#160;&#160;&#160;&#160;<strong><u>" + alphabet.charAt(j % 26) + "</u></strong>&#160;&#160;&#160;&#160;</th>");
//        }
//        os.print("<th><strong><u>Total att/solv</strong></th>");
        os.print("</tr>");
        os.println();
    }

    public void printHTML(PrintWriter out) {
        boolean aborted = true;
        try {
            synchronized (printingLock) {
                Ranklist rank = new Ranklist(contest);
//                TreeMap tm = (TreeMap)user_map.clone();
//                ClientScoreData sd[] = rank.getStandings(allRuns, tm.keySet());
                TreeMap<String, Boolean> login = new TreeMap();

                printHeader(out);

                for (SolutionBean sbean : allRuns) {
                    printRow(out, user_map.get(sbean.getUserId()), true);
                    login.put(sbean.getUserId(), true);
                    cntLoginUser++;
                }
                Iterator iter = user_map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry)iter.next();
                    String uid = (String)entry.getKey();
                    Boolean status = login.get(uid);
                    if (status == null || status.booleanValue() == false)
                        printRow(out, (UserBean)entry.getValue(), false);
                }
//                if (sd == null) {
//                    System.out.println("no standings ?!?");
//                } else if (sd.length == 0) {
//                    System.out.println("no standings");
//                } else {
//                    int totalSolved = 0;
//                    for (int i = 0; i < sd.length; i++) {
//                        totalSolved = (int) ((long) totalSolved + sd[i].getNumberOfSolvedProblems());
//                        printRow(out, sd[i]);
//                    }
//                }

                summarizePSD(out);
                printTrailer(out);

                out.close();
            }
            aborted = false;
        } catch (Exception e) {
            System.out.println("printHTML()" + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("printHTML() end (aborted=" + aborted + ")");
            String date = (new Date()).toString();
            System.out.println(date + " html generated");
        }
    }

    private void printRow(PrintWriter os, UserBean u, boolean login) {
        if (os == null) {
            os = new PrintWriter(System.out, true);
        } else {
            os.print("<tr>");
            os.print("<td  align='center'>" + u.getUserId() + "</td>");
            os.print("<td  align='center'>" + u.getNick() + "</td>");
            os.print("<td  align='center'>" + login + "</td>");
            os.print("</tr>");
            os.println();
        }
    }

    private void printTrailer(PrintWriter os) {
        if (os == null) {
            os = new PrintWriter(System.out, true);
        }

        os.println("</table>");
        os.println("</div>");
        os.println("</body>");
        os.println("</html>");
    }

    private void summarizePSD(PrintWriter os) {
        StringBuffer line = new StringBuffer();
        line.append("<tr>");
        line.append("<td>Logined Users/Total Users</td>");
        line.append("<td align='center'>" + cntLoginUser + "/" + cntTotalUser + "</td>");
        line.append("</tr>");
        os.println(line);
    }
}