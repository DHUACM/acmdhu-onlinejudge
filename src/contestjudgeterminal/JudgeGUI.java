package contestjudgeterminal;

import javax.swing.UIManager;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;

/**
 * <p>Title: ContestJudgeTerminal</p>
 * <p>Description: ContestJudgeTerminal</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: DHU</p>
 * @author DHU
 * @version 1.0
 */

public class JudgeGUI {
  boolean packFrame = false;

  //Construct the application
  public JudgeGUI() {
    MainFrame frame = new MainFrame();
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
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxx
    frame.setSize(800,570);
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxx
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }
  //Main method
  public static void main(String[] args) {
    try {
      JDialog.setDefaultLookAndFeelDecorated(true);
      JFrame.setDefaultLookAndFeelDecorated(true);

      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new JudgeGUI();
    JOptionPane.showMessageDialog(null,"请先设置server IP 和 primeserver IP\n 按[Apply]应用");
  }
}