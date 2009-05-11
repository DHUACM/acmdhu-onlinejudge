package student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;
import com.sjn.*;
import com.dyf.*;
import com.lrf.*;
import com.zxp.*;
import javax.swing.border.*;

public class Student extends JFrame {

  private String language;
  private MyEditorKit kit;
  private EnvironmentBean eb;
  private SolutionBean[] stdsb;
  private PaperBean pb;
  private JudgeBean jb;
  private int Nowpapernum;
  private int checktimes;
  private int[] accepted;
  private int allAC;
  private ImageIcon imageicon;
  private JTextArea LnViewTA = new JTextArea("1");
  private JTextArea CodeCommentTA = new JTextArea("请在这里写程序");
  private JTextArea MessageCommentTA = new JTextArea("这里将会显示你的程序的编译信息");
  private JTextArea TestInputCommentTA = new JTextArea("请在下面的文本框中填入你自己的测试数据");
  private JTextArea TestOutputCommentTA = new JTextArea("下面文本框中的内容是你程序的输出");
  private Insets ins = new Insets(0, 5, 0, 5);
  private MyDocument doc;
  private String[] SubTblColumnNames = {"Index", "ProblemIndex", "Language", "Status"};
  private DefaultTableModel subdataModel;
  private Vector LanguageList = new Vector();
  private DefaultComboBoxModel languagecbm;
  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JToolBar jToolBar = new JToolBar();
  private JButton OpenBtn = new JButton();
  private JButton SaveAsBtn = new JButton();
  private JButton AboutBtn = new JButton();
  private ImageIcon image1;
  private ImageIcon image2;
  private ImageIcon image3;
  private ImageIcon imageSaveAs;
  private ImageIcon imageExit;
  private ImageIcon imageCCP;
  private ImageIcon imageCFP;
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JTabbedPane jTP = new JTabbedPane();
  private JPanel CodePn = new JPanel();
  private JToolBar jToolBar2 = new JToolBar();
  private JButton SaveBtn = new JButton();
  private JComboBox LanguageCB = new JComboBox();
  private JButton CompileBtn = new JButton();
  private JButton ToCheckBtn = new JButton();
  private JPanel CheckPn = new JPanel();
  private JPanel GradePn = new JPanel();
  private BorderLayout borderLayout5 = new BorderLayout();
  private JLabel GradeLabel = new JLabel();
  private BorderLayout borderLayout4 = new BorderLayout();
  private JMenuItem jMenuFileOpen ;
  private JMenuItem jMenuFileSave ;
  private JMenuItem jMenuFileSaveAs;
  private JMenuItem jMenuFileExit;
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JSplitPane jSplitPane3 = new JSplitPane();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JScrollPane jScrollPane6 = new JScrollPane();
  private JEditorPane ProblemEP = new JEditorPane();
  private JEditorPane CodeEP = new JEditorPane();
  private JEditorPane MessageEP = new JEditorPane();
  private JButton LoginBtn = new JButton();
  private JComboBox ProblemCB = new JComboBox();
  private JPanel TestPn = new JPanel();
  private JToolBar jToolBar1 = new JToolBar();
  private JButton RunBtn = new JButton();
  private BorderLayout borderLayout3 = new BorderLayout();
  private JButton ClearBtn = new JButton();
  private JToolBar jToolBar3 = new JToolBar();
  private BorderLayout borderLayout8 = new BorderLayout();
  private JButton CheckBtn = new JButton();
  private JSplitPane jSplitPane4 = new JSplitPane();
  private JSplitPane jSplitPane5 = new JSplitPane();
  private JScrollPane jScrollPane8 = new JScrollPane();
  private JScrollPane jScrollPane9 = new JScrollPane();
  private JScrollPane jScrollPane10 = new JScrollPane();
  private JMenu jMenuTools = new JMenu();
  private JMenuItem jMenuToolsCompilerpath ;
  private JMenuItem jMenuToolsProgrampath ;
  private JMenuItem jMenuHelpAbout ;
  private JMenu jMenuHelp = new JMenu();
  private JSplitPane jSplitPane6 = new JSplitPane();
  private JScrollPane jScrollPane5 = new JScrollPane();
  private JScrollPane jScrollPane7 = new JScrollPane();
  private JTextArea InputTA = new JTextArea();
  private JTextArea OutputTA = new JTextArea();
  private JButton ToTestBtn = new JButton();
  private JEditorPane judgeOutput = new JEditorPane();
  private JTextArea CheckMsg = new JTextArea();
  private TitledBorder titledBorder1;
  private JEditorPane stdOutput = new JEditorPane();
  private JLabel PaintLb = new JLabel();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JTable SubmitRecordTbl = new JTable();
  private JLabel jLabel1 = new JLabel();

