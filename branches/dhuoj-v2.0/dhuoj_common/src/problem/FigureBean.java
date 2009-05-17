// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
package problem;

import java.io.*;
import org.jdom.Element;

// Referenced classes of package com.dyf:
//            NodeBean, Base64
public final class FigureBean extends NodeBean {

    public FigureBean() {
        super("Figure");
        setFilename("");
        addNode(new NodeBean("Text"));
        addNode(new NodeBean("Image", true));
    }

    public FigureBean(Element element) {
        super(element);
    }

    public void read(String s)
            throws IOException {
        File file = new File(s);
        byte abyte0[] = new byte[(int) file.length()];
        BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(file));
        byte abyte1[] = new byte[32768];
        int i = bufferedinputstream.read(abyte1, 0, abyte1.length);
        bufferedinputstream.close();
        setFilename(file.getName());
        setImage(abyte1, i);
    }

    public void write(String s)
            throws IOException {
        File file = new File(s, getFilename());
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(file));
        byte abyte0[] = getImage();
        bufferedoutputstream.write(abyte0, 0, abyte0.length);
        bufferedoutputstream.close();
    }

    public String getFilename() {
        return super.root.getAttributeValue("filename");
    }

    public void setFilename(String s) {
        super.root.setAttribute("filename", s);
    }

    public String getText() {
        return super.root.getChildText("Text");
    }

    public void setText(String s) {
        super.root.setChildText("Text", s);
    }

    public byte[] getImage() {
        return Base64.base64ToByteArray(super.root.getChildText("Image"));
    }

    public void setImage(byte abyte0[], int i) {
        String s = Base64.byteArrayToBase64(abyte0, i);
        super.root.getChild("Image").setText(s);
    }
}
