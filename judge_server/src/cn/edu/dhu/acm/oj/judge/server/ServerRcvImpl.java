package cn.edu.dhu.acm.oj.judge.server;

import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.common.config.Const;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRcvImpl implements Runnable {

    private DBService dbs = null;
    private ServerSocket server = null;

    public ServerRcvImpl(DBService d) {
        try {
            server = new ServerSocket(Const.SERVER_RCV_SOCKET);
            dbs = d;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        service();
    }

    private void service() {
        ObjectInputStream ois = null;
        while (true) {
            try {
                Socket socket = server.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                Object obj = ois.readObject();
                if (obj instanceof SolutionBean) {
                    SolutionBean sbean = (SolutionBean) obj;
                    dbs.addJudged(sbean);
                    System.out.println("Server receive RunBean " + sbean.getSolutionId() + ", judged result: " + sbean.getResult());
                    socket.getOutputStream().write("OK\r\n".getBytes());
                } else if (obj instanceof MessageBean) {
                    MessageBean mbean = (MessageBean) obj;
                    dbs.addAnswered(mbean);
                    System.out.println("Server receive MessageBean " + mbean.getMessageId()  + ".");
                    socket.getOutputStream().write("OK\r\n".getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
