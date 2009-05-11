package com.dyf;

import java.util.*;
import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class TestDataBean
        extends NodeBean {

    public TestDataBean() {
        super("TestData");

        addNode(new NodeBean("TimeLimit"));
        addNode(new NodeBean("InputFile"));
        addNode(new NodeBean("OutputFile"));

        addNode(new InputFormatBean());
        addNode(new OutputFormatBean());
    }

    public TestDataBean(Element x) {
        super(x);
    }

    public int getTimeLimit() {
        String str = root.getChildText("TimeLimit");

        int  n = 0;
        try {
            n = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {}

        return n;
    }

    public void setTimeLimit(int n) {
        String str = Integer.toString(n);
        root.setChildText("TimeLimit", str);
    }

    public String getInputFile() {
        return root.getChildText("InputFile");
    }

    public void setInputFile(String str) {
        root.setChildText("InputFile", str);
    }

    public String getOutputFile() {
        return root.getChildText("OutputFile");
    }

    public void setOutputFile(String str) {
        root.setChildText("OutputFile", str);
    }

    public InputFormatBean getInputFormat() {
        return new InputFormatBean(root.getChild("InputFormat"));
    }

    public OutputFormatBean getOutputFormat() {
        return new OutputFormatBean(root.getChild("OutputFormat"));
    }

    public List getTestCaseList() {
        return root.getChildren("TestCase");
    }

    public int getTestCaseCount() {
        return root.getChildrenCount("TestCase");
    }

    public int getTestCaseCount(String difficulty) {
        int count = 0;
        Iterator it = getTestCaseList().iterator();

        while (it.hasNext()) {
            TestCaseBean bean = new TestCaseBean((Element) it.next());
            if (bean.getDifficulty().equals(difficulty))
                count++;
        }

        return count;
    }

    public TestCaseBean getTestCase(int index) {
        Element elem =  root.getChild("TestCase", index);
        return new TestCaseBean(elem);
    }

    public void addTestCase(TestCaseBean bean) {
        addNode(bean);
    }

    public void removeTestCase(int index) {
        root.removeChild("TestCase", index);
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
            if (difficulty != null &&
                !testCase.getDifficulty().equals(difficulty))
                continue;

            if (count == 0 && (layout.equals("bottom") || layout.equals("middle"))) {
                buff.append(testCase.getTestInput());
                count++;
            }
            else {
                buff.append(inner);
                buff.append(testCase.getTestInput());
                count++;
            }
        }
        if (count > 0 && (layout.equals("bottom") || layout.equals("both")))
            buff.append(inner);

        String inputType = inputFormat.getInputType();
        if (inputType.equals("num"))
            buff.insert(0, Integer.toString(count) + '\n');
        if (inputType.equals("zero"))
            buff.append(inputFormat.getTerminator());

        return buff.toString();
    }

    public String getSampleInput() {
        return getTestInput(0, getTestCaseCount() - 1, "sample");
    }

    public String getTestInput() {
        return getTestInput(0, getTestCaseCount() - 1, null);
    }

    public String getTestInput(int index) {
        return getTestInput(index, index, null);
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
            if (difficulty != null &&
                !testCase.getDifficulty().equals(difficulty))
                continue;

            if (count == 0 && (layout.equals("bottom") || layout.equals("middle"))) {
                buff.append(testCase.getTestOutput());
                count++;
            }
            else {
                buff.append(inner);
                buff.append(testCase.getTestOutput());
                count++;
            }
        }
        if (count > 0 && (layout.equals("bottom") || layout.equals("both")))
            buff.append(inner);

        buff.insert(0, outputFormat.getHeader());
        buff.append(outputFormat.getFooter());

        return buff.toString();
    }

    public String getSampleOutput() {
        return getTestOutput(0, getTestCaseCount() - 1, "sample");
    }

    public String getTestOutput() {
        return getTestOutput(0, getTestCaseCount() - 1, null);
    }

    public String getTestOutput(int index) {
        return getTestOutput(index, index, null);
    }
}