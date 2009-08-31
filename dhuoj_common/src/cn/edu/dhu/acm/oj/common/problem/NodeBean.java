// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package cn.edu.dhu.acm.oj.common.problem;

import org.jdom.Element;

class NodeBean {

    protected NodeBean() {
    }

    public NodeBean(String s) {
        root = new Element(s);
    }

    public NodeBean(String s, boolean flag) {
        root = new Element(s, flag);
    }

    public NodeBean(Element element) {
        setElement(element);
    }

    public Element getElement() {
        return root;
    }

    public void setElement(Element element) {
        root = element;
    }

    public void addNode(NodeBean nodebean) {
        root.addContent(nodebean.getElement());
    }
    protected Element root;
}
