package cn.edu.dhu.acm.oj.common.paper;

import org.jdom.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.security.Key;
import java.security.Security;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

public class PaperEncrypt {
  private Element node;
  private Key key;
  /**
   * �Ծ������Ĺ��캯��
   **/
  public PaperEncrypt() {
    Namespace ns;
    node = new Element("EncryptedData", "", "http://w2c.org/2001/04/xmlenc#");
    node.setAttribute("Type", "http://www.w3.org/2001/04/xmlenc#Element");
    Element elem = new Element("EncryptionMethod");
    elem.setAttribute("Algorithm",
                "http://www.w3.org/2001/04/xmlenc#tripledes-cbc");
    node.addContent(elem);

    ns = Namespace.getNamespace("ds", "http://www.w3.org/2000/09/xmldsig#");
    elem = new Element("KeyInfo", ns);
    elem.addContent(new Element("KeyName"));

    node.addContent(elem);

    elem = new Element("CipherData");
    elem.addContent(new Element("CipherValue"));
    node.addContent(elem);

    initKey();
  }
  /**
   * �Ծ������Ĺ��캯��elem��Ҫ���Ƶ�һ�����Ԫ��
   **/
  public PaperEncrypt(Element elem){
    node = elem;
    initKey();
  }
  /**
   * ��ʼ������key
   **/

  public void initKey(){
    Security.addProvider(new com.sun.crypto.provider.SunJCE());
    try {
      Cipher cipher = Cipher.getInstance("DES");
      KeyGenerator kg = KeyGenerator.getInstance("DES");
      key = kg.generateKey();
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }
    catch (IllegalStateException e) {
      e.printStackTrace();
    }
  }
  /**
   * �õ�����key
   **/
  public String getKey(){
    return Base64.byteArrayToBase64(key.getEncoded());
  }
  /**
   * ���ü���key
   **/
  public void setKey( String keyName ){
    javax.crypto.spec.SecretKeySpec desKey=new
        javax.crypto.spec.SecretKeySpec(Base64.base64ToByteArray(keyName),"DES");
    key = desKey;
  }
  /**
   * �õ��Ծ��ڵ�
   **/
  public Element getRootElement(){
    return node;
  }
  /**
    * ���ñ����浽�Ծ��ڵļ��ܵ���Կ
   **/
  public void setKeyName( String keyName ){
    Namespace ns = Namespace.getNamespace("ds",
        "http://www.w3.org/2000/09/xmldsig#");
    node.getChild("KeyInfo", ns).setChildText("KeyName", keyName);
  }
  /**
    * �õ�Ҫ���浽�Ծ��ڵļ��ܵ���Կ
   **/
  public String getKeyName(){
    Namespace ns = Namespace.getNamespace("ds",
       "http://www.w3.org/2000/09/xmldsig#");
    return node.getChild("KeyInfo", ns).getChildText("KeyName");
  }
  /**
    * ����Ҫ�����ܵ���Ϣ
   **/
  public void setCipherValue( String cipherValue ){
    node.getChild("CipherData").setChildText("CipherValue", cipherValue);
  }
  /**
    * �õ������ܵ���Ϣ
   **/
  public String getCipherValue(){
    return node.getChild("CipherData").getChildText("CipherValue");
  }

  /**
    * ���ַ�str���м��ܣ������ؼ��ܺ���ַ�
   **/
  public String encryptString( String str ) throws Exception{
    Security.addProvider(new com.sun.crypto.provider.SunJCE());

    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(Cipher.ENCRYPT_MODE, key);

    byte[] data = str.getBytes();
    data = cipher.doFinal(data);

    return Base64.byteArrayToBase64(data);
  }
  /**
   * �Լ��ܹ���ַ�str���н��ܣ������ؽ��ܺ���ַ�
   **/
  public String decryptString( String str ) throws Exception{
    Security.addProvider(new com.sun.crypto.provider.SunJCE());

      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.DECRYPT_MODE, key);

      byte[] data = Base64.base64ToByteArray( str );
      return new String(cipher.doFinal(data));
  }
}