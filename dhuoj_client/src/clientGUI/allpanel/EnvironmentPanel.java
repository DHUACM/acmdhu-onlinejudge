package clientGUI.allpanel;

import config.*;
import clientGUI.Control;

public class EnvironmentPanel extends javax.swing.JPanel {

    /** Creates new form EnvironmentPanel */
    public EnvironmentPanel(javax.swing.JDialog d) {
        eb = Control.getEnvBean();
        dialog = d;
        initComponents();
        try {
            OSname = System.getProperty("os.name");
            JL_OS.setText("OS: " + OSname);
            JTF_Cpp.setText(eb.getPath("Cpp"));
            JTF_C.setText(eb.getPath("C"));
            JTF_Java.setText(eb.getPath("Java"));
            JTF_Pascal.setText(eb.getPath("Pascal"));
            target = new String[numLan];
            flag = new boolean[numLan];
            for (int i = 0; i < numLan; i++) {
                target[i] = eb.getCmd(LanguageList[i]) + ".exe";
                flag[i] = false;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JB_Auto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        JL_OS = new javax.swing.JLabel();
        JTF_Cpp = new javax.swing.JTextField();
        JTF_C = new javax.swing.JTextField();
        JTF_Java = new javax.swing.JTextField();
        JTF_Pascal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        JB_OK = new javax.swing.JButton();
        JB_CppFind = new javax.swing.JButton();
        JB_CFind = new javax.swing.JButton();
        JB_JavaFind = new javax.swing.JButton();
        JB_PascalFind = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(5, 0));

        JB_Auto.setText("AutoSet");
        JB_Auto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_AutoActionPerformed(evt);
            }
        });
        jPanel1.add(JB_Auto);

        jLabel2.setText("Cpp-g++");
        jPanel1.add(jLabel2);

        jLabel3.setText("C-gcc");
        jPanel1.add(jLabel3);

        jLabel4.setText("Java-javac");
        jPanel1.add(jLabel4);

        jLabel5.setText("Pascal-fpc");
        jPanel1.add(jLabel5);

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.GridLayout(5, 0));

        JL_OS.setText("OS: Unknow");
        jPanel2.add(JL_OS);

        JTF_Cpp.setColumns(20);
        JTF_Cpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTF_CppActionPerformed(evt);
            }
        });
        jPanel2.add(JTF_Cpp);

        JTF_C.setColumns(20);
        jPanel2.add(JTF_C);

        JTF_Java.setColumns(20);
        jPanel2.add(JTF_Java);

        JTF_Pascal.setColumns(20);
        jPanel2.add(JTF_Pascal);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(5, 0));

        JB_OK.setText("Save");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });
        jPanel3.add(JB_OK);

        JB_CppFind.setText("find");
        JB_CppFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CppFindActionPerformed(evt);
            }
        });
        jPanel3.add(JB_CppFind);

        JB_CFind.setText("find");
        JB_CFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CFindActionPerformed(evt);
            }
        });
        jPanel3.add(JB_CFind);

        JB_JavaFind.setText("find");
        JB_JavaFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_JavaFindActionPerformed(evt);
            }
        });
        jPanel3.add(JB_JavaFind);

        JB_PascalFind.setText("find");
        JB_PascalFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PascalFindActionPerformed(evt);
            }
        });
        jPanel3.add(JB_PascalFind);

        add(jPanel3, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void JTF_CppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTF_CppActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_JTF_CppActionPerformed

    private void JB_JavaFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_JavaFindActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString();
            if (path.charAt(path.length() - 1) != separator) {
                path += separator;
            }
            eb.setPath("Java", path);
            JTF_Java.setText(path);
        }
}//GEN-LAST:event_JB_JavaFindActionPerformed

    private void JB_AutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_AutoActionPerformed
        if (OSname.equals("Linux")) {
            String all = "/usr/bin/";
            JTF_Cpp.setText(all);
            JTF_C.setText(all);
            JTF_Java.setText(all);
            JTF_Pascal.setText(all);
        } else {
            int times = eb.getSearchPathCount();
            for (int k = 0; k < times; k++) {
                java.io.File f = new java.io.File(eb.getSearchPath(k));
                if (!f.isFile() && !f.isDirectory()) {
                    continue;
                }
                java.io.File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        for (int j = 0; j < numLan; j++) {
                            if (flag[j]) {
                                continue;
                            }
                            if (0 == files[i].getName().compareToIgnoreCase(target[j])) {
                                String tpath = files[i].getAbsolutePath();
                                tpath = tpath.substring(0, tpath.length() - target[j].length());
                                flag[j] = true;
                                eb.setPath(LanguageList[j].toString(), tpath);
                            }
                        }
                    }
                }
            }
            JTF_Cpp.setText(eb.getPath("Cpp"));
            JTF_C.setText(eb.getPath("C"));
            JTF_Java.setText(eb.getPath("Java"));
            JTF_Pascal.setText(eb.getPath("Pascal"));
        }
        try {
            eb.toFile("./Environment.xml");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_JB_AutoActionPerformed

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        try {
            eb.setPath("Cpp", JTF_Cpp.getText());
            eb.setPath("C", JTF_C.getText());
            eb.setPath("Java", JTF_Java.getText());
            eb.setPath("Pascal", JTF_Pascal.getText());
            eb.toFile("./Environment.xml");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dialog.dispose();
        }
    }//GEN-LAST:event_JB_OKActionPerformed

    private void JB_CppFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CppFindActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString();
            if (path.charAt(path.length() - 1) != separator) {
                path += separator;
            }
            eb.setPath("Cpp", path);
            JTF_Cpp.setText(path);
        }
    }//GEN-LAST:event_JB_CppFindActionPerformed

    private void JB_CFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CFindActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString();
            if (path.charAt(path.length() - 1) != separator) {
                path += separator;
            }
            eb.setPath("C", path);
            JTF_C.setText(path);
        }
    }//GEN-LAST:event_JB_CFindActionPerformed

    private void JB_PascalFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PascalFindActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString();
            if (path.charAt(path.length() - 1) != separator) {
                path += separator;
            }
            eb.setPath("Pascal", path);
            JTF_Pascal.setText(path);
        }
    }//GEN-LAST:event_JB_PascalFindActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Auto;
    private javax.swing.JButton JB_CFind;
    private javax.swing.JButton JB_CppFind;
    private javax.swing.JButton JB_JavaFind;
    private javax.swing.JButton JB_OK;
    private javax.swing.JButton JB_PascalFind;
    private javax.swing.JLabel JL_OS;
    private javax.swing.JTextField JTF_C;
    private javax.swing.JTextField JTF_Cpp;
    private javax.swing.JTextField JTF_Java;
    private javax.swing.JTextField JTF_Pascal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
    private String OSname = "Unknow";
    private EnvironmentBean eb;
    private String target[];
    private String LanguageList[] = {"Cpp", "C", "Java", "Pascal"};
    private int numLan = LanguageList.length;
    private boolean flag[];
    private char separator = System.getProperty("file.separator").charAt(0);
    private javax.swing.JDialog dialog;
}
