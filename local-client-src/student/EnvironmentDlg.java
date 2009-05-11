package student;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import com.lrf.*;

/**
 * <p>Title: Student</p>
 * <p>Description: 1.0</p>
 * <p>Copyright: RabbitHunter Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class EnvironmentDlg extends JDialog {

  private boolean packFrame = false;

  private Object LanguageList[] = {"Pascal","C","Cpp","Java"};
  private String language;
  private EnvironmentBean eb;
  private String target;
  private String tpath;
  private boolean isconfigure;

  private JPanel panel1 = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder1;
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JComboBox LanguageCB = new JComboBox();
  private JTextField PathTF = new JTextField();
  private JButton AutoBtn = new JButton();
  private JButton BrowseBtn = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JCheckBox ConfirmCB = new JCheckBox();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JTextField StatusTF = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JButton FinishBtn = new JButton();

  public EnvironmentDlg(Frame frame, String title, boolean modal, boolean flag) {
    super(frame, title, modal);
    try {
      isconfigure = flag;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public EnvironmentDlg(boolean flag, EnvironmentBean e) {
    this(null, "", false,flag);
    eb = e;

  }
  private void jbInit() throws Exception {

    for ( int i = 0 ; i < LanguageList.length ; i++ )
      LanguageCB.addItem(LanguageList[i]);
    //PathTF.setText(eb.getPath(LanguageCB.getSelectedItem().toString()));
    language = LanguageList[0].toString();

    titledBorder1 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout1);
    jLabel1.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel1.setText("你也可以在下面手动设置编译器路径");
    AutoBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    AutoBtn.setText("自动");
    AutoBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        AutoBtn_actionPerformed(e);
      }
    });
    BrowseBtn.setEnabled(false);
    BrowseBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    BrowseBtn.setText("浏览");
    BrowseBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        BrowseBtn_actionPerformed(e);
      }
    });
    jLabel2.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel2.setText("路径 ： ");
    ConfirmCB.setEnabled(false);
    ConfirmCB.setFont(new java.awt.Font("DialogInput", 1, 14));
    ConfirmCB.setText("以后不改变了");
    ConfirmCB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ConfirmCB_actionPerformed(e);
      }
    });
    PathTF.setBackground(new Color(255, 255, 190));
    PathTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    PathTF.setEditable(false);
    LanguageCB.setBackground(new Color(255, 255, 190));
    LanguageCB.setEnabled(false);
    LanguageCB.setFont(new java.awt.Font("DialogInput", 1, 13));
    LanguageCB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LanguageCB_actionPerformed(e);
      }
    });
    this.getContentPane().setLayout(gridBagLayout1);
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.setFont(new java.awt.Font("DialogInput", 1, 13));
    this.setModal(true);
    this.setResizable(false);
    StatusTF.setBackground(new Color(255, 255, 190));
    StatusTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    StatusTF.setEditable(false);
    jLabel3.setText("状态 ： ");
    jLabel3.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel4.setText("请设置编译器的路径");
    jLabel4.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel5.setText("选择语言 ： ");
    jLabel5.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel5.setToolTipText("");
    FinishBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FinishBtn_actionPerformed(e);
      }
    });
    FinishBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    FinishBtn.setText("确定");
    getContentPane().add(panel1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 6, 20));
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jLabel2,       new XYConstraints(4, 192, 62, 27));
    jPanel1.add(PathTF,      new XYConstraints(72, 192, 277, 26));
    jPanel1.add(ConfirmCB,    new XYConstraints(28, 250, 273, -1));
    jPanel1.add(StatusTF, new XYConstraints(119, 59, 277, 26));
    jPanel1.add(jLabel3, new XYConstraints(36, 59, -1, 27));
    jPanel1.add(jLabel1,     new XYConstraints(13, 105, 359, 30));
    jPanel1.add(jLabel4,   new XYConstraints(13, 15, 331, 30));
    jPanel1.add(LanguageCB, new XYConstraints(222, 149, -1, -1));
    jPanel1.add(BrowseBtn,  new XYConstraints(364, 191, 91, 27));
    jPanel1.add(FinishBtn,      new XYConstraints(326, 252, 94, 25));
    jPanel1.add(jLabel5,  new XYConstraints(80, 148, 103, 30));
    jPanel1.add(AutoBtn, new XYConstraints(334, 15, 94, 25));
  }

  public void setConfigure(boolean flag) {
    isconfigure = flag;
  }

  private boolean getFilePaths(File f) {
    if ( !f.isFile() && !f.isDirectory() )
      return false;
    File[] files = f.listFiles();
    for( int i = 0 ; i < files.length ; i++ ) {
      if ( files[i].isFile() ) {
        if ( 0 == files[i].getName().compareToIgnoreCase(target) ) {
          tpath = files[i].getAbsolutePath();
          tpath = tpath.substring(0,tpath.length()-target.length());
          return true;
        }
      }
      else {
        getFilePaths(files[i]);
      }
    }
    return false;
  }

  void AutoBtn_actionPerformed(ActionEvent e) {

    boolean flag;
    String temp = "";

    for ( int i = 0 ; i < LanguageList.length ; i++ ) {
      flag = false;
      int times = eb.getSearchPathCount();
      target = eb.getCmd(LanguageList[i].toString())+".exe";
      for ( int j = 0 ; j < times ; j++ ) {
        if ( getFilePaths(new File(eb.getSearchPath(j))) ) {
          flag = true;
          eb.setPath(LanguageList[i].toString(), tpath);
          try {
            eb.toFile("./Environment.xml");
          }
          catch(IOException IOE) {
            System.out.println(IOE.toString());
          }
          break;
        }
      }
      if ( !flag ) {
         temp += LanguageList[i].toString()+ " ";
      }
    }

    if ( temp.length() > 0 ) {
      StatusTF.setText("下列语言的编译器没有找到 "+temp);
    }
    else {
      StatusTF.setText("所有的编译器都设置好了");
    }

    LanguageCB.setEnabled(true);
    BrowseBtn.setEnabled(true);
    ConfirmCB.setEnabled(true);
    PathTF.setText(eb.getPath(LanguageList[0].toString()));
  }

  void BrowseBtn_actionPerformed(ActionEvent e) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
    if ( chooser.showOpenDialog(this) == chooser.APPROVE_OPTION ) {
      PathTF.setText(chooser.getSelectedFile().toString());
      String path = PathTF.getText();
      if ( path.charAt(path.length()-1) != '\\' )
        path += '\\';
      eb.setPath(language, path);
      PathTF.setText(path);
      try {
        eb.toFile("./Environment.xml");
      }
      catch(IOException IOE) {
        IOE.toString();
      }
    }

  }

  void LanguageCB_actionPerformed(ActionEvent e) {
    language = LanguageCB.getSelectedItem().toString();
    PathTF.setText(eb.getPath(language));
  }

  void FinishBtn_actionPerformed(ActionEvent e) {
    try {
        eb.toFile("./Environment.xml");
    }
    catch(IOException IOE) {
      System.out.println(IOE.toString());
    }
    this.dispose();
    if ( !isconfigure ) {
      Student frame = new Student(eb);
      //Validate frames that have preset sizes
      //Pack frames that have useful preferred size info, e.g. from their layout
      if (packFrame) {
        frame.pack();
      }
      else {
        frame.validate();
      }
      //Center the window
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = frame.getSize();
      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
      frame.setVisible(true);
      frame.setTitle("Student -- Not Login ");
    }

    eb.setIsChecked(Boolean.toString(ConfirmCB.isSelected()));
    try {
      eb.toFile("./Environment.xml");
    }
    catch(IOException IOE) {
      System.out.println(IOE.toString());
    }

  }

  void ConfirmCB_actionPerformed(ActionEvent e) {

  }

}