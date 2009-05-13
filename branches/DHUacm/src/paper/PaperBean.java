package paper;

import javax.swing.*;
import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import java.security.*;
import java.security.spec.*;
import problem.*;

public class PaperBean {

    public boolean passFlag;
    private Element root;
    private ArrayList problemList = new ArrayList();
    private Element problemRoot = new Element("ProblemList");
    ;
    private PaperDetail paperDetail = new PaperDetail();

    /**
     *试卷类的构造函数
     **/
    public PaperBean() {
        passFlag = true;
        root = new Element("PaperSetter");
        new Document(root);
        root.setAttribute("checked", "false");
        problemRoot.setAttribute("encrypted", "0");

        root.addContent(paperDetail.getRootElement());
        root.addContent(problemRoot);
    }

    /**
     *试卷类的构造函数
     * elem代表试卷要复制的外界节点节点
     **/
    public PaperBean(Element elem) {
        root = elem;
        new Document(root);
        problemRoot = root.getChild("ProblemList");
        List list = problemRoot.getChildren("ProblemArchive");
        problemList.clear();
        for (int i = 0; i < list.size(); i++) {
            problemList.add(list.get(i));
        }
        paperDetail = new PaperDetail(root.getChild("PaperDetail"));
    }

    /**
     *设置试卷自检位
     * 试卷自检位被设置为checked代表的true或false值
     **/
    public void setCheckSignal(boolean checked) {
        if (checked) {
            root.setAttribute("checked", "true");
        } else {
            root.setAttribute("checked", "false");
        }
    }

    /**
     * 设置试卷的数字签名
     **/
    private void setDigitalSign() {
        XMLOutputter fmt = new XMLOutputter();
        String info = fmt.outputString(problemRoot);
        try {
            java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator.getInstance("DSA");
            SecureRandom secrand = new SecureRandom();
            secrand.setSeed("anada".getBytes()); //初始化随机产生器
            keygen.initialize(512, secrand);     //初始化密钥生成器
            KeyPair keys = keygen.generateKeyPair(); //生成密钥组
            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();

            java.security.Signature signet = java.security.Signature.getInstance("DSA");
            signet.initSign(prikey);
            signet.update(info.getBytes());
            byte[] signed = signet.sign();
            byte[] bobEncodedPubKey = pubkey.getEncoded(); //生成编码
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
     * 验证试卷的数字签名
     * 返回是否验证成功
     **/
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
                System.out.println("签名正常");
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
        System.out.println("签名验证完毕");
        return false;
    }

    /**
     * 保存整张试卷
     * filePath代表要保存到的位置
     * @param filePath
     * @throws Exception
     */
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
     * 得到试卷根节点
     **/
    public Element getRootElement() {
        for (int i = 0; i < problemList.size(); i++) {
            Element elem = (Element) problemList.get(i);
            elem.detach();
        }
        problemRoot.setContent(problemList);
        return root;
    }

    /**
     * 得到试卷内包含的电子程序题的总数
     **/
    public int getProblemCount() {
        return problemList.size();
    }

    /**
     * 向试卷内添加一道电子程序题
     **/
    public void addProblem(ProblemArchiveBean bean) {
        Element elem = bean.getElement();
        elem.detach();
        new Document(elem);
        problemList.add(elem);
    }

    /**
     * 删除试卷内的第index道电子程序题
     **/
    public void removeProblem(int index) {
        problemList.remove(index);
    }

    /**
     * 交换试卷内两道电子程序题的位置
     **/
    public void swapProblem(int a, int b) {
        Element tmpElem = (Element) problemList.get(a);
        problemList.set(a, problemList.get(b));
        problemList.set(b, tmpElem);
    }

    /**
     * 得到试卷内的试卷信息类
     **/
    public PaperDetail getPaperDetail() {
        return paperDetail;
    }

    /**
     * 对XML电子程序题试卷的节点node进行加密
     **/
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
            } catch (Exception e) {
            }
        }
    }

    /**
     * 解密试卷内所有节点
     **/
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
     * 清除试卷内所有试题的dataType类型的测试数据
     * 比如，清除trivial special 数据以后的测试卷是训练器在网络模式下需要的试卷
     **/
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
     * 检查试卷内的所有题目
     * 方便自检程序
     **/
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

    /**
     * 检查试卷完整性
     **/
    public String checkPaperIntegrality() {
        PaperCheckBean checkBean = new PaperCheckBean();
        String tmp = checkBean.checkPaperIntegrality(paperDetail);
        passFlag = checkBean.integralityFlag;
        return tmp;
    }

    /**
     * 检查试卷的DTD配置，预留
     **/
    public String checkPaperDtd() {
        passFlag = true;
        PaperCheckBean checkBean = new PaperCheckBean();
        return checkBean.checkDtd();
    }

    /**
     * 检查试卷内是否所有数据的类型正确
     **/
    public String checkPaperType() {
        PaperCheckBean checkBean = new PaperCheckBean();
        String tmp = checkBean.checkElementType(paperDetail, getProblemCount());
        passFlag = checkBean.typeFlag;
        return tmp;
    }

    /**
     * 检查试卷内试题源代码
     **/
    public String checkPapersolution(ProblemArchiveBean bean) {
        PaperCheckBean checkBean = new PaperCheckBean();
        String tmp = checkBean.checkSolution(bean);
        passFlag = checkBean.solutionFlag;
        return tmp;
    }

    /**
     * Read a paper from a file.
     *
     * @param filePath Path of the paper's XML file.
     *        A path name must be passed in, instead of a URI.
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

        //decypt
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
     * Directory where the paper's XML file is.
     *
     * @author Zhu Kai.
     */
    private String paperDirectory;
}
