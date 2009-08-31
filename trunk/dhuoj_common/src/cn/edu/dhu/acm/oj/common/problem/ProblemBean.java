// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package cn.edu.dhu.acm.oj.common.problem;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean, FigureBean
public final class ProblemBean extends NodeBean {

    public ProblemBean() {
        super("Problem");
        setContentType("html");
        addNode(new NodeBean("Description", true));
        addNode(new NodeBean("InputSpec", true));
        addNode(new NodeBean("OutputSpec", true));
    }

    public ProblemBean(Element element) {
        super(element);
    }

    public String getContentType() {
        return super.root.getAttributeValue("contentType");
    }

    public void setContentType(String s) {
        super.root.setAttribute("contentType", s);
    }

    public String getDescription() {
        return super.root.getChildText("Description");
    }

    public void setDescription(String s) {
        super.root.setChildText("Description", s);
    }

    public String getInputSpec() {
        return super.root.getChildText("InputSpec");
    }

    public void setInputSpec(String s) {
        super.root.setChildText("InputSpec", s);
    }

    public String getOutputSpec() {
        return super.root.getChildText("OutputSpec");
    }

    public void setOutputSpec(String s) {
        super.root.setChildText("OutputSpec", s);
    }

    public List getFigureList() {
        return super.root.getChildren("Figure");
    }

    public int getFigureCount() {
        return super.root.getChildrenCount("Figure");
    }

    public FigureBean getFigure(int i) {
        return new FigureBean(super.root.getChild("Figure", i));
    }

    public boolean addFigure(FigureBean figurebean) {
        for (Iterator iterator = super.root.getChildren("Figure").iterator(); iterator.hasNext();) {
            FigureBean figurebean1 = new FigureBean((Element) iterator.next());
            if (figurebean1.getFilename().equals(figurebean.getFilename())) {
                return false;
            }
        }

        addNode(figurebean);
        return true;
    }

    public void removeFigure(int i) {
        super.root.removeChild("Figure", i);
    }

    public void writeFigureList(String s)
            throws IOException {
        FigureBean figurebean;
        for (Iterator iterator = getFigureList().iterator(); iterator.hasNext(); figurebean.write(s)) {
            figurebean = new FigureBean((Element) iterator.next());
        }

    }
}
