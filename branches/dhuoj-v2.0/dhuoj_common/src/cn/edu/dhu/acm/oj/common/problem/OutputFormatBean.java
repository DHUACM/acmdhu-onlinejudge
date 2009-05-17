// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package cn.edu.dhu.acm.oj.common.problem;

import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean, SeperatorBean
public final class OutputFormatBean extends NodeBean {

    public OutputFormatBean() {
        super("OutputFormat");
        addNode(new NodeBean("Header", true));
        addNode(new NodeBean("Footer", true));
        addNode(new SeperatorBean());
    }

    public OutputFormatBean(Element element) {
        super(element);
    }

    public String getHeader() {
        return super.root.getChildText("Header");
    }

    public void setHeader(String s) {
        super.root.setChildText("Header", s);
    }

    public String getFooter() {
        return super.root.getChildText("Footer");
    }

    public void setFooter(String s) {
        super.root.setChildText("Footer", s);
    }

    public SeperatorBean getSeperator() {
        return new SeperatorBean(super.root.getChild("Seperator"));
    }
}
