package clientGUI.allpanel.ate;

import java.awt.Font;
import java.awt.Insets;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.*;

public class ClipTextArea extends JEditorPane
        implements ClipboardOwner {

    public ClipTextArea(JScrollPane JSP_Code) {
        insets = new Insets(0, 5, 0, 5);
        JTA_LineView = new JTextArea("1");
        undo = new UndoManager();
        JSP_Code.setRowHeaderView(JTA_LineView);
        JSP_Code.setViewportView(this);
        kit = new MyEditorKit();
        kit.setLanguage("Cpp");
        setEditorKitForContentType("", kit);
        setFont(new Font("DialogInput", 0, 13));
        setContentType("");
        doc = new MyDocument();
        setDocument(doc);
        doc.setJTextArea(JTA_LineView);
        doc.setJEditorPane(this);
        doc.setLanguage("Cpp");
        doc.addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent e) {
                doc_addedit(e);
            }
        });
        setMargin(insets);
        addActionMap();
    }

    public void setLanguage(String language) {
        doc.setLanguage(language);
        kit.setLanguage(language);
        String temp = getText();
        setText(temp);
    }

    public void addActionMap() {
        getActionMap().put("Undo", new AbstractAction("Undo") {

            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canUndo()) {
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                }
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

        getActionMap().put("Redo", new AbstractAction("Redo") {

            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canRedo()) {
                        undo.redo();
                    }
                } catch (CannotRedoException e) {
                }
            }
        });
        getInputMap().put(KeyStroke.getKeyStroke("control R"), "Redo");
    }

    public void lostOwnership(Clipboard clipboard1, Transferable transferable) {
    }

    void doc_addedit(UndoableEditEvent e) {
        undo.addEdit(e.getEdit());
    }

    @Override
    protected void processKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_TAB && keyEvent.getID() == KeyEvent.KEY_PRESSED) {

            int start = getSelectionStart();
            int stop = getSelectionEnd();
            if (start != stop) {
                // Ident the lines covered by the selection
                try {
                    indentText(start, stop, keyEvent.isShiftDown() ? -1 : 1);
                    keyEvent.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                super.processKeyEvent(keyEvent);
            }
        } else {
            super.processKeyEvent(keyEvent);
        }
    }

    protected void indentText(final int start, final int stop, final int direction) throws Exception {
        String text = getText();
        boolean hasr = false;
        if (text.indexOf("\r") != -1) {
            hasr = true;
            text = text.replaceAll("\r", "");
        }
        text = "\n" + text;// add \n
        int begin = start + 1;
        int end = stop + 1;

        while (begin > 0 && text.charAt(begin) != '\n') {
            begin--;
        }
        while (end < text.length() && text.charAt(end) != '\n') {
            end++;
        }
        String a, b, c;
        try {
            a = text.substring(0, begin);
        } catch (Exception e) {
            a = "";
        }
        b = text.substring(begin, end);
        if (direction == -1) {
            b = b.replaceAll("\n ", "\n");
            b = b.replaceAll("\n\t", "\n");
        } else {
            b = b.replaceAll("\n", "\n\t");
        }
        try {
            c = text.substring(end, text.length());
        } catch (Exception e) {
            c = "";
        }
        String out = a + b + c;
        out = out.substring(1);// remove /n
        if (hasr) {
            out = out.replaceAll("\n", "\r\n");
        }
        setText(out);

        // adjust the selection
        getCaret().setDot(a.length());
        getCaret().moveDot(a.length() + b.length() - 1);
    }
    private Insets insets;
    private JTextArea JTA_LineView;
    private UndoManager undo;
    private MyDocument doc;
    private MyEditorKit kit;
}