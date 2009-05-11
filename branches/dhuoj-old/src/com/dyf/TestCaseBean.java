package com.dyf;

import org.jdom.*;
import java.util.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class TestCaseBean
        extends NodeBean {

    public TestCaseBean() {
        super("TestCase");

        setDifficulty("trivial");
        addNode(new NodeBean("TestInput", true));
        addNode(new NodeBean("TestOutput", true));
    }

    public TestCaseBean(Element x) {
        super(x);
    }

    public String getDifficulty() {
        return root.getAttributeValue("difficulty");
    }

    public void setDifficulty(String str) {
        root.setAttribute("difficulty", str);
    }

    public String getTestInput() {
        return root.getChildText("TestInput");
    }

    public void setTestInput(String str) {
        if (!str.endsWith("\n"))
            str = str + '\n';
        root.setChildText("TestInput", str);
    }

    public String getTestOutput() {
        return root.getChildText("TestOutput");
    }

    public void setTestOutput(String str) {
        if (!str.endsWith("\n"))
            str = str + '\n';
        root.setChildText("TestOutput", str);
    }
}