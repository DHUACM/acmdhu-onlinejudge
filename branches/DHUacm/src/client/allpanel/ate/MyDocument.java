package client.allpanel.ate;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.text.*;
import client.Control;
import config.Const;

public class MyDocument extends PlainDocument {

    public MyDocument() {
        language = "";
        putProperty(javax.swing.text.PlainDocument.tabSizeAttribute, 4);
    }

    public void setJTextArea(JTextArea ta) {
        jta = ta;
        jta.setFont(Const.font);
        jta.setEditable(false);
    }

    public void setJEditorPane(JEditorPane ep) {
        jep = ep;
    }

    public void setLanguage(String l) {
        language = l;
    }

    private int getEditorPaneLineCount(String s) {
        int count = 0;
        int strlen = s.length();
        for (int i = 0; i < strlen; i++) {
            if (s.charAt(i) == '\n') {
                count++;
            }
        }

        return count;
    }

    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        int lineCount = jta.getLineCount();
        int strlen = str.length();
        for (int i = 0; i < strlen; i++) {
            if (str.charAt(i) == '\n') {
                jta.append("\n".concat(String.valueOf(String.valueOf(++lineCount))));
            }
        }
        if (str.equals("\n")) {
            int elementIndex = getDefaultRootElement().getElementIndex(offs);
            Element element = getDefaultRootElement().getElement(elementIndex);
            int startOffset = element.getStartOffset();
            int endOffset = element.getEndOffset();
            String elementText = null;
            elementText = getText(startOffset, endOffset - startOffset);

            String tabs = "\n";
            int cnt = 0;
            while (elementText.charAt(cnt) == ' ' || elementText.charAt(cnt) == '\t') {
                tabs += elementText.charAt(cnt);
                cnt++;
            }

            super.insertString(offs, tabs, a);
        } else {
            super.insertString(offs, str, a);
        }
    }

    protected void insertUpdate(javax.swing.text.AbstractDocument.DefaultDocumentEvent e, AttributeSet attr) {
        super.insertUpdate(e, attr);
        int jeplenth = getEditorPaneLineCount(jep.getText());
        String numLine = "1\n";
        for (int i = 0; i < jeplenth; i++) {
            numLine += (i + 2) + "\n";
        }

        jta.setText(numLine);
        jeplenth = getEditorPaneLineCount(jep.getText());
        int jtalenth = jta.getLineCount();
    }
    private JTextArea jta;
    private JEditorPane jep;
    private String language;
}