// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClipTextArea.java

package client;

import java.awt.Font;
import java.awt.Insets;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.*;

// Referenced classes of package client:
//            MyEditorKit, MyDocument

public class ClipTextArea extends JEditorPane
        implements ClipboardOwner {

    public ClipTextArea(JScrollPane JSP_Code) {
        insets = new Insets(0, 5, 0, 5);
        JTA_LineView = new JTextArea("1");
        JTA_Codehere = new JTextArea("Code here~");
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
    private Insets insets;
    private JTextArea JTA_LineView;
    private JTextArea JTA_Codehere;
    private UndoManager undo;
    private MyDocument doc;
    private MyEditorKit kit;
}