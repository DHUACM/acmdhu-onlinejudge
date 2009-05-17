// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package cn.edu.dhu.acm.oj.common.problem;

import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean, SeperatorBean
public final class InputFormatBean extends NodeBean {

    public InputFormatBean() {
        super("InputFormat");
        setInputType("eof");
        addNode(new NodeBean("Terminator", true));
        addNode(new SeperatorBean());
    }

    public InputFormatBean(Element element) {
        super(element);
    }

    public String getInputType() {
        return super.root.getAttributeValue("inputType");
    }

    public void setInputType(String s) {
        super.root.setAttribute("inputType", s);
    }

    public String getTerminator() {
        return super.root.getChildText("Terminator");
    }

    public void setTerminator(String s) {
        super.root.setChildText("Terminator", s);
    }

    public SeperatorBean getSeperator() {
        return new SeperatorBean(super.root.getChild("Seperator"));
    }
}
