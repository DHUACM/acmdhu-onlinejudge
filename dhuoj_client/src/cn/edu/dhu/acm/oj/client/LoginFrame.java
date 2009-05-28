package cn.edu.dhu.acm.oj.client;

import cn.edu.dhu.acm.oj.client.panel.*;

public class LoginFrame extends MyFrame {

    /** Creates new form LoginFrame */
    public LoginFrame() {
        initComponents();
        model = JCB_Model.getSelectedItem().toString();
        Control.setLoginFrame(this);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = this.getSize();
        this.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
        new MainFrame();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JB_Register = new javax.swing.JButton();
        JB_Help = new javax.swing.JButton();
        JP_Right = new javax.swing.JPanel();
        JF_UserID = new javax.swing.JTextField();
        JPF_Password = new javax.swing.JPasswordField();
        JCB_Model = new javax.swing.JComboBox();
        TF_Server = new javax.swing.JTextField();
        JB_Login = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DHUacm");

        JP_Left.setLayout(new java.awt.GridLayout(6, 1));

        jLabel1.setText("User ID:");
        JP_Left.add(jLabel1);

        jLabel2.setText("Password:");
        JP_Left.add(jLabel2);

        jLabel3.setText("Model:");
        JP_Left.add(jLabel3);

        jLabel5.setText("Server:");
        JP_Left.add(jLabel5);

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

        JP_Right.setLayout(new java.awt.GridLayout(6, 1));

        JF_UserID.setColumns(20);
        JP_Right.add(JF_UserID);
        JP_Right.add(JPF_Password);

        JCB_Model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contest", "Examination", "Trainer-Net", "Trainer-Local" }));
        JCB_Model.setEnabled(false);
        JCB_Model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCB_ModelActionPerformed(evt);
            }
        });
        JP_Right.add(JCB_Model);

        TF_Server.setText("acm.dhu.edu.cn");
        JP_Right.add(TF_Server);

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
        javax.swing.JDialog d = new javax.swing.JDialog();
        RegisterPanel rp = new RegisterPanel(d);
        newDialog(d, rp, "Register");
    }//GEN-LAST:event_JB_RegisterActionPerformed

    private void JB_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_LoginActionPerformed
        model = TF_Server.getText();
        if (model.indexOf("Local") != -1) {
            Control.setModel(model);
            this.setVisible(false);
            Control.getMainFrame().setVisible(true);
            Control.CheckTmppath();
        } else {
            String ser = TF_Server.getText();
            Control.setServer(ser);
            boolean ans = Control.login(JF_UserID.getText(), JPF_Password.getText());
            if (ans) {
                Control.setModel(model);
                setVisible(false);
                Control.getMainFrame().setVisible(true);
                Control.CheckTmppath();
                Control.getMainFrame().smallDialog(Control.getMessage(), "Done", 1);
            } else {
                smallDialog(Control.getMessage(), "Error", 0);
            }
        }
    }//GEN-LAST:event_JB_LoginActionPerformed

    private void JCB_ModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCB_ModelActionPerformed
        model = JCB_Model.getSelectedItem().toString();
}//GEN-LAST:event_JCB_ModelActionPerformed

    private void JB_HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_HelpActionPerformed
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("http://acm.dhu.edu.cn/dhuoj/help.htm"));
            } else {
                smallDialog("JRE version low!\nPlease use your browser to open:\n" + "http://acm.dhu.edu.cn/dhuoj/help.htm", "Warning", 0);
            }
        } catch (Exception e) {
            smallDialog("JRE version low!\nPlease use your browser to open:\n" + "http://acm.dhu.edu.cn/dhuoj/help.htm", "Warning", 0);
        }
    }//GEN-LAST:event_JB_HelpActionPerformed

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
    private javax.swing.JComboBox JCB_Model;
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
    private String model;
}
