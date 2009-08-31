package cn.edu.dhu.acm.oj.judge;

/*
 * CodeInfFrame.java
 *
 * Created on 2007��5��26��, ����3:26
 */
/**
 *
 * @author  ��Ǻ�
 */
public class CodeInfFrame extends javax.swing.JFrame {

    /** Creates new form CodeInfFrame */
    public CodeInfFrame() {
        initComponents();
    }
    
    public void setInfo(String info){
        JTA_Info.setText(info);
    }

    public void setCode(String str) {
        JTA_Code.setText(str);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTA_Code = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTA_Info = new javax.swing.JTextArea();

        setTitle("Information");

        JTA_Code.setBackground(new java.awt.Color(0, 0, 0));
        JTA_Code.setColumns(20);
        JTA_Code.setFont(new java.awt.Font("宋体", 1, 13)); // NOI18N
        JTA_Code.setForeground(new java.awt.Color(255, 255, 255));
        JTA_Code.setRows(5);
        jScrollPane1.setViewportView(JTA_Code);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        JTA_Info.setColumns(20);
        JTA_Info.setRows(5);
        jScrollPane2.setViewportView(JTA_Info);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.PAGE_START);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-508)/2, (screenSize.height-640)/2, 508, 640);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTA_Code;
    private javax.swing.JTextArea JTA_Info;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}