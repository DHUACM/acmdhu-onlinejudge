package student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.table.*;
import java.util.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.*;
import java.net.*;
import com.dyf.*;
import com.sjn.*;
import com.lrf.*;
import com.zxp.*;
import com.hry.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;

public class Student extends JFrame {

  private String frameTitle = "Student -- Not Login";
  private String language;
  private MyEditorKit kit;
  private EnvironmentBean eb;
  private SolutionBean[] stdsb;
  private PaperBean pb;
  private org.jdom.Element paper;
  private JudgeBean jb;
  private ClientNet cn;
  private java.util.Timer t1;
  private java.util.Timer t2;
  private int Nowpapernum;
  private int checktimes;
  private int[] accepted;
  private int allAC;
  private int submittimes;
  private String stdName;
  private String stdNo;
  private String stdTestID;
  private String stdIP;
  private String stdClass;
  private String stdCNo;
  private ImageIcon imageicon;
  private JTextArea LnViewTA = new JTextArea("1");
  private JTextArea CodeCommentTA = new JTextArea("请在这里写程序");
  private JTextArea MessageCommentTA = new JTextArea("这里将会显示你的程序的编译信息");
  private JTextArea TestInputCommentTA = new JTextArea("请在下面的文本框中填入你自己的测试数据");
  private JTextArea TestOutputCommentTA = new JTextArea("下面文本框中的内容是你程序的输出");
  private Insets ins = new Insets(0, 5, 0, 5);
  private MyDocument doc;
  private String[] SubTblColumnNames = {"Index", "Time", "ProblemIndex", "Status"};
  private String[] AskTblColumnNames = {"Topic", "Content", "Reply"};
  private DefaultTableModel subdataModel;
  private DefaultTableModel askdataModel;
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
  private JButton ToTestBtn = new JButton();
  private JButton SubmitBtn = new JButton();
  private JPanel GradePn = new JPanel();
  private JSplitPane jSplitPane2 = new JSplitPane();
  private BorderLayout borderLayout5 = new BorderLayout();
  private JScrollPane jScrollPane3 = new JScrollPane();
  //private JScrollPane jScrollPane4 = new JScrollPane();
  private JPanel jP = new JPanel();
  private JPanel jPanel3 = new JPanel();
  private JTable SubmitRecordTbl = new JTable();
  private JLabel TimeLb = new JLabel();
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
  private JPanel AskPn = new JPanel();
  private BorderLayout borderLayout6 = new BorderLayout();
  private JSplitPane jSplitPane7 = new JSplitPane();
  private JPanel jPanel2 = new JPanel();
  private JLabel jLabel1 = new JLabel();
  private JScrollPane jScrollPane11 = new JScrollPane();
  private JTextArea ContentTA = new JTextArea();
  private JButton AskBtn = new JButton();
  private JScrollPane jScrollPane12 = new JScrollPane();
  private JEditorPane RanklistEP = new JEditorPane();
  private BorderLayout borderLayout7 = new BorderLayout();
  private JTable AskRecordTbl = new JTable();
  private TitledBorder titledBorder1;
  private JLabel PaintLb = new JLabel();
  private JButton GetPaper = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JComboBox AskTopicCB = new JComboBox();
  private JLabel AlltimeLb = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel UsedtimeLb = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JPanel RulePn = new JPanel();
  private BorderLayout borderLayout9 = new BorderLayout();
  private JScrollPane jScrollPane13 = new JScrollPane();
  private JEditorPane RuleEP = new JEditorPane();
  private JButton RefreshBtn = new JButton();
  private JPanel jPanel4 = new JPanel();
  private BorderLayout borderLayout10 = new BorderLayout();
  private JLabel jLabel5 = new JLabel();
  private JTextField AddressTF = new JTextField();
  private JButton ConnectBtn = new JButton();
  private BorderLayout borderLayout11 = new BorderLayout();
  private JScrollPane jScrollPane15 = new JScrollPane();

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

