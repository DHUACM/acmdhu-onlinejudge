package paperdesign;

import java.net.URL;
import com.dyf.*;
import com.zxp.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Properties;
import java.awt.font.FontRenderContext;
import java.awt.print.*;

import javax.print.*;
import javax.print.attribute.*;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.text.html.*;

import org.jdom.*;
import org.jdom.Namespace;
import javax.swing.border.*;


public class PaperFrame extends JFrame {

  ArrayList tmpList = new ArrayList();
  PaperBean paperBean = new PaperBean();

  String printString = "";
  String workDocument = "";
  String tmpDir = System.getProperty("java.io.tmpdir");

  DefaultListModel listModelPre = new DefaultListModel();
  DefaultListModel listModelNow = new DefaultListModel();


  private JPanel contentPane;
  private JTabbedPane jTabbed1 = new JTabbedPane();
  private JPanel jPanelDetail = new JPanel();
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenu1 = new JMenu();
  private JMenuItem jMenuItemNew = new JMenuItem();
  private JMenuItem jMenuItemOpen = new JMenuItem();
  private JMenuItem jMenuItemExit = new JMenuItem();
  private JMenu jMenu2 = new JMenu();
  private JMenuItem jMenuItemCheck = new JMenuItem();
  private JLabel jLabelName = new JLabel();
  private JLabel jLabelAuthor = new JLabel();
  private JLabel jLabelTotaltime = new JLabel();
  private JTextField jTextFieldName = new JTextField();
  private JTextField jTextFieldAuthor = new JTextField();
  private JTextField jTextFieldTotaltime = new JTextField();
  private JPanel jPanelAddProblem = new JPanel();
  private JMenuItem jMenuItemSave = new JMenuItem();
  private JMenuItem jMenuItemSaveas = new JMenuItem();
  private JButton jButtonPreAddProblem = new JButton();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jListPre = new JList();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JList jListNow = new JList();
  private JPanel jPanelView = new JPanel();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JEditorPane jEditorPane1 = new JEditorPane();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JScrollPane jScrollPane4 = new JScrollPane();
  private JList jListPreview = new JList();
  private JPanel jPanelOutput = new JPanel();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder1;
  private JPanel jPanel2 = new JPanel();
  private TitledBorder titledBorder2;
  private TitledBorder titledBorder3;
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JCheckBox jCheckBoxCode = new JCheckBox();
  private JButton jButtonOutputTP = new JButton();
  private JCheckBox jCheckBoxClearP = new JCheckBox();
  private JButton jButtonOutputAP = new JButton();
  private JCheckBox jCheckBoxEncryptAP = new JCheckBox();
  private JCheckBox jCheckBoxEncryptTP = new JCheckBox();
  private JPanel jPanelEncryptTP = new JPanel();
  private JPanel jPanel4 = new JPanel();
  private JRadioButton jRadioTPSolution = new JRadioButton();
  private JRadioButton jRadioTPTestData = new JRadioButton();
  private JRadioButton jRadioAPSolution = new JRadioButton();
  private JRadioButton jRadioAPTestData = new JRadioButton();
  private JPanel jPanelCheck = new JPanel();
  private JScrollPane jScrollPane5 = new JScrollPane();
  private JEditorPane jEditorPaneError = new JEditorPane();
  private JPanel jPanel3 = new JPanel();
  private JCheckBox jCheckPaperComplite = new JCheckBox();
  private JCheckBox jCheckElementType = new JCheckBox();
  private JCheckBox jCheckDTD = new JCheckBox();
  private JCheckBox jCheckSource = new JCheckBox();
  private JCheckBox jCheckRandomData = new JCheckBox();
  private TitledBorder titledBorder4;
  private JButton jButtonStartCheck = new JButton();
  private JProgressBar jProgressBarCheck = new JProgressBar();
  private TitledBorder titledBorder5;
  private JPanel jPanel6 = new JPanel();
  private JButton jButtonPreviewPaper = new JButton();
  private JCheckBox jCheckBoxPreviewPaper = new JCheckBox();
  private JButton jButtonPrintPreview = new JButton();
  private JPanel jPanel7 = new JPanel();
  private JLabel jLabelPaperVersion = new JLabel();
  private JTextField jTextFieldPaperVersion = new JTextField();
  private JLabel jLabelPaperId = new JLabel();
  private JTextField jTextFieldPaperId = new JTextField();
  private JLabel jLabelTotalProblemNum = new JLabel();
  private JTextField jTextFieldTotalPNum = new JTextField();
  private JLabel jLabereference = new JLabel();
  private JTextField jTextFieldPaperRef = new JTextField();
  private JTextField jTextFieldStartTime = new JTextField();
  private JLabel jLabelStartTime = new JLabel();
  private JLabel jLabelPaperDescribe = new JLabel();
  private JScrollPane jScrollPane6 = new JScrollPane();
  private JEditorPane jEditorPanePaperDescribe = new JEditorPane();
  private JScrollPane jScrollPane7 = new JScrollPane();
  private JEditorPane jEditorPane2 = new JEditorPane();
  private ButtonGroup buttonGroupLanguage = new ButtonGroup();
  private JLabel jLabel5 = new JLabel();
  private JButton jPreviewPreSelectProblem = new JButton();
  private JCheckBox jCheckBoxClearData = new JCheckBox();
  private JPanel jPanelClearProblem = new JPanel();
  private JRadioButton jRadioButtonSam = new JRadioButton();
  private JRadioButton jRadioButtonSpe = new JRadioButton();
  private JRadioButton jRadioButtonTri = new JRadioButton();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private GridBagLayout gridBagLayout2 = new GridBagLayout();
  private JCheckBox jCheckBox1 = new JCheckBox();
  private JButton jButtonClear = new JButton();
  private JLabel jLabel3 = new JLabel();

