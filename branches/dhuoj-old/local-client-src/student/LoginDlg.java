package student;
import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import org.jdom.*;
import com.sjn.*;
import com.lrf.*;

public class LoginDlg extends JDialog {

  private String tempStdNo;
  private EnvironmentBean eb;
  private ImageIcon[] images;
  private JPanel panel1 = new JPanel();
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JTextField NameTF = new JTextField();
  private JTextField StdNoTF = new JTextField();
  private TitledBorder titledBorder1;
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JButton ClearBtn = new JButton();
  private JButton GetPaperBtn = new JButton();
  private XYLayout xYLayout1 = new XYLayout();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JLabel ImageLb = new JLabel();
  private JComboBox PaintCB ;

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

  public LoginDlg(EnvironmentBean e) {
    this(null, "", false);
    eb = e;
  }
  private void jbInit() throws Exception {

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
    this.setSize(new Dimension(469, 292));
    jPanel1.setLayout(xYLayout1);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setMinimumSize(new Dimension(469, 292));
    jPanel1.setPreferredSize(new Dimension(469, 292));
    jLabel1.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel1.setText("姓名 ： ");
    jLabel2.setFont(new java.awt.Font("DialogInput", 1, 13));
    jLabel2.setText("学号 ：");
    ClearBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    ClearBtn.setText("重填");
    ClearBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ClearBtn_actionPerformed(e);
      }
    });
    GetPaperBtn.setFont(new java.awt.Font("DialogInput", 1, 13));
    GetPaperBtn.setText("确定");
    GetPaperBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GetPaperBtn_actionPerformed(e);
      }
    });
    NameTF.setBackground(new Color(255, 255, 190));
    NameTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    StdNoTF.setBackground(new Color(255, 255, 190));
    StdNoTF.setFont(new java.awt.Font("DialogInput", 0, 13));
    panel1.setMinimumSize(new Dimension(469, 292));
    jPanel1.add(GetPaperBtn,   new XYConstraints(278, 244, -1, 25));
    jPanel1.add(ClearBtn, new XYConstraints(63, 244, 84, 25));
    jPanel1.add(ImageLb, new XYConstraints(172, 9, 137, 31));
    jPanel1.add(NameTF, new XYConstraints(188, 142, 209, 25));
    jPanel1.add(jLabel1, new XYConstraints(107, 141, 58, 25));
    jPanel1.add(jLabel2, new XYConstraints(108, 182, 56, 25));
    jPanel1.add(StdNoTF, new XYConstraints(188, 182, 209, 25));
    jPanel1.add(PaintCB, new XYConstraints(198, 69, 78, 39));
    this.getContentPane().add(panel1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, -1, 0, 0), -3, 0));
    panel1.add(jPanel1, BorderLayout.CENTER);
  }

  void ClearBtn_actionPerformed(ActionEvent e) {
    NameTF.setText("");
    StdNoTF.setText("");
  }

  void GetPaperBtn_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  public String getStdName() {
    return NameTF.getText();
  }

  public String getStdNo() {
    return StdNoTF.getText();
  }

  public int getIconIndex() {
    return PaintCB.getSelectedIndex();
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

}