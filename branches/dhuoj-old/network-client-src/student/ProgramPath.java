package student;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.io.*;
import com.lrf.*;

public class ProgramPath extends JDialog {

  private EnvironmentBean eb;
  private String path;

  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JLabel jLabel1 = new JLabel();
  private JTextField PathTF = new JTextField();
  private JButton BrowseBtn = new JButton();
  private JButton OkBtn = new JButton();
  private JButton CancelBtn = new JButton();


  public ProgramPath(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ProgramPath(EnvironmentBean e) {
    this(null, "", false);
    eb = e;
    PathTF.setText(eb.getSourceCodeTemp());
  }

  private void jbInit() throws Exception {

    panel1.setLayout(xYLayout1);
    this.setModal(true);
    this.setResizable(false);
    this.getContentPane().setLayout(borderLayout1);
    jLabel1.setFont(new java.awt.Font("DialogInput", 1, 15));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel1.setText("这里，修改你自己保存程序的路径");
    BrowseBtn.setText("浏览");
    BrowseBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        BrowseBtn_actionPerformed(e);
      }
    });
    OkBtn.setText("确定");
    OkBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OkBtn_actionPerformed(e);
      }
    });
    CancelBtn.setText("取消");
    CancelBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CancelBtn_actionPerformed(e);
      }
    });
    PathTF.setBackground(new Color(255, 255, 190));
    PathTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    PathTF.setEditable(false);
    panel1.add(jLabel1,           new XYConstraints(7, 18, 385, 61));
    panel1.add(OkBtn, new XYConstraints(66, 205, -1, -1));
    panel1.add(CancelBtn,  new XYConstraints(236, 205, -1, -1));
    panel1.add(PathTF, new XYConstraints(19, 119, 296, 28));
    panel1.add(BrowseBtn, new XYConstraints(327, 118, -1, -1));
    this.getContentPane().add(panel1, BorderLayout.CENTER);
  }

  void CancelBtn_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void BrowseBtn_actionPerformed(ActionEvent e) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
    if ( chooser.showOpenDialog(this) == chooser.APPROVE_OPTION ) {
      PathTF.setText(chooser.getSelectedFile().toString());
      path = PathTF.getText();
      if ( path.charAt(path.length()-1) != '\\' )
        path += '\\';
    }
  }

  void OkBtn_actionPerformed(ActionEvent e) {
    path = PathTF.getText();
    eb.setSourceCodeTemp(path);
    File fpath = new File(path);
    if ( !fpath.exists() )
      fpath.mkdirs();
    try {
      eb.toFile("./Environment.xml");
    }
    catch(IOException IOE) {
      IOE.toString();
    }
    this.dispose();
  }
}