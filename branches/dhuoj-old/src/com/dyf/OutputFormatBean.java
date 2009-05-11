package com.dyf;

import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class OutputFormatBean
        extends NodeBean {

    public OutputFormatBean() {
        super("OutputFormat");

        addNode(new NodeBean("Header", true));
        addNode(new NodeBean("Footer", true));
        addNode(new SeperatorBean());
    }

    public OutputFormatBean(Element x) {
        super(x);
    }

    public String getHeader() {
        return root.getChildText("Header");
    }

    public void setHeader(String str) {
        root.setChildText("Header", str);
    }

    public String getFooter() {
        return root.getChildText("Footer");
    }

    public void setFooter(String str) {
        root.setChildText("Footer", str);
    }

    public SeperatorBean getSeperator() {
        return new SeperatorBean(root.getChild("Seperator"));
    }
}