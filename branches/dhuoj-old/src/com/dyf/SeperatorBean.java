package com.dyf;

import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */

public final class SeperatorBean
        extends NodeBean {

    public SeperatorBean() {
        super("Seperator", true);
        setLayout("bottom");
    }

    public SeperatorBean(Element x) {
        super(x);
    }

    public String getText() {
        return root.getText();
    }

    public void setText(String str) {
        root.setText(str);
    }

    public String getLayout() {
        return root.getAttributeValue("layout");
    }

    public void setLayout(String str) {
        root.setAttribute("layout", str);
    }
}