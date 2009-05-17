package clientGUI;

public class MyFrame extends javax.swing.JFrame {

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
}
