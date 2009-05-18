// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TestDataBean.java
package cn.edu.dhu.acm.oj.common.problem;

import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean, InputFormatBean, OutputFormatBean, TestCaseBean, 
//            SeperatorBean
public final class TestDataBean extends NodeBean {

    public TestDataBean(Element x) {
        super(x);
    }

    public TestDataBean() {
        super("TestData");
        addNode(new NodeBean("TimeLimit"));
        addNode(new NodeBean("InputFile"));
        addNode(new NodeBean("OutputFile"));
        addNode(new InputFormatBean());
        addNode(new OutputFormatBean());
    }

    public void addTestCase(TestCaseBean bean) {
        addNode(bean);
    }

    public String getInputFile() {
        return root.getChildText("InputFile");
    }

    public InputFormatBean getInputFormat() {
        return new InputFormatBean(root.getChild("InputFormat"));
    }

    public String getOutputFile() {
        return root.getChildText("OutputFile");
    }

    public OutputFormatBean getOutputFormat() {
        return new OutputFormatBean(root.getChild("OutputFormat"));
    }

    public String getSampleInput() {
        return getTestInput(0, getTestCaseCount() - 1, "sample");
    }

    public String getSampleOutput() {
        return getTestOutput(0, getTestCaseCount() - 1, "sample");
    }

    public int getTestCaseCount(String difficulty) {
        int count = 0;
        for (Iterator it = getTestCaseList().iterator(); it.hasNext();) {
            TestCaseBean bean = new TestCaseBean((Element) it.next());
            if (bean.getDifficulty().equals(difficulty)) {
                count++;
            }
        }

        return count;
    }

    public int getTestCaseCount() {
        return root.getChildrenCount("TestCase");
    }

    public List getTestCaseList() {
        return root.getChildren("TestCase");
    }

    public String getTestInput(int index) {
        return getTestInput(index, index, null);
    }

    public String getTestInput() {
        return getTestInput(0, getTestCaseCount() - 1, null);
    }

    protected String getTestInput(int first, int last, String difficulty) {
        StringBuffer buff = new StringBuffer();
        InputFormatBean inputFormat = getInputFormat();
        SeperatorBean seperator = inputFormat.getSeperator();
        String inner = seperator.getText();
        String layout = seperator.getLayout();
        int count = 0;
        for (int i = first; i <= last; i++) {
            TestCaseBean testCase = getTestCase(i);
            if (difficulty == null || testCase.getDifficulty().equals(difficulty)) {
                if (count == 0 && (layout.equals("bottom") || layout.equals("middle"))) {
                    buff.append(testCase.getTestInput());
                    count++;
                } else {
                    buff.append(inner);
                    buff.append(testCase.getTestInput());
                    count++;
                }
            }
        }

        if (count > 0 && (layout.equals("bottom") || layout.equals("both"))) {
            buff.append(inner);
        }
        String inputType = inputFormat.getInputType();
        if (inputType.equals("num")) {
            buff.insert(0, Integer.toString(count) + '\n');
        }
        if (inputType.equals("zero")) {
            buff.append(inputFormat.getTerminator());
        }
        return buff.toString();
    }

    public String getTestOutput(int index) {
        return getTestOutput(index, index, null);
    }

    public String getTestOutput() {
        return getTestOutput(0, getTestCaseCount() - 1, null);
    }

    protected String getTestOutput(int first, int last, String difficulty) {
        StringBuffer buff = new StringBuffer();
        OutputFormatBean outputFormat = getOutputFormat();
        SeperatorBean seperator = outputFormat.getSeperator();
        String inner = seperator.getText();
        String layout = seperator.getLayout();
        int count = 0;
        for (int i = first; i <= last; i++) {
            TestCaseBean testCase = getTestCase(i);
            if (difficulty == null || testCase.getDifficulty().equals(difficulty)) {
                if (count == 0 && (layout.equals("bottom") || layout.equals("middle"))) {
                    buff.append(testCase.getTestOutput());
                    count++;
                } else {
                    buff.append(inner);
                    buff.append(testCase.getTestOutput());
                    count++;
                }
            }
        }

        if (count > 0 && (layout.equals("bottom") || layout.equals("both"))) {
            buff.append(inner);
        }
        buff.insert(0, outputFormat.getHeader());
        buff.append(outputFormat.getFooter());
        return buff.toString();
    }