  class checkThread extends Thread {

    public void run(){
      jProgressBarCheck.setValue(0);
      String strReport = "<font color=\"red\">Check Report:</font><br>";
      int sum = 3 + paperBean.getProblemCount();

      strReport += paperBean.checkPaperIntegrality();
      jProgressBarCheck.setValue( 100/sum );
      jEditorPaneError.setText( strReport );
      if( !paperBean.passFlag ){
        return;
      }

      strReport += paperBean.checkPaperDtd();
      jProgressBarCheck.setValue( 2*100/sum );
      jEditorPaneError.setText( strReport );
      if( !paperBean.passFlag ){
              return;
      }

      strReport += paperBean.checkPaperType();
      jProgressBarCheck.setValue( 3*100/sum );
      jEditorPaneError.setText( strReport );
      if( !paperBean.passFlag ){
              return;
      }

      for( int i = 0; i < paperBean.getProblemCount(); i++ ){
        strReport += paperBean.checkPapersolution( paperBean.getProblemAt(i) );
        jEditorPaneError.setText( strReport );
        jProgressBarCheck.setValue( (4+i)*100/sum );
      }

      if( paperBean.passFlag ){
        paperBean.setCheckSignal(true);
      }
    }
  }


  //Construct the frame
  public PaperFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();

      HTMLDocument doc = (HTMLDocument)jEditorPane1.getDocument();
      doc.setBase(new URL("file:///" + tmpDir + "/"));

