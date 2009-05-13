package client.allpanel;

import client.Control;

public class PaperSelectPanel extends javax.swing.JPanel {

    /** Creates new form PaperSelectPanel */
    private javax.swing.JDialog dialog;
    private PaperPanel paperpanel;

    public PaperSelectPanel(javax.swing.JDialog d, PaperPanel p) {
        initComponents();
        dialog = d;
        paperpanel = p;
        try {
            java.io.File dir = new java.io.File("./paper");
            String[] files = dir.list();
            if (Control.getModel().equals("Net")) {
                for (int i = 0; i < files.length; i++) {
                    java.io.File file = new java.io.File(dir, files[i]);
                    if (file.getName().indexOf("a+b") == -1) {
                        JCB_Paper.addItem(file.getName());
                    }
                }
            } else {
                for (int i = 0; i < files.length; i++) {
                    java.io.File file = new java.io.File(dir, files[i]);
                    String str = file.getName();

                    if (str.indexOf("a+b") == -1 && str.indexOf("contest") == -1) {
                        JCB_Paper.addItem(file.getName());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JCB_Paper = new javax.swing.JComboBox();
        JB_Get = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        add(JCB_Paper, java.awt.BorderLayout.CENTER);

        JB_Get.setText("Get");
        JB_Get.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_GetActionPerformed(evt);
            }
        });
        add(JB_Get, java.awt.BorderLayout.EAST);

        jLabel1.setText("Now you can select the paper:  ");
        add(jLabel1, java.awt.BorderLayout.WEST);

        jPanel1.setLayout(new java.awt.BorderLayout());
        add(jPanel1, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_GetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_GetActionPerformed
        //if (JPF_Password.getText().equals("acm426")) {
            Control.setPaper(JCB_Paper.getSelectedItem().toString());
            paperpanel.setPaper();
            dialog.dispose();
        //} else {
        //    Control.getMainFrame().smallDialog("       Wrong Password!", "Error", 0);
        //}
    }//GEN-LAST:event_JB_GetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Get;
    private javax.swing.JComboBox JCB_Paper;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
