package student;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.net.InetAddress;
import org.jdom.*;
import com.sjn.*;
import com.hry.*;

public class LoginDlg extends JDialog {

  private String tempStdNo;
  private ClientNet cn;
  private boolean islogined;
  private ImageIcon[] images;
  private JPanel panel1 = new JPanel();
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JTextField NameTF = new JTextField();
  private JTextField StdNoTF = new JTextField();
  private JTextField IPTF = new JTextField();
  private TitledBorder titledBorder1;
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JButton ClearBtn = new JButton();
  private JButton ConfirmBtn = new JButton();
  private XYLayout xYLayout1 = new XYLayout();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JLabel ImageLb = new JLabel();
  private JComboBox PaintCB ;
  private JTextField TestIDTF = new JTextField();
  private JTextField ServerIPTF = new JTextField();
  private JLabel jLabel6 = new JLabel();
  private JButton CancelBtn = new JButton();
  private JTextField CNoTF = new JTextField();
  private JLabel jLabel7 = new JLabel();
  private JTextField ClassTF = new JTextField();
  private JLabel jLabel8 = new JLabel();
  public LoginDlg(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public LoginDlg(ClientNet c) {
    this(null, "", false);
    cn = c;
  }
  private void jbInit() throws Exception {

    IPTF.setText(InetAddress.getLocalHost().getHostAddress());
    islogined = false;
    images = new ImageIcon[85];
    Integer[] intArray = new Integer[85];
    for ( int i = 0; i < 85; i++ ) {
      intArray[i] = new Integer(i);
      images[i] = new ImageIcon(student.Student.class.getResource(Integer.toString(i+1)+"-1.gif"));
      if (images[i] != null) {
        images[i].setDescription("");
      }
    }
    PaintCB = new JComboBox(intArray);
    ComboBoxRenderer renderer= new ComboBoxRenderer();
    renderer.setPreferredSize(new Dimension(32, 32));
    PaintCB.setRenderer(renderer);
    PaintCB.setMaximumRowCount(8);
    ImageLb.setIcon(new ImageIcon(student.Student.class.getResource("login.gif")));
    titledBorder1 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.setFont(new java.awt.Font("DialogInput", 1, 13));
    this.setModal(true);
    this.setResizable(false);
    this.getContentPane().setLayout(gridBagLayout1);
    this.setSize(new Dimension(480, 359));
    jPanel1.setLayout(xYLayout1);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setMinimumSize(new Dimension(480, 359));
    jPanel1.setPreferredSize(new Dimension(480, 359));
    jLabel1.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel1.setText("姓名 ： ");
    jLabel2.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel2.setText("学号 ：");
    jLabel3.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel3.setText("准考证号　：　");
    jLabel4.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel4.setText("本地ＩＰ　：");
    ClearBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    ClearBtn.setText("重填");
    ClearBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ClearBtn_actionPerformed(e);
      }
    });
    ConfirmBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    ConfirmBtn.setText("确定");
    ConfirmBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ConfirmBtn_actionPerformed(e);
      }
    });
    NameTF.setBackground(new Color(255, 255, 190));
    NameTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    StdNoTF.setBackground(new Color(255, 255, 190));
    StdNoTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    IPTF.setBackground(new Color(255, 255, 190));
    IPTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    IPTF.setEditable(false);
    panel1.setMinimumSize(new Dimension(480, 359));
    TestIDTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    TestIDTF.setBackground(new Color(255, 255, 190));
    ServerIPTF.setBackground(new Color(255, 255, 190));
    ServerIPTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    jLabel6.setText("服务器ＩＰ　：");
    jLabel6.setFont(new java.awt.Font("DialogInput", 1, 13));
    CancelBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CancelBtn_actionPerformed(e);
      }
    });
    CancelBtn.setText("取消");
    CancelBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    CNoTF.setBackground(new Color(255, 255, 190));
    CNoTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    jLabel7.setText("机号 ：");
    jLabel7.setFont(new java.awt.Font("DialogInput", 1, 13));
    ClassTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    ClassTF.setBackground(new Color(255, 255, 190));
    jLabel8.setText("班级 ： ");
    jLabel8.setFont(new java.awt.Font("DialogInput", 1, 13));
    jPanel1.add(PaintCB,        new XYConstraints(13, 69, 78, 39));
    jPanel1.add(StdNoTF, new XYConstraints(216, 119, 209, 25));
    jPanel1.add(TestIDTF, new XYConstraints(216, 159, 209, 25));
    jPanel1.add(ServerIPTF, new XYConstraints(215, 279, 209, 25));
    jPanel1.add(jLabel2, new XYConstraints(136, 119, 56, 25));
    jPanel1.add(CNoTF, new XYConstraints(216, 199, 209, 25));
    jPanel1.add(jLabel4, new XYConstraints(103, 239, 86, 25));
    jPanel1.add(IPTF, new XYConstraints(215, 239, 209, 25));
    jPanel1.add(jLabel3, new XYConstraints(104, 156, 96, 25));
    jPanel1.add(jLabel6, new XYConstraints(90, 276, 97, 25));
    jPanel1.add(ConfirmBtn, new XYConstraints(324, 321, -1, 25));
    jPanel1.add(ClearBtn, new XYConstraints(109, 321, -1, 25));
    jPanel1.add(CancelBtn, new XYConstraints(216, 321, -1, 25));
    jPanel1.add(NameTF,  new XYConstraints(216, 39, 209, 25));
    jPanel1.add(jLabel1, new XYConstraints(135, 39, 58, 25));
    this.getContentPane().add(panel1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, -1, 0, 0), -3, 0));
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(ClassTF,   new XYConstraints(216, 79, 209, 25));
    jPanel1.add(jLabel8,     new XYConstraints(135, 81, 58, 25));
    jPanel1.add(jLabel7, new XYConstraints(135, 198, 54, 25));
    jPanel1.add(ImageLb, new XYConstraints(164, 0, 137, 31));
  }

  void ClearBtn_actionPerformed(ActionEvent e) {
    NameTF.setText("");
    StdNoTF.setText("");
    TestIDTF.setText("");
    ServerIPTF.setText("");
  }

  void ConfirmBtn_actionPerformed(ActionEvent e) {

    SingleAnswerDoc sad = new SingleAnswerDoc();
    sad.setName(NameTF.getText());
    sad.setStdNo(StdNoTF.getText());
    sad.setTestID(TestIDTF.getText());
    sad.setStdClass(ClassTF.getText());
    sad.setComputerNo(CNoTF.getText());
    sad.setCheckPass("false");
    sad.setProblemID("-1");
    sad.setFilename("");
    sad.setLanguage("");
    sad.setSourceCode("");
    try {
      sad.setIPAddress(InetAddress.getLocalHost().getHostAddress());
    }
    catch(Exception E) {
      System.out.println(E.toString());
    }
    cn.setServerIP(ServerIPTF.getText());
    boolean flag = false;
    try {
      flag = cn.testNetwork();
      if ( flag )
        JOptionPane.showMessageDialog(this,"         Server exist!","Done",JOptionPane.INFORMATION_MESSAGE);
      cn.setInterval(60);
      islogined = true;
    }
    catch(Exception E1) {
      JOptionPane.showMessageDialog(this,"Server can't be found!","Error",JOptionPane.ERROR_MESSAGE);
    }
    try {
      if ( flag )
        cn.submit(sad);
    }
    catch(Exception E2) {
      JOptionPane.showMessageDialog(this,"Server Busy!\nReport this to the Judge!","Error",JOptionPane.ERROR_MESSAGE);
    }
    this.dispose();
  }

  public String getStdName() {
    return NameTF.getText();
  }

  public String getStdNo() {
    return StdNoTF.getText();
  }

  public String getTestID() {
    return TestIDTF.getText();
  }

  public String getIP() {
    return IPTF.getText();
  }

  public String getStdClass() {
    return ClassTF.getText();
  }

  public String getComputerNo() {
    return CNoTF.getText();
  }

  public int getIconIndex() {
    return PaintCB.getSelectedIndex();
  }

  public boolean isLogined() {
    return islogined;
  }

  public class ComboBoxRenderer extends JLabel implements ListCellRenderer {

    private Font uhOhFont;

    public ComboBoxRenderer() {
      setOpaque(true);
      setHorizontalAlignment(CENTER);
      setVerticalAlignment(CENTER);
    }

    public Component getListCellRendererComponent( JList list,
                                                   Object value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus) {
      int selectedIndex = ((Integer)value).intValue();
      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
      }
      else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }
      ImageIcon icon = images[selectedIndex];
      setIcon(icon);
      if (icon != null) {
        setText("");
        setFont(list.getFont());
      }
      else {
        setUhOhText(Integer.toString(selectedIndex) + " (no image available)", list.getFont());
      }
      return this;
    }

    protected void setUhOhText(String uhOhText, Font normalFont) {
      if (uhOhFont == null) {
        uhOhFont = normalFont.deriveFont(Font.ITALIC);
      }
      setFont(uhOhFont);
      setText(uhOhText);
    }
  }

  void CancelBtn_actionPerformed(ActionEvent e) {
    this.dispose();
  }

}