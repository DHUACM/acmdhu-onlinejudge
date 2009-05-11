package student;

import javax.swing.UIManager;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;




import com.lrf.*;
/**
 * <p>Title: Student</p>
 * <p>Description: 1.0</p>
 * <p>Copyright: RabbitHunter Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


public class App {

  private EnvironmentBean eb = new EnvironmentBean("./Environment.xml");
  private JDialog dlg;
  private JFrame frame;
  private boolean packFrame = false;

  //Construct the application
  public App() {

    if ( eb.isChecked() ) {
      frame = new Student(eb);
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
    else {
      dlg = new EnvironmentDlg(false,eb);
      dlg.setTitle("Set the Path of the Compiler !");


      //Validate frames that have preset sizes
      //Pack frames that have useful preferred size info, e.g. from their layout
      if (packFrame) {
        dlg.pack();
      }
      else {
        dlg.validate();
      }
      //Center the window
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
    }
  }
  //Main method
  public static void main(String[] args) {

/*

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }

*/

///*
    try {
      JDialog.setDefaultLookAndFeelDecorated(true);
      JFrame.setDefaultLookAndFeelDecorated(true);
//    Toolkit.getDefaultToolkit().setDynamicLayout(true);
//    System.setProperty("sun.awt.noerasebackground","true");

      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    }
    catch ( Exception e ) {
        System.out.println ("Unexpected error. \nProgram Terminated");
        e.printStackTrace();
        System.exit(0);
    }
//*/
    new App();
  }





}