      tmpList.clear();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    jLabelTotaltime.setToolTipText("");
    jScrollPane7.setBounds(new Rectangle(11, 317, 512, 116));
    jEditorPane2.setForeground(Color.white);
    jEditorPane2.setEditable(false);
    jEditorPane2.setContentType("text/html");
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel5.setForeground(Color.blue);
    jLabel5.setText("Paper Problems");
    jLabel5.setBounds(new Rectangle(326, 39, 154, 20));
    jEditorPaneError.setEditable(false);
    jEditorPaneError.setContentType("text/html");
    jPreviewPreSelectProblem.setBounds(new Rectangle(532, 353, 117, 26));
    jPreviewPreSelectProblem.setFont(new java.awt.Font("Dialog", 1, 14));
    jPreviewPreSelectProblem.setForeground(Color.blue);
    jPreviewPreSelectProblem.setToolTipText("Pre-Select Problem");
    jPreviewPreSelectProblem.setText("PREVIEW");
    jPreviewPreSelectProblem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jPreviewPreSelectProblem_actionPerformed(e);
      }
    });
    jButtonPreviewPaper.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonPreviewPaper_actionPerformed(e);
      }
    });
    jCheckBoxPreviewPaper.setActionCommand("Preview Paper");
    jButtonPreviewPaper.setActionCommand("Preview");
    jButtonPrintPreview.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonPrintPreview_actionPerformed(e);
      }
    });
    jMenuItemCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemCheck_actionPerformed(e);
      }
    });
    jCheckBoxEncryptTP.setSelected(true);
    jRadioTPTestData.setSelected(true);

    jCheckBoxClearData.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jCheckBoxClearData_stateChanged(e);
      }
    });
    jCheckBoxClearData.setBounds(new Rectangle(22, 177, 149, 23));
    jCheckBoxClearData.setText("Clear TestData");
    jCheckBoxClearData.setSelected(true);
    jPanelClearProblem.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanelClearProblem.setBounds(new Rectangle(36, 200, 137, 114));
    jPanelClearProblem.setLayout(null);
    jRadioButtonSam.setText("Sample Data");
    jRadioButtonSam.setBounds(new Rectangle(13, 6, 112, 26));
    jRadioButtonSpe.setSelected(true);
    jRadioButtonSpe.setText("Special Data");
    jRadioButtonSpe.setBounds(new Rectangle(13, 71, 102, 26));
    jRadioButtonTri.setSelected(true);
    jRadioButtonTri.setText("Trivial Data");
    jRadioButtonTri.setBounds(new Rectangle(13, 39, 108, 26));
    jButton2.setToolTipText("Check and Add");
    jLabelPaperDescribe.setFont(new java.awt.Font("Dialog", 1, 13));
    jLabelPaperDescribe.setForeground(Color.blue);
    jCheckBox1.setSelected(true);
    jCheckBox1.setText("Self-Check");
    jCheckBox1.setBounds(new Rectangle(226, 90, 94, 22));
    jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jCheckBox1_stateChanged(e);
      }
    });
    jCheckPaperComplite.setEnabled(false);
    jCheckPaperComplite.setSelected(true);
    jCheckElementType.setEnabled(false);
    jCheckElementType.setSelected(true);
    jCheckDTD.setEnabled(false);
    jCheckDTD.setSelected(true);
    jCheckSource.setEnabled(false);
    jCheckSource.setSelected(true);
    jButtonClear.setBounds(new Rectangle(139, 29, 72, 30));
    jButtonClear.setForeground(Color.red);
    jButtonClear.setToolTipText("");
    jButtonClear.setText("清空");
    jButtonClear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonClear_actionPerformed(e);
      }
    });
    jLabel3.setForeground(Color.blue);
    jLabel3.setText("预选题预览");
    jLabel3.setBounds(new Rectangle(534, 324, 72, 23));
    jPanel7.add(jLabelPaperDescribe, null);
    jPanel7.add(jScrollPane6, null);
    jScrollPane6.getViewport().add(jEditorPanePaperDescribe, null);
    //myself

    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    titledBorder5 = new TitledBorder("");
    jListNow.setModel(listModelNow);
    jListPre.setModel(listModelPre);
    jListPreview.setModel(listModelNow);

    //setIconImage(Toolkit.getDefaultToolkit().createImage(PaperFrame.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(gridBagLayout2);
    this.setSize(new Dimension(742, 553));
    this.setTitle("Paper Designer");
    jTabbed1.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jTabbed1_stateChanged(e);
      }
    });
    jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemOpen_actionPerformed(e);
      }
    });
    jPanelAddProblem.setLayout(null);
    jMenuItemSave.setText("Save(no encrypt)");
    jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSave_actionPerformed(e);
      }
    });
    jMenuItemSaveas.setText("Save as..(no encrypt)");
    jMenuItemSaveas.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSaveas_actionPerformed(e);
      }
    });
    jButtonPreAddProblem.setBounds(new Rectangle(15, 28, 121, 31));
    jButtonPreAddProblem.setForeground(Color.blue);
    jButtonPreAddProblem.setText("添加预选题");
    jButtonPreAddProblem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonPreAddProblem_actionPerformed(e);
      }
    });
    jScrollPane1.setBounds(new Rectangle(17, 67, 198, 242));
    jButton2.setBounds(new Rectangle(226, 120, 94, 36));
    jButton2.setText("Add(C) >>");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setBounds(new Rectangle(226, 243, 94, 23));
    jButton3.setToolTipText("");
    jButton3.setText("Delete<<");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jScrollPane2.setBounds(new Rectangle(326, 67, 198, 242));
    jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemNew_actionPerformed(e);
      }
    });
    jPanelView.setLayout(gridBagLayout1);
    jEditorPane1.setEditable(false);
    jEditorPane1.setContentType("text/html");
    jButton4.setBounds(new Rectangle(538, 123, 111, 23));
    jButton4.setText("Move up");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setBounds(new Rectangle(536, 240, 111, 22));
    jButton5.setText("Move down");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jPanelOutput.setLayout(null);
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel1.setBounds(new Rectangle(29, 16, 227, 380));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(353, 16, 227, 330));
    jPanel2.setLayout(null);
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setText("Output Standard Test Paper");
    jLabel1.setBounds(new Rectangle(26, 7, 193, 18));
    jLabel2.setText("Output Standard Answer Paper");
    jLabel2.setBounds(new Rectangle(24, 7, 199, 18));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
    jCheckBoxCode.setFont(new java.awt.Font("Dialog", 1, 13));
    jCheckBoxCode.setForeground(Color.red);
    jCheckBoxCode.setText("Remain Source Code");
    jCheckBoxCode.setBounds(new Rectangle(24, 64, 170, 25));
    jButtonOutputTP.setBounds(new Rectangle(36, 330, 152, 27));
    jButtonOutputTP.setText("Output   Test-Paper");
    jButtonOutputTP.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonOutputTP_actionPerformed(e);
      }
    });
    jCheckBoxClearP.setText("Clear Problem Descrition");
    jCheckBoxClearP.setBounds(new Rectangle(21, 68, 182, 27));
    jButtonOutputAP.setBounds(new Rectangle(32, 278, 169, 29));
    jButtonOutputAP.setText("Output STD-ANS-Paper");
    jButtonOutputAP.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonOutputAP_actionPerformed(e);
      }
    });
    jCheckBoxEncryptAP.setText("Encrypt Ans-Paper");
    jCheckBoxEncryptAP.setBounds(new Rectangle(22, 112, 156, 27));
    jCheckBoxEncryptAP.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jCheckBoxEncryptAP_stateChanged(e);
      }
    });
    jCheckBoxEncryptTP.setText("Encrypt Test-Paper");
    jCheckBoxEncryptTP.setBounds(new Rectangle(24, 93, 149, 23));
    jCheckBoxEncryptTP.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jCheckBoxEncryptTP_stateChanged(e);
      }
    });
    jCheckBoxEncryptTP.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxEncryptTP_actionPerformed(e);
      }
    });
    jPanelDetail.setLayout(null);
    jMenu1.setText("File");
    jMenuItemNew.setText("New Paper");
    jMenuItemOpen.setText("Open Paper");
    jMenuItemExit.setText("Exit");
    jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemExit_actionPerformed(e);
      }
    });
    jMenu2.setText("Tool");
    jMenuItemCheck.setText("Check");
    jLabelName.setText("Paper Name");
    jLabelName.setBounds(new Rectangle(9, 81, 106, 25));
    jLabelAuthor.setText("Paper Author");
    jLabelAuthor.setBounds(new Rectangle(9, 118, 106, 25));
    jLabelTotaltime.setText("Total Time(Hour)");
    jLabelTotaltime.setBounds(new Rectangle(9, 219, 131, 25));
    jPanelEncryptTP.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanelEncryptTP.setBounds(new Rectangle(38, 117, 130, 53));
    jPanelEncryptTP.setLayout(null);
    jPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel4.setBounds(new Rectangle(41, 141, 133, 129));
    jPanel4.setLayout(null);
    jRadioTPSolution.setText("Solution");
    jRadioTPSolution.setBounds(new Rectangle(15, 5, 93, 22));
    jRadioTPTestData.setText("TestData");
    jRadioTPTestData.setBounds(new Rectangle(15, 27, 87, 23));
    jRadioAPSolution.setEnabled(false);
    jRadioAPSolution.setText("Solution");
    jRadioAPSolution.setBounds(new Rectangle(16, 20, 96, 23));
    jRadioAPTestData.setEnabled(false);
    jRadioAPTestData.setText("TestData");
    jRadioAPTestData.setBounds(new Rectangle(16, 48, 104, 30));
    jPanelCheck.setLayout(null);
    jScrollPane5.setBounds(new Rectangle(225, 13, 418, 319));
    jEditorPaneError.setFont(new java.awt.Font("Dialog", 1, 13));
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setBounds(new Rectangle(15, 12, 190, 382));
    jPanel3.setLayout(null);
    jCheckPaperComplite.setFont(new java.awt.Font("Dialog", 1, 12));
    jCheckPaperComplite.setForeground(Color.blue);
    jCheckPaperComplite.setText("Check Paper Integrality ");
    jCheckPaperComplite.setBounds(new Rectangle(9, 29, 178, 20));

    jCheckElementType.setFont(new java.awt.Font("Dialog", 1, 12));
    jCheckElementType.setForeground(Color.blue);
    jCheckElementType.setText("Check Element Type");
    jCheckElementType.setBounds(new Rectangle(9, 68, 176, 20));

    jCheckDTD.setFont(new java.awt.Font("Dialog", 1, 12));
    jCheckDTD.setForeground(Color.blue);
    jCheckDTD.setText("Check DTD File");
    jCheckDTD.setBounds(new Rectangle(9, 107, 161, 20));

    jCheckSource.setFont(new java.awt.Font("Dialog", 1, 12));
    jCheckSource.setForeground(Color.blue);
    jCheckSource.setText("Check Problem Source");
    jCheckSource.setBounds(new Rectangle(9, 145, 175, 20));

    jCheckRandomData.setFont(new java.awt.Font("Dialog", 1, 12));
    jCheckRandomData.setForeground(Color.blue);
    jCheckRandomData.setText("Random Data Check");
    jCheckRandomData.setBounds(new Rectangle(9, 181, 161, 20));

    jButtonStartCheck.setBounds(new Rectangle(16, 343, 160, 30));
    jButtonStartCheck.setFont(new java.awt.Font("Dialog", 1, 12));
    jButtonStartCheck.setForeground(Color.red);
    jButtonStartCheck.setText("START");
    jButtonStartCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonStartCheck_actionPerformed(e);
      }
    });
    jProgressBarCheck.setBounds(new Rectangle(226, 354, 417, 39));
    jPanel6.setBorder(BorderFactory.createEtchedBorder());
    jPanel6.setLayout(null);
    jButtonPreviewPaper.setBounds(new Rectangle(10, 7, 159, 29));
    jButtonPreviewPaper.setFont(new java.awt.Font("Dialog", 1, 12));
    jButtonPreviewPaper.setForeground(Color.blue);
    jButtonPreviewPaper.setText("Preview");
    jCheckBoxPreviewPaper.setFont(new java.awt.Font("Dialog", 3, 12));
    jCheckBoxPreviewPaper.setForeground(Color.red);
    jCheckBoxPreviewPaper.setText("All Paper");
    jCheckBoxPreviewPaper.setBounds(new Rectangle(5, 51, 86, 23));
    jButtonPrintPreview.setBounds(new Rectangle(92, 49, 76, 26));
    jButtonPrintPreview.setFont(new java.awt.Font("Dialog", 1, 12));
    jButtonPrintPreview.setText("Print");
    jPanel7.setBorder(BorderFactory.createEtchedBorder());
    jPanel7.setBounds(new Rectangle(14, 29, 603, 361));
    jPanel7.setLayout(null);
    jTextFieldAuthor.setBounds(new Rectangle(164, 121, 150, 21));
    jTextFieldName.setBounds(new Rectangle(164, 85, 150, 21));
    jTextFieldTotaltime.setBounds(new Rectangle(164, 220, 150, 21));
    jLabelPaperVersion.setText("Paper Version");
    jLabelPaperVersion.setBounds(new Rectangle(10, 18, 106, 25));
    jTextFieldPaperVersion.setBounds(new Rectangle(164, 19, 150, 21));
    jLabelPaperId.setText("Paper ID");
    jLabelPaperId.setBounds(new Rectangle(9, 49, 106, 25));
    jTextFieldPaperId.setBounds(new Rectangle(164, 51, 150, 21));
    jLabelTotalProblemNum.setText("Problem Sum Number");
    jLabelTotalProblemNum.setBounds(new Rectangle(9, 152, 141, 25));
    jTextFieldTotalPNum.setBounds(new Rectangle(164, 155, 150, 21));
    jLabereference.setText("Paper Reference");
    jLabereference.setBounds(new Rectangle(9, 254, 106, 25));
    jTextFieldPaperRef.setBounds(new Rectangle(164, 261, 150, 86));
    jTextFieldStartTime.setToolTipText("");
    jTextFieldStartTime.setBounds(new Rectangle(164, 186, 150, 21));
    jLabelStartTime.setText("Test Start Time");
    jLabelStartTime.setBounds(new Rectangle(9, 182, 106, 25));
    jLabelPaperDescribe.setText("Paper Describe");
    jLabelPaperDescribe.setBounds(new Rectangle(350, 15, 106, 25));
    jScrollPane6.setBounds(new Rectangle(354, 42, 234, 310));
    jPanelDetail.add(jPanel7, null);
    jPanel7.add(jLabelPaperVersion, null);
    jPanel7.add(jLabelTotaltime, null);
    jPanel7.add(jLabelPaperId, null);
    jPanel7.add(jLabelName, null);
    jPanel7.add(jLabelAuthor, null);
    jPanel7.add(jLabelTotalProblemNum, null);
    jPanel7.add(jLabelStartTime, null);
    jPanel7.add(jLabereference, null);
    jPanel7.add(jTextFieldTotalPNum, null);
    jPanel7.add(jTextFieldPaperVersion, null);
    jPanel7.add(jTextFieldPaperId, null);
    jPanel7.add(jTextFieldName, null);
    jPanel7.add(jTextFieldAuthor, null);
    jPanel7.add(jTextFieldStartTime, null);
    jPanel7.add(jTextFieldTotaltime, null);
    jPanel7.add(jTextFieldPaperRef, null);
    jPanelAddProblem.add(jButtonPreAddProblem, null);
    jPanelAddProblem.add(jScrollPane1, null);
    jPanelAddProblem.add(jButton3, null);
    jPanelAddProblem.add(jButton2, null);
    jPanelAddProblem.add(jScrollPane2, null);
    jPanelAddProblem.add(jButton4, null);
    jPanelAddProblem.add(jButton5, null);
    jPanelAddProblem.add(jLabel5, null);
    jPanelAddProblem.add(jCheckBox1, null);
    jPanelAddProblem.add(jButtonClear, null);
    jPanelAddProblem.add(jScrollPane7, null);
    jPanelAddProblem.add(jLabel3, null);
    jPanelAddProblem.add(jPreviewPreSelectProblem, null);
    jScrollPane7.getViewport().add(jEditorPane2, null);
    jTabbed1.add(jPanelDetail,     "Paper Detail");
    jTabbed1.add(jPanelAddProblem, "Add Problem");
    jTabbed1.add(jPanelView,  "Preview Paper");
    jTabbed1.add(jPanelCheck,  "Check Paper");
    jTabbed1.add(jPanelOutput, "Output Paper");
    jScrollPane2.getViewport().add(jListNow, null);
    jScrollPane1.getViewport().add(jListPre, null);
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenu1.add(jMenuItemNew);
    jMenu1.add(jMenuItemOpen);
    jMenu1.add(jMenuItemSave);
    jMenu1.add(jMenuItemSaveas);
    jMenu1.add(jMenuItemExit);
    jMenu2.add(jMenuItemCheck);
    jPanelView.add(jScrollPane3,  new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(18, 10, 8, 0), 407, 400));
    jPanelView.add(jScrollPane4,  new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(18, 9, 0, 41), -79, 157));
    jScrollPane4.getViewport().add(jListPreview, null);
    jPanelView.add(jPanel6,  new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(34, 13, 8, 41), 178, 81));
    jScrollPane3.getViewport().add(jEditorPane1, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jCheckBoxCode, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(jCheckBoxClearP, null);
    jPanel2.add(jButtonOutputAP, null);
    jPanel2.add(jCheckBoxEncryptAP, null);
    jPanel2.add(jPanel4, null);
    jPanelOutput.add(jPanel1, null);
    jPanelOutput.add(jPanel2, null);
    contentPane.add(jTabbed1,     new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(3, 3, 20, 13), 29, 57));
    jPanelEncryptTP.add(jRadioTPSolution, null);
    jPanelEncryptTP.add(jRadioTPTestData, null);
    jPanel1.add(jCheckBoxClearData, null);
    jPanel1.add(jCheckBoxEncryptTP, null);
    jPanel1.add(jPanelEncryptTP, null);
    jPanel4.add(jRadioAPTestData, null);
    jPanel4.add(jRadioAPSolution, null);
    jPanel3.add(jButtonStartCheck, null);
    jPanel3.add(jCheckPaperComplite, null);
    jPanel3.add(jCheckRandomData, null);
    jPanel3.add(jCheckSource, null);
    jPanel3.add(jCheckDTD, null);
    jPanel3.add(jCheckElementType, null);
    jPanelCheck.add(jPanel3, null);
    jPanelCheck.add(jScrollPane5, null);
    jPanelCheck.add(jProgressBarCheck, null);
    jScrollPane5.getViewport().add(jEditorPaneError, null);
    jPanel6.add(jButtonPreviewPaper, null);
    jPanel6.add(jButtonPrintPreview, null);
    jPanel6.add(jCheckBoxPreviewPaper, null);

    this.setJMenuBar(jMenuBar1);
    jPanel1.add(jPanelClearProblem, null);
    jPanelClearProblem.add(jRadioButtonSam, null);
    jPanelClearProblem.add(jRadioButtonTri, null);
    jPanelClearProblem.add(jRadioButtonSpe, null);
    jPanel1.add(jButtonOutputTP, null);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jMenuItemOpen_actionPerformed(ActionEvent e) {
          JFileChooser fileChooser = new JFileChooser(".");
          ExampleFileFilter filter = new ExampleFileFilter();
          filter.addExtension("xml");
          filter.addExtension("xml.xp");
          filter.setDescription("xml & xml.xp paper");
          fileChooser.setFileFilter(filter);
          fileChooser.setMultiSelectionEnabled(false);

          JFrame frame = new JFrame();
          int retval = fileChooser.showDialog(frame, null);
	  if(retval == JFileChooser.APPROVE_OPTION)  {
            File theFile = fileChooser.getSelectedFile();
            if(theFile != null){
              File [] files = fileChooser.getSelectedFiles();

              workDocument = fileChooser.getSelectedFile().getPath();
              unmarshal();
            }
          }
  }

  void jMenuItemSave_actionPerformed(ActionEvent e){
          if (workDocument == "") {
              JFileChooser fileChooser = new JFileChooser(".");
              ExampleFileFilter filter = new ExampleFileFilter();
              filter.addExtension("xml");
              filter.addExtension("xml.xp");
              filter.setDescription("xml & xml.xp paper");
              fileChooser.setFileFilter(filter);
              fileChooser.setMultiSelectionEnabled(false);

              if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                workDocument = fileChooser.getSelectedFile().getPath();
                marshal(paperBean);
              }
          }
          else
            marshal(paperBean);
  }

  void jMenuItemSaveas_actionPerformed(ActionEvent e) {
          JFileChooser fileChooser = new JFileChooser(".");
          ExampleFileFilter filter = new ExampleFileFilter();
          filter.addExtension("xml");
          filter.addExtension("xml.xp");
          filter.setDescription("xml & xml.xp paper");
          fileChooser.setFileFilter(filter);
          fileChooser.setMultiSelectionEnabled(false);

          if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                  workDocument = fileChooser.getSelectedFile().getPath();
                  marshal(paperBean);
        }
  }

  void unmarshal(){
    try{
      paperBean.unmarshal( workDocument );
    }
    catch( Exception e ){}

    jTextFieldName.setText( paperBean.getPaperDetail().getName() );
    jTextFieldAuthor.setText( paperBean.getPaperDetail().getAuthor() );
    jTextFieldTotaltime.setText( paperBean.getPaperDetail().getTotalTime() );
    jTextFieldPaperId.setText( paperBean.getPaperDetail().getID() );
    jTextFieldPaperRef.setText( paperBean.getPaperDetail().getReference() );
    jTextFieldTotalPNum.setText( paperBean.getPaperDetail().getProblemSum() );
    jTextFieldStartTime.setText( paperBean.getPaperDetail().getStartTime() );
    jEditorPanePaperDescribe.setText( paperBean.getPaperDetail().getDecribe() );
    jTextFieldPaperVersion.setText( paperBean.getPaperDetail().getVersion() );

    listModelNow.clear();

    int i;
    for( i = 0; i < paperBean.getProblemCount(); i++ ){
      listModelNow.addElement( paperBean.getProblemAt(i).getTitle() );
    }
  }
  void detailMarshal( PaperBean bean ){
     bean.getPaperDetail().setName( jTextFieldName.getText());
     bean.getPaperDetail().setAuthor( jTextFieldAuthor.getText());
     bean.getPaperDetail().setTotalTime( jTextFieldTotaltime.getText());
     bean.getPaperDetail().setID( jTextFieldPaperId.getText() );
     bean.getPaperDetail().setReference( jTextFieldPaperRef.getText() );
     bean.getPaperDetail().setProblemSum( jTextFieldTotalPNum.getText() );
     bean.getPaperDetail().setStartTime( jTextFieldStartTime.getText() );
     bean.getPaperDetail().setDecribe( jEditorPanePaperDescribe.getText() );
     bean.getPaperDetail().setVersion( jTextFieldPaperVersion.getText() );
  }

  void marshal( PaperBean bean ){
    detailMarshal( bean );

    try{
      bean.marshal(workDocument);
    }
    catch( Exception e ){}
  }

  void jButtonPreAddProblem_actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(".");
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("xml");
        filter.addExtension("xml.xp");
        filter.setDescription("xml & xml.xp paper");
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String filename;
                File files[] = fileChooser.getSelectedFiles();
                for( int i = 0; i < files.length; i++ ){
                  ProblemArchiveBean bean = new ProblemArchiveBean();

                  filename = files[i].getPath();
                  bean.read(filename);
                  tmpList.add( bean );
                  listModelPre.addElement(bean.getTitle());
                  bean.getProblem().writeFigureList(tmpDir);
                }
            }
            catch (Exception ex) {}
        }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    int index = jListPre.getSelectedIndex();

    if( jCheckBox1.isSelected() ){
      ProblemArchiveBean bean = (ProblemArchiveBean)tmpList.get(index);
      if ( bean.isChecked() )
        JOptionPane.showMessageDialog( new JFrame(), "Problem Checked." );
      else
        JOptionPane.showMessageDialog( new JFrame(), "Problem not Checked!" );
    }
    listModelNow.addElement( listModelPre.getElementAt( index ));
    listModelPre.removeElementAt( index );

    paperBean.addProblem( (ProblemArchiveBean)tmpList.get(index) );
    tmpList.remove( index );
  }

  void jButton3_actionPerformed(ActionEvent e) {
    int index = jListNow.getSelectedIndex();
    listModelPre.addElement( listModelNow.getElementAt( index ));
    listModelNow.removeElementAt( index );

    tmpList.add( paperBean.getProblemAt(index) );
    paperBean.removeProblem( index );
  }

  void jMenuItemNew_actionPerformed(ActionEvent e) {
    paperBean = new PaperBean();
    workDocument = "";
    listModelPre.clear();
    listModelNow.clear();
    jTextFieldName.setText("");
    jTextFieldAuthor.setText("");
    jTextFieldTotaltime.setText("");
    jTextFieldPaperId.setText("");
    jTextFieldPaperRef.setText("");
    jTextFieldTotalPNum.setText("");
    jTextFieldStartTime.setText("");
    jEditorPanePaperDescribe.setText("");
    jTextFieldPaperVersion.setText("");

    tmpList.clear();
    jListPre.removeAll();
    jListNow.removeAll();
    jListPreview.removeAll();
    jEditorPane1.setText("");
    jEditorPane2.setText("");
    jEditorPaneError.setText("");
    jTabbed1.setSelectedIndex(0);
  }


  void jTabbed1_stateChanged(ChangeEvent e) {
  }

