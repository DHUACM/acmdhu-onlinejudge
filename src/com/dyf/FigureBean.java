package com.dyf;

import java.io.*;
import org.jdom.*;

/**
 * @author  Dong Yunfeng
 * @version 1.0, 5/10/2003
 *
 */
public final class FigureBean
        extends NodeBean {

    public FigureBean() {
        super("Figure");

        setFilename("");
        addNode(new NodeBean("Text"));
        addNode(new NodeBean("Image", true));
    }

    public FigureBean(Element x) {
        super(x);
    }

    public void read(String filename) throws IOException {
        File file = new File(filename);
        byte[] data = new byte[ (int) file.length()];

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        byte[] buff = new byte[1 << 15];
        int nch = in.read(buff, 0, buff.length);
        in.close();

        setFilename(file.getName());
        setImage(buff, nch);
    }

    public void write(String directory) throws IOException {
        File file = new File(directory, getFilename());

        BufferedOutputStream out = new BufferedOutputStream(new
                FileOutputStream(file));
        byte[] data = getImage();
        out.write(data, 0, data.length);
        out.close();
    }

    public String getFilename() {
        return root.getAttributeValue("filename");
    }

    public void setFilename(String filename) {
        root.setAttribute("filename", filename);
    }

    public String getText() {
        return root.getChildText("Text");
    }

    public void setText(String str) {
        root.setChildText("Text", str);
    }

    public byte[] getImage() {
        return Base64.base64ToByteArray(root.getChildText("Image"));
    }

    public void setImage(byte[] data, int length) {
        String str = Base64.byteArrayToBase64(data, length);
        root.getChild("Image").setText(str);
    }
}