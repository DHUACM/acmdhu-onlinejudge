package ProblemSetter;

import com.dyf.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>Title: ProblemSetter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Dong Yunfeng
 * @version 1.0
 */

public class Dialog1
        extends JDialog {

    ProblemArchiveBean problemArchive;

    JPanel panel1 = new JPanel();
    JButton jTest = new JButton();
    JButton jExit = new JButton();
    JProgressBar jTestProgress = new JProgressBar();
    JLabel jTestKey = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JLabel jLabel1 = new JLabel();
    JTextArea jTestResult = new JTextArea();

    public Dialog1(Frame1 frame, ProblemArchiveBean bean) {
        this(frame, "", true);
        this.problemArchive = bean;
    }

    public Dialog1(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        try {
            jbInit();
            pack();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Dialog1() {
        this(null, "", false);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.setModal(true);
        this.setResizable(false);
        this.setTitle("ProblemTester 2003");
        panel1.setPreferredSize(new Dimension(450, 350));
        jTest.setBounds(new Rectangle(99, 281, 83, 28));
        jTest.setText("Test");
        jTest.addActionListener(new Dialog1_jTest_actionAdapter(this));
        jExit.setBounds(new Rectangle(257, 281, 83, 28));
        jExit.setVerifyInputWhenFocusTarget(true);
        jExit.setText("Exit");
        jExit.addActionListener(new Dialog1_jExit_actionAdapter(this));
        jTestProgress.setBounds(new Rectangle(63, 68, 317, 14));
        jTestKey.setText("Test  Progress");
        jTestKey.setBounds(new Rectangle(64, 41, 317, 18));
        jScrollPane1.setBounds(new Rectangle(62, 129, 317, 108));
        jLabel1.setText("Test Result");
        jLabel1.setBounds(new Rectangle(63, 107, 129, 18));
        jTestResult.setEditable(false);
        getContentPane().add(panel1);
        panel1.add(jTestKey, null);
        panel1.add(jTestProgress, null);
        panel1.add(jLabel1, null);
        panel1.add(jScrollPane1, null);
        panel1.add(jExit, null);
        panel1.add(jTest, null);
        jScrollPane1.getViewport().add(jTestResult, null);
    }

    boolean isEmpty(String str) {
        return str.trim().length() == 0;
    }

    boolean testDocument() throws InterruptedException {
        ProblemBean problem = problemArchive.getProblem();
        TestDataBean testData = problemArchive.getTestData();
        SolutionBean solution = problemArchive.getSolution();

        int value = 0;

        jTestProgress.setValue(value);
        jTestResult.setText("");

        jTestKey.setText("Testing Author");
        for (; value <= 5; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (isEmpty(problemArchive.getAuthor())) {
            jTestResult.setText("<Author> can't be empty.");
            return false;
        }

        jTestKey.setText("Testing Problem Title");
        for (; value <= 10; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (isEmpty(problemArchive.getTitle())) {
            jTestResult.setText("<Title> can't be empty.");
            return false;
        }

        jTestKey.setText("Testing Time Limit");
        for (; value <= 20; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (testData.getTimeLimit() <= 0) {
            jTestResult.setText("<TimeLimit> must be a positive integer.");
            return false;
        }

        jTestKey.setText("Testing Description");
        for (; value <= 30; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (isEmpty(problem.getDescription())) {
            jTestResult.setText("<Description> can't be empty.");
            return false;
        }

        jTestKey.setText("Testing Input Specification");
        for (; value <= 35; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (isEmpty(problem.getInputSpec())) {
            jTestResult.setText("<InputSpec> can't be empty.");
            return false;
        }

        jTestKey.setText("Testing Output Specification");
        for (; value <= 40; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (isEmpty(problem.getOutputSpec())) {
            jTestResult.setText("<OutputSpec> can't be empty.");
            return false;
        }

        jTestKey.setText("Testing Sample Input and Output");
        for (; value <= 45; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (testData.getTestCaseCount("sample") == 0) {
            jTestResult.setText("There ought to be at least one sample data.");
            return false;
        }

        jTestKey.setText("Testing Test Data");
        for (; value <= 60; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (testData.getTestCaseCount() < 5) {
            jTestResult.setText("The number of test data can't be less than 5.");
            return false;
        }

        for (; value <= 65; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        InputFormatBean inputFormat = testData.getInputFormat();
        if (inputFormat.getInputType().equals("zero") &&
            inputFormat.getTerminator().length() == 0) {

            jTestResult.setText("<Terminator> can't be empty.");
            return false;
        }

        jTestKey.setText("Testing Special Test Data");
        for (; value <= 70; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        if (testData.getTestCaseCount("special") == 0) {
            jTestResult.setText("There ought to be at least one special data.");
            return false;
        }

        jTestKey.setText("Runing Solution");
        com.sjn.JudgeBean judge = new com.sjn.JudgeBean();
        judge.setEnvironmentBean(new com.lrf.EnvironmentBean("./Environment.xml"));
        judge.setSolutionBean(problemArchive.getSolution());
        judge.setTestDataBean(problemArchive.getTestData());
        judge.judgeCheck();

        for (; value <= 100; value++) {
            Thread.sleep(20);
            jTestProgress.setValue(value);
        }
        com.sjn.ResultBean result = judge.getResultBean();
        if (!result.getCheckResult()) {
            jTestResult.setText(result.getCheckInfo());
            return false;
        }

        jTestKey.setText("Test Completed");
        Thread.sleep(100);
        jTestProgress.setValue(100);
        jTestResult.append("OK!");

        return true;
    }

    void jTest_actionPerformed(ActionEvent e) {
        try {
            new Thread() {
                public void run() {
                    try {
                        jExit.setEnabled(false);

                        boolean result = testDocument();
                        problemArchive.setChecked(result);

                        jExit.setEnabled(true);
                        } catch (Exception ex) { }
                }
                }.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jExit_actionPerformed(ActionEvent e) {
        dispose();
    }
}

class Dialog1_jTest_actionAdapter
        implements java.awt.event.ActionListener {
    Dialog1 adaptee;

    Dialog1_jTest_actionAdapter(Dialog1 adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jTest_actionPerformed(e);
    }
}

class Dialog1_jExit_actionAdapter
        implements java.awt.event.ActionListener {
    Dialog1 adaptee;

    Dialog1_jExit_actionAdapter(Dialog1 adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jExit_actionPerformed(e);
    }
}