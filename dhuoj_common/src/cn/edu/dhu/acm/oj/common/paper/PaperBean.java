package cn.edu.dhu.acm.oj.common.paper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.common.problem.ProblemArchiveBean;
import cn.edu.dhu.acm.oj.common.problem.TestDataBean;

public class PaperBean {

    public boolean passFlag;
    private Element root;
    
    private Element problemRoot = new Element("ProblemList");
    private PaperDetail paperDetail = new PaperDetail();

    @SuppressWarnings("unchecked")
    private ArrayList problemList = new ArrayList();





    /**
     * Constructing an empty paper.
     */
    public PaperBean() {
        passFlag = true;
        root = new Element("PaperSetter");
        
        //TODO This looks ugly, going to delete.
        new Document(root);
        
        root.setAttribute("checked", "false");
        problemRoot.setAttribute("encrypted", "0");

        root.addContent(paperDetail.getRootElement());
        root.addContent(problemRoot);
    }

    /**
     * Constructing a paper from an XML element.
     */
    public PaperBean(Element elem) {
        root = elem;
        
        //TODO This looks ugly, going to delete.
        new Document(root);
        
        problemRoot = root.getChild("ProblemList");
        List list = problemRoot.getChildren("ProblemArchive");
        problemList.clear();
        for (int i = 0; i < list.size(); i++) {
            problemList.add( list.get(i) );
        }
        paperDetail = new PaperDetail(root.getChild("PaperDetail"));
    }

    /**
     * Setting this paper has been checked or not.
     */
    public void setCheckSignal(boolean checked) {
        if (checked) {
            root.setAttribute("checked", "true");
        } else {
            root.setAttribute("checked", "false");
        }
    }

