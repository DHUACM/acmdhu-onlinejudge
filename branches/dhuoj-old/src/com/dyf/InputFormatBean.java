package com.dyf;

import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class InputFormatBean
        extends NodeBean {

    public InputFormatBean() {
        super("InputFormat");

        setInputType("eof");
        addNode(new NodeBean("Terminator", true));
        addNode(new SeperatorBean());
    }

    public InputFormatBean(Element x) {
        super(x);
    }

    public String getInputType() {
        return root.getAttributeValue("inputType");
    }

    public void setInputType(String str) {
        root.setAttribute("inputType", str);
    }

    public String getTerminator() {
        return root.getChildText("Terminator");
    }

    public void setTerminator(String str) {
        root.setChildText("Terminator", str);
    }

    public SeperatorBean getSeperator() {
        return new SeperatorBean(root.getChild("Seperator"));
    }

}