  //Construct the frame
  public Student(EnvironmentBean ebb) {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      eb = ebb;
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {

    checktimes = 0;
    allAC = 0;
    jSplitPane6.setOneTouchExpandable(true);
    jSplitPane1.setOneTouchExpandable(true);
    jSplitPane3.setOneTouchExpandable(true);
    stdOutput.setEnabled(false);
    stdOutput.setDisabledTextColor(Color.white);
    stdOutput.setEditable(false);
    InputTA.setFont(new java.awt.Font("DialogInput", 0, 13));
    OutputTA.setFont(new java.awt.Font("DialogInput", 0, 13));
    subdataModel = new DefaultTableModel(SubTblColumnNames, 0);
    SubmitRecordTbl.setModel(subdataModel);
    jLabel1.setText("                                 ");
    judgeOutput.setEnabled(false);
    judgeOutput.setDisabledTextColor(Color.white);
    LanguageList.add(new Object[] {new ImageIcon(student.Student.class.getResource("P.gif")), "Pascal"});
    LanguageList.add(new Object[] {new ImageIcon(student.Student.class.getResource("C.gif")), "C"});
    LanguageList.add(new Object[] {new ImageIcon(student.Student.class.getResource("Cpp.gif")), "Cpp"});
    LanguageList.add(new Object[] {new ImageIcon(student.Student.class.getResource("J.gif")), "Java"});
    languagecbm = new DefaultComboBoxModel(LanguageList);
    LanguageCB.setModel(languagecbm);
    LanguageCB.setRenderer(new Render());
    language = (String)((Object[])LanguageCB.getSelectedItem())[1];
    imageicon = new ImageIcon(student.Student.class.getResource("middle.gif"));
    titledBorder1 = new TitledBorder("");
    jb = new JudgeBean();
    jb.setEnvironmentBean(eb);
    kit = new MyEditorKit();
    kit.setLanguage("Pascal");
    CodeEP.setEditorKitForContentType("",kit);
    CodeEP.setFont(new Font("DialogInput", 0, 13));
    CodeEP.setContentType("");
    doc = new MyDocument();
    CodeEP.setDocument(doc);
    CodeEP.setMargin(ins);
    LnViewTA.setBackground(new Color(255, 255, 190));
    LnViewTA.setEditable(false);
    LnViewTA.setFont(new Font("DialogInput", 1, 13));
    CodeCommentTA.setBackground(new Color(255, 255, 190));
    CodeCommentTA.setEditable(false);
    CodeCommentTA.setFont(new Font("DialogInput", 1, 15));
    CodeCommentTA.setForeground(Color.red);
    MessageCommentTA.setBackground(new Color(255, 255, 190));
    MessageCommentTA.setEditable(false);
    MessageCommentTA.setFont(new Font("DialogInput", 1, 15));
    TestInputCommentTA.setBackground(new Color(255, 255, 190));
    TestInputCommentTA.setEditable(false);
    TestInputCommentTA.setFont(new Font("DialogInput", 1, 15));
    TestOutputCommentTA.setBackground(new Color(255, 255, 190));
    TestOutputCommentTA.setEditable(false);
    TestOutputCommentTA.setFont(new Font("DialogInput", 1, 15));
    jScrollPane2.setRowHeaderView(LnViewTA);
    jScrollPane2.setColumnHeaderView(CodeCommentTA);
    jScrollPane5.setColumnHeaderView(TestInputCommentTA);
    jScrollPane7.setColumnHeaderView(TestOutputCommentTA);
    jScrollPane6.setColumnHeaderView(MessageCommentTA);
    doc.setJTextArea(LnViewTA);
    doc.setJEditorPane(CodeEP);
    doc.setLanguage(language);
    CodeEP.getDocument().addDocumentListener(new DocumentListener() {
      public void insertUpdate(DocumentEvent e){
      }
      public void removeUpdate(DocumentEvent e){
      }
      public void changedUpdate(DocumentEvent e){
      }
    });
    image1 = new ImageIcon(student.Student.class.getResource("openFile.gif"));
    image2 = new ImageIcon(student.Student.class.getResource("closeFile.gif"));
    image3 = new ImageIcon(student.Student.class.getResource("help.gif"));
    imageSaveAs = new ImageIcon(student.Student.class.getResource("SaveAs.gif"));
    imageExit = new ImageIcon(student.Student.class.getResource("exit.gif"));
    imageCCP = new ImageIcon(student.Student.class.getResource("c1.gif"));
    imageCFP = new ImageIcon(student.Student.class.getResource("c2.gif"));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(750, 550));
    jMenuFile.setBackground(new Color(210, 203, 255));
    jMenuFile.setText("文件");
    OpenBtn.setIcon(image1);
    OpenBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OpenBtn_actionPerformed(e);
      }
    });
    OpenBtn.setBackground(new Color(210, 203, 255));
    OpenBtn.setToolTipText("打开源程序");
    SaveAsBtn.setIcon(image2);
    SaveAsBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuFileSaveAs_actionPerformed(e);
      }
    });
    SaveAsBtn.setBackground(new Color(210, 203, 255));
    SaveAsBtn.setToolTipText("源程序另存");
    AboutBtn.setIcon(image3);
    AboutBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    AboutBtn.setBackground(new Color(210, 203, 255));
    AboutBtn.setToolTipText("关于");
    jPanel1.setLayout(borderLayout2);
    CodePn.setLayout(borderLayout4);
    SaveBtn.setBackground(new Color(210, 203, 255));
    SaveBtn.setText("保存");
    SaveBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SaveBtn_actionPerformed(e);
      }
    });
    CompileBtn.setBackground(new Color(210, 203, 255));
    CompileBtn.setText("编译");
    CompileBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CompileBtn_actionPerformed(e);
      }
    });
    ToCheckBtn.setBackground(new Color(210, 203, 255));
    ToCheckBtn.setText("打分");
    ToCheckBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ToCheckBtn_actionPerformed(e);
      }
    });
    CheckPn.setLayout(borderLayout8);
    GradePn.setLayout(borderLayout5);
    GradeLabel.setToolTipText("");
    GradeLabel.setText("现在答对了  0  题");
    jTP.setBackground(new Color(210, 203, 255));
    jTP.setToolTipText("");
    jMenuFileOpen = new JMenuItem("打开源程序。。。", image1);
    jMenuFileOpen.setBackground(new Color(210, 203, 255));
    jMenuFileOpen.setActionCommand("Open File...");
    jMenuFileOpen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OpenBtn_actionPerformed(e);
      }
    });
    jMenuFileSave = new JMenuItem("保存源程序", image2);
    jMenuFileSave.setBackground(new Color(210, 203, 255));
    jMenuFileSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SaveBtn_actionPerformed(e);
      }
    });
    jMenuFileSaveAs = new JMenuItem("源程序另存为。。。", imageSaveAs);
    jMenuFileSaveAs.setBackground(new Color(210, 203, 255));
    jMenuFileSaveAs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuFileSaveAs_actionPerformed(e);
      }
    });
    jMenuFileExit = new JMenuItem("退出", imageExit);
    jMenuFileExit.setBackground(new Color(210, 203, 255));
    jMenuFileExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuFileExit_actionPerformed(e);
      }
    });
    LanguageCB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LanguageCB_actionPerformed(e);
      }
    });
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.setDividerSize(8);
    jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane3.setDividerSize(8);
    MessageEP.setFont(new java.awt.Font("DialogInput", 0, 13));
    MessageEP.setEditable(false);
    LoginBtn.setBackground(new Color(210, 203, 255));
    LoginBtn.setText("登陆");
    LoginBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LoginBtn_actionPerformed(e);
      }
    });
    ProblemCB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ProblemCB_actionPerformed(e);
      }
    });
    CodePn.setBackground(new Color(210, 203, 255));
    CodePn.setToolTipText("");
    TestPn.setLayout(borderLayout3);
    RunBtn.setBackground(new Color(210, 203, 255));
    RunBtn.setText("运行");
    RunBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RunBtn_actionPerformed(e);
      }
    });
    ProblemEP.setEditable(false);
    ProblemEP.setContentType("text/html");
    ClearBtn.setBackground(new Color(210, 203, 255));
    ClearBtn.setText("清除");
    ClearBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ClearBtn_actionPerformed(e);
      }
    });
    CheckBtn.setBackground(new Color(210, 203, 255));
    CheckBtn.setText("打分");
    CheckBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CheckBtn_actionPerformed(e);
      }
    });
    jSplitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane4.setDividerSize(4);
    jSplitPane5.setDividerSize(4);
    jMenuTools.setBackground(new Color(210, 203, 255));
    jMenuTools.setText("工具");
    jMenuToolsCompilerpath = new JMenuItem("设置编译器路径", imageCCP);
    jMenuToolsCompilerpath.setBackground(new Color(210, 203, 255));
    jMenuToolsCompilerpath.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuToolsCompilerpath_actionPerformed(e);
      }
    });
    jMenuToolsProgrampath = new JMenuItem("设置源程序保存路径", imageCFP);
    jMenuToolsProgrampath.setBackground(new Color(210, 203, 255));
    jMenuToolsProgrampath.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuToolsProgrampath_actionPerformed(e);
      }
    });
    jMenuHelpAbout = new JMenuItem("关于", image3);
    jMenuHelpAbout.setBackground(new Color(210, 203, 255));
    jMenuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    jMenuHelp.setBackground(new Color(210, 203, 255));
    jMenuHelp.setText("帮助");
    jSplitPane6.setDividerSize(8);
    OutputTA.setEditable(false);
    ToTestBtn.setBackground(new Color(210, 203, 255));
    ToTestBtn.setText("测试");
    ToTestBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ToTestBtn_actionPerformed(e);
      }
    });
    jPanel1.setBackground(new Color(210, 203, 255));
    jToolBar2.setBackground(new Color(210, 203, 255));
    jScrollPane6.getViewport().setBackground(new Color(210, 203, 255));

    jToolBar.setBackground(new Color(210, 203, 255));
    jMenuBar1.setBackground(new Color(210, 203, 255));
    jToolBar1.setBackground(new Color(210, 203, 255));
    jToolBar3.setBackground(new Color(210, 203, 255));
    ProblemCB.setBackground(new Color(210, 203, 255));
    LanguageCB.setBackground(new Color(210, 203, 255));
    judgeOutput.setEditable(false);
    CheckMsg.setEditable(false);
    jTP.addTab("编码", imageicon, CodePn, "Code");
    jTP.addTab("测试", imageicon, TestPn, "Test");
    jTP.addTab("打分", imageicon, CheckPn, "Check");
    jTP.addTab("成绩", imageicon, GradePn, "Grade");
    GradePn.add(jScrollPane3,  BorderLayout.CENTER);
    jScrollPane3.getViewport().add(SubmitRecordTbl, null);
    CheckPn.add(jToolBar3, BorderLayout.NORTH);
    jToolBar3.add(CheckBtn, null);
    jToolBar3.add(ToTestBtn, null);
    jToolBar2.add(LoginBtn, null);
    jToolBar2.add(ProblemCB, null);
    jToolBar2.add(LanguageCB, null);
    jToolBar2.add(SaveBtn, null);
    jToolBar2.add(CompileBtn, null);
    jToolBar2.add(ToCheckBtn, null);
    CodePn.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane3.add(jScrollPane1, JSplitPane.TOP);
    jSplitPane3.add(jScrollPane2, JSplitPane.BOTTOM);
    jScrollPane2.getViewport().add(CodeEP, null);
    jScrollPane1.getViewport().add(ProblemEP, null);
    CodePn.add(jToolBar2, BorderLayout.NORTH);
    jSplitPane1.add(jScrollPane6, JSplitPane.BOTTOM);
    jSplitPane1.add(jSplitPane3, JSplitPane.TOP);
    jScrollPane6.getViewport().add(MessageEP, null);
    jToolBar.add(OpenBtn);
    jToolBar.add(SaveAsBtn);
    jToolBar.add(AboutBtn);
    jToolBar.add(jLabel1, null);
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jTP,  BorderLayout.CENTER);
    jMenuFile.add(jMenuFileOpen);
    jMenuFile.add(jMenuFileSave);
    jMenuFile.add(jMenuFileSaveAs);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuTools);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    jToolBar.add(PaintLb, null);
    jToolBar.add(GradeLabel, null);
    TestPn.add(jToolBar1, BorderLayout.NORTH);
    jToolBar1.add(RunBtn, null);
    jToolBar1.add(ClearBtn, null);
    TestPn.add(jSplitPane6, BorderLayout.CENTER);
    jSplitPane6.add(jScrollPane5, JSplitPane.LEFT);
    jScrollPane5.getViewport().add(InputTA, null);
    jSplitPane6.add(jScrollPane7, JSplitPane.RIGHT);
    jScrollPane7.getViewport().add(OutputTA, null);
    CheckPn.add(jSplitPane4, BorderLayout.CENTER);
    jSplitPane4.add(jSplitPane5, JSplitPane.TOP);
    jSplitPane5.add(jScrollPane9, JSplitPane.TOP);
    jScrollPane9.getViewport().add(stdOutput, null);
    jSplitPane5.add(jScrollPane10, JSplitPane.BOTTOM);
    jScrollPane10.getViewport().add(judgeOutput, null);
    jSplitPane4.add(jScrollPane8, JSplitPane.BOTTOM);
    jScrollPane8.getViewport().add(CheckMsg, null);
    jMenuTools.add(jMenuToolsCompilerpath);
    jMenuTools.add(jMenuToolsProgrampath);
    jSplitPane1.setDividerLocation(350);
    jSplitPane3.setDividerLocation(200);
    jSplitPane4.setDividerLocation(350);
    jSplitPane5.setDividerLocation(400);
    jMenuHelp.add(jMenuHelpAbout);
    jSplitPane6.setDividerLocation(400);
    Auto_getPaper();
  }
  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    Student_AboutBox dlg = new Student_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  private void Auto_getPaper() {
    pb = new PaperBean();
    try {
      pb.unmarshal(eb.getPaper()+eb.getPaperName());
      ((javax.swing.text.html.HTMLDocument)(ProblemEP.getDocument())).setBase(new File(System.getProperty("java.io.tmpdir")).toURL());
    }
    catch(Exception E1) {
      System.out.println(E1.toString());
    }
    int papernum = pb.getProblemCount();
    accepted = new int[papernum];
    stdsb = new SolutionBean[papernum];
    for ( int i = 0 ; i < papernum ; i++ ) {
      ProblemCB.addItem("p"+Integer.toString(i)+".   "+pb.getProblemAt(i).getTitle());
      String temp = "p"+Integer.toString(i);
      stdsb[i] = new SolutionBean();
      stdsb[i].setFilename(temp);
      accepted[i] = 0;
    }
    Nowpapernum = 0;
    stdsb[0].setLanguage(language);
    try {
      ProblemEP.setText(pb.getProblemAt(0).transform());
      ProblemEP.setCaretPosition(0);
    }
    catch(Exception E2) {
      System.out.println(E2.toString());
    }
  }

  void LoginBtn_actionPerformed(ActionEvent e) {
    boolean packFrame = false;
    LoginDlg dlg = new LoginDlg(eb);
    dlg.setTitle("Login & GetPaper");
    if (packFrame) {
      dlg.pack();
    }
    else {
      dlg.validate();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = dlg.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    dlg.setVisible(true);
    this.setTitle("Student -- Name : "+dlg.getStdName()+"  StudentNo : "+dlg.getStdNo());
    int i = dlg.getIconIndex();
    PaintLb.setIcon(new ImageIcon(student.Student.class.getResource(Integer.toString(i+1)+"-1.gif")));
  }

  void ProblemCB_actionPerformed(ActionEvent e) {
    int i = ProblemCB.getSelectedIndex();
    if ( Nowpapernum != i ) {
      stdsb[Nowpapernum].setSourceCode(CodeEP.getText());
      Nowpapernum = i;
      try {
        stdsb[i].setLanguage(language);
        jb.setSolutionBean(stdsb[i]);
        CodeEP.setText(" ");
        CodeEP.setText("");
        CodeEP.setText(stdsb[i].getSourceCode());
        try {
          ProblemEP.setText(pb.getProblemAt(i).transform());
          ProblemEP.setCaretPosition(0);
        }
        catch(Exception E2) {
          System.out.println(E2.toString());
        }
      }
      catch(Exception E) {
        System.out.println(E.toString());
      }
      stdOutput.setText("");
      judgeOutput.setText("");
      CheckMsg.setText("");
      MessageEP.setText("");
    }
  }

  void LanguageCB_actionPerformed(ActionEvent e) {
    language = (String)((Object[])LanguageCB.getSelectedItem())[1];
    kit.setLanguage(language);
    doc.setLanguage(language);
    String temp = CodeEP.getText();
    CodeEP.setText(temp);
    stdsb[ProblemCB.getSelectedIndex()].setLanguage(language);
  }

  void OpenBtn_actionPerformed(ActionEvent e) {
    File path = new File(eb.getSourceCodeTemp());
    if ( !path.isFile() )
      path.mkdirs();
    JFileChooser chooser = new JFileChooser(path);
    if ( chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) {
      try {
        String fileName = chooser.getSelectedFile().getPath();
        File file = new File(fileName);
        int fileSize = (int) file.length();
        int charReaded = 0;
        FileReader in = new FileReader (file);
        char[] data = new char [fileSize];
        while (in.ready()){
          charReaded += in.read(data,charReaded,fileSize-charReaded);
        }
        in.close();
        String SourceCode = "";
        for(int i = 0 ; i < charReaded ; i++ ){
          SourceCode += data[i];
        }
        CodeEP.setText(SourceCode);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    MessageEP.setText("");
  }

  void SaveBtn_actionPerformed(ActionEvent e) {
    String fileName = eb.getSourceCodeTemp();
    File fpath = new File(eb.getSourceCodeTemp());
    if ( !fpath.exists() )
      fpath.mkdirs();
    fileName += stdsb[ProblemCB.getSelectedIndex()].getFilename();
    fileName += eb.getFormerSuffix(stdsb[ProblemCB.getSelectedIndex()].getLanguage());
    try {
      File file = new File (fileName);
      FileWriter out = new FileWriter(file);
      String text = CodeEP.getText();
      out.write(text);
      out.close();
    }
    catch (IOException IOE) {
      System.out.print(IOE.toString());
    }
  }

  void jMenuFileSaveAs_actionPerformed(ActionEvent e) {
    File path = new File(eb.getSourceCodeTemp());
    if ( !path.isFile() )
      path.mkdirs();
    JFileChooser chooser = new JFileChooser(path);
    if ( chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ) {
      try {
        File file = chooser.getSelectedFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(CodeEP.getText());
        out.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  void CompileBtn_actionPerformed(ActionEvent e) {
    MessageEP.setText("Wait for Compiling...");
    stdsb[ProblemCB.getSelectedIndex()].setSourceCode(CodeEP.getText());
    jb.setSolutionBean(stdsb[ProblemCB.getSelectedIndex()]);
    jb.judgeCompile();
    boolean flag = jb.getResultBean().getCompileResult();
    MessageEP.setText(jb.getResultBean().getCompileInfo());
    if ( flag )
      JOptionPane.showMessageDialog(this,"Compile Successfully!");
    else
      JOptionPane.showMessageDialog(this,"Compile Error!","Error",JOptionPane.ERROR_MESSAGE);
    InputTA.setText("");
    OutputTA.setText("");
    CheckMsg.setText("");
    judgeOutput.setText("");
    stdOutput.setText("");
    System.gc();
  }

  void RunBtn_actionPerformed(ActionEvent e) {
    jb.setTestDataBean(pb.getProblemAt(ProblemCB.getSelectedIndex()).getTestData());
    jb.setTestInput(InputTA.getText());
    jb.judgeTestRun();
    boolean flag = jb.getResultBean().getRunResult();
    if ( flag )
      JOptionPane.showMessageDialog(this,"Run Successfully!");
    else
      JOptionPane.showMessageDialog(this,jb.getResultBean().getRunInfo(),"Error",JOptionPane.ERROR_MESSAGE);
    OutputTA.setText(jb.getResultBean().getRunOutputData());
    System.gc();
  }

  void ClearBtn_actionPerformed(ActionEvent e) {
    InputTA.setText("");
    OutputTA.setText("");
    jb.getResultBean().setRunOutputData("");
    jb.getResultBean().setRunOutputError("");
  }

  void CheckBtn_actionPerformed(ActionEvent e) {
    stdOutput.setText("");
    judgeOutput.setText("");
    CheckMsg.setText("");
    String stutas = "";
    String str = jb.getResultBean().getRunOutputData();
    if ( str.length() > 2097152 ) {
      CheckMsg.setText("No --- Output Limit Exceeded");
      stutas = "No --- Output Limit Exceeded";
    }
    else {
      jb.setTestDataBean(pb.getProblemAt(ProblemCB.getSelectedIndex()).getTestData());
      jb.judgeCheck();
      boolean flag = jb.getResultBean().getCheckResult();
      if ( !flag ) {
        CheckMsg.setText("No --- ");
        CheckMsg.append(jb.getResultBean().getCheckInfo());
        stutas = CheckMsg.getText();
      }
      else {
        CheckMsg.setText("Yes --- Accepted\n");
        stutas = "Yes --- Accepted";
        CheckMsg.append("Your program has run "+Float.toString(jb.getResultBean().getTimerun()));
        CheckMsg.append(" seconds.\n");
        if ( 0 == accepted[ProblemCB.getSelectedIndex()] ) {
          accepted[ProblemCB.getSelectedIndex()] = 1;
          allAC++;
          GradeLabel.setText("    你现在答对了  "+allAC+"  题    ");
        }
      }
      stdOutput.setText(jb.getResultBean().getRunOutputData());
      judgeOutput.setText(jb.getJudgeOutput());
    }
    if ( CheckMsg.getText().length() < 1 )
      return;
    Object[] obj = {Integer.toString(checktimes++),
                  Integer.toString(ProblemCB.getSelectedIndex()),
                  language,
                  stutas
                 };
    subdataModel.addRow(obj);
  }

  void jMenuToolsCompilerpath_actionPerformed(ActionEvent e) {
    boolean packFrame = false;
    EnvironmentDlg dlg = new EnvironmentDlg(true,eb);
    dlg.setTitle("Configure : Set the Path of the Compiler !");
    if (packFrame) {
      dlg.pack();
    }
    else {
      dlg.validate();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = dlg.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    dlg.setVisible(true);
    jb.setEnvironmentBean(eb);
  }

  void jMenuToolsProgrampath_actionPerformed(ActionEvent e) {
    boolean packFrame = false;
    ProgramPath dlg = new ProgramPath(eb);
    dlg.setTitle("Configure : Set the Path of Source Code!");
    if (packFrame) {
      dlg.pack();
    }
    else {
      dlg.validate();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = dlg.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    dlg.setVisible(true);
    jb.setEnvironmentBean(eb);
    System.out.println(eb.getSourceCodeTemp());
  }

  void ToCheckBtn_actionPerformed(ActionEvent e) {
    jTP.setSelectedIndex(2);
  }

  void ToTestBtn_actionPerformed(ActionEvent e) {
    jTP.setSelectedIndex(1);
  }
}