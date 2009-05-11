package com.dyf;

import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
class NodeBean {

    protected Element root;

    protected NodeBean() {

    }

    public NodeBean(String name) {
        root = new Element(name);
    }

    public NodeBean(String name, boolean white) {
        root = new Element(name, white);
    }

    public NodeBean(Element x) {
        setElement(x);
    }

    public Element getElement() {
        return root;
    }

    public void setElement(Element x) {
        root = x;
    }

    public void addNode(NodeBean x) {
        root.addContent(x.getElement());
    }
}