    /**
     * Setting the digital signature of this paper.
     */
    private void setDigitalSign() {
        XMLOutputter fmt = new XMLOutputter();
        String info = fmt.outputString(problemRoot);
        try {
            java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator.getInstance("DSA");
            SecureRandom secrand = new SecureRandom();
            secrand.setSeed("anada".getBytes());
            keygen.initialize(512, secrand);
            KeyPair keys = keygen.generateKeyPair();
            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();

            java.security.Signature signet = java.security.Signature.getInstance("DSA");
            signet.initSign(prikey);
            signet.update(info.getBytes());
            byte[] signed = signet.sign();
            byte[] bobEncodedPubKey = pubkey.getEncoded();
            String str = Base64.byteArrayToBase64(bobEncodedPubKey);

            paperDetail.setPublicKey(str);
            paperDetail.setSignature(Base64.byteArrayToBase64(signed));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("InvalidKeyException");
        } catch (SignatureException e) {
            System.out.println("SignatureException");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    /**
     * Checking whether this paper has been modified based on the signature.
     */
    private boolean vertifySignature() {
        try {
            String str = paperDetail.getPublicKey();
            byte[] bobEncodedPubKey = Base64.base64ToByteArray(str);
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");

            XMLOutputter fmt = new XMLOutputter();
            String info = fmt.outputString(problemRoot);
            PublicKey pubkey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.base64ToByteArray(paperDetail.getSignature());
//System.out.println(info);
            java.security.Signature signetcheck = java.security.Signature.getInstance("DSA");
            signetcheck.initVerify(pubkey);
            signetcheck.update(info.getBytes());

            if (signetcheck.verify(signed)) {
                System.out.println("Verify signed");
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("InvalidKeyException");
        } catch (SignatureException e) {
            System.out.println("SignatureException");
        } catch (Exception e) {
            System.out.println("Open Exception");
        }
        System.out.println("vertifySignature");
        return false;
    }

    /**
     * Saving this paper to a file.
     * 
     * @param filePath The filename to save.
     * 
     * @deprecated this method writes the XML file with the system default
     *             character set, but always writes the XML header as:<br />
     *             {@code <?xml version="1.0" encoding="gb2312"?>}<br />
     *             So if the system's default character set is not GB2312,
     *             the output file is wrong.
     *             
     * @see #savePaper
     */
    @Deprecated
    public void marshal(String filePath) throws Exception {
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            elem.detach();
        }
        problemRoot.setContent(problemList);
        setDigitalSign();
        root.marshal(filePath);
    }
    
    /**
     * Getting the root XML element of this paper.
     */
    public Element getRootElement() {
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            elem.detach();
        }
        problemRoot.setContent(problemList);
        return root;
    }

    /**
     * Getting the number of problems in this paper.
     **/
    public int getProblemCount() {
        return problemList.size();
    }

    /**
     * Adding a problem into paper.
     **/
    public void addProblem(ProblemArchiveBean bean) {
        Element elem = bean.getElement();
        elem.detach();
        
        //TODO This looks ugly, going to delete.
        new Document(elem);
        
        problemList.add(elem);
    }

    /**
     * Removing the problem at the specified index.
     **/
    public void removeProblem(int index) {
        problemList.remove(index);
    }

    /**
     * Swaping the order of two problems.
     */
    public void swapProblem(int a, int b) {
        Element tmpElem = (Element) problemList.get(a);
        problemList.set(a, problemList.get(b));
        problemList.set(b, tmpElem);
    }

    public PaperDetail getPaperDetail() {
        return paperDetail;
    }

    /**
     * Encrypting the element named &ldquo;node&rdquo; in each problem.
     *
     * @param node Name of the elements to be encrypted.
     */
    public void encryptNode(String node) {
        int count = Integer.parseInt(problemRoot.getAttributeValue("encrypted"));
        count++;
        problemRoot.setAttribute("encrypted", Integer.toString(count));


        for (int i = 0; i < problemList.size(); i++) {
            PaperEncrypt paperEncrypt = new PaperEncrypt();
            Element elem = (Element) problemList.get(i);

            try {
                XMLOutputter fmt = new XMLOutputter();
                String str = fmt.outputString(elem.getChild(node));
                str = paperEncrypt.encryptString(str);

                paperEncrypt.setKeyName(paperEncrypt.getKey());
                paperEncrypt.setCipherValue(str);

                elem.removeChild(node);
                Element eRoot = paperEncrypt.getRootElement();
                elem.addContent(eRoot);
            } catch (Exception e) {}
        }
    }

    /**
     * Decrypting all elements in the paper.
     */
    public void decryptNode() {
        int count = Integer.parseInt(problemRoot.getAttributeValue("encrypted"));
        problemRoot.setAttribute("encrypted", "0");

        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);

            for (int j = 0; j < count; j++) {
                try {
                    Namespace ns = Namespace.getNamespace("", "http://w2c.org/2001/04/xmlenc#");
                    PaperEncrypt paperEncrypt =
                            new PaperEncrypt(elem.getChild("EncryptedData", ns));

                    String keyName = paperEncrypt.getKeyName();
                    paperEncrypt.setKey(keyName);
                    String str = paperEncrypt.getCipherValue();

                    str = paperEncrypt.decryptString(str);

                    elem.removeChild("EncryptedData", ns);

                    SAXBuilder ix = new SAXBuilder();
                    Document dc = ix.build(new StringReader(str));

                    elem.addContent(dc.getRootElement().detach());
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Removing all test data of the given type.
     *
     * @param dataType The type name of the test data to be removed.
     *                 e.g. &ldquo;sample&rdquo;, &ldquo;trivial&rdquo;
     *                 of &ldquo;special&rdquo;.
     */
    public void clearTestData(String dataType) {
        //dataType = "sample","trivial","special"
        int count = getProblemCount();
        for (int i = 0; i < count; i++) {
            TestDataBean testDataBean = getProblemAt(i).getTestData();
            int testCount = testDataBean.getTestCaseCount();
            for (int j = testCount - 1; j >= 0; j--) {
                if (testDataBean.getTestCase(j).getDifficulty().equals(dataType)) {
                    testDataBean.removeTestCase(j);
                }
            }
        }
    }

    /**
     * Checking all the problems in the paper.
     */
    public boolean checkPaperProblems() {
        JFrame frame = new JFrame();

        int passFlag = -1;
        int count = getProblemCount();

        for (int i = 0; i < count; i++) {
            PaperCheckBean checkBean = new PaperCheckBean();
            if (!checkBean.checkProblem(getProblemAt(i))) {
                passFlag = i;
                String message = "Alert: Problem" + Integer.toString(passFlag) + " Not Pass the Paper Check!!";
                JOptionPane.showMessageDialog(frame, message);
            } else {
                JOptionPane.showMessageDialog(frame, "Pass " + Integer.toString(i));
            }
        }

        String message;
        if (passFlag == -1) {
            message = "Congratulations! Pass Paper Check";
            JOptionPane.showMessageDialog(frame, message);
            return true;
        } else {
            message = "Alert: Problem" + Integer.toString(passFlag) + " Not Pass the Paper Check!!";
            JOptionPane.showMessageDialog(frame, message);
            return false;
        }
    }

    public String checkPaperIntegrality() {
        PaperCheckBean checkBean = new PaperCheckBean();
        String tmp = checkBean.checkPaperIntegrality(paperDetail);
        passFlag = checkBean.integralityFlag;
        return tmp;
    }

    public String checkPaperDtd() {
        passFlag = true;
        PaperCheckBean checkBean = new PaperCheckBean();
        return checkBean.checkDtd();
    }


    public String checkPaperType() {
        PaperCheckBean checkBean = new PaperCheckBean();
        String tmp = checkBean.checkElementType(paperDetail, getProblemCount());
        passFlag = checkBean.typeFlag;
        return tmp;
    }

    public String checkPapersolution(ProblemArchiveBean bean) {
        PaperCheckBean checkBean = new PaperCheckBean();
        String tmp = checkBean.checkSolution(bean);
        passFlag = checkBean.solutionFlag;
        return tmp;
    }

    /**
     * Read a paper from a file.
     * 
     * @param filePath Path of the paper's XML file. A path name must be
     *            passed in, instead of a URI.
     * 
     * @author Zhou Xiaopeng, Zhu Kai.
     */
    public void unmarshal(String filePath)
            throws Exception {
        File paperFile = new File(filePath);
        if (!paperFile.isAbsolute()) {
            paperFile = paperFile.getAbsoluteFile();
        }

        this.paperDirectory = paperFile.getParent();

        String fileURI = paperFile.toURI().toASCIIString();

        root = Element.unmarshal(fileURI);
        problemRoot = root.getChild("ProblemList");
        paperDetail = new PaperDetail(root.getChild("PaperDetail"));

        vertifySignature();

        List list = problemRoot.getChildren("ProblemArchive");
        problemList.clear();
        for (int i = 0; i < list.size(); i++) {
            problemList.add( list.get(i) );
        }

        String tmpDir = System.getProperty("java.io.tmpdir");
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            ProblemArchiveBean bean = new ProblemArchiveBean(elem);
            bean.getProblem().writeFigureList(tmpDir);

            elem.detach();
            
            new Document(elem);
        }

        //decrypt
        String tmp = problemRoot.getAttributeValue("encrypted");
        if ( !tmp.equals("0") ) {
            decryptNode();
        }
    }

    /**
     * Reading the default paper.
     *
     * @author Zhou Xiaopeng, Sun Cihai.
     */
    public void unmarshal()
            throws Exception {
        InputStream in = getClass().getResourceAsStream(
                             "/cn/edu/dhu/acm/oj/common/resource/" +
                             Const.INITPAPER
                         );
        root = Element.unmarshal(in);
        problemRoot = root.getChild("ProblemList");
        paperDetail = new PaperDetail(root.getChild("PaperDetail"));

        vertifySignature();

        List list = problemRoot.getChildren("ProblemArchive");
        problemList.clear();
        for (int i = 0; i < list.size(); i++) {
            problemList.add(list.get(i));
        }

        String tmpDir = System.getProperty("java.io.tmpdir");
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            ProblemArchiveBean bean = new ProblemArchiveBean(elem);
            bean.getProblem().writeFigureList(tmpDir);

            elem.detach();
            
            new Document(elem);
        }

        //decrypt
        String tmp = problemRoot.getAttributeValue("encrypted");
        if (!tmp.equals("0")) {
            decryptNode();
        }
    }

    /**
     * Get the problem at index {@code ind} in this paper.
     *
     * @author Zhou Xiaopeng, Zhu Kai.
     */
    public ProblemArchiveBean getProblemAt(int ind) {
        Element elem = (Element) problemList.get(ind);
        ProblemArchiveBean prob = new ProblemArchiveBean(elem);
        prob.setArchiveDirectory(this.paperDirectory);
        return prob;
    }

    /**
     * Saving this paper to an XML file with the system default character
     * set. This is a substitution of {@link #marshal marshal()}.
     * 
     * @param filename the filename to save.
     * @throws IOException if any I/O error occurs.
     * 
     * @author Zhu Kai
     * 
     * @since SVN 98
     */
    public void savePaper(String filename)
    throws IOException {
        updateProblemList();
        
        setDigitalSign();
        
        Charset defaultCharset = Charset.defaultCharset();
        
        Writer out = new OutputStreamWriter(
            new BufferedOutputStream( new FileOutputStream(filename) ),
            defaultCharset
        );
        
        XMLOutputter xmloutputter = new XMLOutputter();
        xmloutputter.setEncoding( defaultCharset.name() );
        xmloutputter.setNewlines(true);
        xmloutputter.setIndent("    ");
        xmloutputter.setTextNormalize(true);
        
        xmloutputter.output( this.root.getDocument(), out );
    }
    
    /**
     * Reading a paper from a stream of XML. Note that a paper read from a
     * stream doesn't have a {@link #getPaperDirectory paper directory}, so
     * it doesn't support external test case files.
     * 
     * @param in the input stream.
     * 
     * @throws JDOMException if error occurs in parsing.
     * @throws IOException if I/O error occurs.
     * 
     * @author Zhu Kai
     * 
     * @since SVN 98
     */
    @SuppressWarnings("unchecked")
    public void readPaper(InputStream in)
    throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(in);
        
        this.root = document.getRootElement();
        this.problemRoot = root.getChild("ProblemList");
        
        this.problemList.clear();
        this.problemList.addAll(
            this.problemRoot.getChildren("ProblemArchive") );
        
        this.paperDetail =
            new PaperDetail( this.root.getChild("PaperDetail") );
        
        vertifySignature();
        
        String tmpDir = System.getProperty("java.io.tmpdir");
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            ProblemArchiveBean bean = new ProblemArchiveBean(elem);
            bean.getProblem().writeFigureList(tmpDir);
            
            this.problemRoot.removeContent(elem);
            
            /*
             * This line is necessary, though the reason is unknown! The
             * ProblemArchiveBean.transform() doesn't work without this.
             */
            new Document(elem);
        }

        //decrypt
        String tmp = problemRoot.getAttributeValue("encrypted");
        if ( !tmp.equals("0") ) {
            decryptNode();
        }
    }
    
    /**
     * Return the directory of the paper's XML file.
     *
     * @author Zhu Kai.
     */
    public String getPaperDirectory() {
        return this.paperDirectory;
    }

    /**
     * Set the directory of the paper's XML file.
     *
     * @param dir Path of the directory to be set.
     *
     * @author Zhu Kai.
     */
    public void setPaperDirectory(String dir) {
        this.paperDirectory = dir;
    }





    /**
     * Replacing the old problem list with the edited problem list stored in
     * member {@link #problemList problemList}.
     * 
     * @author Zhu Kai
     * 
     * @since SVN 93
     */
    private void updateProblemList() {
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            this.problemRoot.removeContent(elem);
        }
        this.problemRoot.setContent(this.problemList);
    }





    /**
     * Directory where the paper's XML file is.
     *
     * @author Zhu Kai.
     */
    private String paperDirectory;
}
