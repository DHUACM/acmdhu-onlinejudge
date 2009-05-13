package client;

import client.allpanel.*;

/**
 *
 * @author  Suncihai
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame() {
        paperpanel = new PaperPanel();
        Control.setPaperpanel(paperpanel);
        initComponents();
        Control.init(this);
        JP_Paper.add(paperpanel, java.awt.BorderLayout.CENTER);
        int i = Control.getAllcodecnt();
        String title = "Code00";
        codenum = 1;
        JTP_Code.add(title, new CodePanel(title, i, JTP_Code));
        JTP_Code.setTabComponentAt(i, new ButtonTabComponent(JTP_Code));

        Control.setPaper("a+b.xml");
        paperpanel.setPaper();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBarAll = new javax.swing.JToolBar();
        JCB_Model = new javax.swing.JComboBox();
        JB_Login = new javax.swing.JButton();
        JB_GetPaper = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        JB_New = new javax.swing.JButton();
        JB_Open = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        JPT_Work = new javax.swing.JPanel();
        JSP_PC = new javax.swing.JSplitPane();
        JP_Paper = new javax.swing.JPanel();
        JTP_Code = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        JM_Tool = new javax.swing.JMenu();
        JMI_SetEnv = new javax.swing.JMenuItem();
        JMI_SetFile = new javax.swing.JMenuItem();
        JM_Help = new javax.swing.JMenu();
        JMI_Help = new javax.swing.JMenuItem();
        JMI_About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBarAll.setFloatable(false);

        JCB_Model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trainer Model", "Net Model", "Code Model" }));
        JCB_Model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCB_ModelActionPerformed(evt);
            }
        });
        jToolBarAll.add(JCB_Model);

        JB_Login.setText("Login");
        JB_Login.setEnabled(false);
        JB_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_LoginActionPerformed(evt);
            }
        });
        jToolBarAll.add(JB_Login);

        JB_GetPaper.setText("GetPaper");
        JB_GetPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_GetPaperActionPerformed(evt);
            }
        });
        jToolBarAll.add(JB_GetPaper);

        jLabel1.setText("      ");
        jToolBarAll.add(jLabel1);

        JB_New.setText("New");
        JB_New.setFocusable(false);
        JB_New.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JB_New.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JB_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_NewActionPerformed(evt);
            }
        });
        jToolBarAll.add(JB_New);

        JB_Open.setText("Open");
        JB_Open.setFocusable(false);
        JB_Open.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JB_Open.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JB_Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OpenActionPerformed(evt);
            }
        });
        jToolBarAll.add(JB_Open);

        jLabel2.setText("      ");
        jToolBarAll.add(jLabel2);

        jButton1.setText("ViewSubmissions");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBarAll.add(jButton1);

        getContentPane().add(jToolBarAll, java.awt.BorderLayout.NORTH);

        JPT_Work.setLayout(new java.awt.BorderLayout());

        JSP_PC.setDividerLocation(382);
        JSP_PC.setDividerSize(7);
        JSP_PC.setOneTouchExpandable(true);

        JP_Paper.setLayout(new java.awt.BorderLayout());
        JSP_PC.setLeftComponent(JP_Paper);
        JSP_PC.setRightComponent(JTP_Code);

        JPT_Work.add(JSP_PC, java.awt.BorderLayout.CENTER);

        getContentPane().add(JPT_Work, java.awt.BorderLayout.CENTER);

        JM_Tool.setText("Tool");

        JMI_SetEnv.setText("SetCompilerPath");
        JMI_SetEnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMI_SetEnvActionPerformed(evt);
            }
        });
        JM_Tool.add(JMI_SetEnv);

        JMI_SetFile.setText("SetFilePath");
        JM_Tool.add(JMI_SetFile);

        jMenuBar1.add(JM_Tool);

        JM_Help.setText("Help");

        JMI_Help.setText("Help");
        JMI_Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMI_HelpActionPerformed(evt);
            }
        });
        JM_Help.add(JMI_Help);

        JMI_About.setText("About");
        JM_Help.add(JMI_About);

        jMenuBar1.add(JM_Help);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-783)/2, (screenSize.height-569)/2, 783, 569);
    }// </editor-fold>//GEN-END:initComponents

private void JMI_SetEnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMI_SetEnvActionPerformed
    javax.swing.JDialog dialog = new javax.swing.JDialog();
    EnvironmentPanel dlg = new EnvironmentPanel(dialog);
    newDialog(dialog, dlg, "SetEnvironment");
}//GEN-LAST:event_JMI_SetEnvActionPerformed

private void JB_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OpenActionPerformed
    javax.swing.JFileChooser chooser = new javax.swing.JFileChooser("./");
    if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
        try {
            String fileName = chooser.getSelectedFile().getPath();
            java.io.File file = new java.io.File(fileName);
            int fileSize = (int) file.length();
            int charReaded = 0;
            java.io.FileReader in = new java.io.FileReader(file);
            char[] data = new char[fileSize];
            while (in.ready()) {
                charReaded += in.read(data, charReaded, fileSize - charReaded);
            }
            in.close();
            String SourceCode = "";
            for (int i = 0; i < charReaded; i++) {
                SourceCode += data[i];
            }
            int i = Control.getAllcodecnt();
            String title = file.getName();
            if (title.indexOf(".") != -1) {
                title = title.substring(0, title.indexOf("."));
            }
            CodePanel cp = new CodePanel(title, i, JTP_Code);
            cp.setCode(SourceCode);
            JTP_Code.add(title, cp);
            JTP_Code.setTabComponentAt(i, new ButtonTabComponent(JTP_Code));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}//GEN-LAST:event_JB_OpenActionPerformed

private void JB_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_NewActionPerformed
    int i = Control.getAllcodecnt();
    String title = "Code";
    if (codenum < 10) {
        title += "0" + codenum;
    } else {
        title += codenum;
    }
    codenum++;
    JTP_Code.add(title, new CodePanel(title, i, JTP_Code));
    JTP_Code.setTabComponentAt(i, new ButtonTabComponent(JTP_Code));
}//GEN-LAST:event_JB_NewActionPerformed

    private void JB_GetPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_GetPaperActionPerformed
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        PaperSelectPanel psp = new PaperSelectPanel(dialog, paperpanel);
        newDialog(dialog, psp, "Paper");
    }//GEN-LAST:event_JB_GetPaperActionPerformed

    private void JCB_ModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCB_ModelActionPerformed
        String select = JCB_Model.getSelectedItem().toString();
        if (select.equals("Trainer Model")) {
            JSP_PC.setDividerLocation(0.5);
            JB_GetPaper.setEnabled(true);
            JB_Login.setEnabled(false);
            Control.setModel("Trainer");
        } else if (select.equals("Net Model")) {
            JSP_PC.setDividerLocation(0.5);
            JB_GetPaper.setEnabled(false);
            JB_Login.setEnabled(true);
            Control.setModel("Net");
        } else {
            JB_GetPaper.setEnabled(false);
            JSP_PC.setDividerLocation(0.0);
            Control.setModel("Code");
        }
    }//GEN-LAST:event_JCB_ModelActionPerformed

    private void JB_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_LoginActionPerformed
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        LoginPanel dlg = new LoginPanel(dialog);
        newDialog(dialog, dlg, "Login");
        
    }//GEN-LAST:event_JB_LoginActionPerformed

    private void JMI_HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMI_HelpActionPerformed
    }//GEN-LAST:event_JMI_HelpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Control.showSubFrame();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void newDialog(javax.swing.JDialog dialog, javax.swing.JPanel p, String title) {
        dialog.setAlwaysOnTop(true);
        dialog.setTitle(title);
        dialog.add(p);
        dialog.pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = dialog.getSize();
        dialog.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
        dialog.setVisible(true);
    }

    public void smallDialog(String str, String title, int model) {
        javax.swing.JOptionPane.showMessageDialog(this, str, title, model);
    }

    public void getAC(int x) {
        //JL_ACcnt.setText(" Accepted " + x + " problem(s).");
    }
    public javax.swing.JButton getJB_GetPaper(){
        return JB_GetPaper;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_GetPaper;
    private javax.swing.JButton JB_Login;
    private javax.swing.JButton JB_New;
    private javax.swing.JButton JB_Open;
    private javax.swing.JComboBox JCB_Model;
    private javax.swing.JMenuItem JMI_About;
    private javax.swing.JMenuItem JMI_Help;
    private javax.swing.JMenuItem JMI_SetEnv;
    private javax.swing.JMenuItem JMI_SetFile;
    private javax.swing.JMenu JM_Help;
    private javax.swing.JMenu JM_Tool;
    private javax.swing.JPanel JPT_Work;
    private javax.swing.JPanel JP_Paper;
    private javax.swing.JSplitPane JSP_PC;
    private javax.swing.JTabbedPane JTP_Code;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBarAll;
    // End of variables declaration//GEN-END:variables
    private PaperPanel paperpanel;
    private static java.util.Timer t1;
    private static java.util.Timer t2;
    private int codenum;
}