//move to the top
  void jButton4_actionPerformed(ActionEvent e) {
    int index = jListNow.getSelectedIndex();
    if( index > 0 ){
      String str = listModelNow.getElementAt( index-1 ).toString();
      listModelNow.setElementAt(listModelNow.elementAt( index ), index-1 );
      listModelNow.setElementAt( str, index );
      jListNow.setSelectedIndex( jListNow.getSelectedIndex()-1 );

      paperBean.swapProblem( index, index-1 );
    }
  }

//move to the end
  void jButton5_actionPerformed(ActionEvent e) {
    int index = jListNow.getSelectedIndex();
      if( index < listModelNow.getSize()-1 ){
        String str = listModelNow.getElementAt( index+1 ).toString();
        listModelNow.setElementAt(listModelNow.elementAt( index ), index+1 );
        listModelNow.setElementAt( str, index );
        jListNow.setSelectedIndex( jListNow.getSelectedIndex()+1 );

        paperBean.swapProblem( index, index+1 );
   }
  }

  void jCheckBoxEncryptTP_actionPerformed(ActionEvent e) {
  }
/**
 *output test paper
 **/
 void jButtonOutputTP_actionPerformed(ActionEvent e) {
   JFileChooser fileChooser = new JFileChooser(".");
   ExampleFileFilter filter = new ExampleFileFilter();
   filter.addExtension("xml");
   filter.addExtension("xml.xp");
   filter.setDescription("xml & xml.xp paper");
   fileChooser.setFileFilter(filter);
   fileChooser.setMultiSelectionEnabled(false);

   if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
     workDocument = fileChooser.getSelectedFile().getPath();
     Element newRoot = (Element)paperBean.getRootElement().clone();
     PaperBean testPaper =  new PaperBean(newRoot);

     if( !jCheckBoxCode.isSelected() ){
       for(int i = 0; i < testPaper.getProblemCount(); i++){
         testPaper.getProblemAt(i).getSolution().setLanguage("");
         testPaper.getProblemAt(i).getSolution().setFilename("");
         testPaper.getProblemAt(i).getSolution().setSourceCode("");
       }
     }

     if( jCheckBoxClearData.isSelected() ){
       if( jRadioButtonSam.isSelected() )
         testPaper.clearTestData("sample");
       if( jRadioButtonTri.isSelected() )
         testPaper.clearTestData("trivial");
       if( jRadioButtonSpe.isSelected() )
         testPaper.clearTestData("special");
     }

     if( jCheckBoxEncryptTP.isSelected() ){
       if( jRadioTPSolution.isSelected() )
         testPaper.encryptNode("Solution");
       if( jRadioTPTestData.isSelected() )
         testPaper.encryptNode("TestData");
     }

     marshal(testPaper);
   }
 }

  void jButtonOutputAP_actionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser(".");
    ExampleFileFilter filter = new ExampleFileFilter();
    filter.addExtension("xml");
    filter.addExtension("xml.xp");
    filter.setDescription("xml & xml.xp paper");
    fileChooser.setFileFilter(filter);
    fileChooser.setMultiSelectionEnabled(false);

    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      workDocument = fileChooser.getSelectedFile().getPath();
      Element newRoot = (Element)paperBean.getRootElement().clone();
      PaperBean answerPaper =  new PaperBean(newRoot);

    if( jCheckBoxClearP.isSelected() ){
      for(int i = 0; i < answerPaper.getProblemCount(); i++){
        ProblemBean problemBean = answerPaper.getProblemAt(i).getProblem();
        problemBean.setDescription("");
        problemBean.setInputSpec("");
        problemBean.setOutputSpec("");
        for(int j = 0; j < problemBean.getFigureCount(); j++ )
          try{
            problemBean.removeFigure(j);
          }
          catch(Exception e1){}
      }
    }
    if( jCheckBoxEncryptAP.isSelected() ){
      if( jRadioAPSolution.isSelected() )
        answerPaper.encryptNode("Solution");
      if( jRadioAPTestData.isSelected() )
        answerPaper.encryptNode("TestData");
    }
    marshal(answerPaper);
   }
  }

  void jButtonStartCheck_actionPerformed(ActionEvent e) {
    PaperCheckBean selfCheck = new PaperCheckBean();
    String strReport = new String();

    marshal(paperBean);

    jProgressBarCheck.setValue( 0 );
    strReport += "<font color=\"red\">Check Report:</font><br>";
    jEditorPaneError.setText( strReport );

    checkThread myThread = new checkThread();
    myThread.start();
  }

  void jCheckBoxEncryptTP_stateChanged(ChangeEvent e) {
    if( jCheckBoxEncryptTP.isSelected() ){
      jRadioTPSolution.setEnabled(true);
      jRadioTPTestData.setEnabled(true);
    }
    else{
      jRadioTPSolution.setEnabled(false);
      jRadioTPTestData.setEnabled(false);
    }
  }

  void jCheckBoxEncryptAP_stateChanged(ChangeEvent e) {
    if( jCheckBoxEncryptAP.isSelected() ){
      jRadioAPSolution.setEnabled(true);
      jRadioAPTestData.setEnabled(true);
    }
    else{
      jRadioAPSolution.setEnabled(false);
      jRadioAPTestData.setEnabled(false);
    }
  }

  void jMenuItemExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  void jPreviewPreSelectProblem_actionPerformed(ActionEvent e) {
    int index = jListPre.getSelectedIndex();
    if(index >= 0 && index < tmpList.size()){
      ProblemArchiveBean problemSetter = (ProblemArchiveBean)tmpList.get( index );
      try{
        jEditorPane2.setText(problemSetter.transform());
        jEditorPane2.setCaretPosition(0);
     }
     catch( Exception ex ){}
   }
   else
     jEditorPane2.setText("<font color=\"red\">Only preview problem in left table</font>");
  }

  void jButtonPreviewPaper_actionPerformed(ActionEvent e) {
    String str = "";
    if(jCheckBoxPreviewPaper.isSelected()){
      for( int i = 0; i < paperBean.getProblemCount(); i++ ){
        ProblemArchiveBean problemSetter = paperBean.getProblemAt(i);
        String tmp = "";
        try{
          tmp = problemSetter.transform( );
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
        tmp = tmp.substring(14, tmp.length()-18);
        str += tmp + "<br><hr><br>";
      }
      str = "<html><body>" +
            "<center><b><font size = 5>" +
            jTextFieldName.getText() + "</font><hr></b></center>" +
            str + "</body></html>";

      jEditorPane1.setText(str);
      jEditorPane1.setCaretPosition(0);
    }
    else{
      int index = jListPreview.getSelectedIndex();
      if( index >= 0 && index < paperBean.getProblemCount() ){
        ProblemArchiveBean problemSetter = paperBean.getProblemAt( index );
        try{
          str = problemSetter.transform();
          jEditorPane1.setText(str);
          jEditorPane1.setCaretPosition(0);
        }
        catch (Exception ex) {
              ex.printStackTrace();
        }
      }
    }
    printString = str;
  }

  void jButtonPrintPreview_actionPerformed(ActionEvent e) {
    String filename = tmpDir+"paperPrint.htm";
    try{
      String str = jEditorPane1.getText();
      FileOutputStream fOut = new FileOutputStream( filename );
      fOut.write( str.getBytes() );
      fOut.close();
    }
    catch( Exception e1 ){}

    PrintRequestAttributeSet pras =
      new HashPrintRequestAttributeSet();
    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    PrintService printService[] =
      PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService defaultService =
      PrintServiceLookup.lookupDefaultPrintService();
    PrintService service = ServiceUI.printDialog(null, 200, 200,
      printService, defaultService, flavor, pras);
    if (service != null)
    try {
      DocPrintJob job = service.createPrintJob();
      FileInputStream fis = new FileInputStream(filename);
      DocAttributeSet das = new HashDocAttributeSet();
      Doc doc = new SimpleDoc(fis, flavor, das);
      job.print(doc, pras);
    }
    catch(Exception e1){}
  }

  void jMenuItemCheck_actionPerformed(ActionEvent e) {
    paperBean.setCheckSignal( paperBean.checkPaperProblems() );
  }

  void jCheckBoxClearData_stateChanged(ChangeEvent e) {
    if( jCheckBoxClearData.isSelected() ){
      jRadioButtonSam.setEnabled( true );
      jRadioButtonTri.setEnabled( true );
      jRadioButtonSpe.setEnabled( true );
    }
    else{
      jRadioButtonSam.setEnabled( false );
      jRadioButtonTri.setEnabled( false );
      jRadioButtonSpe.setEnabled( false );
    }
  }

  void jCheckBox1_stateChanged(ChangeEvent e) {
    if( jCheckBox1.isSelected() )
      jButton2.setText("Add(C) >>");
    else
      jButton2.setText("Add >>");
  }

  void jButtonClear_actionPerformed(ActionEvent e) {
    tmpList.clear();
    listModelPre.clear();
  }
}