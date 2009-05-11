package contestjudgeterminal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * <p>Title: ContestJudgeTerminal</p>
 * <p>Description: ContestJudgeTerminal</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: DHU</p>
 * @author DHU
 * @version 1.0
 */

public class MainFrame_AboutBox extends JDialog implements ActionListener {

  JPanel panel1 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageLabel = new JLabel();
  ImageIcon image1 = new ImageIcon();
  ImageIcon image2 = new ImageIcon();
  ImageIcon image3 = new ImageIcon();
//  ImageIcon image = new ImageIcon((MainFrame.class.getResource(".png")));
  String product = "ContestJudgeTerminal";
  String version = "1.0";
  String copyright = "Copyright (c) 2003";
  String comments = "ContestJudgeTerminal";
  JLabel labelCopyRight = new JLabel();
  public MainFrame_AboutBox(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image2 = new ImageIcon((contestjudgeterminal.MainFrame.class.getResource("ok.png")));
    image3 = new ImageIcon((contestjudgeterminal.MainFrame.class.getResource("copyright.png")));
    image1 = new ImageIcon(contestjudgeterminal.MainFrame.class.getResource("car.GIF"));
    imageLabel.setBackground(new Color(203, 203, 203));
    imageLabel.setIcon(image1);
    imageLabel.setBounds(new Rectangle(17, 15, 180, 121));
    this.getContentPane().setBackground(new Color(200, 200, 235));
    this.setTitle("About");
    panel1.setLayout(null);
    button1.setBackground(Color.white);
    button1.setBounds(new Rectangle(180, 142, 54, 32));
    button1.setFont(new java.awt.Font("Serif", 1, 15));
    button1.setForeground(new Color(150, 150, 255));
    button1.setBorder(BorderFactory.createLineBorder(Color.black));
    button1.setBorderPainted(false);
    button1.setText("");
    button1.setIcon(image2);
    button1.addActionListener(this);
    labelCopyRight.setFont(new java.awt.Font("Serif", 1, 15));
    labelCopyRight.setForeground(new Color(150, 150, 255));
    labelCopyRight.setText("");
    labelCopyRight.setIcon(image3);
    labelCopyRight.setBounds(new Rectangle(196, 22, 238, 107));
    panel1.setBackground(new Color(200, 200, 235));
    panel1.setMaximumSize(new Dimension(430, 190));
    panel1.setMinimumSize(new Dimension(450, 200));
    panel1.setPreferredSize(new Dimension(450, 200));
    this.getContentPane().add(panel1, null);
    panel1.add(button1, null);
    panel1.add(imageLabel, null);
    panel1.add(labelCopyRight, null);
    setResizable(true);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  //Close the dialog
  void cancel() {
    dispose();
  }
  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}