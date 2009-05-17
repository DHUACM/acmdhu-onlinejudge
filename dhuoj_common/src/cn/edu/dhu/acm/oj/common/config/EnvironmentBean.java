package cn.edu.dhu.acm.oj.common.config;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

public class EnvironmentBean {

    private Element EnvironmentRoot;

    public EnvironmentBean(String filepath) {
        try {
            this.EnvironmentRoot = Element.unmarshal(filepath);
        } catch (Exception e) {
            System.err.println(e.toString());
            System.err.println("*******************************");
            System.err.println("* environment file open error *");
            System.err.println("*******************************");
        }
    }

    public String getPaper() {
        return this.EnvironmentRoot.getChildText("Paper");
    }

    public String getHistory() {
        return this.EnvironmentRoot.getChildText("History");
    }

    public String getImageDir() {
        return this.EnvironmentRoot.getChildText("Image");
    }

    public String getPaperName() {
        return this.EnvironmentRoot.getChildText("PaperName");
    }

    public String getSourceCodeTemp() {
        return this.EnvironmentRoot.getChildText("SourceCodeTemp");
    }

    public String getJavaProblemNotice() {
        return this.EnvironmentRoot.getChildText("JavaProblemNotice");
    }

    public String getSource() {
        return this.EnvironmentRoot.getChild("Run").getChildText("Source");
    }

    public String getTarget() {
        return this.EnvironmentRoot.getChild("Run").getChildText("Target");
    }

    public Element getCompilerNode(String compilerName) {
        Element x;
        Iterator it = this.EnvironmentRoot.getChildren("Compiler").iterator();
        do {
            if (!(it.hasNext())) {
                return null;
            }
            x = (Element) it.next();
        } while (x.getChildText("Language").compareToIgnoreCase(compilerName) != 0);
        return x;

    }

    public List getUsage(String compilerName) {
        return getCompilerNode(compilerName).getChild("Usage").getContent();
    }

    public String getCmd(String compilerName) {
        return getCompilerNode(compilerName).getChildText("Cmd");
    }

    public String getPath(String compilerName) {
        return getCompilerNode(compilerName).getChildText("Path");
    }

    public String getVersion(String compilerName) {
        return getCompilerNode(compilerName).getChildText("Version");
    }

    public String getName(String compilerName) {
        return getCompilerNode(compilerName).getChildText("Name");
    }

    public String getFormerSuffix(String compilerName) {
        return getCompilerNode(compilerName).getChildText("FormerSuffix");
    }

    public String getLatterSuffix(String compilerName) {
        return getCompilerNode(compilerName).getChildText("LatterSuffix");
    }

    public String getErrorType(String compilerName) {
        return getCompilerNode(compilerName).getChildText("ErrorType");
    }

    public List getCommand(String compilerName) {
        return getCompilerNode(compilerName).getChild("Command").getContent();
    }

    public boolean isChecked() {
        return (this.EnvironmentRoot.getChildText("IsChecked").toString().compareToIgnoreCase("True") == 0);
    }

    public void setIsChecked(String check) {
        Element element = this.EnvironmentRoot.getChild("IsChecked");
        element.setText("");
        element.addContent(check);
    }

    public void setPath(String compilerName, String path) {
        getCompilerNode(compilerName).setChildText("Path", path);
    }

    public void setSourceCodeTemp(String path) {
        this.EnvironmentRoot.setChildText("SourceCodeTemp", path);
    }

    public void toFile(String filepath) throws IOException {
        this.EnvironmentRoot.marshal(filepath);
    }

    public String getSearchPath(int index) {
        return this.EnvironmentRoot.getChild("SearchPath").getChild("Spath", index).getText();
    }

    public int getSearchPathCount() {
        return this.EnvironmentRoot.getChild("SearchPath").getChildrenCount("Spath");
    }

    public String getCompileCmd(String lan, String file, String exe) {
        List l = getUsage(lan);
        String commandline = "";
        for (int i = 1; i < l.size() - 1; i++) {
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Path/>]") == 0) {
                commandline += getPath(lan);
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Cmd/>]") == 0) {
                commandline += getCmd(lan);
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Source/>]") == 0) {
                commandline += getSource();
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <SName/>]") == 0) {
                commandline += file;
                commandline += getFormerSuffix(lan);
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Target/>]") == 0) {
                commandline += getTarget();
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <TName/>]") == 0) {
                commandline += exe;
                commandline += getLatterSuffix(lan);
            } else {
                String temp = l.get(i).toString();
                int j = temp.length();
                temp = temp.substring(7, j - 1);
                commandline += temp;
            }
        }
        return commandline;
    }

    public String getRunCmd(String lan, String runName) {
        String commandline;
        List l = getCommand(lan);
        commandline = "";
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Target/>]") == 0) {
                commandline += getTarget();
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <TName/>]") == 0) {
                commandline += runName;
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Path/>]") == 0) {
                commandline += getPath(lan);
            } else {
                String temp = l.get(i).toString();
                int j = temp.length();
                temp = temp.substring(7, j - 1);
                commandline += temp;
            }
        }
        return commandline;
    }
}