    stdName = "";
    stdNo = "";
    stdTestID = "";
    stdIP = "";
    stdClass = "";
    stdCNo = "";
    submittimes = 0;
    cn = new ClientNet();
    t1 = new java.util.Timer();
    cn.setInterval(0);
    t1.schedule( new TimerTask(){
                 public void  run (){
                   UsedtimeLb.setText(cn.getTime());
                 }
              }
      ,0,1000);
    t2 = new java.util.Timer();
    t2.schedule( new TimerTask(){
             public void  run (){
               try {
                 String s = cn.getMessage().getContext();
                 if ( s.length() > 0 )
                   SaveReplyMessage(s);
               }
               catch(Exception E2) {
                 System.out.println(E2.toString());
               }
             }
          }
      ,0,20000);
    checktimes = 0;
    allAC = 0;
    jSplitPane2.setBackground(new Color(210, 203, 255));
    jSplitPane2.setOneTouchExpandable(true);
    jSplitPane7.setOneTouchExpandable(true);
    jSplitPane6.setOneTouchExpandable(true);
    jSplitPane1.setOneTouchExpandable(true);
    jSplitPane3.setOneTouchExpandable(true);
    InputTA.setFont(new java.awt.Font("DialogInput", 0, 13));
    OutputTA.setFont(new java.awt.Font("DialogInput", 0, 13));
    AskBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        AskBtn_actionPerformed(e);
      }
    });
    GetPaper.setBackground(new Color(210, 203, 255));
    GetPaper.setEnabled(false);
    GetPaper.setText("GetPaper");
    GetPaper.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GetPaper_actionPerformed(e);
      }
    });
    SubmitRecordTbl.setRowSelectionAllowed(false);
    SubmitBtn.setEnabled(false);
    jLabel2.setText("关于");
    AlltimeLb.setText("0小时");
    jLabel4.setText("    已用时：");
    UsedtimeLb.setText("0:00");
    jLabel3.setText("          ");
    RanklistEP.setText("");
    RanklistEP.setContentType("text/html");
    RanklistEP.setFont(new Font("gb2312",1,20));
    RefreshBtn.setBackground(new Color(210, 203, 255));
    RefreshBtn.setEnabled(false);
    RefreshBtn.setText("排名");
    RefreshBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RefreshBtn_actionPerformed(e);
      }
    });
    RulePn.setLayout(borderLayout9);
    RuleEP.setEditable(false);
    jPanel4.setLayout(borderLayout10);
    jLabel5.setBackground(new Color(210, 203, 255));
    jLabel5.setText("地址：");
    ConnectBtn.setText("连接");
    ConnectBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ConnectBtn_actionPerformed(e);
      }
    });
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
    SubmitBtn.setBackground(new Color(210, 203, 255));
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
    subdataModel = new DefaultTableModel(SubTblColumnNames, 0);
    SubmitRecordTbl.setFont(new java.awt.Font("DialogInput", 1, 13));
    SubmitRecordTbl.setGridColor(new Color(210, 203, 255));
    SubmitRecordTbl.setModel(subdataModel);
    SubmitRecordTbl.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        SubmitRecordTbl_mousePressed(e);
      }
    });
    askdataModel = new DefaultTableModel(AskTblColumnNames, 0);
    AskRecordTbl.setFont(new java.awt.Font("DialogInput", 1, 13));
    AskRecordTbl.setGridColor(new Color(210, 203, 255));
    AskRecordTbl.setModel(askdataModel);
    AskRecordTbl.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        AskRecordTbl_mousePressed(e);
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
    ToTestBtn.setBackground(new Color(210, 203, 255));
    ToTestBtn.setText("测试");
    ToTestBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ToTestBtn_actionPerformed(e);
      }
    });
    SubmitBtn.setText("提交");
    SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SubmitBtn_actionPerformed(e);
      }
    });
    GradePn.setLayout(borderLayout5);
    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane2.setDividerSize(8);
    TimeLb.setToolTipText("");
    TimeLb.setText("    比赛总时间：");
    GradeLabel.setToolTipText("");
    GradeLabel.setText("    现在答对了  0  题  ");
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
    jPanel1.setBackground(new Color(210, 203, 255));
    jToolBar2.setBackground(new Color(210, 203, 255));
    jScrollPane6.getViewport().setBackground(new Color(210, 203, 255));
    jToolBar.setBackground(new Color(210, 203, 255));
    jMenuBar1.setBackground(new Color(210, 203, 255));
    jToolBar1.setBackground(new Color(210, 203, 255));
    ProblemCB.setBackground(new Color(210, 203, 255));
    LanguageCB.setBackground(new Color(210, 203, 255));
    AskPn.setLayout(borderLayout6);
    jSplitPane7.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane7.setDividerSize(8);
    jPanel2.setLayout(gridBagLayout1);
    jLabel1.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel1.setText("主题 ： ");
    ContentTA.setFont(new java.awt.Font("DialogInput", 0, 13));
    AskBtn.setBackground(new Color(210, 203, 255));
    AskBtn.setText("提问");
    jPanel2.setBackground(new Color(210, 203, 255));
    jScrollPane3.getViewport().setBackground(new Color(210, 203, 255));
    jScrollPane3.setFont(new java.awt.Font("DialogInput", 1, 13));
    jScrollPane3.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel3.setLayout(borderLayout7);
    RanklistEP.setEditable(false);
    jScrollPane12.getViewport().setBackground(new Color(210, 203, 255));
    jSplitPane2.add(jScrollPane3, JSplitPane.TOP);
    //jSplitPane2.add(jScrollPane4, JSplitPane.BOTTOM);
    jSplitPane2.add(jP, JSplitPane.BOTTOM);
    jP.setLayout(borderLayout11);
    //jScrollPane4.getViewport().add(jPanel3, null);
    jP.add(jScrollPane15, BorderLayout.CENTER);
    jP.add(jPanel4, BorderLayout.NORTH);
    //jPanel3.add(RanklistEP,  BorderLayout.CENTER);
    jScrollPane15.getViewport().add(RanklistEP, null);
    //jPanel3.add(jPanel4, BorderLayout.NORTH);
    jPanel4.add(jLabel5, BorderLayout.WEST);
    jPanel4.add(AddressTF,  BorderLayout.CENTER);
    jPanel4.add(ConnectBtn,  BorderLayout.EAST);
    jScrollPane3.getViewport().add(SubmitRecordTbl, null);
    jTP.addTab("编码", imageicon, CodePn, "Code");
    jTP.addTab("测试", imageicon, TestPn, "Test");
    jTP.addTab("成绩", imageicon, GradePn, "Grade");
    jTP.addTab("提问", imageicon, AskPn, "Test");
    jTP.addTab("规则", imageicon, RulePn, "Rule");
    RulePn.add(jScrollPane13,  BorderLayout.CENTER);
    jScrollPane13.getViewport().add(RuleEP, null);
    GradePn.add(jSplitPane2, BorderLayout.CENTER);
    jToolBar2.add(ProblemCB, null);
    jToolBar2.add(LanguageCB, null);
    jToolBar2.add(SaveBtn, null);
    jToolBar2.add(CompileBtn, null);
    jToolBar2.add(ToTestBtn, null);
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
    jToolBar.add(LoginBtn, null);
    jToolBar.add(GetPaper, null);
    jToolBar.add(SubmitBtn, null);
    jToolBar.add(RefreshBtn, null);
    jToolBar.add(TimeLb, null);
    jToolBar.add(AlltimeLb, null);
    jToolBar.add(jLabel4, null);
    jToolBar.add(UsedtimeLb, null);
    jToolBar.add(jLabel3, null);
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
    jMenuTools.add(jMenuToolsCompilerpath);
    jMenuTools.add(jMenuToolsProgrampath);
    jSplitPane2.setDividerLocation(250);
    jSplitPane1.setDividerLocation(350);
    jSplitPane3.setDividerLocation(200);
    jMenuHelp.add(jMenuHelpAbout);
    AskPn.add(jSplitPane7, BorderLayout.CENTER);
    jSplitPane7.add(jPanel2, JSplitPane.BOTTOM);
    jPanel2.add(jScrollPane11,  new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(12, 68, 0, 91), 580, 155));
    jScrollPane11.getViewport().add(ContentTA, null);
    jPanel2.add(AskBtn,  new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 68, 16, 91), 525, 0));
    jPanel2.add(AskTopicCB,  new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(8, 0, 0, 201), 174, 0));
    jPanel2.add(jLabel2,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 8, 9));
    jPanel2.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 151, 0, 0), 2, 7));
    jSplitPane7.add(jScrollPane12, JSplitPane.TOP);

    jScrollPane12.getViewport().add(AskRecordTbl, null);
    jSplitPane6.setDividerLocation(400);
    jSplitPane7.setDividerLocation(200);
    /////////////////////////////////////
    Auto_getPaper();
    /////////////////////////////////////
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

  private void SaveReplyMessage(String str) {
    if ( str.indexOf("SubmitReply: ") >= 0 ) {
      submittimes++;
      int subt = 0;
      int problemindex = 0;
      String info = "";
      String status = "";
      StringTokenizer st = new StringTokenizer(str);
      st.nextToken();
      subt = Integer.parseInt(st.nextToken());
      problemindex = Integer.parseInt(st.nextToken());
      status = st.nextToken();
      while ( st.hasMoreTokens() ) {
        info += st.nextToken()+' ';
      }
      if ( info.charAt(info.length()-1) == ' ' ) {
        info = info.substring(0,info.length()-1);
      }
      String t = "0";
      t += Integer.toString(subt/3600000);
      subt %= 3600000;
      t += ":";
      if ( subt/60000 < 10 ) {
        t += "0";
      }
      t += Integer.toString(subt/60000);
      subt %= 60000;
      t += ":";
      if ( subt/1000 < 10 ) {
        t += "0";
      }
      t += Integer.toString(subt/1000);
      String reply = "";
      reply += " Submit Time : "+t+"\n";
      reply += "Problem Index: "+problemindex+"\n";
      reply += "Submit Reply : "+status+" --- "+info+"\n";
      Object[] obj = {Integer.toString(submittimes),
                      t,
                      Integer.toString(problemindex),
                      status+" --- "+info
                     };
      subdataModel.addRow(obj);
      JOptionPane.showMessageDialog(contentPane,reply,"SubmitReply",JOptionPane.INFORMATION_MESSAGE);
      if ( reply.indexOf("Yes") >= 0 ) {
        if ( 0 == accepted[problemindex] ) {
          accepted[problemindex] = 1;
          allAC++;
          GradeLabel.setText("    你现在答对了  "+allAC+"  题    ");
        }
      }
    }
    else if ( str.indexOf("QuestionReply: ") >= 0 ) {
      String asktopic = "";
      String askcontent = "";
      String askreply = "";
      int begin = 0, end = 0;
      begin = str.indexOf("@!@!");
      end = str.lastIndexOf("@!@!");
      asktopic = str.substring(begin+4,end);
      begin = str.indexOf("@@!!");
      end = str.lastIndexOf("@@!!");
      askcontent = str.substring(begin+4,end);
      begin = str.indexOf("!!@@");
      end = str.lastIndexOf("!!@@");
      askreply = str.substring(begin+4,end);
      String reply = "";
      reply += "  Topic : "+asktopic+"\n";
      reply += "Content : "+askcontent+"\n";
      reply += "  Reply : "+askreply;
      Object[] obj = {asktopic,
                      askcontent,
                      askreply
                     };
      askdataModel.addRow(obj);
      JOptionPane.showMessageDialog(contentPane,reply,"QuestionReply",JOptionPane.INFORMATION_MESSAGE);
    }
    else if ( str.indexOf("FAILED") >= 0 ){
      JOptionPane.showMessageDialog(contentPane,str,"Error",JOptionPane.ERROR_MESSAGE);
      LoginBtn.setEnabled(true);
    }
    else {
      JOptionPane.showMessageDialog(contentPane,str,"Information",JOptionPane.INFORMATION_MESSAGE);
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
    AskTopicCB.removeAllItems();
    for ( int i = 0 ; i < papernum ; i++ ) {
      ProblemCB.addItem("p"+Integer.toString(i)+".   "+pb.getProblemAt(i).getTitle());
      String temp = "p"+Integer.toString(i);
      stdsb[i] = new SolutionBean();
      stdsb[i].setFilename(temp);
      AskTopicCB.addItem("p"+Integer.toString(i)+".   "+pb.getProblemAt(i).getTitle());
    }
    AskTopicCB.addItem("其他");
    AlltimeLb.setText(pb.getPaperDetail().getTotalTime()+"小时");
    Nowpapernum = 0;
    stdsb[0].setLanguage(language);
    try {
      ProblemEP.setText(pb.getProblemAt(0).transform());
      ProblemEP.setCaretPosition(0);
    }
    catch(Exception E2) {
      System.out.println(E2.toString());
    }
    RuleEP.setText(pb.getPaperDetail().getDecribe());
    RuleEP.setCaretPosition(0);
  }

  void LoginBtn_actionPerformed(ActionEvent e) {

    boolean packFrame = false;
    LoginDlg dlg = new LoginDlg(cn);
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
    if ( dlg.isLogined() ) {
      LoginBtn.setEnabled(false);
      int i = dlg.getIconIndex();
      PaintLb.setIcon(new ImageIcon(student.Student.class.getResource(Integer.toString(i+1)+"-1.gif")));
      stdName = dlg.getStdName();
      stdNo = dlg.getStdNo();
      stdTestID = dlg.getTestID();
      stdIP = dlg.getIP();
      stdClass = dlg.getStdClass();
      stdCNo = dlg.getComputerNo();
      cn.setInterval(60);
      frameTitle = "Student -- Name : "+stdName+"  StudentNo : "+stdNo;
      this.setTitle(frameTitle);
      GetPaper.setEnabled(true);
      RefreshBtn.setEnabled(true);
    }
    try {
      paper = cn.getTestPaper();
      if ( paper == null ) {
        JOptionPane.showMessageDialog(this,"No Paper!","No paper",JOptionPane.WARNING_MESSAGE);
        return ;
      }
      if ( paper != null )
        paper.marshal("./p.xml");
    }
    catch(Exception E) {
      System.out.println(E.toString());
    }
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
    System.gc();
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
  }

  void ToTestBtn_actionPerformed(ActionEvent e) {
    jTP.setSelectedIndex(1);
  }

  void SubmitBtn_actionPerformed(ActionEvent e) {

    try {
      if ( !cn.isStarted() ) {
        JOptionPane.showMessageDialog(this,"The Contest is closed!","Error",JOptionPane.WARNING_MESSAGE);
        return;
      }
    }
    catch(Exception E) {
      System.out.println(E.toString());
    }
    SingleAnswerDoc sad = new SingleAnswerDoc();
    sad.setName(stdName);
    sad.setStdNo(stdNo);
    sad.setTestID(stdTestID);
    sad.setIPAddress(stdIP);
    sad.setStdClass(stdClass);
    sad.setComputerNo(stdCNo);
    sad.setProblemID(Integer.toString(Nowpapernum));
    sad.setFilename(stdsb[Nowpapernum].getFilename());
    sad.setLanguage(stdsb[Nowpapernum].getLanguage());
    sad.setSourceCode(stdsb[Nowpapernum].getSourceCode());
    JudgeBean tempjb = new JudgeBean();
    tempjb.setEnvironmentBean(eb);
    tempjb.setSolutionBean(stdsb[Nowpapernum]);
    tempjb.setTestDataBean(pb.getProblemAt(Nowpapernum).getTestData());
    tempjb.setTestInput(pb.getProblemAt(Nowpapernum).getTestData().getTestInput());
    tempjb.judgeTestRun();
    if ( !tempjb.getResultBean().getRunResult() ) {
      sad.setCheckPass("false");
    }
    else {
      String tempout = pb.getProblemAt(Nowpapernum).getTestData().getTestOutput();
      if ( tempout.equals(tempjb.getResultBean().getRunOutputData()))
        sad.setCheckPass("true");
      else
        sad.setCheckPass("false");
    }
    try {
      cn.submit(sad);
      JOptionPane.showMessageDialog(this,"         Submit OK!","Done",JOptionPane.INFORMATION_MESSAGE);
    }
    catch(Exception E1) {
      JOptionPane.showMessageDialog(this,"       Submit Fail!","Error",JOptionPane.ERROR_MESSAGE);
    }
    System.gc();
  }

  void AskBtn_actionPerformed(ActionEvent e) {
    String s = "";
    s += "  Topic : "+"关于"+AskTopicCB.getSelectedItem().toString();
    s += "\n";
    s += "Content : "+ContentTA.getText();
    try {
      cn.sendMessage(s);
      JOptionPane.showMessageDialog(this,"Your Question has been Sent!","OK",JOptionPane.INFORMATION_MESSAGE);
    }
    catch(Exception E) {
      JOptionPane.showMessageDialog(this,"Fail to Ask Question!","Error",JOptionPane.ERROR_MESSAGE);
    }
  }

  void GetPaper_actionPerformed(ActionEvent e) {
    try {
      if ( !cn.isStarted() ) {
        JOptionPane.showMessageDialog(this,"The Contest is not started!","Please Waiting...",JOptionPane.WARNING_MESSAGE);
        return ;
      }
      pb = new PaperBean();
      pb.unmarshal("./p.xml");
      JOptionPane.showMessageDialog(this,"Test Paper Initilization Completed!","OK",JOptionPane.INFORMATION_MESSAGE);
      ((javax.swing.text.html.HTMLDocument)(ProblemEP.getDocument())).setBase(new File(System.getProperty("java.io.tmpdir")).toURL());
      ProblemCB.removeAllItems();
      int papernum = pb.getProblemCount();
      accepted = new int[papernum];
      stdsb = new SolutionBean[papernum];
      AskTopicCB.removeAllItems();
      for ( int i = 0 ; i < papernum ; i++ ) {
        ProblemCB.addItem("p"+Integer.toString(i)+".   "+pb.getProblemAt(i).getTitle());
        String temp = "p"+Integer.toString(i);
        stdsb[i] = new SolutionBean();
        stdsb[i].setFilename(temp);
        accepted[i] = 0;
        AskTopicCB.addItem("p"+Integer.toString(i)+".   "+pb.getProblemAt(i).getTitle());
      }
      AskTopicCB.addItem("其他");
      AlltimeLb.setText(pb.getPaperDetail().getTotalTime()+"分钟");
      frameTitle += "   "+pb.getPaperDetail().getName();
      this.setTitle(frameTitle);
      Nowpapernum = 0;
      stdsb[0].setLanguage(language);
      ProblemEP.setText(pb.getProblemAt(0).transform());
      ProblemEP.setCaretPosition(0);
      RuleEP.setText(pb.getPaperDetail().getDecribe());
      RuleEP.setCaretPosition(0);
      SubmitBtn.setEnabled(true);
    }
    catch(Exception E) {
      System.out.println(E.toString());
    }
    GetPaper.setEnabled(false);
  }

  void AskRecordTbl_mousePressed(MouseEvent e) {
    int row = ((JTable)e.getSource()).getSelectedRow();
    String info = "";
    Object[] obj = {"","",""};
    for ( int i = 0 ; i < 3 ; i++ )
      obj[i] = ((JTable)e.getSource()).getModel().getValueAt(row, i);
    info += " Topic : "+obj[0]+"\n";
    info += "Content: "+obj[1]+"\n";
    info += " Reply : "+obj[2]+"\n";
    JOptionPane.showMessageDialog(this,info,"QuestionReply",JOptionPane.INFORMATION_MESSAGE);
  }

  void SubmitRecordTbl_mousePressed(MouseEvent e) {
    int row = ((JTable)e.getSource()).getSelectedRow();
    String info = "";
    Object[] obj = {"","","",""};
    for ( int i = 0 ; i < 4 ; i++ )
      obj[i] = ((JTable)e.getSource()).getModel().getValueAt(row, i);
    info += " Submit Time : "+obj[1]+"\n";
    info += "Problem Index: "+obj[2]+"\n";
    info += "Submit Reply : "+obj[3]+"\n";
    JOptionPane.showMessageDialog(this,info,"SumbitReply",JOptionPane.INFORMATION_MESSAGE);
  }

  void RefreshBtn_actionPerformed(ActionEvent e) {
    String str = AddressTF.getText();
    try {
      String temp = str.substring(0,str.lastIndexOf("/"));
      RanklistEP.setPage(new URL(temp));
      RanklistEP.setPage(new URL(str));
      RanklistEP.setCaretPosition(0);
    }
    catch(Exception E) {
      JOptionPane.showMessageDialog(this,"无法找到网页","Warning",JOptionPane.WARNING_MESSAGE);
    }
  }

  void ConnectBtn_actionPerformed(ActionEvent e) {
    String str = AddressTF.getText();
    try {
      String temp = str.substring(0,str.lastIndexOf("/"));
      RanklistEP.setPage(new URL(temp));
      RanklistEP.setPage(new URL(str));
      RanklistEP.setCaretPosition(0);
    }
    catch(Exception E) {
      JOptionPane.showMessageDialog(this,"无法找到网页","Warning",JOptionPane.WARNING_MESSAGE);
    }
  }
}
