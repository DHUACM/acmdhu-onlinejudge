package com.dyf;

import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class SolutionBean
        extends NodeBean {

    public SolutionBean() {
        super("Solution", true);

        setLanguage("cpp");
        setFilename("");
    }

    public SolutionBean(Element x) {
        super(x);
    }

    public String getSourceCode() {
        return root.getText();
    }

    public void setSourceCode(String str) {
        root.setText(str);
    }

    public String getLanguage() {
        return root.getAttributeValue("language");
    }

    public void setLanguage(String str) {
        root.setAttribute("language", str);
    }

    public String getFilename() {
        return root.getAttributeValue("filename");
    }

    public void setFilename(String str) {
        root.setAttribute("filename", str);
    }
}