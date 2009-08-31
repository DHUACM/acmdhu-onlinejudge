// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package cn.edu.dhu.acm.oj.common.problem;

import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean
public final class SolutionBean extends NodeBean {

    public SolutionBean() {
        super("Solution", true);
        setLanguage("cpp");
        setFilename("");
    }

    public SolutionBean(Element element) {
        super(element);
    }

    public String getSourceCode() {
        return super.root.getText();
    }

    public void setSourceCode(String s) {
        super.root.setText(s);
    }

    public String getLanguage() {
        return super.root.getAttributeValue("language");
    }

    public void setLanguage(String s) {
        super.root.setAttribute("language", s);
    }

    public String getFilename() {
        return super.root.getAttributeValue("filename");
    }

    public void setFilename(String s) {
        super.root.setAttribute("filename", s);
    }
}
