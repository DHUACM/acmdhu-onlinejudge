// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package cn.edu.dhu.acm.oj.common.problem;

import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean
public final class SeperatorBean extends NodeBean {

    public SeperatorBean() {
        super("Seperator", true);
        setLayout("bottom");
    }

    public SeperatorBean(Element element) {
        super(element);
    }

    public String getText() {
        return super.root.getText();
    }

    public void setText(String s) {
        super.root.setText(s);
    }

    public String getLayout() {
        return super.root.getAttributeValue("layout");
    }

    public void setLayout(String s) {
        super.root.setAttribute("layout", s);
    }
}
