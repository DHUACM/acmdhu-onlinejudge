package cn.edu.dhu.acm.oj.client;

import cn.edu.dhu.acm.oj.client.panel.*;
import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.client.thread.RunSubmit;
import java.util.*;
import java.io.*;

public class LoginFrame extends MyFrame {

	class Item {

		String name;
		int index;

		Item(String str, int i) {
			name = str;
			index = i;
		}

		@Override
		public String toString() {
			return name;
		}

		public int getIndex() {
			return index;
		}

		public String getName() {
			return name;
		}
	}

	/** Creates new form LoginFrame */
	public LoginFrame() {

		initComponents();
		hostServer = TF_Server.getText();
		Control.setLoginFrame(this);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Dimension dialogSize = this.getSize();
		this.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
		new MainFrame();

		setNet();

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JP_Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JB_ShowContest = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JB_Register = new javax.swing.JButton();
        JB_Help = new javax.swing.JButton();
        JP_Right = new javax.swing.JPanel();
        JF_UserID = new javax.swing.JTextField();
        JPF_Password = new javax.swing.JPasswordField();
        JCB_Contest = new javax.swing.JComboBox();
        TF_Server = new javax.swing.JTextField();
        JCB_Net = new javax.swing.JComboBox();
        JB_Login = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DHUacm");

        JP_Left.setLayout(new java.awt.GridLayout(7, 1));

        jLabel1.setText("User ID:");
        JP_Left.add(jLabel1);

        jLabel2.setText("Password:");
        JP_Left.add(jLabel2);

        JB_ShowContest.setText("ShowContest:");
        JB_ShowContest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ShowContestActionPerformed(evt);
            }
        });
        JP_Left.add(JB_ShowContest);

        jLabel5.setText("Server:");
        JP_Left.add(jLabel5);

        jLabel3.setText("Net:");
        JP_Left.add(jLabel3);

        JB_Register.setText("Register");
        JB_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_RegisterActionPerformed(evt);
            }
        });
        JP_Left.add(JB_Register);

        JB_Help.setText("Help");
        JB_Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_HelpActionPerformed(evt);
            }
        });
        JP_Left.add(JB_Help);

        getContentPane().add(JP_Left, java.awt.BorderLayout.WEST);

        JP_Right.setLayout(new java.awt.GridLayout(7, 1));

        JF_UserID.setColumns(20);
        JP_Right.add(JF_UserID);
        JP_Right.add(JPF_Password);

        JCB_Contest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCB_ContestActionPerformed(evt);
            }
        });
        JP_Right.add(JCB_Contest);

        TF_Server.setText("acm.dhu.edu.cn");
        JP_Right.add(TF_Server);
        JP_Right.add(JCB_Net);

        JB_Login.setText("Login");
        JB_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_LoginActionPerformed(evt);
            }
        });
        JP_Right.add(JB_Login);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("DHUacm_version3.0 ");
        JP_Right.add(jLabel4);

        getContentPane().add(JP_Right, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_RegisterActionPerformed
		hostServer = TF_Server.getText();
		Control.setServer(hostServer);
		javax.swing.JDialog d = new javax.swing.JDialog();
		RegisterPanel rp = new RegisterPanel(d);
		newDialog(d, rp, "Register");
    }//GEN-LAST:event_JB_RegisterActionPerformed

    private void JB_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_LoginActionPerformed
		hostServer = TF_Server.getText();
		if (hostServer.startsWith("Local")) {
			Control.setModel(hostServer);
			this.setVisible(false);
			Control.getMainFrame().setVisible(true);
			Control.CheckTmppath();
			Control.getPaperpanel().showOpenPaper();
			Control.getMainFrame().setTitle("Local Training");
		} else {
			if (!hasGetContest) {
				smallDialog("Press the ShowContest button first!", "Error", 0);
				return;
			}
			Item it = (Item) JCB_Net.getSelectedItem();
			if (it != null) {
				Control.setNetList(Alist.get(it.getIndex()));
			}
			Control.setServer(hostServer);
			Item item = (Item) JCB_Contest.getSelectedItem();
			boolean ans;
			ans = Control.login(JF_UserID.getText(), JPF_Password.getText());
			if (ans) {
				ans = Control.SetContest(item.getIndex());
				if (ans) {
					Control.setContestTitle(item.toString());
					Control.setModel(hostServer);
					setVisible(false);
					Control.getMainFrame().setVisible(true);
					Control.CheckTmppath();
					Control.getMainFrame().smallDialog(Control.getMessage(), "Done", 1);
					Control.getMainFrame().setTitle(JF_UserID.getText() + " " + item.toString());
					Control.getPaperpanel().showDownload();
					try {
						RunSubmit runSubmit = new RunSubmit(0, " AutoSubmit  A+B  Problem ");
						Thread thread = new Thread(runSubmit);
						thread.start();
					} catch (Exception e) {
					}
				}
			} else {
				smallDialog(Control.getMessage(), "Error", 0);
			}
		}
    }//GEN-LAST:event_JB_LoginActionPerformed

    private void JCB_ContestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCB_ContestActionPerformed
}//GEN-LAST:event_JCB_ContestActionPerformed

    private void JB_HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_HelpActionPerformed
		hostServer = TF_Server.getText();
		Control.setServer(hostServer);
		try {
			if (java.awt.Desktop.isDesktopSupported()) {
				java.awt.Desktop.getDesktop().browse(new java.net.URI("http://" + hostServer + "/dhuoj/help.htm"));
			} else {
				smallDialog("JRE version low!\nPlease use your browser to open:\n" + "http://" + hostServer + "/dhuoj/help.htm", "Warning", 0);
			}
		} catch (Exception e) {
			smallDialog("JRE version low!\nPlease use your browser to open:\n" + "http://" + hostServer + "/dhuoj/help.htm", "Warning", 0);
		}
    }//GEN-LAST:event_JB_HelpActionPerformed

    private void JB_ShowContestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ShowContestActionPerformed
		JCB_Contest.removeAllItems();
		Item it = (Item) JCB_Net.getSelectedItem();
		if (it != null) {
			Control.setNetList(Alist.get(it.getIndex()));
		}
		hostServer = TF_Server.getText();
		Control.setServer(hostServer);
		if (JF_UserID.getText().equals("") || JPF_Password.getText().equals("")) {
			smallDialog("Set your id or password!", "Error", 0);
			return;
		}
		Control.setUserid(JF_UserID.getText());
		Control.setUserpassword(JPF_Password.getText());

		//boolean ans = Control.login(JF_UserID.getText(), JPF_Password.getText());
		//if (ans) {
		String[] contest = Control.getContest();
		int[] status = Control.getStatuslist();
		if (contest != null) {
			for (int i = 0; i < contest.length; i++) {
				if (status[i] == Const.CONTEST_PENDING || status[i] == Const.CONTEST_RUNNING) {
					Item item = new Item(contest[i], i);
					JCB_Contest.addItem(item);
				}
			}
			hasGetContest = true;
		}
		//} else {
		//	smallDialog(Control.getMessage(), "Error", 0);
		//}

		this.pack();
    }//GEN-LAST:event_JB_ShowContestActionPerformed

	private void setNet() {
		try {
			JCB_Net.removeAllItems();
			InputStream in = getClass().getResourceAsStream(
					"/cn/edu/dhu/acm/oj/webservice/client/net.ini");
			Scanner scan = new Scanner(in);
			int cnt = 0;
			ArrayList<String> list = null;
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (!line.equals("")) {
					if (line.startsWith("#")) {
						if (list != null) {
							Alist.add(list);
							cnt++;
						}
						JCB_Net.addItem(new Item(line, cnt));
						list = new ArrayList();
						list.add(hostServer);
					} else {
						list.add(line);
					}
				}
			}
			if (list != null) {
				Alist.add(list);
				cnt++;
			}
			if (Alist.size() == 0) {
				list = new ArrayList();
				list.add(hostServer);
				Alist.add(list);
			}
			Control.setNetList(Alist.get(0));
		} catch (Exception ex) {
			ex.printStackTrace();
			ArrayList<String> list = new ArrayList();
			list.add(hostServer);
			Control.setNetList(list);
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new LoginFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Help;
    private javax.swing.JButton JB_Login;
    private javax.swing.JButton JB_Register;
    private javax.swing.JButton JB_ShowContest;
    private javax.swing.JComboBox JCB_Contest;
    private javax.swing.JComboBox JCB_Net;
    private javax.swing.JTextField JF_UserID;
    private javax.swing.JPasswordField JPF_Password;
    private javax.swing.JPanel JP_Left;
    private javax.swing.JPanel JP_Right;
    private javax.swing.JTextField TF_Server;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
	private String hostServer;
	private boolean hasGetContest = false;
	ArrayList<ArrayList<String>> Alist = new ArrayList();
}
