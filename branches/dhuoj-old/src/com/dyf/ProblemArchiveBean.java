package com.dyf;

import java.io.*;
import org.jdom.*;

/*
* @author  Dong Yunfeng
* @version 1.0, 5/10/2003
*
 */

public final class ProblemArchiveBean
        extends NodeBean {

    private final static String SOFTWARE_VERSION = "1.0";

    public ProblemArchiveBean() {
        super("ProblemArchive");
        Document document = new Document(root);

        root.setAttribute("version", SOFTWARE_VERSION);
        setChecked(false);

        addNode(new NodeBean("Title"));
        addNode(new NodeBean("Author"));
        addNode(new ProblemBean());
        addNode(new TestDataBean());
        addNode(new SolutionBean());
    }

    public ProblemArchiveBean(Element x) {
        super(x);
    }

    public void read(String filename) throws IOException, JDOMException {
        root = Element.unmarshal(filename);
    }

    public void write(String filename) throws IOException {
        root.marshal(filename);
    }

    public String getVersion() {
        return root.getAttributeValue("version");
    }

    public boolean isChecked() {
        return root.getAttributeValue("checked").equals("true");
    }

    public void setChecked(boolean flag) {
        root.setAttribute("checked", Boolean.toString(flag));
    }

    public String transform() throws Exception {
        ProblemBean problem = getProblem();
        TestDataBean testData = getTestData();

        NodeBean sampleInput = new NodeBean("SampleInput", true);
        NodeBean sampleOutput = new NodeBean("SampleOutput", true);
        sampleInput.getElement().setText(testData.getSampleInput());
        sampleOutput.getElement().setText(testData.getSampleOutput());

        problem.addNode(sampleInput);
        problem.addNode(sampleOutput);

        InputStream stylesheet =
                ProblemArchiveBean.class.getResourceAsStream("ProblemArchive.xsl");
        String str = root.transform(stylesheet);

        sampleInput.getElement().detach();
        sampleOutput.getElement().detach();

        return str;
    }

    public String getTitle() {
        return root.getChildText("Title");
    }

    public void setTitle(String str) {
        root.setChildText("Title", str);
    }

    public String getAuthor() {
        return root.getChildText("Author");
    }

    public void setAuthor(String str) {
        root.setChildText("Author", str);
    }

    public ProblemBean getProblem() {
        return new ProblemBean(root.getChild("Problem"));
    }

    public TestDataBean getTestData() {
        return new TestDataBean(root.getChild("TestData"));
    }

    public SolutionBean getSolution() {
        return new SolutionBean(root.getChild("Solution"));
    }
}