    public int getTimeLimit() {
        String str = root.getChildText("TimeLimit");

        int n = 0;
        try {
            n = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }

        return n;
    }

    public void removeTestCase(int index) {
        root.removeChild("TestCase", index);
    }

    public void setInputFile(String str) {
        root.setChildText("InputFile", str);
    }

    public void setOutputFile(String str) {
        root.setChildText("OutputFile", str);
    }

    public void setTimeLimit(int n) {
        String str = Integer.toString(n);
        root.setChildText("TimeLimit", str);
    }


    /**
     * Get the input data of all test cases,
     * each as a complete single-case input data.
     *
     * @return Array of input data of all the test cases.
     *
     * @author Zhu Kai
     */
    public String[] getSingleCaseInputs() {
        InputFormatBean inputFormat = getInputFormat();
        SeperatorBean seperator = inputFormat.getSeperator();
        String inner = seperator.getText();
        String layout = seperator.getLayout();

        String terminator = inputFormat.getTerminator();

        String inputType = inputFormat.getInputType();

        List testCaseList = getTestCaseList();
        String[] result = new String[ testCaseList.size() ];

        String lineBreak = System.getProperty("line.separator");

        int i = 0;
        Iterator it = testCaseList.iterator();
        while ( it.hasNext() ) {
            TestCaseBean testCase = new TestCaseBean( (Element)( it.next() ) );
            testCase.setArchiveDirectory(this.archiveDirectory);

            StringBuffer strBuf = new StringBuffer();

            if ( inputType.equals("num") )
                strBuf.append("1" + lineBreak);
            
            if ( layout.equals("top") || layout.equals("both") )
                strBuf.append(inner);

            strBuf.append( testCase.getTestOutput() );

            if ( layout.equals("bottom") || layout.equals("both") )
                strBuf.append(inner);

            if (inputType.equals("zero"))
                strBuf.append(terminator);

            result[i] = strBuf.toString();

            i++;
        }

        return result;
    }

    /**
     * Get the output data of all test cases,
     * each as a complete single-case output data.
     *
     * @return Array of output data of all the test cases.
     *
     * @author Zhu Kai
     */
    public String[] getSingleCaseOutputs() {
        OutputFormatBean outputFormat = getOutputFormat();
        String header = outputFormat.getHeader();
        String footer = outputFormat.getFooter();

        SeperatorBean seperator = outputFormat.getSeperator();
        String inner = seperator.getText();
        String layout = seperator.getLayout();

        List testCaseList = getTestCaseList();
        String[] result = new String[ testCaseList.size() ];
        
        int i = 0;
        Iterator it = testCaseList.iterator();
        while ( it.hasNext() ) {
            TestCaseBean testCase = new TestCaseBean( (Element)( it.next() ) );
            testCase.setArchiveDirectory(this.archiveDirectory);

            StringBuffer strBuf = new StringBuffer();

            strBuf.append(header);

            if ( layout.equals("top") || layout.equals("both") )
                strBuf.append(inner);

            strBuf.append( testCase.getTestOutput() );

            if ( layout.equals("bottom") || layout.equals("both") )
                strBuf.append(inner);

            strBuf.append(footer);

            result[i] = strBuf.toString();

            i++;
        }

        return result;
    }

    /**
     * Get the test case at index i.
     *
     * @author Dong Yunfeng, Zhu Kai
     */
    public TestCaseBean getTestCase(int i) {
        Element element = super.root.getChild("TestCase", i);
        TestCaseBean testcase = new TestCaseBean(element);
        testcase.setArchiveDirectory(this.archiveDirectory);
        return testcase;
    }

    /**
     * Return the directory of the problem's XML file.
     *
     * @author Zhu Kai.
     */
    public String getArchiveDirectory() {
        return this.archiveDirectory;
    }

    /**
     * Set the directory of the problem's XML file.
     *
     * @param dir Path of the directory to be set.
     *
     * @author Zhu Kai.
     */
    public void setArchiveDirectory(String dir) {
        this.archiveDirectory = dir;
    }
    /**
     * Directory where the problem's XML file is.
     *
     * @author Zhu Kai.
     */
    private String archiveDirectory;
}
