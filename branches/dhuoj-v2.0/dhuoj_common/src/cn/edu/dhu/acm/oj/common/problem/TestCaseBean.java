// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TestCaseBean.java
package cn.edu.dhu.acm.oj.common.problem;

import org.jdom.Element;
import java.io.*;

// Referenced classes of package com.dyf:
//            NodeBean
public final class TestCaseBean extends NodeBean {

    public TestCaseBean(Element x) {
        super(x);
    }

    public TestCaseBean() {
        super("TestCase");
        setDifficulty("trivial");
        addNode(new NodeBean("TestInput", true));
        addNode(new NodeBean("TestOutput", true));
    }

    public String getDifficulty() {
        return root.getAttributeValue("difficulty");
    }

    //Old version, useless now.
    //public String getTestInput()
    //{
    //    return root.getChildText("TestInput");
    //}

    //Old version, useless now.
    //public String getTestOutput()
    //{
    //    return root.getChildText("TestOutput");
    //}
    public void setDifficulty(String str) {
        root.setAttribute("difficulty", str);
    }

    public void setTestInput(String str) {
        if (!str.endsWith("\n")) {
            str = str + '\n';
        }
        root.setChildText("TestInput", str);
    }

    public void setTestOutput(String str) {
        if (!str.endsWith("\n")) {
            str = str + '\n';
        }
        root.setChildText("TestOutput", str);
    }

    /**
     * Get the input data of this test case.
     *
     * @return The input data of this test case. Empty string is returned
     *         if any error happens during the file reading.
     *         If no filename is set, the XML element text is returned.
     *
     * @author Zhu Kai
     */
    public String getTestInput() {
        String filename = super.root.getChild("TestInput").getAttributeValue("filename");
        if (null == filename) {
            return super.root.getChildText("TestInput");
        }

        File inFile = new File(filename);
        if (!inFile.isAbsolute()) {
            inFile = new File(this.archiveDirectory, filename);
        }

        String fileContent = "";//The string to return.

        try {
            RandomAccessFile raf = new RandomAccessFile(inFile, "r");
            byte[] fileBytes = new byte[(int) raf.length()];
            raf.read(fileBytes);

            //Assuming there is only ASCII text in the file.
            fileContent = new String(fileBytes);

            raf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        //Do nothing here, the string fileContent keeps empty.
        }

        return fileContent;
    }

    /**
     * Set the filename of the input data.
     * After setting this, the text in the {@code TestInput} element will
     * be ignored.
     *
     * @param filename The filename to be set.
     *        To remove the filename , give {@code null} or a empty string.
     *
     * @author Zhu Kai
     */
    public void setTestInputFilename(String filename) {
        if (null == filename || filename.isEmpty()) {
            super.root.removeAttribute("filename");
        } else {
            super.root.getChild("TestInput").setAttribute("filename", filename);
        }
    }

    /**
     * Get the output data of this test case.
     *
     * @return The output data of this test case. Empty string is returned
     *         if any error happens during the file reading.
     *         If no filename is set, the XML element text is returned.
     *
     * @author Zhu Kai
     */
    public String getTestOutput() {
        String filename = super.root.getChild("TestOutput").getAttributeValue("filename");
        if (null == filename) {
            return super.root.getChildText("TestOutput");
        }

        File outFile = new File(filename);
        if (!outFile.isAbsolute()) {
            outFile = new File(this.archiveDirectory, filename);
        }

        String fileContent = "";//The string to return.

        try {
            RandomAccessFile raf = new RandomAccessFile(outFile, "r");
            byte[] fileBytes = new byte[(int) raf.length()];
            raf.read(fileBytes);

            //Assuming there is only ASCII text in the file.
            fileContent = new String(fileBytes);
            raf.close();
        } catch (IOException ex) {
            //Do nothing here, the string fileContent keeps empty.
        }

        return fileContent;
    }

    /**
     * Set the filename of the output data.
     * After setting this, the text in the {@code TestOutput} element will
     * be ignored.
     *
     * @param filename The filename to be set.
     *        To remove the filename , give {@code null} or a empty string.
     *
     * @author Zhu Kai
     */
    public void setTestOutputFilename(String filename) {
        if (null == filename || filename.isEmpty()) {
            super.root.removeAttribute("filename");
        } else {
            super.root.getChild("TestOutput").setAttribute("filename", filename);
        }
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
