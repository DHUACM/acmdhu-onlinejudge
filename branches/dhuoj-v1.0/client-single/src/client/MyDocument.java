package client;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.text.*;

public class MyDocument extends PlainDocument {

    public MyDocument() {
        language = "";
    }

    public void setJTextArea(JTextArea ta) {
        jta = ta;
        jta.setFont(new Font("DialogInput", 0, 13));
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
            int spaceCount = 0;
            for (spaceCount = 0; elementText.charAt(spaceCount) == ' '; spaceCount++) {
                ;
            }
            String tabs = "";
            for (int i = 0; i < spaceCount; i++) {
                tabs = (new StringBuilder()).append(tabs).append(" ").toString();
            }

            super.insertString(offs, (new StringBuilder()).append("\n").append(tabs).toString(), a);
        } else if (str.equals("\t")) {
            super.insertString(offs, "    ", a);
        } else {
            super.insertString(offs, str, a);
        }
    }

    protected void insertUpdate(javax.swing.text.AbstractDocument.DefaultDocumentEvent e, AttributeSet attr) {
        super.insertUpdate(e, attr);
        int jeplenth = getEditorPaneLineCount(jep.getText());
        String numLine = "1\n";
        for (int i = 0; i < jeplenth; i++) {
            numLine = (new StringBuilder()).append(numLine).append(i + 2).append("\n").toString();
        }

        jta.setText(numLine);
        jeplenth = getEditorPaneLineCount(jep.getText());
        int jtalenth = jta.getLineCount();
    }
    private JTextArea jta;
    private JEditorPane jep;
    private String language;
}