// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProblemArchiveBean.java
package cn.edu.dhu.acm.oj.common.problem;

import java.io.*;
import org.jdom.*;

// Referenced classes of package com.dyf:
//            NodeBean, ProblemBean, TestDataBean, SolutionBean
public final class ProblemArchiveBean extends NodeBean {

    public ProblemArchiveBean(Element x) {
        super(x);
    }

    public ProblemArchiveBean() {
        super("ProblemArchive");
        Document document = new Document(root);
        root.setAttribute("version", "1.1");
        setChecked(false);
        addNode(new NodeBean("Title"));
        addNode(new NodeBean("Author"));
        addNode(new NodeBean("JudgeType"));
        setJudgeType("Multiple Case");
        addNode(new ProblemBean());
        addNode(new TestDataBean());
        addNode(new SolutionBean());
    }

    public String getAuthor() {
        return root.getChildText("Author");
    }

    public String getJudgeType() {
        if (getVersion().equals("1.0") || root.getChildText("JudgeType").equals("")) {
            return "Multiple Case";
        } else {
            return root.getChildText("JudgeType");
        }
    }

    public ProblemBean getProblem() {
        return new ProblemBean(root.getChild("Problem"));
    }

    public SolutionBean getSolution() {
        return new SolutionBean(root.getChild("Solution"));
    }

    public String getTitle() {
        return root.getChildText("Title");
    }

    public String getVersion() {
        return root.getAttributeValue("version");
    }

    public boolean isChecked() {
        return root.getAttributeValue("checked").equals("true");
    }

    public void setAuthor(String str) {
        root.setChildText("Author", str);
    }

    public void setChecked(boolean flag) {
        root.setAttribute("checked", Boolean.toString(flag));
    }

    public void setJudgeType(String str) {
        if (getVersion().equals("1.0")) {
            root.setAttribute("version", "1.1");
            addNode(new NodeBean("JudgeType"));
        }
        root.setChildText("JudgeType", str);
    }

    public void setTitle(String str) {
        root.setChildText("Title", str);
    }

    public String transform()
            throws Exception {
        ProblemBean problem = getProblem();
        TestDataBean testData = getTestData();
        NodeBean sampleInput = new NodeBean("SampleInput", true);
        NodeBean sampleOutput = new NodeBean("SampleOutput", true);
        sampleInput.getElement().setText(testData.getSampleInput());
        sampleOutput.getElement().setText(testData.getSampleOutput());
        problem.addNode(sampleInput);
        problem.addNode(sampleOutput);
        java.io.InputStream stylesheet = new FileInputStream("./ProblemArchive.xsl");
                //(ProblemArchiveBean.class).getResourceAsStream("./ProblemArchive.xsl");
        String str = root.transform(stylesheet);
        sampleInput.getElement().detach();
        sampleOutput.getElement().detach();
        return str;
    }

    public void write(String filename)
            throws IOException {
        root.marshal(filename);
    }
    private static final String SOFTWARE_VERSION = "1.1";

    /**
     * Read a problem archive from a file.
     *
     * @param filename Path of the problem's XML file.
     *        A path name must be passed in, instead of a URI.
     *
     * @author Dong Yunfeng, Zhu Kai
     */
    public void read(String filename)
            throws JDOMException, IOException {
        File problemFile = new File(filename);
        if (!problemFile.isAbsolute()) {
            problemFile = problemFile.getAbsoluteFile();
        }

        this.archiveDirectory = problemFile.getParent();

        String fileURI = problemFile.toURI().toASCIIString();

        super.root = Element.unmarshal(fileURI);
    }

    /**
     * Get the test data object of this problem.
     *
     * @author Dong Yunfeng, Zhu Kai
     */
    public TestDataBean getTestData() {
        TestDataBean testData = new TestDataBean(super.root.getChild("TestData"));
        testData.setArchiveDirectory(this.archiveDirectory);
        return testData;
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
