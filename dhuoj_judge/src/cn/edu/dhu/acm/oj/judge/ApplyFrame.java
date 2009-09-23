package cn.edu.dhu.acm.oj.judge;

public class ApplyFrame extends javax.swing.JFrame {

    /** Creates new form ApplyFrame */
    public ApplyFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TF_ServerIP = new javax.swing.JTextField();
        JB_OK = new javax.swing.JButton();
        JB_Cancel = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridLayout(2, 2));

        jLabel1.setText("ServerIP:");
        getContentPane().add(jLabel1);

        TF_ServerIP.setColumns(10);
        TF_ServerIP.setText("192.168.0.101");
        TF_ServerIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_ServerIPActionPerformed(evt);
            }
        });
        getContentPane().add(TF_ServerIP);

        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });
        getContentPane().add(JB_OK);

        JB_Cancel.setText("Cancel");
        JB_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CancelActionPerformed(evt);
            }
        });
        getContentPane().add(JB_Cancel);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_JB_CancelActionPerformed

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        Control.setIP(TF_ServerIP.getText());
        Control.getMainFrame().StartApply();
        this.setVisible(false);
    }//GEN-LAST:event_JB_OKActionPerformed

    private void TF_ServerIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_ServerIPActionPerformed
        this.JB_OKActionPerformed(evt);
}//GEN-LAST:event_TF_ServerIPActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Cancel;
    private javax.swing.JButton JB_OK;
    private javax.swing.JTextField TF_ServerIP;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}