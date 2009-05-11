package contestjudgeterminal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.*;
import com.borland.jbcl.layout.*;
import javax.swing.text.html.*;
import java.net.*;
import com.dyf.*;
import com.zxp.*;
import com.sjn.*;
import com.lrf.*;
import com.hry.*;
import javax.swing.border.*;
/**
 * <p>Title: ContestJudgeTerminal</p>
 * <p>Description: ContestJudgeTerminal</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: DHU</p>
 * @author DHU
 * @version 1.0
 */

final public class MainFrame extends JFrame {
  Vector runTableColumns          = new Vector();
  Vector newclarTableColumns      = new Vector();
  Vector answeredclarTableColumns = new Vector();
  Vector runTableData             = new Vector();
  Vector newclarTableData         = new Vector();
  Vector answeredclarTableData    = new Vector();
  String paperFilePath;
  String environmentFilePath;
  String tablepath = "/StatisticTable.xml";
  String tmpDir = System.getProperty("java.io.tmpdir");
  int howManyProblems = 0;
  int inttemp  = 0;
  int inttemp2 = 0;
  int inttemp3 = 0;
  int inttemp4 = 0;
  int readyForJudge =0;
  StatisticTableBean statictisTable;
  String ebpath = "./Environment.xml";
  String checkinfo = "";
  EnvironmentBean    eb = null;
  PaperBean            paperBean = null;
  JudgeBean            jb        = null;
  JudgeNet             net       = null;
  SingleAnswerDoc      sad       = null;
  java.util.Timer    taskMonitor = new java.util.Timer();
  java.util.Timer    messageMonitor = new java.util.Timer();
  java.util.Timer    timeMonitor = new java.util.Timer();
  JPanel contentPane;
  JPanel jPanel1                  = new JPanel();
  JPanel jPanel2                  = new JPanel();
  JPanel jPanel3                  = new JPanel();
  JPanel jPanel4                  = new JPanel();
  JPanel jPanel5                  = new JPanel();
  JPanel jPanel6                  = new JPanel();
  JPanel jPanel7                  = new JPanel();
  JPanel jPanel8                  = new JPanel();
  JPanel jPanel9                  = new JPanel();
  JPanel jPanel11                 = new JPanel();
  JPanel jPanel12                 = new JPanel();
  JPanel jPanel13                 = new JPanel();
  JTabbedPane jTabbedPane1        = new JTabbedPane();
  JTabbedPane jTabbedPane2        = new JTabbedPane();
  JTabbedPane jTabbedPane3        = new JTabbedPane();
  JTabbedPane jTabbedPane4        = new JTabbedPane();
  JTabbedPane jTabbedPane5        = new JTabbedPane();
  JTable answeredclarTable        = new JTable();
  JTable newclarTable             = new JTable();
  JScrollPane jScrollPane2        = new JScrollPane();
  JScrollPane jScrollPane3        = new JScrollPane();
  JScrollPane jScrollPane5        = new JScrollPane();
  JButton jButtonAnswer           = new JButton();
  JButton jButtonApply            = new JButton();
  JButton jButtonFinishAnswer     = new JButton();
  JButton jButtonSetEB            = new JButton();
  JButton jButtonOpenPaper        = new JButton();
  JLabel jLabelStudentName        = new JLabel();
  JLabel jLabelSelectProblem      = new JLabel();
  JLabel LabelServerAddress       = new JLabel();
  JLabel LabelPrimeServerIP        = new JLabel();
  JTextField jTextFieldServerIP        = new JTextField();
  JTextField jTextFieldPrimeServerIP         = new JTextField();
  JTextField jTextStudentName               = new JTextField();
  JToolBar jToolBarPreview        = new JToolBar();
  JToolBar jToolBarRun            = new JToolBar();
  JButton jButtonViewAnswers                = new JButton();
  PaneLayout paneLayout1          = new PaneLayout();
  PaneLayout paneLayout2          = new PaneLayout();
  PaneLayout paneLayout3          = new PaneLayout();
  JTextArea jTextAreaViewQuiz     = new JTextArea();
  JTextArea jTextAreaAnswerText   = new JTextArea();
  BorderLayout borderLayout1      = new BorderLayout();
  BorderLayout borderLayout2      = new BorderLayout();
  BorderLayout borderLayout3      = new BorderLayout();
  JComboBox jComboBox1            = new JComboBox();
  JEditorPane jEditorPane1        = new JEditorPane();
  JToolBar jToolBar1 = new JToolBar();
  JSplitPane jSplitPane1 = new JSplitPane();
  JTextArea jTextAreaAnswerInput = new JTextArea();
  JToolBar jToolBar2 = new JToolBar();
  BorderLayout borderLayout5 = new BorderLayout();
  JToolBar jToolBar3 = new JToolBar();
  JToolBar jToolBar4 = new JToolBar();
  JSplitPane jSplitPane2 = new JSplitPane();
  BorderLayout borderLayout7 = new BorderLayout();
  JTextArea jTextAreaShowQuiz = new JTextArea();
  JScrollPane jScrollPane6 = new JScrollPane();
  JScrollPane jScrollPane7 = new JScrollPane();
  BorderLayout borderLayout4 = new BorderLayout();
  JScrollPane jScrollPane8 = new JScrollPane();
  JScrollPane jScrollPane9 = new JScrollPane();
  BorderLayout borderLayout6 = new BorderLayout();
  JToolBar jToolBar5 = new JToolBar();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JPanel jPanel10 = new JPanel();
  JLabel jLabelWelcomeInformation = new JLabel();
  JButton jButtonExit = new JButton();
  JButton jButtonAbout = new JButton();
  BorderLayout borderLayout8 = new BorderLayout();
  JLabel jLabelClarCount = new JLabel();
  ImageIcon image1  = new ImageIcon();
    ImageIcon image2  = new ImageIcon();
    ImageIcon image3  = new ImageIcon();
    ImageIcon image4  = new ImageIcon();
    ImageIcon image5  = new ImageIcon();
    ImageIcon image6  = new ImageIcon();
    ImageIcon image7  = new ImageIcon();
    ImageIcon image8  = new ImageIcon();
    ImageIcon image9  = new ImageIcon();
    ImageIcon image10 = new ImageIcon();
    ImageIcon image11 = new ImageIcon();
    ImageIcon image12 = new ImageIcon();
    ImageIcon image13 = new ImageIcon();
    ImageIcon image14 = new ImageIcon();
    ImageIcon image15 = new ImageIcon();
    ImageIcon imagePreview = new ImageIcon();
    ImageIcon imageRunning = new ImageIcon();
    ImageIcon image16 = new ImageIcon();
    ImageIcon image17 = new ImageIcon();
    ImageIcon image18 = new ImageIcon();
    ImageIcon image19 = new ImageIcon();
    ImageIcon image20 = new ImageIcon();
    ImageIcon image21 = new ImageIcon();
    ImageIcon image22 = new ImageIcon();
    ImageIcon image23 = new ImageIcon();
    ImageIcon image24 = new ImageIcon();
    ImageIcon image25 = new ImageIcon();
    ImageIcon image26 = new ImageIcon();
    ImageIcon imageWelcome = new ImageIcon();
  private JLabel jLabelTaskCount = new JLabel();
  private JPanel jPanel14 = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JTextField jTextFieldName = new JTextField();
  private JTextField jTextFieldStudentNo = new JTextField();
  private JLabel jLabelName = new JLabel();
  private JLabel jLabelStudentNo = new JLabel();
  private JLabel jLabelProblemIndex = new JLabel();
  private JTextField jTextFieldProblemIndex = new JTextField();
  private JLabel jLabelLanguage = new JLabel();
  private JTextField jTextFieldLanguage = new JTextField();
  private JLabel jLabelSourceCode = new JLabel();
  private JCheckBox jCheckBoxChecked = new JCheckBox();
  private JEditorPane jEditorPaneSourceCode = new JEditorPane();
  private JLabel jLabelSubmitTime = new JLabel();
  private JTextField jTextFieldSubmitTime = new JTextField();
  JButton jButton5 = new JButton();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JButton jButtonGetSubmit = new JButton();
  JPanel jPanel15 = new JPanel();
  JScrollPane jScrollPane12 = new JScrollPane();
  JTextArea jTextAreaSubmitOutput = new JTextArea();
  JTextArea jTextAreaStandardOutput = new JTextArea();
  JScrollPane jScrollPane11 = new JScrollPane();
  JTextArea jTextAreaJudgeInformation = new JTextArea();
  JScrollPane jScrollPane10 = new JScrollPane();
  JToolBar jToolBar6 = new JToolBar();
  BorderLayout borderLayout9 = new BorderLayout();
  JButton jButtonSendResult = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JButton jButtonJudge = new JButton();
  private GridBagLayout gridBagLayout3 = new GridBagLayout();
  public MainFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception  {
    eb = new EnvironmentBean(ebpath);
    jb = new JudgeBean();
    jb.setEnvironmentBean(eb);
    net = new JudgeNet();
    image1  = new ImageIcon((MainFrame.class.getResource("arbiter.gif")));
    image2  = new ImageIcon((MainFrame.class.getResource("submited.gif")));
    image3  = new ImageIcon((MainFrame.class.getResource("checking.gif")));
    image4  = new ImageIcon((MainFrame.class.getResource("problemviewer.gif")));
    image5  = new ImageIcon((MainFrame.class.getResource("clars and answers.gif")));
    image6  = new ImageIcon((MainFrame.class.getResource("new clars.gif")));
    image7  = new ImageIcon((MainFrame.class.getResource("clar queue.gif")));
    image8  = new ImageIcon((MainFrame.class.getResource("write the answer.gif")));
    image9  = new ImageIcon((MainFrame.class.getResource("answered clars.gif")));
    image10 = new ImageIcon((MainFrame.class.getResource("answered list.gif")));
    image11 = new ImageIcon((MainFrame.class.getResource("view the clar and answer.gif")));
    image12 = new ImageIcon((MainFrame.class.getResource("settings.gif")));
    image13 = new ImageIcon((MainFrame.class.getResource("about.png")));
    image14 = new ImageIcon((MainFrame.class.getResource("exit.png")));
    image15 = new ImageIcon((MainFrame.class.getResource("get submit.png")));
    imagePreview = new ImageIcon((MainFrame.class.getResource("preview.gif")));
    imageRunning = new ImageIcon((MainFrame.class.getResource("running.gif")));
    image16 = new ImageIcon((MainFrame.class.getResource("judge.png")));
    image17 = new ImageIcon((MainFrame.class.getResource("send result.png")));
    image18 = new ImageIcon((MainFrame.class.getResource("preview.png")));
    image19 = new ImageIcon((MainFrame.class.getResource("answer.png")));
    image20 = new ImageIcon((MainFrame.class.getResource("finish answer.png")));
    image21 = new ImageIcon((MainFrame.class.getResource("view answers.png")));
    image22 = new ImageIcon((MainFrame.class.getResource("student ip.png")));
    image23 = new ImageIcon((MainFrame.class.getResource("send manually.png")));
    image24 = new ImageIcon((MainFrame.class.getResource("apply.png")));
    image25 = new ImageIcon((MainFrame.class.getResource("openpaper.png")));
    image26 = new ImageIcon((MainFrame.class.getResource("seteb.png")));
    imageWelcome = new ImageIcon((MainFrame.class.getResource("welcome.png")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setBackground(new Color(200,200,235));
    jButtonAnswer.addActionListener(new MainFrame_jButtonAnswer_actionAdapter(this));
    jPanel5.setLayout(paneLayout2);
    jPanel6.setLayout(paneLayout3);
    jTabbedPane2.setTabPlacement(JTabbedPane.TOP);
    jTabbedPane2.setBackground(new Color(200, 200, 235));
    jPanel3.setLayout(borderLayout5);
    jPanel7.setLayout(borderLayout4);
    jPanel8.setLayout(borderLayout7);
    jPanel9.setLayout(borderLayout6);
    jButtonFinishAnswer.setBackground(new Color(200, 200, 235));
    jButtonFinishAnswer.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonFinishAnswer.setMaximumSize(new Dimension(150, 32));
    jButtonFinishAnswer.setMinimumSize(new Dimension(150, 32));
    jButtonFinishAnswer.setPreferredSize(new Dimension(150, 32));
    jButtonFinishAnswer.setBorderPainted(false);
    jButtonFinishAnswer.setText("");
    jButtonFinishAnswer.setIcon(image20);
    jButtonFinishAnswer.setMargin(new Insets(0, 0, 0, 0));
    jButtonFinishAnswer.addActionListener(new MainFrame_jButtonFinishAnswer_actionAdapter(this));
    jTextAreaViewQuiz.setBackground(new Color(171, 184, 236));
    jTextAreaViewQuiz.setBorder(null);
    jTextAreaViewQuiz.setEditable(false);
    jTextAreaViewQuiz.setText("quizText");
    jTextAreaAnswerText.setBackground(new Color(171, 184, 236));
    jTextAreaAnswerText.setBorder(null);
    jTextAreaAnswerText.setEditable(false);
    jTextAreaAnswerText.setText("answerText");
    jTextStudentName.setEditable(false);
    jTextStudentName.setText("");
    jButtonOpenPaper.setBackground(new Color(200, 200, 235));
    jButtonOpenPaper.setBounds(new Rectangle(388, 96, 131, 29));
    jButtonOpenPaper.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonOpenPaper.setBorderPainted(false);
    jButtonOpenPaper.setText("");
    jButtonOpenPaper.setIcon(image25);
    jButtonOpenPaper.addActionListener(new MainFrame_jButtonOpenPaper_actionAdapter(this));
    jButtonViewAnswers.addActionListener(new MainFrame_jButtonViewAnswers_actionAdapter(this));
    jLabelStudentName.setBackground(new Color(200, 200, 235));
    jLabelStudentName.setText("");
    jLabelStudentName.setIcon(image22);
    jButtonApply.addActionListener(new MainFrame_jButtonApply_actionAdapter(this));
    jButtonSetEB.setBackground(new Color(200, 200, 235));
    jButtonSetEB.setBounds(new Rectangle(40, 164, 277, 29));
    jButtonSetEB.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonSetEB.setBorderPainted(false);
    jButtonSetEB.setText("");
    jButtonSetEB.setIcon(image26);
    jButtonSetEB.addActionListener(new MainFrame_jButtonSetEB_actionAdapter(this));
    jPanel11.setLayout(borderLayout3);
    jPanel12.setLayout(borderLayout9);
    newclarTable.setBackground(new Color(200, 200, 255));
    newclarTable.setGridColor(new Color(153, 153, 180));
    newclarTable.setSelectionBackground(Color.white);
    answeredclarTable.setBackground(new Color(200, 200, 255));
    answeredclarTable.setGridColor(new Color(153, 153, 180));
    answeredclarTable.setSelectionBackground(Color.white);
    jPanel13.setLayout(borderLayout2);
    jPanel13.setBackground(new Color(200, 200, 235));
    jPanel13.setDebugGraphicsOptions(0);
    jLabelSelectProblem.setBackground(new Color(200, 200, 235));
    jLabelSelectProblem.setFont(new java.awt.Font("Dialog", 3, 13));
    jLabelSelectProblem.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabelSelectProblem.setIconTextGap(10);
    jLabelSelectProblem.setIcon(null);
    jLabelSelectProblem.setText("Please Select Problem :   ");
    jEditorPane1.setEditable(false);
    jEditorPane1.setText("jEditorPane1");
    jEditorPane1.setContentType("text/html");
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.setBackground(new Color(200, 200, 235));
    jSplitPane1.setDividerSize(8);
    jSplitPane1.setOneTouchExpandable(true);
    jTextAreaAnswerInput.setBackground(SystemColor.text);
    jTextAreaAnswerInput.setBorder(null);
    jTextAreaAnswerInput.setText("jTextArea1");
    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane2.setBackground(new Color(200, 200, 235));
    jSplitPane2.setDividerSize(8);
    jSplitPane2.setOneTouchExpandable(true);
    jTextAreaShowQuiz.setBackground(new Color(171, 184, 236));
    jTextAreaShowQuiz.setBorder(null);
    jTextAreaShowQuiz.setEditable(false);
    jTextAreaShowQuiz.setText("jTextArea1");
    jb = new JudgeBean();
    jb.setEnvironmentBean(eb);
    jToolBarRun.setOrientation(JToolBar.HORIZONTAL);
    jToolBarRun.setBackground(new Color(200, 200, 235));
    jToolBarRun.setMargin(new Insets(0, 10, 0, 0));
    jToolBarRun.setFloatable(false);
    jComboBox1.addActionListener(new MainFrame_jComboBox1_actionAdapter(this));
    jToolBarPreview.setBackground(new Color(200, 200, 235));
    jToolBarPreview.setFloatable(false);
    jToolBar5.setBackground(new Color(200, 200, 235));
    jToolBar5.setBorder(null);
    jToolBar5.setFloatable(false);
    jToolBar1.setBackground(new Color(200, 200, 235));
    jToolBar1.setFloatable(false);
    jToolBar3.setBackground(new Color(200, 200, 235));
    jToolBar3.setFloatable(false);
    jToolBar4.setBackground(new Color(200, 200, 235));
    jToolBar4.setFloatable(false);
    jToolBar2.setBackground(new Color(200, 200, 235));
    jToolBar2.setFloatable(false);
    jTabbedPane1.setBackground(new Color(200, 200, 235));
    jTabbedPane1.setBorder(null);
    jTabbedPane1.setOpaque(true);
    contentPane.setBackground(new Color(200, 200, 235));
    jComboBox1.setBackground(new Color(200, 200, 235));
    jComboBox1.setEnabled(false);
    jComboBox1.setMaximumSize(new Dimension(32767, 32));
    jComboBox1.setMinimumSize(new Dimension(126, 32));
    jComboBox1.setPreferredSize(new Dimension(130, 32));
    jLabelWelcomeInformation.setBackground(new Color(200, 200, 235));
    jLabelWelcomeInformation.setFont(new java.awt.Font("Serif", 3, 18));
    jLabelWelcomeInformation.setForeground(new Color(100, 100, 155));
    jLabelWelcomeInformation.setMaximumSize(new Dimension(366, 32));
    jLabelWelcomeInformation.setMinimumSize(new Dimension(366, 32));
    jLabelWelcomeInformation.setPreferredSize(new Dimension(366, 32));
    jLabelWelcomeInformation.setToolTipText("");
    jLabelWelcomeInformation.setHorizontalAlignment(SwingConstants.CENTER);
    jLabelWelcomeInformation.setText("");
    jLabelWelcomeInformation.setIcon(imageWelcome);
    jButtonExit.setBackground(new Color(200, 200, 235));
    jButtonExit.setFont(new java.awt.Font("Dialog", 1, 14));
    jButtonExit.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonExit.setMaximumSize(new Dimension(90, 32));
    jButtonExit.setMinimumSize(new Dimension(90, 32));
    jButtonExit.setPreferredSize(new Dimension(90, 32));
    jButtonExit.setBorderPainted(false);
    jButtonExit.setText("");
    jButtonExit.setIcon(image14);
    jButtonExit.setMargin(new Insets(0, 0, 0, 0));
    jButtonExit.addActionListener(new MainFrame_jButtonExit_actionAdapter(this));
    jButtonAbout.setBackground(new Color(200, 200, 235));
    jButtonAbout.setFont(new java.awt.Font("Dialog", 1, 14));
    jButtonAbout.setAlignmentX((float) 0.0);
    jButtonAbout.setAlignmentY((float) 0.5);
    jButtonAbout.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonAbout.setMaximumSize(new Dimension(85, 32));
    jButtonAbout.setMinimumSize(new Dimension(85, 32));
    jButtonAbout.setPreferredSize(new Dimension(85, 32));
    jButtonAbout.setToolTipText("");
    jButtonAbout.setBorderPainted(false);
    jButtonAbout.setHorizontalTextPosition(SwingConstants.CENTER);
    jButtonAbout.setText("");
    jButtonAbout.setIcon(image13);
    jButtonAbout.setMargin(new Insets(0, 0, 0, 0));
    jButtonAbout.setVerticalAlignment(SwingConstants.CENTER);
    jButtonAbout.addActionListener(new MainFrame_jButtonAbout_actionAdapter(this));
    jPanel10.setLayout(borderLayout8);
    jPanel10.setBackground(new Color(200, 200, 235));
    jPanel10.setMaximumSize(new Dimension(2147483647, 32));
    jLabelClarCount.setBackground(new Color(200, 200, 235));
    jLabelClarCount.setText("New Clar Count :  ");
    this.getContentPane().setBackground(new Color(200, 200, 235));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    jPanel7.setBackground(new Color(200, 200, 235));
    jPanel4.setBackground(new Color(200, 200, 235));
    jLabelTaskCount.setBackground(new Color(200, 200, 235));
    jLabelTaskCount.setFont(new java.awt.Font("Serif", 1, 20));
    jLabelTaskCount.setHorizontalAlignment(SwingConstants.CENTER);
    jLabelTaskCount.setText("count");
    jScrollPane1.getViewport().setBackground(new Color(200, 200, 235));
    jScrollPane1.setAlignmentY((float) 0.5);
    jScrollPane1.setAutoscrolls(true);
    jScrollPane1.setBorder(null);
    jScrollPane1.setPreferredSize(new Dimension(40, 40));
    jPanel14.setLayout(gridBagLayout1);
    jLabelName.setBackground(new Color(200, 200, 235));
    jLabelName.setForeground(new Color(100, 100, 155));
    jLabelName.setText("Name : ");
    jLabelStudentNo.setBackground(new Color(200, 200, 235));
    jLabelStudentNo.setForeground(new Color(100, 100, 155));
    jLabelStudentNo.setText("StudentNo : ");
    jLabelProblemIndex.setBackground(new Color(200, 200, 235));
    jLabelProblemIndex.setForeground(new Color(100, 100, 155));
    jLabelProblemIndex.setText("ProblemIndex : ");
    jLabelLanguage.setBackground(new Color(200, 200, 235));
    jLabelLanguage.setForeground(new Color(100, 100, 155));
    jLabelLanguage.setText("Language : ");
    jLabelSourceCode.setBackground(new Color(200, 200, 235));
    jLabelSourceCode.setForeground(new Color(100, 100, 155));
    jLabelSourceCode.setText("SourceCode : ");
    jCheckBoxChecked.setBackground(new Color(200, 200, 235));
    jCheckBoxChecked.setForeground(new Color(102, 102, 153));
    jCheckBoxChecked.setText("HasRunAtClient ? ");
    jPanel1.setBackground(new Color(200, 200, 235));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel1.setToolTipText("");
    jTabbedPane5.setBackground(new Color(200, 200, 235));
    jPanel11.setBackground(new Color(200, 200, 235));
    jPanel14.setBackground(new Color(200, 200, 235));
    jPanel14.setForeground(new Color(100, 100, 155));
    jPanel14.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel12.setBackground(new Color(200, 200, 235));
    jScrollPane5.getViewport().setBackground(new Color(200, 200, 235));
    jPanel2.setBackground(new Color(200, 200, 235));
    jPanel5.setBackground(new Color(200, 200, 235));
    jTabbedPane3.setBackground(new Color(200, 200, 235));
    jPanel3.setBackground(new Color(200, 200, 235));
    jScrollPane2.getViewport().setBackground(new Color(200, 200, 235));
    jButtonAnswer.setBackground(new Color(200, 200, 235));
    jButtonAnswer.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonAnswer.setMaximumSize(new Dimension(118, 32));
    jButtonAnswer.setMinimumSize(new Dimension(118, 32));
    jButtonAnswer.setPreferredSize(new Dimension(118, 32));
    jButtonAnswer.setBorderPainted(false);
    jButtonAnswer.setMargin(new Insets(0, 0, 0, 0));
    jScrollPane6.getViewport().setBackground(new Color(200, 200, 235));
    jScrollPane7.getViewport().setBackground(new Color(200, 200, 235));
    jPanel6.setBackground(new Color(200, 200, 235));
    jTabbedPane4.setBackground(new Color(200, 200, 235));
    jPanel8.setBackground(new Color(200, 200, 235));
    jScrollPane3.getViewport().setBackground(new Color(200, 200, 235));
    jButtonViewAnswers.setBackground(new Color(200, 200, 235));
    jButtonViewAnswers.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonViewAnswers.setMaximumSize(new Dimension(148, 32));
    jButtonViewAnswers.setMinimumSize(new Dimension(148, 32));
    jButtonViewAnswers.setPreferredSize(new Dimension(148, 32));
    jButtonViewAnswers.setBorderPainted(false);
    jButtonViewAnswers.setMargin(new Insets(0, 0, 0, 0));
    jPanel9.setBackground(new Color(200, 200, 235));
    jScrollPane8.getViewport().setBackground(new Color(200, 200, 235));
    jScrollPane9.getViewport().setBackground(new Color(200, 200, 235));
    LabelPrimeServerIP.setBackground(new Color(200, 200, 235));
    jLabelSubmitTime.setForeground(new Color(100, 100, 155));
    jLabelSubmitTime.setText("SubmitTime : ");
    jButton5.setBackground(new Color(200, 200, 235));
    jButton5.setFont(new java.awt.Font("DialogInput", 1, 16));
    jButton5.setBorder(BorderFactory.createLineBorder(Color.black));
    jButton5.setText("   Go to Judge-Page ->   ");
    jButton5.addActionListener(new MainFrame_jButton5_actionAdapter(this));
    jButtonApply.setBackground(new Color(200, 200, 235));
    jButtonApply.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonApply.setMaximumSize(new Dimension(88, 32));
    jButtonApply.setMinimumSize(new Dimension(88, 32));
    jButtonApply.setPreferredSize(new Dimension(93, 35));
    jButtonApply.setBorderPainted(false);
    jTextFieldPrimeServerIP.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextFieldPrimeServerIP.setCaretColor(new Color(200, 200, 235));
    jTextFieldServerIP.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextFieldServerIP.setCaretColor(new Color(200, 200, 235));
    jTextFieldStudentNo.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextFieldStudentNo.addActionListener(new MainFrame_jTextFieldStudentNo_actionAdapter(this));
    jTextFieldName.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextFieldProblemIndex.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextFieldLanguage.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextFieldSubmitTime.setBorder(BorderFactory.createLineBorder(Color.black));
    jEditorPaneSourceCode.setBorder(BorderFactory.createLineBorder(Color.black));
    jEditorPaneSourceCode.setMinimumSize(new Dimension(30, 20));
    jEditorPaneSourceCode.setPreferredSize(new Dimension(30, 20));
    jButtonGetSubmit.setBackground(new Color(200, 200, 235));
    jButtonGetSubmit.setText("");
    jButtonGetSubmit.addActionListener(new MainFrame_jButtonGetSubmit_actionAdapter(this));
    jButtonGetSubmit.setIcon(image15);
    jButtonGetSubmit.setMargin(new Insets(2, 14, 2, 14));
    jButtonGetSubmit.setFont(new java.awt.Font("Serif", 1, 26));
    jButtonGetSubmit.setForeground(new Color(30, 76, 186));
    jButtonGetSubmit.setAlignmentX((float) 0.5);
    jButtonGetSubmit.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonGetSubmit.setMaximumSize(new Dimension(320, 36));
    jButtonGetSubmit.setMinimumSize(new Dimension(320, 36));
    jButtonGetSubmit.setPreferredSize(new Dimension(320, 36));
    jButtonGetSubmit.setVerifyInputWhenFocusTarget(true);
    jButtonGetSubmit.setBorderPainted(false);
    jPanel15.setLayout(gridBagLayout3);
    jScrollPane12.getViewport().setBackground(SystemColor.textHighlightText);
    jTextAreaSubmitOutput.setBorder(null);
    jTextAreaSubmitOutput.setSelectionColor(Color.red);
    jTextAreaSubmitOutput.setText("SubmitOutput");
    jTextAreaStandardOutput.setBorder(null);
    jTextAreaStandardOutput.setText("StandardOutput");
    jScrollPane11.getViewport().setBackground(SystemColor.textHighlightText);
    jTextAreaJudgeInformation.setBorder(null);
    jTextAreaJudgeInformation.setText("JudgeInformation");
    jScrollPane10.getViewport().setBackground(SystemColor.textHighlightText);
    jButtonSendResult.setBackground(new Color(200, 200, 235));
    jButtonSendResult.setFont(new java.awt.Font("Dialog", 1, 28));
    jButtonSendResult.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonSendResult.setMaximumSize(new Dimension(130, 32));
    jButtonSendResult.setMinimumSize(new Dimension(130, 32));
    jButtonSendResult.setPreferredSize(new Dimension(130, 32));
    jButtonSendResult.setBorderPainted(false);
    jButtonSendResult.setText("");
    jButtonSendResult.setIcon(image17);
    jButtonSendResult.setMargin(new Insets(0, 0, 0, 0));
    jButtonSendResult.addActionListener(new MainFrame_jButtonSendResult_actionAdapter(this));
    jToolBar6.setBackground(new Color(200, 200, 235));
    jToolBar6.setBorderPainted(false);
    jPanel15.setBackground(new Color(200, 200, 235));
    jLabel1.setText("SubmitOutput : ");
    jLabel2.setToolTipText("");
    jLabel2.setText("StandardOutput : ");
    jLabel3.setText("JudgeInformation : ");
    jLabel4.setText("                           ");
    jButtonJudge.setBackground(new Color(200, 200, 235));
    jButtonJudge.setEnabled(true);
    jButtonJudge.setFont(new java.awt.Font("Dialog", 1, 28));
    jButtonJudge.setBorder(BorderFactory.createLineBorder(Color.black));
    jButtonJudge.setMaximumSize(new Dimension(100, 32));
    jButtonJudge.setMinimumSize(new Dimension(100, 32));
    jButtonJudge.setPreferredSize(new Dimension(100, 32));
    jButtonJudge.setBorderPainted(false);
    jButtonJudge.setText("");
    jButtonJudge.setIcon(image16);
    jButtonJudge.setMargin(new Insets(0, 0, 0, 0));
    jButtonJudge.addActionListener(new MainFrame_jButtonJudge_actionAdapter(this));
    runTableColumns.add("Name");
    runTableColumns.add("ComputerNo");
    runTableColumns.add("ProblemIndex");
    runTableColumns.add("ProgramType");
    DefaultTableModel temp = new DefaultTableModel(null,runTableColumns);
    newclarTableColumns.add("Quiz");
    newclarTableColumns.add("IPAddress");
    temp = new DefaultTableModel(null,newclarTableColumns);
    newclarTable.setModel(temp);
    answeredclarTableColumns.add("IPAddress");
    answeredclarTableColumns.add("Quiz");
    answeredclarTableColumns.add("Answer");
    temp = new DefaultTableModel(null,answeredclarTableColumns);
    answeredclarTable.setModel(temp);
    jLabelSelectProblem.setIcon(imagePreview);
    contentPane.setLayout(gridBagLayout2);
    this.setSize(new Dimension(803, 604));
    this.setTitle("ContestJudgePlatform");
    jPanel1.setLayout(borderLayout1);
    jTextFieldServerIP.setText("202.120.159.24");
    jTextFieldServerIP.setColumns(0);
    jTextFieldServerIP.setBounds(new Rectangle(160, 57, 181, 21));
    jPanel4.setLayout(null);
    LabelServerAddress.setText("ServerIPAddress:");
    LabelServerAddress.setBounds(new Rectangle(49, 58, 153, 19));
    LabelPrimeServerIP.setText("PrimeServerIPAddress:");
    LabelPrimeServerIP.setBounds(new Rectangle(20, 99, 144, 19));
    jTextFieldPrimeServerIP.setText("202.120.159.29");
    jTextFieldPrimeServerIP.setColumns(0);
    jTextFieldPrimeServerIP.setBounds(new Rectangle(159, 97, 181, 21));
    jButtonApply.setBounds(new Rectangle(389, 44, 88, 32));
    jButtonApply.setText("");
    jButtonApply.setIcon(image24);
    jPanel2.setLayout(paneLayout1);
    jButtonAnswer.setText("");
    jButtonAnswer.setIcon(image19);
    jButtonViewAnswers.setText("");
    jButtonViewAnswers.setIcon(image21);
    contentPane.setMaximumSize(new Dimension(640, 480));
    contentPane.setMinimumSize(new Dimension(640, 480));
    contentPane.setPreferredSize(new Dimension(640, 480));
    jPanel1.add(jTabbedPane5, BorderLayout.CENTER);
    jTabbedPane1.addTab("",image1,jPanel1,"Arbiter");
    jTabbedPane5.addTab("",image2,jPanel11,"Submited");
    jTabbedPane5.addTab("",image3,jPanel12,"Checking");
    jTabbedPane5.addTab("",image4,jPanel13,"Preview");
    jPanel13.add(jScrollPane5,  BorderLayout.CENTER);
    jScrollPane5.getViewport().add(jEditorPane1, null);
    jPanel13.add(jToolBarPreview, BorderLayout.NORTH);
    jToolBarPreview.add(jLabelSelectProblem, null);
    jToolBarPreview.add(jComboBox1, null);
    jPanel2.add(jTabbedPane2,  new PaneConstraints("jTabbedPane2", "jTabbedPane2", PaneConstraints.ROOT, 1.0f));
    jPanel5.add(jTabbedPane3, new PaneConstraints("jTabbedPane3", "jTabbedPane3", PaneConstraints.ROOT, 1.0f));
    jTabbedPane1.addTab("",image5,jPanel2,"Clars And Answers");
    jTabbedPane2.addTab("",image6,jPanel5,"New Clars");
    jTabbedPane3.addTab("",image7,jPanel3,"Quiz Queue");
    jPanel3.add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(newclarTable, null);
    jTabbedPane3.addTab("",image8,jPanel7,"Write The Answer");
    jTabbedPane2.addTab("",image9,jPanel6,"Answered Clar");
    jPanel6.add(jTabbedPane4, new PaneConstraints("jTabbedPane4", "jTabbedPane4", PaneConstraints.ROOT, 1.0f));
    jTabbedPane4.addTab("",image10,jPanel8,"Answered List");
    jPanel8.add(jScrollPane3, BorderLayout.CENTER);
    jScrollPane3.getViewport().add(answeredclarTable, null);
    jTabbedPane4.addTab("",image11,jPanel9,"View The Quiz And Answer");
    jTabbedPane1.addTab("",image12,jPanel4,"Settings");
    jPanel4.add(LabelServerAddress, null);
    jPanel4.add(LabelPrimeServerIP, null);
    jPanel4.add(jTextFieldServerIP, null);
    jPanel4.add(jTextFieldPrimeServerIP, null);
    jPanel4.add(jButtonApply, null);
    jPanel4.add(jButtonOpenPaper, null);
    jPanel4.add(jButtonSetEB, null);
    contentPane.add(jToolBar5,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 191, 0));
    jToolBar5.add(jPanel10, null);
    jPanel10.add(jLabelWelcomeInformation, BorderLayout.CENTER);
    jPanel10.add(jButtonExit, BorderLayout.EAST);
    jPanel10.add(jButtonAbout,  BorderLayout.WEST);
    contentPane.add(jTabbedPane1,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), -15, -196));
    jPanel11.add(jToolBarRun,  BorderLayout.SOUTH);
    jToolBarRun.add(jButtonGetSubmit, null);
    jToolBarRun.add(jButton5, null);
    jPanel11.add(jLabelTaskCount, BorderLayout.NORTH);
    jPanel11.add(jPanel14,  BorderLayout.CENTER);
    jPanel7.add(jToolBar1, BorderLayout.SOUTH);
    jToolBar1.add(jButtonFinishAnswer, null);
    jPanel7.add(jSplitPane1,  BorderLayout.CENTER);
    jSplitPane1.add(jScrollPane6, JSplitPane.TOP);
    jScrollPane6.getViewport().add(jTextAreaShowQuiz, null);
    jSplitPane1.add(jScrollPane7, JSplitPane.BOTTOM);
    jScrollPane7.getViewport().add(jTextAreaAnswerInput, null);
    jPanel3.add(jToolBar2, BorderLayout.SOUTH);
    jToolBar2.add(jButtonAnswer, null);
    jPanel3.add(jLabelClarCount, BorderLayout.NORTH);
    jPanel8.add(jToolBar3, BorderLayout.SOUTH);
    jToolBar3.add(jButtonViewAnswers, null);
    jPanel9.add(jToolBar4,  BorderLayout.NORTH);
    jToolBar4.add(jLabelStudentName, null);
    jToolBar4.add(jTextStudentName, null);
    jPanel9.add(jSplitPane2, BorderLayout.CENTER);
    jSplitPane2.add(jScrollPane8, JSplitPane.TOP);
    jScrollPane8.getViewport().add(jTextAreaViewQuiz, null);
    jSplitPane2.add(jScrollPane9, JSplitPane.BOTTOM);
    jScrollPane9.getViewport().add(jTextAreaAnswerText, null);
    jPanel12.add(jPanel15, BorderLayout.CENTER);
    jPanel15.add(jScrollPane12,  new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 2, 0, 6), 684, 111));
    jScrollPane12.getViewport().add(jTextAreaJudgeInformation, null);
    jPanel15.add(jScrollPane11,  new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 2, 0, 6), 694, 111));
    jScrollPane11.getViewport().add(jTextAreaStandardOutput, null);
    jPanel15.add(jScrollPane10,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 2, 0, 6), 705, 127));
    jScrollPane10.getViewport().add(jTextAreaSubmitOutput, null);
    jPanel15.add(jLabel3,  new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 602), 77, 0));
    jPanel15.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(4, 2, 0, 612), 88, 0));
    jPanel15.add(jLabel2,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 609), 79, 0));
    jPanel12.add(jToolBar6,  BorderLayout.SOUTH);
    jToolBar6.add(jButtonJudge, null);
    jToolBar6.add(jLabel4, null);
    jToolBar6.add(jButtonSendResult, null);
    jPanel14.add(jScrollPane1,     new GridBagConstraints(0, 3, 5, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 12, 4, 18), 715, 274));
    jScrollPane1.getViewport().add(jEditorPaneSourceCode, null);
    jPanel14.add(jLabelStudentNo,     new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(11, 12, 0, 0), 23, 2));
    jPanel14.add(jTextFieldStudentNo,     new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(9, 17, 0, 0), 246, 0));
    jPanel14.add(jTextFieldName,     new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 17, 0, 0), 246, 0));
    jPanel14.add(jLabelLanguage,     new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(13, 49, 0, 16), 20, 0));
    jPanel14.add(jLabelProblemIndex,     new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 49, 0, 0), 11, 0));
    jPanel14.add(jLabelSourceCode,     new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 12, 0, 0), 10, 5));
    jPanel14.add(jTextFieldProblemIndex,     new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(3, 14, 0, 26), 196, 0));
    jPanel14.add(jTextFieldLanguage,     new GridBagConstraints(4, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(9, 14, 0, 26), 196, 0));
    jPanel14.add(jLabelName,     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 12, 0, 0), 50, 3));
    jPanel14.add(jLabelSubmitTime,     new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(7, 49, 0, 0), 25, 8));
    jPanel14.add(jTextFieldSubmitTime,     new GridBagConstraints(4, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10, 14, 0, 26), 196, 0));
    jPanel14.add(jCheckBoxChecked,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 23), 27, 3));
    jPanel2.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel3.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel4.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel5.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel6.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel7.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel8.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel9.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel11.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel12.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jPanel13.setBorder(BorderFactory.createLineBorder(Color.magenta,0));
    jSplitPane1.setDividerLocation(120);
    jSplitPane2.setDividerLocation(120);
    jTabbedPane1.setSelectedIndex(2);
    jTextFieldServerIP.setText("202.120.151.");
    jTextFieldPrimeServerIP.setText("202.120.151.");
    jLabelTaskCount.setVisible(false);
  }
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  void addItemToNewClarTable(String Question,String IPAddress){
    JOptionPane.showMessageDialog(this,"New Clar Detected","-=Look=-",JOptionPane.INFORMATION_MESSAGE);
    Vector newclar = new Vector();
    newclar.add(Question);
    newclar.add(IPAddress);
    DefaultTableModel dtm = (DefaultTableModel)newclarTable.getModel();
    dtm.addRow(newclar);
    newclarTable.setModel(dtm);
    newclarTableData.add(newclar);
    inttemp3++;
  }

  void addItemToAnsweredClarTable(String IP,String Question,String Answer){
    Vector answeredclar = new Vector();
    answeredclar.add(IP);
    answeredclar.add(Question);
    answeredclar.add(Answer);
    DefaultTableModel dtm = (DefaultTableModel)answeredclarTable.getModel();
    dtm.addRow(answeredclar);
    answeredclarTable.setModel(dtm);
    answeredclarTableData.add(answeredclar);
  }

  private void removeAllData(Element root){
    try{
      Element remove = root.getChild("Persons");
      if (remove != null){
        root.removeChild("Persons");
      }
      root.getChild("ProblemStatus").removeChildren("Problem");
    }catch(Exception e){
      errorLogo("Error in remove children"+"\n"+e.toString());
    }
  }


  public void refreshTaskMonitor(){
    try{
      int count = net.getTaskCount();
      this.setTitle( "Have received : ["+ Integer.toString(inttemp4)+"]  Now in queue : [" + Integer.toString( count ) + "]  Confirm With Server Count [" +Integer.toString(++inttemp2)+"]");
    }catch (Exception e){
      errorLogo(e.toString());
    }
  }


  public void refreshMessageMonitor(){
    try{
      MessageType mt = net.getMessage();
      if (mt.getContext().length()>0){
        jLabelClarCount.setText( "Message account  : "+ Integer.toString(inttemp3));
        addItemToNewClarTable(mt.getContext() , mt.getStartPoint() );
        judgeLogo("From "+mt.getStartPoint()+ "\nContent :" + mt.getContext() );
      }
    }catch (Exception e){
      JOptionPane.showMessageDialog(this,"Connect to server fail ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
      errorLogo("Connect to server error \n"+e.toString());
    }
  }

  public void refreshTimeMonitor(){
    try{
      jLabelWelcomeInformation.setText( " Contest Time : " + net.getTime());
    }catch(Exception e){
      errorLogo("Error in refresh TimeMonitor\n" + e.toString());
    }
  }

  public int findBalefulCode(String tocheck){
    int result = -1;
    int length = tocheck.length();
    for (int i=0 ; i<length-5;i++){
      if (tocheck.substring(i,i+"System".length()).equalsIgnoreCase("System")){
        result = i;
        break;
      }
    }
    for (int i=0 ; i<length-5;i++){
      if (tocheck.substring(i,i+"Format".length()).equalsIgnoreCase("Format")){
        result = i;
        break;
      }
    }

    for (int i=0 ; i<length-2;i++){
      if (tocheck.substring(i,i+"Net".length()).equalsIgnoreCase("Net")){
        result = i;
        break;
      }
    }

    for (int i=0 ; i<length-5;i++){
      if (tocheck.substring(i,i+"Delete".length()).equalsIgnoreCase("Delete")){
        result = i;
        break;
      }
    }
    return result;
  }

  public String transformation(int subt){
    String t = "";
     t += Integer.toString(subt/3600000);
     subt %= 3600000;
     if (t.length()<1)
       t +="0";
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
    return t;
  }

  public void judgeLogo(String app){
    try{
      FileWriter fw = new FileWriter("Judge.log",true);
      Calendar cal = Calendar.getInstance();
      String head = "Real world time " + cal.getTime();
      fw.write("----------------------------------------------------------------------------------------"+head+"\nContest time : "+net.getTime()+"   " +app+"");
      fw.close();
    }
    catch(Exception ep){
      errorLogo("write log file err  " + ep.toString());
    }
  }

  public void errorLogo(String app){
    try{
       FileWriter fw = new FileWriter("error.log",true);
       Calendar cal = Calendar.getInstance();
       String head = "Real world time " + cal.getTime();
       fw.write("---------------------------------------------------------------------------------------"+head+"contest time : "+net.getTime()+"   " +app+"");
       fw.close();
    }
    catch(Exception ep){
    }
  }

  public String formatUpdateMessage(String SNo,int PNo,String yesorno,String timepast){
    String res = SNo;
    if (PNo<10)
      res += "0" ;
    res += Integer.toString(PNo);
    res += yesorno;
    res += timepast;

    return res;
  }

  public boolean sendOutResult(String finalresult,String toprime){
    boolean res = false;
    try{
      String yesorno = "0";
      if (toprime.equalsIgnoreCase("Accepted"))
        yesorno = "1";
      int timetmp = 0;
      timetmp = Integer.parseInt(sad.getSubmitTime())/60000;
      String updatemsg = formatUpdateMessage(sad.getStdNo(),Integer.parseInt(sad.getProblemID()),yesorno,Integer.toString(timetmp));
      if (updatemsg.length()<12){
        errorLogo("updateinfo error\n" + "["+updatemsg+"]");
        return false;
      }
      judgeLogo("\n Update msg to primeserver\n             "+updatemsg+"+++++++++++++++++");
      net.updateRanklist(updatemsg);
      res = true;
    }catch(Exception msgerr){
      JOptionPane.showMessageDialog(this,"Send updateinfo to prime server error ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
      errorLogo("send updateinfo to prime server error\n" + msgerr.toString());
    }
    try{
      net.sendMessage(sad.getIPAddress(),finalresult);
      judgeLogo("sendMessage"+sad.getIPAddress()+" "+finalresult);
      res = true;
    }catch(Exception msgerr){
      JOptionPane.showMessageDialog(this," Send result to server error ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
      errorLogo("send result to server error\n" + msgerr.toString());
    }
    return res;
  }

  public String getFilePath(){
    JFileChooser chooser = new JFileChooser( "D:/" );
    CustomizedFilter filter = new CustomizedFilter();
    filter.addExtension( "xml" );
    filter.setDescription( "xml files" );
    chooser.setFileFilter( filter );
    if (chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION) {
      try {
        String filename = chooser.getSelectedFile().getPath();
        return filename;
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    JOptionPane.showMessageDialog(this,"No File has been selected ! ", "Warning ! ",JOptionPane.WARNING_MESSAGE);
    return "";
  }

  public Element getDocRoot(String xmlFileName){
    Element temp = null;
    try {
      temp = Element.unmarshal(xmlFileName);
    }
    catch (Exception ex) {
      errorLogo(xmlFileName + " open failed");
    }
    return temp;
  }

  void removeItemFromNewClarTable(String Answer){
    DefaultTableModel dtm = (DefaultTableModel)newclarTable.getModel();

    dtm.removeRow(0);
    Vector temp =(Vector)newclarTableData.remove(0);

    addItemToAnsweredClarTable( (String)temp.lastElement(),(String)temp.firstElement(),Answer);
    newclarTable.setModel(dtm);
  }

  void showQuizAndAnswer(int index){
    if (index >=0 ) {
      jTextStudentName.setText((String)((Vector)answeredclarTableData.get(index)).get(0));
      jTextAreaViewQuiz.setText((String)((Vector)answeredclarTableData.get(index)).get(1));
      jTextAreaAnswerText.setText((String)((Vector)answeredclarTableData.get(index)).get(2));
      jTabbedPane4.setSelectedIndex(1);
    }
    else{
      JOptionPane.showMessageDialog(this,"No item has been selected!", "Warning",JOptionPane.WARNING_MESSAGE);
    }
  }


  void jButtonGetSubmit_actionPerformed(ActionEvent e) {
    try{
      if ( net.getTaskCount() < 1 ){
        JOptionPane.showMessageDialog(this,"there is no submit from server ! ","     -=Hint=-    ",JOptionPane.INFORMATION_MESSAGE);
        return;
      }
      sad = new com.sjn.SingleAnswerDoc(net.getAnswer("").getDocument());
      inttemp4++;
      jTextFieldName.setText(sad.getName());
      jTextFieldProblemIndex.setText(sad.getProblemID());
      jTextFieldStudentNo.setText(sad.getStdNo());
      jTextFieldLanguage.setText(sad.getLanguage());
      jCheckBoxChecked.setSelected(sad.getCheckPass());
      jTextFieldSubmitTime.setText( transformation(Integer.parseInt(sad.getSubmitTime())) );
      jEditorPaneSourceCode.setText(sad.getSourceCode());

      XMLOutputter xo = new XMLOutputter();
      String str = xo.outputString(sad.getDocument());
      judgeLogo(str);
    }catch (Exception expgetanswer){
      JOptionPane.showMessageDialog(this,"Get submit from server error ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
      errorLogo("Get submit from server error ! "+ expgetanswer.toString());
    }
  }

  void jButtonAnswer_actionPerformed(ActionEvent e) {
    if(newclarTable.getSelectedRow()>=0){
      String quiz = (String) ( ( (Vector) newclarTableData.get(newclarTable.
          getSelectedRow())).firstElement());
      jTextAreaShowQuiz.setText(quiz);

      jTabbedPane3.setSelectedIndex(1);
      jTextAreaAnswerInput.setText("");
    }
    else{
      JOptionPane.showMessageDialog(this,"no item has been selected", "Warning!",JOptionPane.WARNING_MESSAGE);
    }
  }

  void jButtonFinishAnswer_actionPerformed(ActionEvent e) {
    String inputValue = jTextAreaAnswerInput.getText();
    try{
      judgeLogo(inputValue);
      String topic = "";
      String content = "";
      int begin,end;
      String quiz = jTextAreaShowQuiz.getText();
      begin = quiz.indexOf("Topic : ");
      end = quiz.indexOf("Content : ");
      topic = quiz.substring(begin+"Topic : ".length(),end-1);
      content = quiz.substring(end+"Content : ".length(),quiz.length());
      inputValue = "QuestionReply: @!@!"+topic+"@!@! @@!!"+content+"@@!! !!@@"+inputValue+"!!@@";

      net.sendMessage((String)((Vector)newclarTableData.get(0)).lastElement(),inputValue);
    }catch(Exception esend){
      JOptionPane.showMessageDialog(this,"Send message to server error ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
      errorLogo("Send message to server error ! "+esend.toString());
    }
    removeItemFromNewClarTable(jTextAreaAnswerInput.getText());

  }

  void jButton8_actionPerformed(ActionEvent e) {
    jTextAreaAnswerInput.setText(String.valueOf(newclarTable.getSelectedRow()));
  }

  void jButtonOpenPaper_actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(this,"Please select paper file!", "-==<HINT>==- ",JOptionPane.WARNING_MESSAGE);
    paperBean = new PaperBean();
    try{
      paperBean.unmarshal(getFilePath());
      ( (javax.swing.text.html.HTMLDocument) (jEditorPane1.getDocument())).
          setBase(new File(System.getProperty("java.io.tmpdir")).toURL());
    }catch(Exception exc){
       JOptionPane.showMessageDialog(this,exc.toString(), "Error",JOptionPane.ERROR_MESSAGE);
    }
    int problemnum = paperBean.getProblemCount();
    howManyProblems = problemnum;
    jComboBox1.setEnabled(true);
    for ( int i = 0 ; i < problemnum ; i++ ) {
      jComboBox1.addItem("p"+Integer.toString(i)+".   "+paperBean.getProblemAt(i).getTitle());
    }
    try {
      JOptionPane.showMessageDialog(this,"试卷打开成功,一共有"+Integer.toString(howManyProblems)+"道题目", "    -=Confirm=-    ",JOptionPane.INFORMATION_MESSAGE);
      readyForJudge +=1;
      if (readyForJudge>1)
        jButtonJudge.setEnabled(true);
      jEditorPane1.setText(paperBean.getProblemAt(0).transform());
      jEditorPane1.setCaretPosition(0);
    }
    catch(Exception E2) {
      JOptionPane.showMessageDialog(this,"transform error", "ERROR",JOptionPane.ERROR_MESSAGE);
    }
    jTabbedPane1.setSelectedIndex(0);
    JOptionPane.showMessageDialog(this,"现在可以批题了", "     -=Hint=-     ",JOptionPane.ERROR_MESSAGE);
  }

  void jButtonViewAnswers_actionPerformed(ActionEvent e) {
    showQuizAndAnswer(answeredclarTable.getSelectedRow());
  }

  void jButtonApply_actionPerformed(ActionEvent e) {
    taskMonitor.schedule(new TimerTask() {
      public void run() {
        refreshTaskMonitor();
      }
    },500,3000);
    messageMonitor.schedule(new TimerTask() {
      public void run() {
        refreshMessageMonitor();
      }
    },3000,10000);
    net.setInterval(60);
    timeMonitor.schedule(new TimerTask() {
      public void run() {
        refreshTimeMonitor();
      }
    },1000,1000);

    readyForJudge +=1;
    if (readyForJudge>1)
      jButtonJudge.setEnabled(true);
    JOptionPane.showMessageDialog(this,"Apply success \n接下来请打开试卷","-=INFO=-",JOptionPane.INFORMATION_MESSAGE);
    jButtonOpenPaper.doClick(1);
  }

  void jButtonSetEB_actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(this,"Please select environment file!", "Warning",JOptionPane.WARNING_MESSAGE);
    environmentFilePath = getFilePath();
  }


  void jButtonJudge_actionPerformed(ActionEvent e) {
      JudgeBean jb = new JudgeBean();
      String str = sad.getFilename();
      int index = Integer.parseInt(sad.getProblemID());
      jb.setEnvironmentBean(eb);
      SolutionBean sb = new SolutionBean();
      sb.setFilename(sad.getFilename());
      sb.setLanguage(sad.getLanguage());
      sb.setSourceCode(sad.getSourceCode());
      jb.setSolutionBean(sb);
      jb.setTestDataBean(paperBean.getProblemAt(index).getTestData());
      jTextAreaStandardOutput.setText(paperBean.getProblemAt(index).getTestData().getTestOutput());
      jb.judgeCheck();
      checkinfo = jb.getResultBean().getCheckInfo();
      jTextAreaSubmitOutput.setText(jb.getResultBean().getRunOutputData());
      jTextAreaJudgeInformation.setText(checkinfo);

    Element insert = (Element) sad.getDocument().getRootElement().clone();
    new Document(insert);
    int tempInt = Integer.parseInt(insert.getChild("SubmitInformation").getChildText("Time"));
    if (tempInt < 1)
      tempInt = 1;
    String minute = Integer.toString(tempInt/60000);
    insert.getChild("SolutionInformation").addContent((Element)insert.getChild("SubmitInformation").getChild("Time").clone());
    insert.getChild("SolutionInformation").setChildText("Time",minute);
    SingleHistoryBean shb = new SingleHistoryBean();
    String singlePath = eb.getHistory()+"\\"+insert.getChild("PersonalInformation").getChildText("StudentNo")+".xml";
    File store = new File(singlePath);
    if (!store.exists()){
      shb.init((Element)(insert.getChild("PersonalInformation").clone()),ebpath);
    }else{
      try{
        shb.setroot(Element.unmarshal(singlePath));
      }catch(Exception ee){
        JOptionPane.showMessageDialog(this,"Open personal history failed ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
        errorLogo("shb unmatshal "+ ee.toString());
      }
    }
    shb.setEBPath(ebpath);
    shb.setfileName(singlePath);
    shb.addanswer(insert.getChild("SolutionInformation"));
    shb.save();

    jButtonSendResult.setEnabled(true);
}

  void jComboBox1_actionPerformed(ActionEvent e) {
    int index = jComboBox1.getSelectedIndex();
    ProblemArchiveBean pb = paperBean.getProblemAt(index);
    try{
      jEditorPane1.setText(pb.transform());
      jEditorPane1.setCaretPosition(0);
    }catch(Exception exp){
      JOptionPane.showMessageDialog(this,"Open problem error ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
      errorLogo("Open problem error ! "+ exp.toString());
    }
  }

  void jButtonExit_actionPerformed(ActionEvent e) {
    if (JOptionPane.showConfirmDialog(this,"Are you sure ?"," Warning ! ",JOptionPane.YES_NO_CANCEL_OPTION)==0){
      taskMonitor.cancel();
      messageMonitor.cancel();
      System.exit(0);
    }
  }

    void jButtonAbout_actionPerformed(ActionEvent e) {
      MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.pack();
      dlg.show();
    }


  void jButtonSendResult_actionPerformed(ActionEvent e) {
    String result = jTextAreaJudgeInformation.getText();
    if (result.equalsIgnoreCase("Accepted"))
      result = " Yes " + result;
    else
      result = " No " + result;
    String send = "";
    send = "SubmitReply: " + sad.getSubmitTime() + " " + sad.getProblemID() + result;
    judgeLogo(send);
    if (!sendOutResult(send,jTextAreaJudgeInformation.getText()))
      JOptionPane.showMessageDialog(this,"Send out result fail ! ","     -=Error=-    ",JOptionPane.ERROR_MESSAGE);
    else{
      jTabbedPane5.setSelectedIndex(0);
    }
    jTextAreaJudgeInformation.setText("");
    jTextAreaSubmitOutput.setText("");
    jTextAreaStandardOutput.setText("");
    jTextFieldName.setText("");
    jTextFieldProblemIndex.setText("");
    jTextFieldStudentNo.setText("");
    jTextFieldLanguage.setText("");
    jCheckBoxChecked.setSelected(false);
    jTextFieldSubmitTime.setText("");
    jEditorPaneSourceCode.setText("");
  }

  void jButton5_actionPerformed(ActionEvent e) {
    int xxx = findBalefulCode(jEditorPaneSourceCode.getText());
    if (xxx>-1){
      jEditorPaneSourceCode.setCaretPosition(xxx);
      jEditorPaneSourceCode.setSelectedTextColor(Color.green);
      jEditorPaneSourceCode.select(xxx,xxx+6);
      JOptionPane.showInputDialog(this,"Find Baleful code !!!!","AT No : "+Integer.toString(xxx)+"  Char");
    }
      jTabbedPane5.setSelectedIndex(1);
  }

  void jTextFieldStudentNo_actionPerformed(ActionEvent e) {

  }




}

class MainFrame_jButtonAnswer_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonAnswer_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonAnswer_actionPerformed(e);
  }
}

class MainFrame_jButtonFinishAnswer_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonFinishAnswer_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonFinishAnswer_actionPerformed(e);
  }
}

class MainFrame_jButtonViewAnswers_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonViewAnswers_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonViewAnswers_actionPerformed(e);
  }
}

