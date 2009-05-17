// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   SingleAnswerDoc.java
package judge;

import org.jdom.Document;
import org.jdom.Element;

public class SingleAnswerDoc {

    public SingleAnswerDoc() {
        root = new Element("SingleAnswer");
        root.addContent(new Element("PersonalInformation"));
        root.addContent(new Element("SolutionInformation"));
        root.addContent(new Element("SubmitInformation"));
        setElement("PersonalInformation", "Name");
        setElement("PersonalInformation", "StudentNo");
        setElement("PersonalInformation", "TestID");
        setElement("PersonalInformation", "IPAddress");
        setElement("PersonalInformation", "Class");
        setElement("PersonalInformation", "ComputerNo");
        setElement("SolutionInformation", "Filename");
        setElement("SolutionInformation", "Language");
        setElement("SolutionInformation", "SourceCode");
        setElement("SolutionInformation", "ProblemID");
        setElement("SubmitInformation", "Time");
        setElement("SubmitInformation", "CheckPass");
        doc = new Document(root);
    }

    public SingleAnswerDoc(Document d) {
        doc = d;
        root = doc.getRootElement();
    }

    private void setElement(String e1, String e2) {
        Element element = root.getChild(e1);
        element.addContent(new Element(e2));
    }

    public String getName() {
        return root.getChild("PersonalInformation").getChildText("Name");
    }

    public String getStdNo() {
        return root.getChild("PersonalInformation").getChildText("StudentNo");
    }

    public String getTestID() {
        return root.getChild("PersonalInformation").getChildText("TestID");
    }

    public String getIPAddress() {
        return root.getChild("PersonalInformation").getChildText("IPAddress");
    }

    public String getStdClass() {
        return root.getChild("PersonalInformation").getChildText("Class");
    }

    public String getComputerNo() {
        return root.getChild("PersonalInformation").getChildText("ComputerNo");
    }

    public String getFilename() {
        return root.getChild("SolutionInformation").getChildText("Filename");
    }

    public String getLanguage() {
        return root.getChild("SolutionInformation").getChildText("Language");
    }

    public String getSourceCode() {
        return root.getChild("SolutionInformation").getChildText("SourceCode");
    }

    public String getProblemID() {
        return root.getChild("SolutionInformation").getChildText("ProblemID");
    }

    public String getSubmitTime() {
        return root.getChild("SubmitInformation").getChildText("Time");
    }

    public boolean getCheckPass() {
        String s = root.getChild("SubmitInformation").getChildText("CheckPass");
        return s.equals("true");
    }

    public Document getDocument() {
        return doc;
    }

    public void setName(String name) {
        root.getChild("PersonalInformation").setChildText("Name", name);
    }

    public void setStdNo(String stdno) {
        root.getChild("PersonalInformation").setChildText("StudentNo", stdno);
    }

    public void setTestID(String testid) {
        root.getChild("PersonalInformation").setChildText("TestID", testid);
    }

    public void setIPAddress(String ip) {
        root.getChild("PersonalInformation").setChildText("IPAddress", ip);
    }

    public void setStdClass(String cla) {
        root.getChild("PersonalInformation").setChildText("Class", cla);
    }

    public void setComputerNo(String cno) {
        root.getChild("PersonalInformation").setChildText("ComputeNo", cno);
    }

    public void setFilename(String filename) {
        root.getChild("SolutionInformation").setChildText("Filename", filename);
    }

    public void setLanguage(String language) {
        root.getChild("SolutionInformation").setChildText("Language", language);
    }

    public void setSourceCode(String code) {
        root.getChild("SolutionInformation").setChildText("SourceCode", code);
    }

    public void setProblemID(String pid) {
        root.getChild("SolutionInformation").setChildText("ProblemID", pid);
    }

    public void setSubmitTime(String time) {
        root.getChild("SubmitInformation").setChildText("Time", time);
    }

    public void setCheckPass(String isPass) {
        root.getChild("SubmitInformation").setChildText("CheckPass", isPass);
    }

    public void setDocument(Document dc) {
        doc = dc;
    }
    private Document doc;
    private Element root;
}
