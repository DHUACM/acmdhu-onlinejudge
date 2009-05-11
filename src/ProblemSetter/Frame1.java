package ProblemSetter;

import com.dyf.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import java.beans.*;

/**
 * <p>Title: ProblemSetter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Dong Yunfeng
 * @version 1.0
 */

public class Frame1
        extends JFrame {

    class XmlFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String str = file.getName().toLowerCase();
            return file.isDirectory() ||
                    str.endsWith("xml");
        }

        public String getDescription() {
            return "XML files (*.xml)";
        }
    }

    class ImageFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String str = file.getName().toLowerCase();
            return file.isDirectory() ||
                    (str.endsWith("gif") ||
                    str.endsWith("jpg"));
        }

        public String getDescription() {
            return "Image files (*.gif; *.jpg)";
        }
    }

    class HtmlFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String str = file.getName().toLowerCase();
            return file.isDirectory() ||
                    (str.endsWith("htm") ||
                    str.endsWith("html"));
        }

        public String getDescription() {
            return "HTML files (*.htm; *.html)";
        }
    }

    class SourceFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String str = file.getName().toLowerCase();
            return file.isDirectory() ||
                    (str.endsWith("cpp") ||
                    str.endsWith("c") ||
                    str.endsWith("java") ||
                    str.endsWith("dpr"));
        }

        public String getDescription() {
            return "Source files (*.cpp; *.c; *.java; *.dpr)";
        }
    }

    class FilePreviewer extends JComponent implements PropertyChangeListener {
        ImageIcon thumbnail = null;

        public FilePreviewer(JFileChooser fc) {
            setPreferredSize(new Dimension(100, 50));
            fc.addPropertyChangeListener(this);
        }

        public void loadImage(File f) {
            if (f == null) {
                thumbnail = null;
            } else {
                ImageIcon tmpIcon = new ImageIcon(f.getPath());
                if(tmpIcon.getIconWidth() > 90) {
                    thumbnail = new ImageIcon(
                            tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT));
                } else {
                    thumbnail = tmpIcon;
                }
            }
        }

        public void propertyChange(PropertyChangeEvent e) {
            String prop = e.getPropertyName();
            if(prop == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
                if(isShowing()) {
                    loadImage((File) e.getNewValue());
                    repaint();
                }
            }
        }

        public void paint(Graphics g) {
            if(thumbnail != null) {
                int x = getWidth()/2 - thumbnail.getIconWidth()/2;
                int y = getHeight()/2 - thumbnail.getIconHeight()/2;
                if(y < 0) {
                    y = 0;
                }

                if(x < 5) {
                    x = 5;
                }
                thumbnail.paintIcon(this, g, x, y);
            }
        }
    }

    class FigureTableModel extends DefaultTableModel {
        public FigureTableModel(Object[] columnNames, int numRows) {
            super(columnNames, numRows);
        }

        public boolean isCellEditable(int row, int column) {
            return column == 0 ? false : true;
        }
    }



    ProblemArchiveBean problemArchive = new ProblemArchiveBean();
    String frameTitle = "Problem Setter 2003";
    String workDocument = null;
    File imgDir = new File(System.getProperty("java.io.tmpdir"));

    int curProblemKey = -1;
    int numOfCase = 0;
    int curTestCase = -1;

    private String[] dataProblemKey = {
        "Description", "The Input", "The Output"};
    private String[] figureHeader = {
        "Filename", "Text"};

    private FigureTableModel modelFigure = new FigureTableModel (
            figureHeader, 0);

    private DefaultTreeModel modelTestData = new DefaultTreeModel(
            new DefaultMutableTreeNode("TestData"), true);
    private DefaultTreeSelectionModel selectionTestData = new
            DefaultTreeSelectionModel();

    private JTextArea jLineNumber = new JTextArea();

    private JPanel contentPane;
    private JMenuBar jMenuBar1 = new JMenuBar();
    private JMenu jMenuFile = new JMenu();
    private JMenuItem jMenuFileExit = new JMenuItem();
    private JMenu jMenuHelp = new JMenu();
    private JMenuItem jMenuHelpAbout = new JMenuItem();
    private JLabel statusBar = new JLabel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private JTabbedPane jTabbedPane2 = new JTabbedPane();
    private JPanel jPanel5 = new JPanel();
    private JPanel jPanel6 = new JPanel();
    private JPanel jPanel7 = new JPanel();
    private BorderLayout borderLayout4 = new BorderLayout();
    private JToolBar jToolBar1 = new JToolBar();
    private JRadioButton jTextPlain = new JRadioButton();
    private JRadioButton jTextHtml = new JRadioButton();
    private JSplitPane jSplitPane1 = new JSplitPane();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTextArea jProblemValue = new JTextArea();
    private JPanel jPanel4 = new JPanel();
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private JScrollPane jScrollPane3 = new JScrollPane();
    private BorderLayout borderLayout5 = new BorderLayout();
    private JScrollPane jScrollPane4 = new JScrollPane();
    private JEditorPane jViewHtml = new JEditorPane();
    private BorderLayout borderLayout6 = new BorderLayout();
    private JMenuItem jMenuFileNew = new JMenuItem();
    private JMenuItem jMenuFileOpen = new JMenuItem();
    private JMenuItem jMenuFileSave = new JMenuItem();
    private JMenuItem jMenuFileSaveAs = new JMenuItem();
    private JToolBar jToolBar3 = new JToolBar();
    private JButton jViewSaveAs = new JButton();
    private JButton jViewRefresh = new JButton();
    private JList jProblemKey = new JList();
    private JSplitPane jSplitPane2 = new JSplitPane();
    private JSplitPane jSplitPane3 = new JSplitPane();
    private JPanel jPanel9 = new JPanel();
    private BorderLayout borderLayout8 = new BorderLayout();
    private JScrollPane jScrollPane6 = new JScrollPane();
    private JPanel jPanel10 = new JPanel();
    private JPanel jPanel11 = new JPanel();
    private BorderLayout borderLayout9 = new BorderLayout();
    private BorderLayout borderLayout10 = new BorderLayout();
    private JScrollPane jScrollPane7 = new JScrollPane();
    private JScrollPane jScrollPane8 = new JScrollPane();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JTextArea jTestInput = new JTextArea();
    private JTextArea jTestOutput = new JTextArea();
    private JTabbedPane jTabbedPane3 = new JTabbedPane();
    private JPanel jPanel12 = new JPanel();
    private BorderLayout borderLayout11 = new BorderLayout();
    private JPanel jPanel13 = new JPanel();
    private JPanel jPanel14 = new JPanel();
    private BorderLayout borderLayout13 = new BorderLayout();
    private JPanel jPanel16 = new JPanel();
    private JTree jTestData = new JTree();
    private JMenuItem jMenuFileTest = new JMenuItem();
    private JPanel jPanel18 = new JPanel();
    private JPanel jPanel19 = new JPanel();
    private BorderLayout borderLayout15 = new BorderLayout();
    private BorderLayout borderLayout16 = new BorderLayout();
    private JToolBar jToolBar5 = new JToolBar();
    private JButton jFormatDataRefresh = new JButton();
    private JSplitPane jSplitPane4 = new JSplitPane();
    private JPanel jPanel20 = new JPanel();
    private JPanel jPanel21 = new JPanel();
    private BorderLayout borderLayout17 = new BorderLayout();
    private BorderLayout borderLayout18 = new BorderLayout();
    private JLabel jLabel17 = new JLabel();
    private JLabel jLabel18 = new JLabel();
    private JScrollPane jScrollPane9 = new JScrollPane();
    private JScrollPane jScrollPane15 = new JScrollPane();
    private JTextArea jFormatInput = new JTextArea();
    private JTextArea jFormatOutput = new JTextArea();
    private JToolBar jToolBar6 = new JToolBar();
    private JLabel jLabel20 = new JLabel();
    private JComboBox jDifficulty = new JComboBox();
    private JButton jTagImg = new JButton();
    private JButton jTagBr = new JButton();
    private JButton jTagPre = new JButton();
    private JButton jTagB = new JButton();
    private JButton jTagI = new JButton();
    private JButton jTagU = new JButton();
    private JButton jTagSub = new JButton();
    private JButton jTagSup = new JButton();
    private BorderLayout borderLayout19 = new BorderLayout();
    private JToolBar jToolBar7 = new JToolBar();
    private JButton jFigureRemove = new JButton();
    private JButton jFigureAdd = new JButton();
    private JButton jTagFont = new JButton();
    private JPanel jPanel23 = new JPanel();
    private JComboBox jLanguage = new JComboBox();
    private BorderLayout borderLayout20 = new BorderLayout();
    private JPanel jPanel22 = new JPanel();
    private JScrollPane jScrollPane5 = new JScrollPane();
    private JTabbedPane jTabbedPane4 = new JTabbedPane();
    private JSplitPane jSplitPane5 = new JSplitPane();
    private JScrollPane jScrollSourceCode = new JScrollPane();
    private JLabel jLabel6 = new JLabel();
    private JToolBar jToolBar2 = new JToolBar();
    private JButton jSolutionOpen = new JButton();
    private JLabel jLabel19 = new JLabel();
    private JButton jCompile = new JButton();
    private JTextField jSolutionFilename = new JTextField();
    private BorderLayout borderLayout21 = new BorderLayout();
    private JToolBar jToolBar8 = new JToolBar();
    private JButton jDebugRun = new JButton();
    private JSplitPane jSplitPane6 = new JSplitPane();
    private JButton jDebugClear = new JButton();
    private JTextArea jSourceCode = new JTextArea();
    private JTextArea jInfo = new JTextArea();
    private JTextField jTitle = new JTextField();
    private JTextField jTimeLimit = new JTextField();
    private JTextField jOutputFile = new JTextField();
    private JTextField jInputFile = new JTextField();
    private JLabel jLabel23 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel22 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel21 = new JLabel();
    private JLabel jLabel1 = new JLabel();
    private JTextField jAuthor = new JTextField();
    private JButton jTestCaseAdd = new JButton();
    private JToolBar jToolBar4 = new JToolBar();
    private JButton jTestCaseRemove = new JButton();
    private JComboBox jInputType = new JComboBox();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JScrollPane jScrollPane11 = new JScrollPane();
    private JLabel jLabel4 = new JLabel();
    private JScrollPane jScrollPane10 = new JScrollPane();
    private JComboBox jInputSeperatorLayout = new JComboBox();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel15 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JScrollPane jScrollPane14 = new JScrollPane();
    private JScrollPane jScrollPane13 = new JScrollPane();
    private JScrollPane jScrollPane12 = new JScrollPane();
    private JComboBox jOutputSeperatorLayout = new JComboBox();
    private JPanel jPanel15 = new JPanel();
    private BorderLayout borderLayout7 = new BorderLayout();
    private JScrollPane jScrollPane16 = new JScrollPane();
    private JLabel jLabel5 = new JLabel();
    private JScrollPane jScrollPane17 = new JScrollPane();
    private BorderLayout borderLayout12 = new BorderLayout();
    private JPanel jPanel8 = new JPanel();
    private JLabel jLabel14 = new JLabel();
    private JTextArea jDebugInput = new JTextArea();
    private JTextArea jDebugOutput = new JTextArea();
    private JTextArea jInputTerminator = new JTextArea();
    private JTextArea jInputSeperator = new JTextArea();
    private JTextArea jOutputHeader = new JTextArea();
    private JTextArea jOutputFooter = new JTextArea();
    private JTextArea jOutputSeperator = new JTextArea();
    private JTable jFigure = new JTable();
    private JComboBox jCharEntity = new JComboBox();
    private JMenuItem jMenuHelpOption = new JMenuItem();

    //Construct the frame
    public Frame1() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();

            javax.swing.text.html.HTMLDocument doc =
                    (javax.swing.text.html.HTMLDocument) jViewHtml.getDocument();
            doc.setBase(imgDir.toURL());

            loadDocument();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void jbInit() throws Exception {

        jProblemKey.setListData(dataProblemKey);
        modelFigure.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                jFigure_valueChanged(e);
            }
        });

        jDifficulty.addItem("trivial");
        jDifficulty.addItem("sample");
        jDifficulty.addItem("special");

        jCharEntity.addItem("Character Entity");
        jCharEntity.addItem("&amp;");
        jCharEntity.addItem("&lt;");
        jCharEntity.addItem("&gt;");
        jCharEntity.addItem("&quot;");
        jCharEntity.addItem("&nbsp;");

        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setForeground(Color.black);
        this.setSize(new Dimension(750, 573));
        this.setTitle(frameTitle);
        jMenuFile.setText("File");
        jMenuFileExit.setText("     Exit");
        jMenuFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(88, java.awt.event.KeyEvent.CTRL_MASK, false));
        jMenuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileExit_actionPerformed(e);
            }
        });
        jMenuHelp.setText("Help");
        jMenuHelpAbout.setIcon(new ImageIcon(Frame1.class.getResource("about.gif")));
        jMenuHelpAbout.setText("About");
        jMenuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });
        jPanel2.setLayout(borderLayout2);
        jPanel3.setLayout(borderLayout3);
        jPanel1.setLayout(null);
        jTabbedPane2.setTabPlacement(JTabbedPane.BOTTOM);
        jTabbedPane3.setTabPlacement(JTabbedPane.BOTTOM);
        jPanel5.setLayout(borderLayout4);
        jTextHtml.setSelected(true);
        jTextPlain.setText("   Plain     ");
        jTextHtml.setText("   Html     ");
        jProblemValue.setLineWrap(true);
        jProblemValue.setTabSize(4);
        jPanel6.setLayout(borderLayout19);
        jPanel7.setLayout(borderLayout5);
        jViewHtml.setEditable(false);
        jViewHtml.setContentType("text/html");
        jPanel4.setLayout(borderLayout6);
        jMenuFileNew.setText("     New");
        jMenuFileNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(78, java.awt.event.KeyEvent.CTRL_MASK, false));
        jMenuFileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileNew_actionPerformed(e);
            }
        });
        jMenuFileOpen.setIcon(new ImageIcon(Frame1.class.getResource("open.gif")));
        jMenuFileOpen.setText("Open...");
        jMenuFileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(79, java.awt.event.KeyEvent.CTRL_MASK, false));
        jMenuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileOpen_actionPerformed(e);
            }
        });
        jMenuFileSave.setIcon(new ImageIcon(Frame1.class.getResource("save.gif")));
        jMenuFileSave.setText("Save");
        jMenuFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(83, java.awt.event.KeyEvent.CTRL_MASK, false));
        jMenuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileSave_actionPerformed(e);
            }
        });
        jMenuFileSaveAs.setText("     Save As...");
        jMenuFileSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileSaveAs_actionPerformed(e);
            }
        });
        jProblemKey.setSelectedIndex(0);
        jProblemKey.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jProblemKey.addListSelectionListener(new javax.swing.event.
                ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                jProblemKey_valueChanged(e);
            }
        });
                jViewSaveAs.setText("   Save As    ");
                jViewSaveAs.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jViewSaveAs_actionPerformed(e);
                    }
                });
                jViewRefresh.setText("   Refresh   ");
                jViewRefresh.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jViewRefresh_actionPerformed(e);
                    }
                });
                jPanel9.setLayout(borderLayout8);
                jTestData.setAutoscrolls(true);
                jTestData.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                    public void valueChanged(TreeSelectionEvent e) {
                        jTestData_valueChanged(e);
                    }
                });
                jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
                jSplitPane3.addComponentListener(new java.awt.event.ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        jSplitPane3_componentResized(e);
                    }
                });
                jSplitPane5.addComponentListener(new java.awt.event.ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        jSplitPane5_componentResized(e);
                    }
                });
                jSplitPane6.addComponentListener(new java.awt.event.ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        jSplitPane6_componentResized(e);
                    }
                });
                jPanel10.setLayout(borderLayout9);
                jPanel11.setLayout(borderLayout10);
                jLabel7.setText("Test Input");
                jLabel8.setText("Test Output");
                jPanel12.setLayout(borderLayout11);
                jPanel13.setLayout(borderLayout13);
                jPanel14.setLayout(null);
                jPanel16.setLayout(null);
                selectionTestData.setSelectionMode(4);
                jMenuFileTest.setToolTipText("");
                jMenuFileTest.setIcon(new ImageIcon(Frame1.class.getResource("go.gif")));
                jMenuFileTest.setText("Test");
                jMenuFileTest.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jMenuFileTest_actionPerformed(e);
                    }
                });
                jPanel18.setLayout(borderLayout15);
                jPanel19.setLayout(borderLayout16);
                jFormatDataRefresh.setText("   Refresh   ");
                jFormatDataRefresh.addActionListener(new java.awt.event.
                        ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jFormatDataRefresh_actionPerformed(e);
                    }
                });
                        jPanel20.setLayout(borderLayout17);
                        jPanel21.setLayout(borderLayout18);
                        jLabel17.setText("Formatted Input Data");
                        jLabel18.setText("Formatted Output Data");
                        jSplitPane4.addComponentListener(new java.awt.event.ComponentAdapter() {
                            public void componentResized(ComponentEvent e) {
                                jSplitPane4_componentResized(e);
                            }
                        });
                        jFormatInput.setEditable(false);
                        jFormatOutput.setEditable(false);
                        jLabel20.setText("Difficulty    ");
                        jTagImg.setFont(new java.awt.Font("Dialog", 0, 11));
                        jTagImg.setIcon(new ImageIcon(Frame1.class.getResource("figure.gif")));
                        jTagImg.setText("  IMG   ");
                        jTagImg.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagImg_actionPerformed(e);
                            }
                        });
                        jTagBr.setFont(new java.awt.Font("Dialog", 0, 11));
                        jTagBr.setText(" BR ");
                        jTagBr.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagBr_actionPerformed(e);
                            }
                        });
                        jTagPre.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagPre_actionPerformed(e);
                            }
                        });
                        jTagB.setIcon(new ImageIcon(Frame1.class.getResource("bold.gif")));
                        jTagB.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagB_actionPerformed(e);
                            }
                        });
                        jTagI.setActionCommand(" I ");
                        jTagI.setIcon(new ImageIcon(Frame1.class.getResource("italic.gif")));
                        jTagI.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagI_actionPerformed(e);
                            }
                        });
                        jTagU.setIcon(new ImageIcon(Frame1.class.getResource("underline.gif")));
                        jTagU.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagU_actionPerformed(e);
                            }
                        });
                        jTagPre.setFont(new java.awt.Font("Dialog", 0, 11));
                        jTagPre.setText(" Pre ");
                        jTagSub.setFont(new java.awt.Font("Dialog", 0, 11));
                        jTagSub.setText(" Sub ");
                        jTagSub.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagSub_actionPerformed(e);
                            }
                        });
                        jTagSup.setFont(new java.awt.Font("Dialog", 0, 11));
                        jTagSup.setText(" Sup ");
                        jTagSup.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagSup_actionPerformed(e);
                            }
                        });
                        jFigureRemove.setText("   Remove   ");
                        jFigureRemove.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jFigureRemove_actionPerformed(e);
                            }
                        });
                        jFigureAdd.setText("      Add      ");
                        jFigureAdd.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jFigureAdd_actionPerformed(e);
                            }
                        });
                        jTagFont.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTagFont_actionPerformed(e);
                            }
                        });
                        jTagFont.setFont(new java.awt.Font("Dialog", 0, 11));
                        jTagFont.setIcon(new ImageIcon(Frame1.class.getResource("color.gif")));
                        jLineNumber.setBackground(SystemColor.activeCaptionBorder);
                        jLineNumber.setEditable(false);
                        jLineNumber.setText("   1   \n");
                        jPanel22.setLayout(borderLayout20);
                        jSplitPane5.setOrientation(JSplitPane.VERTICAL_SPLIT);
                        jSplitPane5.setLastDividerLocation(400);
                        jSplitPane5.setOneTouchExpandable(true);
                        jLabel6.setText("        Language    ");
                        jSolutionOpen.setText("    Open    ");
                        jSolutionOpen.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jSolutionOpen_actionPerformed(e);
                            }
                        });
                        jLabel19.setText("        Filename    ");
                        jCompile.setText("   Compile   ");
                        jCompile.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jCompile_actionPerformed(e);
                            }
                        });
                        jSolutionFilename.setText("");
                        jTabbedPane4.setTabPlacement(JTabbedPane.BOTTOM);
                        jPanel23.setLayout(borderLayout21);
                        jDebugRun.setText("      Run      ");
                        jDebugRun.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jDebugRun_actionPerformed(e);
                            }
                        });
                        jDebugClear.setText("      Clear      ");
                        jDebugClear.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jDebugClear_actionPerformed(e);
                            }
                        });
                        jSourceCode.setTabSize(2);
                        jSourceCode.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(CaretEvent e) {
                                jSourceCode_caretUpdate(e);
                            }
                        });
                        jInfo.setEditable(false);
                        jTitle.setBounds(new Rectangle(214, 76, 225, 22));
                        jTimeLimit.setToolTipText("");
                        jTimeLimit.setBounds(new Rectangle(214, 185, 81, 22));
                        jOutputFile.setText("");
                        jOutputFile.setBounds(new Rectangle(214, 260, 139, 22));
                        jInputFile.setText("");
                        jInputFile.setBounds(new Rectangle(214, 222, 139, 22));
                        jLabel23.setText("Author");
                        jLabel23.setBounds(new Rectangle(119, 116, 81, 18));
                        jLabel3.setText("sec");
                        jLabel3.setBounds(new Rectangle(301, 187, 32, 18));
                        jLabel22.setText("Output File");
                        jLabel22.setBounds(new Rectangle(119, 263, 81, 18));
                        jLabel2.setText("Time Limit");
                        jLabel2.setBounds(new Rectangle(119, 188, 81, 18));
                        jLabel21.setText("Input File");
                        jLabel21.setBounds(new Rectangle(119, 225, 81, 18));
                        jLabel1.setText("Problem Title");
                        jLabel1.setBounds(new Rectangle(119, 79, 81, 18));
                        jAuthor.setBounds(new Rectangle(214, 113, 158, 22));
                        jTestCaseAdd.setText("      Add      ");
                        jTestCaseAdd.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTestCaseAdd_actionPerformed(e);
                            }
                        });
                        jTestCaseRemove.setText("   Remove   ");
                        jTestCaseRemove.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                jTestCaseRemove_actionPerformed(e);
                            }
                        });
                        jInputType.setBounds(new Rectangle(231, 64, 161, 27));
                        jLabel13.setText("Layout");
                        jLabel13.setBounds(new Rectangle(310, 241, 76, 18));
                        jLabel11.setText("Terminator");
                        jLabel11.setBounds(new Rectangle(116, 133, 79, 18));
                        jLabel9.setText("Seperator");
                        jLabel9.setBounds(new Rectangle(116, 241, 76, 18));
                        jScrollPane11.setBounds(new Rectangle(116, 270, 404, 47));
                        jLabel4.setHorizontalAlignment(SwingConstants.LEADING);
                        jLabel4.setText("Test Input Type");
                        jLabel4.setBounds(new Rectangle(116, 68, 107, 18));
                        jScrollPane10.setBounds(new Rectangle(116, 162, 404, 43));
                        jInputSeperatorLayout.setBounds(new Rectangle(396, 237, 121, 27));
                        jLabel16.setText("Footer");
                        jLabel16.setBounds(new Rectangle(106, 146, 61, 18));
                        jLabel15.setText("Header");
                        jLabel15.setBounds(new Rectangle(105, 54, 65, 18));
                        jLabel12.setText("Layout");
                        jLabel12.setBounds(new Rectangle(329, 251, 51, 18));
                        jLabel10.setText("Seperator");
                        jLabel10.setBounds(new Rectangle(105, 251, 72, 18));
                        jScrollPane14.setBounds(new Rectangle(105, 171, 404, 43));
                        jScrollPane13.setBounds(new Rectangle(105, 79, 404, 43));
                        jScrollPane12.setBounds(new Rectangle(105, 279, 404, 45));
                        jOutputSeperatorLayout.setBounds(new Rectangle(387, 247, 121, 27));
                        jPanel15.setLayout(borderLayout7);
                        jLabel5.setText("Test Input");
                        jPanel8.setLayout(borderLayout12);
                        jLabel14.setText("Test Output");
                        jDebugOutput.setEditable(false);
                        jSplitPane1.setLastDividerLocation(250);
                        jInputSeperator.setText("");
                        jOutputHeader.setTabSize(8);
                        jOutputHeader.setText("");
                        jOutputFooter.setText("");
                        jOutputSeperator.setText("");
                        jFigure.setModel(modelFigure);
                        jCharEntity.setFont(new java.awt.Font("Dialog", 0, 11));
                        jCharEntity.setMaximumSize(new Dimension(32767, 27));
                        jCharEntity.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(ItemEvent e) {
                                jCharEntity_itemStateChanged(e);
                            }
                        });
                        jLanguage.setPreferredSize(new Dimension(150, 27));
                        contentPane.setBackground(SystemColor.control);
                        jMenuHelpOption.setText("     Option...");
        jMenuHelpOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpOption_actionPerformed(e);
            }
        });
        jMenuFile.add(jMenuFileNew);
                        jMenuFile.add(jMenuFileOpen);
                        jMenuFile.add(jMenuFileSave);
                        jMenuFile.add(jMenuFileSaveAs);
                        jMenuFile.addSeparator();
                        jMenuFile.add(jMenuFileTest);
                        jMenuFile.addSeparator();
                        jMenuFile.add(jMenuFileExit);
                        jMenuHelp.add(jMenuHelpOption);
        jMenuHelp.addSeparator();
        jMenuHelp.add(jMenuHelpAbout);
                        jMenuBar1.add(jMenuFile);
                        jMenuBar1.add(jMenuHelp);
                        this.setJMenuBar(jMenuBar1);
                        contentPane.add(statusBar, BorderLayout.SOUTH);
                        contentPane.add(jTabbedPane1, BorderLayout.CENTER);
                        jTabbedPane1.add(jPanel1, "General");
                        jTabbedPane1.add(jPanel2, "Problem");
                        jPanel2.add(jTabbedPane2, BorderLayout.CENTER);
                        jTabbedPane2.add(jPanel5, "Description");
                        jTabbedPane2.add(jPanel6, "Figure");
                        jPanel5.add(jToolBar1, BorderLayout.NORTH);
                        jToolBar1.add(jTextPlain, null);
                        jToolBar1.add(jTextHtml, null);
                        jToolBar1.add(jTagImg, null);
                        jToolBar1.add(jTagB, null);
                        jToolBar1.add(jTagI, null);
                        jToolBar1.add(jTagU, null);
                        jToolBar1.add(jTagFont, null);
                        jToolBar1.add(jTagSub, null);
                        jToolBar1.add(jTagSup, null);
                        jToolBar1.add(jTagBr, null);
                        jToolBar1.add(jTagPre, null);
                        jPanel5.add(jSplitPane1, BorderLayout.CENTER);
                        jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
                        jScrollPane1.getViewport().add(jProblemKey, null);
                        jSplitPane1.add(jScrollPane2, JSplitPane.RIGHT);
                        jScrollPane2.getViewport().add(jProblemValue, null);
                        jPanel6.add(jScrollPane3, BorderLayout.CENTER);
                        jScrollPane3.getViewport().add(jFigure, null);
                        jTabbedPane2.add(jPanel7,   "HTML View");
                        jPanel7.add(jScrollPane4, BorderLayout.CENTER);
                        jPanel7.add(jToolBar3, BorderLayout.NORTH);
                        jToolBar3.add(jViewRefresh, null);
                        jToolBar3.add(jViewSaveAs, null);
                        jScrollPane4.getViewport().add(jViewHtml, null);
                        jTabbedPane1.add(jPanel3, "Test Data");
                        jPanel12.add(jSplitPane3, BorderLayout.CENTER);
                        jPanel3.add(jTabbedPane3, BorderLayout.CENTER);
                        jSplitPane2.add(jPanel9, JSplitPane.TOP);
                        jPanel9.add(jScrollPane6, BorderLayout.CENTER);
                        jPanel9.add(jToolBar4, BorderLayout.NORTH);
                        jToolBar4.add(jTestCaseAdd, null);
                        jToolBar4.add(jTestCaseRemove, null);
                        jSplitPane2.add(jPanel12, JSplitPane.BOTTOM);
                        jScrollPane6.getViewport().add(jTestData, null);
                        jTabbedPane1.add(jPanel4, "Solution");
                        jPanel4.add(jTabbedPane4,  BorderLayout.CENTER);
                        jToolBar2.add(jSolutionOpen, null);
                        jToolBar2.add(jCompile, null);
                        jToolBar2.add(jLabel19, null);
                        jToolBar2.add(jSolutionFilename, null);
                        jToolBar2.add(jLabel6, null);
                        jToolBar2.add(jLanguage, null);
                        jPanel22.add(jSplitPane5, BorderLayout.CENTER);
                        jPanel22.add(jToolBar2,  BorderLayout.NORTH);
                        jSplitPane5.add(jScrollSourceCode, JSplitPane.TOP);
                        jScrollSourceCode.getViewport().add(jSourceCode, null);
                        jSplitPane5.add(jScrollPane5, JSplitPane.BOTTOM);
                        jScrollPane5.getViewport().add(jInfo, null);
                        jPanel23.add(jToolBar8, BorderLayout.NORTH);
                        jPanel23.add(jSplitPane6, BorderLayout.CENTER);
                        jSplitPane6.add(jPanel15, JSplitPane.LEFT);
                        jPanel15.add(jScrollPane16,  BorderLayout.CENTER);
                        jScrollPane16.getViewport().add(jDebugInput, null);
                        jPanel15.add(jLabel5, BorderLayout.NORTH);
                        jSplitPane6.add(jPanel8, JSplitPane.RIGHT);
                        jPanel8.add(jScrollPane17, BorderLayout.CENTER);
                        jScrollPane17.getViewport().add(jDebugOutput, null);
                        jPanel8.add(jLabel14, BorderLayout.NORTH);
                        jToolBar8.add(jDebugRun, null);
                        jToolBar8.add(jDebugClear, null);
                        jTabbedPane4.add(jPanel22,    "Source Code");
                        jTabbedPane4.add(jPanel23,     "Debug");
                        jPanel1.add(jLabel1, null);
                        jPanel1.add(jOutputFile, null);
                        jPanel1.add(jLabel22, null);
                        jPanel1.add(jLabel21, null);
                        jPanel1.add(jInputFile, null);
                        jPanel1.add(jTimeLimit, null);
                        jPanel1.add(jLabel2, null);
                        jPanel1.add(jLabel3, null);
                        jPanel1.add(jAuthor, null);
                        jPanel1.add(jLabel23, null);
                        jPanel1.add(jTitle, null);
                        jSplitPane1.setDividerLocation(180);
                        buttonGroup1.add(jTextPlain);
                        buttonGroup1.add(jTextHtml);
                        jScrollPane1.getViewport().add(jProblemKey, null);
                        jSplitPane3.add(jPanel10, JSplitPane.LEFT);
                        jPanel10.add(jLabel7, BorderLayout.NORTH);
                        jPanel10.add(jScrollPane7, BorderLayout.CENTER);
                        jScrollPane7.getViewport().add(jTestInput, null);
                        jSplitPane3.add(jPanel11, JSplitPane.RIGHT);
                        jPanel11.add(jScrollPane8, BorderLayout.CENTER);
                        jScrollPane8.getViewport().add(jTestOutput, null);
                        jPanel11.add(jLabel8, BorderLayout.NORTH);
                        jPanel12.add(jToolBar6, BorderLayout.NORTH);
                        jToolBar6.add(jLabel20, null);
                        jToolBar6.add(jDifficulty, null);
                        jTabbedPane3.add(jPanel13, "Test Case");
                        jTabbedPane3.add(jPanel14, "Input Format");
                        jPanel14.add(jLabel4, null);
                        jPanel14.add(jInputType, null);
                        jPanel14.add(jLabel11, null);
                        jPanel14.add(jScrollPane10, null);
                        jPanel14.add(jLabel9, null);
                        jPanel14.add(jScrollPane11, null);
                        jPanel14.add(jLabel13, null);
                        jPanel14.add(jInputSeperatorLayout, null);
                        jScrollPane11.getViewport().add(jInputSeperator, null);
                        jScrollPane10.getViewport().add(jInputTerminator, null);
                        jTabbedPane3.add(jPanel16, "Output Format");
                        jPanel16.add(jLabel16, null);
                        jPanel16.add(jLabel15, null);
                        jPanel16.add(jLabel12, null);
                        jPanel16.add(jLabel10, null);
                        jPanel16.add(jScrollPane14, null);
                        jScrollPane14.getViewport().add(jOutputFooter, null);
                        jPanel16.add(jScrollPane13, null);
                        jScrollPane13.getViewport().add(jOutputHeader, null);
                        jPanel16.add(jScrollPane12, null);
                        jScrollPane12.getViewport().add(jOutputSeperator, null);
                        jPanel16.add(jOutputSeperatorLayout, null);
                        jSplitPane3.setDividerLocation(250);
                        jSplitPane2.setDividerLocation(200);
                        jPanel13.add(jSplitPane2, BorderLayout.CENTER);
                        jTabbedPane3.add(jPanel18,  "Format Data");
                        jPanel18.add(jPanel19, BorderLayout.CENTER);
                        jPanel19.add(jToolBar5, BorderLayout.NORTH);
                        jPanel19.add(jSplitPane4, BorderLayout.CENTER);
                        jSplitPane4.add(jPanel20, JSplitPane.LEFT);
                        jPanel20.add(jLabel17, BorderLayout.NORTH);
                        jPanel20.add(jScrollPane9, BorderLayout.CENTER);
                        jScrollPane9.getViewport().add(jFormatInput, null);
                        jSplitPane4.add(jPanel21, JSplitPane.RIGHT);
                        jPanel21.add(jLabel18, BorderLayout.NORTH);
                        jPanel21.add(jScrollPane15, BorderLayout.CENTER);
                        jScrollPane15.getViewport().add(jFormatOutput, null);
                        jToolBar5.add(jFormatDataRefresh, null);
                        jToolBar1.add(jCharEntity, null);
                        jPanel6.add(jToolBar7, BorderLayout.NORTH);
                        jToolBar7.add(jFigureAdd, null);
                        jToolBar7.add(jFigureRemove, null);
                        jTestData.setSelectionModel(selectionTestData);
                        jTestData.setModel(modelTestData);
                        jSplitPane4.setDividerLocation(360);
                        jLanguage.addItem("cpp");
                        jLanguage.addItem("c");
                        jLanguage.addItem("pascal");
                        jLanguage.addItem("java");
                        jSplitPane5.setDividerLocation(400);
                        jScrollSourceCode.setRowHeaderView(jLineNumber);
                        jSplitPane6.setDividerLocation(300);
                        jInputType.addItem("eof");
                        jInputType.addItem("num");
                        jInputType.addItem("zero");
                        jInputSeperatorLayout.addItem("bottom");
                        jInputSeperatorLayout.addItem("top");
                        jInputSeperatorLayout.addItem("middle");
                        jInputSeperatorLayout.addItem("both");
                        jOutputSeperatorLayout.addItem("bottom");
                        jOutputSeperatorLayout.addItem("top");
                        jOutputSeperatorLayout.addItem("middle");
                        jOutputSeperatorLayout.addItem("both");
    }

    //File | Exit action performed
    public void jMenuFileExit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    //Help | About action performed
    public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
        Frame1_AboutBox dlg = new Frame1_AboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                         (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.pack();
        dlg.show();
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            jMenuFileExit_actionPerformed(null);
        }
    }

    void statusBar_showChecked() {
        if (problemArchive.isChecked())
            statusBar.setText("checked: true");
        else
            statusBar.setText("checked: false");
    }

    void jMenuFileTest_actionPerformed(ActionEvent e) {
        Dialog1 dlg = new Dialog1(this, problemArchive);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                         (frmSize.height - dlgSize.height) / 2 + loc.y);

        storeDocument();
        dlg.pack();
        dlg.show();

        statusBar_showChecked();
    }

    void jMenuHelpOption_actionPerformed(ActionEvent e) {
        com.lrf.EnvironmentSetDlg dlg =
                new com.lrf.EnvironmentSetDlg("./Environment.xml", false);

        dlg.pack();
        dlg.show();
    }

    void loadDocument() {
        ProblemBean problem = problemArchive.getProblem();
        TestDataBean testData = problemArchive.getTestData();
        InputFormatBean inputFormat = testData.getInputFormat();
        OutputFormatBean outputFormat = testData.getOutputFormat();
        SolutionBean solution = problemArchive.getSolution();

        jTitle.setText(problemArchive.getTitle());
        jAuthor.setText(problemArchive.getAuthor());

        int tl = testData.getTimeLimit();
        jTimeLimit.setText(Integer.toString(tl));

        jInputFile.setText(testData.getInputFile());
        jOutputFile.setText(testData.getOutputFile());

        if (problem.getContentType().equals("plain"))
            jTextPlain.setSelected(true);
        else
            jTextHtml.setSelected(true);

        curProblemKey = -1;
        jProblemKey.setSelectedIndex(1);
        jProblemKey.setSelectedIndex(0);

        try {
            problem.writeFigureList(imgDir.getPath());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        modelFigure.setRowCount(0);
        Iterator it = problem.getFigureList().iterator();

        while (it.hasNext()) {
            FigureBean bean = new FigureBean( (org.jdom.Element) it.next());
            String[] row = {
                bean.getFilename(), bean.getText()};
            modelFigure.addRow(row);
        }

        try {
            jViewHtml.setText(problemArchive.transform());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        jViewHtml.setCaretPosition(0);

        modelTestData.setRoot(new DefaultMutableTreeNode("TestData"));
        MutableTreeNode root = (MutableTreeNode) modelTestData.getRoot();

        numOfCase = 0;
        curTestCase = -1;

        it = testData.getTestCaseList().iterator();
        while (it.hasNext()) {
            TestCaseBean testCaseBean = new TestCaseBean( (org.jdom.Element) it.next());

            numOfCase++;
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                    "Test Case " + numOfCase);
            node.add(new DefaultMutableTreeNode("Test   Input", false));
            node.add(new DefaultMutableTreeNode("Test Output", false));
            modelTestData.insertNodeInto(node, root, root.getChildCount());
        }
        jDifficulty.setSelectedItem("trivial");
        jTestInput.setText("");
        jTestOutput.setText("");

        jInputType.setSelectedItem(inputFormat.getInputType());
        jInputTerminator.setText(inputFormat.getTerminator());
        jInputSeperatorLayout.setSelectedItem(
                inputFormat.getSeperator().getLayout());
        jInputSeperator.setText(inputFormat.getSeperator().getText());

        jOutputHeader.setText(outputFormat.getHeader());
        jOutputFooter.setText(outputFormat.getFooter());
        jOutputSeperatorLayout.setSelectedItem(
                outputFormat.getSeperator().getLayout());
        jOutputSeperator.setText(outputFormat.getSeperator().getText());

        jFormatInput.setText(testData.getTestInput());
        jFormatOutput.setText(testData.getTestOutput());
        jFormatInput.setCaretPosition(0);
        jFormatOutput.setCaretPosition(0);

        jLanguage.setSelectedItem(solution.getLanguage());
        jSolutionFilename.setText(solution.getFilename());
        jSourceCode.setText(solution.getSourceCode());
        jSourceCode.setCaretPosition(0);

        jInfo.setText("");
        jDebugInput.setText("");
        jDebugInput.setText("");

        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane2.setSelectedIndex(0);
        jTabbedPane3.setSelectedIndex(0);
        jTabbedPane4.setSelectedIndex(0);

        statusBar_showChecked();
        if (workDocument != null)
            setTitle(frameTitle + " - " + workDocument);
        else
            setTitle(frameTitle);

        System.gc();
    }

    void storeProblemValue() {
        ProblemBean bean = problemArchive.getProblem();

        switch (curProblemKey) {
            case 0:
                bean.setDescription(jProblemValue.getText());
                break;
            case 1:
                bean.setInputSpec(jProblemValue.getText());
                break;
            case 2:
                bean.setOutputSpec(jProblemValue.getText());
                break;
        }
    }

    void storeFigureText() {
        int row = jFigure.getSelectedRow();
        if (row == -1)
            return;

        FigureBean bean = problemArchive.getProblem().getFigure(row);
        bean.setText((String)modelFigure.getValueAt(row, 1));
    }

    void storeTestCase() {
        TestDataBean testData = problemArchive.getTestData();

        if (curTestCase != -1) {
            TestCaseBean testCase = testData.getTestCase(curTestCase);

            testCase.setDifficulty((String)jDifficulty.getSelectedItem());
            testCase.setTestInput(jTestInput.getText());
            testCase.setTestOutput(jTestOutput.getText());
        }
    }

    void storeDocument() {
        ProblemBean problem = problemArchive.getProblem();
        TestDataBean testData = problemArchive.getTestData();
        InputFormatBean inputFormat = testData.getInputFormat();
        OutputFormatBean outputFormat = testData.getOutputFormat();
        SolutionBean solution = problemArchive.getSolution();

        problemArchive.setTitle(jTitle.getText());
        problemArchive.setAuthor(jAuthor.getText());

        int tl = 0;
        try {
            String str = jTimeLimit.getText();
            tl = Integer.parseInt(str);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        testData.setTimeLimit(tl);

        testData.setInputFile(jInputFile.getText());
        testData.setOutputFile(jOutputFile.getText());

        if (jTextPlain.isSelected())
            problem.setContentType("plain");
        else
            problem.setContentType("html");

        storeProblemValue();
        storeFigureText();

        inputFormat.setInputType( (String) jInputType.getSelectedItem());
        inputFormat.setTerminator(jInputTerminator.getText());
        inputFormat.getSeperator().setLayout(
                (String) jInputSeperatorLayout.getSelectedItem());
        inputFormat.getSeperator().setText(jInputSeperator.getText());

        outputFormat.setHeader(jOutputHeader.getText());
        outputFormat.setFooter(jOutputFooter.getText());
        outputFormat.getSeperator().setLayout(
                (String) jOutputSeperatorLayout.getSelectedItem());
        outputFormat.getSeperator().setText(jOutputSeperator.getText());

        storeTestCase();

        solution.setLanguage( (String) jLanguage.getSelectedItem());
        solution.setFilename(jSolutionFilename.getText());
        solution.setSourceCode(jSourceCode.getText());

        System.gc();
    }

    void jMenuFileNew_actionPerformed(ActionEvent e) {
        problemArchive = new ProblemArchiveBean();

        workDocument = null;
        loadDocument();
    }

    void jMenuFileOpen_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new XmlFileFilter());

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getPath();
            problemArchive = new ProblemArchiveBean();
            try {
                problemArchive.read(filename);

                workDocument = filename;
                loadDocument();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    String formatFilename(String name, String ext) {
        if (name.toLowerCase().endsWith(ext))
            return name;
        else
            return name + ext;
    }

    void saveAsDocument() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new XmlFileFilter());

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getPath();
            if (chooser.getFileFilter() instanceof XmlFileFilter)
                filename = formatFilename(filename, ".xml");

            try {
                problemArchive.write(filename);

                storeDocument();
                workDocument = filename;
                setTitle(frameTitle + " - " + workDocument);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    void jMenuFileSave_actionPerformed(ActionEvent e) {
        if (workDocument != null) {
            try {
                storeDocument();
                problemArchive.write(workDocument);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            saveAsDocument();
        }
    }

    void jMenuFileSaveAs_actionPerformed(ActionEvent e) {
        saveAsDocument();
    }

    void jProblemKey_valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        storeProblemValue();

        ProblemBean bean = problemArchive.getProblem();
        int position = jProblemKey.getSelectedIndex();

        switch (position) {
            case 0:
                jProblemValue.setText(bean.getDescription());
                break;
            case 1:
                jProblemValue.setText(bean.getInputSpec());
                break;
            case 2:
                jProblemValue.setText(bean.getOutputSpec());
                break;
        }

        jProblemValue.setCaretPosition(0);
        curProblemKey = position;
    }

    void jProblemValue_insertTag(String beginTag, String endTag) {
        String str = jProblemValue.getSelectedText();
        if (str != null)
            jProblemValue.replaceSelection(beginTag + str + endTag);
        else
            jProblemValue.replaceSelection(beginTag);
    }

    void insertImage(String filename, String text) {
        try {
            FigureBean bean = new FigureBean();

            bean.read(filename);
            bean.setText(text);

            if (!problemArchive.getProblem().addFigure(bean))
                return;

            String[] row = {
                bean.getFilename(), bean.getText()};
            modelFigure.addRow(row);

            bean.write(imgDir.getPath());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jTagImg_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new ImageFileFilter());
        FilePreviewer previewer = new FilePreviewer(chooser);
        chooser.setAccessory(previewer);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String imgTag = "<IMG SRC=\"" + file.getName() + "\">";
            jProblemValue_insertTag(imgTag, "</IMG>");

            insertImage(file.getPath(), "");
        }
    }

    void jTagB_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<B>", "</B>");
    }

    void jTagI_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<I>", "</I>");
    }

    void jTagU_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<U>", "</U>");
    }

    void jTagBr_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<BR>", "</BR>");
    }

    void jTagPre_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<PRE>", "</PRE>");
    }

    void jTagSub_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<SUB>", "</SUB>");
    }

    void jTagSup_actionPerformed(ActionEvent e) {
        jProblemValue_insertTag("<SUP>", "</SUP>");
    }

    void jTagFont_actionPerformed(ActionEvent e) {
        Color cr = JColorChooser.showDialog(null, "Font Color", null);
        if (cr != null) {
            int rgb = cr.getRGB() & 0xffffff;
            String hexValue = Integer.toHexString(rgb);
            String fontTag = "<FONT COLOR=\"#" + hexValue + "\">";

            jProblemValue_insertTag(fontTag, "</FONT>");
        }
    }

    void jCharEntity_itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED ||
            jCharEntity.getSelectedIndex() == 0)
            return;

        jProblemValue.replaceSelection((String) jCharEntity.getSelectedItem());
        jCharEntity.setSelectedIndex(0);
    }

    void jFigureAdd_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new ImageFileFilter());
        FilePreviewer previewer = new FilePreviewer(chooser);
        chooser.setAccessory(previewer);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getPath();
            insertImage(filename, "");
        }
    }

    void jFigureRemove_actionPerformed(ActionEvent e) {
        int position = jFigure.getSelectedRow();
        if (position == -1)
            return;

        problemArchive.getProblem().removeFigure(position);
        modelFigure.removeRow(position);
    }

    void jFigure_valueChanged(TableModelEvent e) {
        storeFigureText();
    }

    void jViewRefresh_actionPerformed(ActionEvent e) {
        try {
            storeDocument();
            jViewHtml.setText(problemArchive.transform());
            jViewHtml.setCaretPosition(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jViewSaveAs_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new HtmlFileFilter());

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getPath();
            if (chooser.getFileFilter() instanceof HtmlFileFilter)
                filename = formatFilename(filename, ".htm");

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(filename));
                String str = jViewHtml.getText();
                out.write(str, 0, str.length());
                out.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    void jTestCaseAdd_actionPerformed(ActionEvent e) {
        TestCaseBean bean = new TestCaseBean();
        problemArchive.getTestData().addTestCase(bean);

        numOfCase++;
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                "Test Case " + numOfCase);
        node.add(new DefaultMutableTreeNode("Test   Input", false));
        node.add(new DefaultMutableTreeNode("Test Output", false));

        MutableTreeNode root = (DefaultMutableTreeNode) modelTestData.getRoot();
        modelTestData.insertNodeInto(node, root, root.getChildCount());
    }

    void jTestCaseRemove_actionPerformed(ActionEvent e) {
        TreePath path = selectionTestData.getSelectionPath();
        if (path == null || path.getPathCount() != 2)
            return;

        MutableTreeNode root = (MutableTreeNode) modelTestData.getRoot();
        MutableTreeNode node = (MutableTreeNode) path.getLastPathComponent();
        int position = modelTestData.getIndexOfChild(root, node);

        problemArchive.getTestData().removeTestCase(position);
        modelTestData.removeNodeFromParent(node);
        curTestCase = -1;
    }

    void jTestData_valueChanged(TreeSelectionEvent e) {
        TreePath path = selectionTestData.getSelectionPath();
        if (path == null || path.getPathCount() < 2)
            return;

        MutableTreeNode root = (MutableTreeNode) modelTestData.getRoot();
        MutableTreeNode node = (MutableTreeNode) path.getPathComponent(1);

        int position = modelTestData.getIndexOfChild(root, node);
        if (position == curTestCase)
            return;
        storeTestCase();

        TestDataBean testData = problemArchive.getTestData();
        TestCaseBean testCase = testData.getTestCase(position);

        jDifficulty.setSelectedItem(testCase.getDifficulty());
        jTestInput.setText(testCase.getTestInput());
        jTestOutput.setText(testCase.getTestOutput());
        jTestInput.setCaretPosition(0);
        jTestOutput.setCaretPosition(0);

        curTestCase = position;
    }

    void jFormatDataRefresh_actionPerformed(ActionEvent e) {
        TestDataBean testData = problemArchive.getTestData();
        storeDocument();

        jFormatInput.setText(testData.getTestInput());
        jFormatOutput.setText(testData.getTestOutput());

        jFormatInput.setCaretPosition(0);
        jFormatOutput.setCaretPosition(0);
    }

    void jSolutionOpen_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new SourceFileFilter());

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();

                BufferedReader in = new BufferedReader(new FileReader(file));
                char[] buff = new char[4096];
                int nch;
                StringBuffer text = new StringBuffer();

                nch = in.read(buff, 0, buff.length);
                while (nch != -1) {
                    text.append(buff, 0, nch);
                    nch = in.read(buff, 0, buff.length);
                }
                in.close();

                jSourceCode.setText(new String(text));
                jSourceCode.setCaretPosition(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    void jSourceCode_caretUpdate(CaretEvent e) {
        if (jSourceCode.getLineCount() + 1 == jLineNumber.getLineCount())
            return;

        StringBuffer buf = new StringBuffer();
        for (int i = 1; i <= jSourceCode.getLineCount(); i++) {
            buf.append("   " + i + "   \n");
        }
        jLineNumber.setText(new String(buf));
    }

    void jCompile_actionPerformed(ActionEvent e) {
        storeDocument();
        jInfo.setText("");

        com.sjn.JudgeBean judge = new com.sjn.JudgeBean();
        judge.setEnvironmentBean(new com.lrf.EnvironmentBean("./Environment.xml"));
        judge.setSolutionBean(problemArchive.getSolution());
        judge.setTestDataBean(problemArchive.getTestData());
        judge.judgeCompile();

        com.sjn.ResultBean result = judge.getResultBean();
        jInfo.setText(result.getCompileInfo());
        jInfo.setCaretPosition(0);
    }

    void jDebugRun_actionPerformed(ActionEvent e) {
        storeDocument();
        jInfo.setText("");

        com.sjn.JudgeBean judge = new com.sjn.JudgeBean();
        judge.setEnvironmentBean(new com.lrf.EnvironmentBean("./Environment.xml"));
        judge.setSolutionBean(problemArchive.getSolution());
        judge.setTestDataBean(problemArchive.getTestData());
        judge.setTestInput(jDebugInput.getText());
        judge.judgeTestRun();

        com.sjn.ResultBean result = judge.getResultBean();
        jDebugOutput.setText(result.getRunOutputData());
        jDebugOutput.setCaretPosition(0);
    }

    void jDebugClear_actionPerformed(ActionEvent e) {
        jDebugInput.setText("");
        jDebugOutput.setText("");
    }

    void jSplitPane3_componentResized(ComponentEvent e) {
        jSplitPane3.setDividerLocation(jSplitPane3.getHeight() / 2);
    }

    void jSplitPane4_componentResized(ComponentEvent e) {
        jSplitPane4.setDividerLocation(jSplitPane4.getWidth() / 2);
    }

    void jSplitPane5_componentResized(ComponentEvent e) {
        jSplitPane5.setDividerLocation(jSplitPane5.getHeight() * 8 / 10);
    }

    void jSplitPane6_componentResized(ComponentEvent e) {
        jSplitPane6.setDividerLocation(jSplitPane6.getWidth() / 2);
    }
}