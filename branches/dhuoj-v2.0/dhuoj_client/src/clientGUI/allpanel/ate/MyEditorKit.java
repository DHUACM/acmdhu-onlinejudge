package clientGUI.allpanel.ate;

import java.awt.*;
import javax.swing.text.*;
import config.Const;

public class MyEditorKit extends DefaultEditorKit {

    class MyEditorView extends PlainView {

        @Override
        protected int drawUnselectedText(Graphics g, int x, int y, int p0, int p1)
                throws BadLocationException {
            Document doc = getDocument();
            Segment segment = new Segment();
            Segment token = new Segment();
            int index = 0;
            int count = p1 - p0;
            char c = '\0';
            doc.getText(p0, count, segment);
            for (int i = 0; i < count; i++) {
                if (Character.isLetter(c = segment.array[segment.offset + i]) || c == '_') {
                    index = i;
                    while (++i < count && (Character.isLetter(c = segment.array[segment.offset + i]) || c == '_' || c >= '0' && c <= '9')) {
                    }
                    doc.getText(p0 + index, i-- - index, token);
                    KeyWord _tmp = kw;
                    if (KeyWord.isKeyWord(token)) {
                        g.setFont(KEYWORDFONT);
                        g.setColor(KEYWORDCOLOR);
                    } else {
                        g.setFont(TEXTFONT);
                        g.setColor(TEXTCOLOR);
                    }
                    x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                    continue;
                }
                if (c >= '0' && c <= '9') {
                    index = i;
                    while (++i < count && (c = segment.array[segment.offset + i]) >= '0' && c <= '9') {
                    }
                    doc.getText(p0 + index, i-- - index, token);
                    g.setColor(NUMCOLOR);
                    x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                    continue;
                }
                if (c == '/') {
                    index = i;
                    if (++i < count && segment.array[segment.offset + i] == '/' && kw.getLanguage().compareToIgnoreCase("Pascal") != 0) {
                        doc.getText(p0 + index, count - index, token);
                        g.setFont(COMMENTFONT);
                        g.setColor(COMMENTCOLOR);
                        x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                        break;
                    }
                    doc.getText(p0 + index, 1, token);
                    g.setFont(TEXTFONT);
                    g.setColor(TEXTCOLOR);
                    x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                    i--;
                    continue;
                }
                if (c == '\'' || c == '"') {
                    index = i;
                    char ch = '\0';
                    label0:
                    do {
                        do {
                            if (++i >= count) {
                                break label0;
                            }
                            if ((ch = segment.array[segment.offset + i]) != '\\') {
                                continue label0;
                            }
                            i++;
                        } while (true);
                    } while (ch != c);
                    if (i >= count) {
                        i = count - 1;
                    }
                    doc.getText(p0 + index, (i - index) + 1, token);
                    g.setFont(STRINGFONT);
                    g.setColor(STRINGCOLOR);
                    x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                    continue;
                }
                if (kw.getLanguage().compareToIgnoreCase("Pascal") == 0 && c == '(') {
                    index = i;
                    if (++i < count && segment.array[segment.offset + i] == '*') {
                        do {
                            if (++i >= count) {
                                break;
                            }
                            c = segment.array[segment.offset + i];
                        } while (c != '*' || ++i >= count || (c = segment.array[segment.offset + i]) != ')');
                        if (i >= count) {
                            i = count - 1;
                        }
                        doc.getText(p0 + index, (i - index) + 1, token);
                        g.setFont(COMMENTFONT);
                        g.setColor(COMMENTCOLOR);
                        x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                    } else {
                        doc.getText(p0 + index, 1, token);
                        g.setFont(TEXTFONT);
                        g.setColor(TEXTCOLOR);
                        x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                        i--;
                    }
                    continue;
                }
                index = i;
                while (++i < count && !Character.isLetter(c = segment.array[segment.offset + i]) && c != '_' && c != '/' && c != '\'' && c != '"' && (c < '0' || c > '9')) {
                }
                doc.getText(p0 + index, i-- - index, token);
                g.setFont(TEXTFONT);
                g.setColor(TEXTCOLOR);
                x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
            }

            return x;
        }

        @Override
        protected int drawSelectedText(Graphics g, int x, int y, int p0, int p1)
                throws BadLocationException {
            g.setFont(TEXTFONT);
            g.setColor(TEXTCOLOR);
            return super.drawSelectedText(g, x, y, p0, p1);
        }
        private Font TEXTFONT;
        private Color TEXTCOLOR;
        private Font NUMFONT;
        private Color NUMCOLOR;
        private Font KEYWORDFONT;
        private Color KEYWORDCOLOR;
        private Font COMMENTFONT;
        private Color COMMENTCOLOR;
        private Font STRINGFONT;
        private Color STRINGCOLOR;

        public MyEditorView(Element element) {

            super(element);
            TEXTFONT = Const.font;
            TEXTCOLOR = Color.black;
            NUMFONT = Const.font;
            NUMCOLOR = new Color(255, 0, 0);
            KEYWORDFONT = new Font(TEXTFONT.getFontName(), 1, TEXTFONT.getSize());
            KEYWORDCOLOR = new Color(0, 0, 255);
            COMMENTFONT = TEXTFONT;
            COMMENTCOLOR = new Color(0, 158, 36);
            STRINGFONT = TEXTFONT;
            STRINGCOLOR = new Color(255, 0, 0);
        }
    }

    class MyViewFactory implements ViewFactory {

        public View create(Element element) {
            return new MyEditorView(element);
        }

        public MyViewFactory() {
        }
    }

    public MyEditorKit() {
        kw = new KeyWord();
    }

    public void setLanguage(String l) {
        kw.setKeyWord(l);
    }

    @Override
    public ViewFactory getViewFactory() {
        return new MyViewFactory();
    }
    private KeyWord kw;
}