class MainFrame_jButtonApply_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonApply_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonApply_actionPerformed(e);
  }
}

class MainFrame_jButtonSetEB_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonSetEB_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonSetEB_actionPerformed(e);
  }
}

class MainFrame_jButtonTest_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonTest_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonJudge_actionPerformed(e);
  }
}

class MainFrame_jButtonOpenPaper_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonOpenPaper_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonOpenPaper_actionPerformed(e);
  }
}

class MainFrame_jComboBox1_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jComboBox1_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jComboBox1_actionPerformed(e);
  }
}

class MainFrame_jButtonAbout_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonAbout_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonAbout_actionPerformed(e);
  }
}

class MainFrame_jButtonExit_actionAdapter implements java.awt.event.ActionListener {
  private MainFrame adaptee;

  MainFrame_jButtonExit_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonExit_actionPerformed(e);
  }
}

class MainFrame_jButton5_actionAdapter implements java.awt.event.ActionListener {
  private MainFrame adaptee;

  MainFrame_jButton5_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

class MainFrame_jButtonGetSubmit_actionAdapter implements java.awt.event.ActionListener {
  private MainFrame adaptee;

  MainFrame_jButtonGetSubmit_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonGetSubmit_actionPerformed(e);
  }
}

class MainFrame_jButtonJudge_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonJudge_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonJudge_actionPerformed(e);
  }
}

class MainFrame_jButtonSendResult_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButtonSendResult_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonSendResult_actionPerformed(e);
  }
}

class MainFrame_jTextFieldStudentNo_actionAdapter implements java.awt.event.ActionListener {
  private MainFrame adaptee;

  MainFrame_jTextFieldStudentNo_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextFieldStudentNo_actionPerformed(e);
  }
}

