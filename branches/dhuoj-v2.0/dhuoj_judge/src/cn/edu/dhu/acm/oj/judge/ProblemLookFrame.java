package cn.edu.dhu.acm.oj.judge;

import cn.edu.dhu.acm.oj.common.paper.PaperBean;

public class ProblemLookFrame extends javax.swing.JFrame {

    /**
     * Creates new form ProblemLookFrame
     */
    public ProblemLookFrame() {
        initComponents();
        try {
            JE_Out.setContentType("text/html");
            ((javax.swing.text.html.HTMLDocument) (JE_Out.getDocument())).setBase(new java.io.File(System.getProperty("java.io.tmpdir")).toURL());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void setProblem(PaperBean p, int i) {
        try {
            JE_Out.setText(p.getProblemAt(i).transform());
            JE_Out.setCaretPosition(0);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        JE_Out = new javax.swing.JEditorPane();

        setTitle("Problem");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jScrollPane1.setViewportView(JE_Out);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-667)/2, (screenSize.height-644)/2, 667, 644);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane JE_Out;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}