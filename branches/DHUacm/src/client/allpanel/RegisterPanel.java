/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegisterPanel.java
 *
 * Created on 2009-5-9, 22:20:37
 */
package client.allpanel;

import client.Control;

/**
 *
 * @author Administrator
 */
public class RegisterPanel extends javax.swing.JPanel {

    /** Creates new form RegisterPanel */
    private javax.swing.JDialog dialog;

    public RegisterPanel(javax.swing.JDialog d) {
        initComponents();
        dialog = d;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TF_ID = new javax.swing.JTextField();
        TF_Nick = new javax.swing.JTextField();
        JPF_Pwd = new javax.swing.JPasswordField();
        TF_Sch = new javax.swing.JTextField();
        TF_Email = new javax.swing.JTextField();
        JB_Register = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        JPF_RPwd = new javax.swing.JPasswordField();
        JB_Reset = new javax.swing.JButton();

        jLabel1.setText("Nick Name :");

        jLabel2.setText("User ID :");

        jLabel3.setText("Password :");

        jLabel5.setText("School :");

        jLabel6.setText("Email :");

        TF_Sch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_SchActionPerformed(evt);
            }
        });

        JB_Register.setText("Register");
        JB_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_RegisterActionPerformed(evt);
            }
        });

        jLabel4.setText("Repeat Password :");

        JB_Reset.setText("Reset");
        JB_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(JB_Reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_Register, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(TF_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(TF_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(TF_Sch, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(JPF_Pwd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(TF_Nick, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(JPF_RPwd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TF_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TF_Nick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JPF_Pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JPF_RPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(TF_Sch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TF_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Register)
                    .addComponent(JB_Reset)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TF_SchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_SchActionPerformed
}//GEN-LAST:event_TF_SchActionPerformed

    private void JB_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_RegisterActionPerformed
        String pwd = JPF_Pwd.getText();
        String rpwd = JPF_RPwd.getText();
        if (TF_ID.getText().equals("") || JPF_Pwd.getText().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "UserID or Password can't be empty!", "Error", 0);
        } else if (!pwd.equals(rpwd)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Password Error!\nThe Repeat Password must equal to the Password", "Error", 0);
            JPF_RPwd.setText("");
            JPF_Pwd.setText("");
        }else{
            boolean ans =Control.Register(TF_Nick.getText(), TF_ID.getText(), JPF_Pwd.getText(), TF_Sch.getText(), TF_Email.getText());
            if(ans){
                javax.swing.JOptionPane.showMessageDialog(this, Control.getMessage(), "Done", 1);
                dialog.dispose();
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, Control.getMessage(), "Error", 0);
            }
        }
    }//GEN-LAST:event_JB_RegisterActionPerformed

    private void JB_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ResetActionPerformed
        TF_Nick.setText("");
        TF_ID.setText("");
        JPF_Pwd.setText("");
        JPF_RPwd.setText("");
        TF_Sch.setText("");
        TF_Email.setText("");
    }//GEN-LAST:event_JB_ResetActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Register;
    private javax.swing.JButton JB_Reset;
    private javax.swing.JPasswordField JPF_Pwd;
    private javax.swing.JPasswordField JPF_RPwd;
    private javax.swing.JTextField TF_Email;
    private javax.swing.JTextField TF_ID;
    private javax.swing.JTextField TF_Nick;
    private javax.swing.JTextField TF_Sch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
