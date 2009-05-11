package com.dyf;

import java.io.*;
import java.util.*;
import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class ProblemBean
        extends NodeBean {

    public ProblemBean() {
        super("Problem");

        setContentType("html");

        addNode(new NodeBean("Description", true));
        addNode(new NodeBean("InputSpec", true));
        addNode(new NodeBean("OutputSpec", true));
    }

    public ProblemBean(Element x) {
        super(x);
    }

    public String getContentType() {
        return root.getAttributeValue("contentType");
    }

    public void setContentType(String str) {
        root.setAttribute("contentType", str);
    }

    public String getDescription() {
        return root.getChildText("Description");
    }

    public void setDescription(String str) {
        root.setChildText("Description", str);
    }

    public String getInputSpec() {
        return root.getChildText("InputSpec");
    }

    public void setInputSpec(String str) {
        root.setChildText("InputSpec", str);
    }

    public String getOutputSpec() {
        return root.getChildText("OutputSpec");
    }

    public void setOutputSpec(String str) {
        root.setChildText("OutputSpec", str);
    }

    public List getFigureList() {
        return root.getChildren("Figure");
    }

    public int getFigureCount() {
        return root.getChildrenCount("Figure");
    }

    public FigureBean getFigure(int index) {
        return new FigureBean(root.getChild("Figure", index));
    }

    public boolean addFigure(FigureBean bean)  {
        Iterator it = root.getChildren("Figure").iterator();
        while (it.hasNext()) {
            FigureBean x = new FigureBean((Element)it.next());
            if (x.getFilename().equals(bean.getFilename()))
                return false;
        }

        addNode(bean);
        return true;
    }

    public void removeFigure(int index) {
        root.removeChild("Figure", index);
    }

    public void writeFigureList(String directory) throws IOException {
        Iterator it = getFigureList().iterator();

        while (it.hasNext()) {
            FigureBean x = new FigureBean( (Element) it.next());
            x.write(directory);
        }
    }
}