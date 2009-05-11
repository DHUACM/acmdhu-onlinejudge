package com.zxp;

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
   * 试卷加密类的构造函数
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
   * 试卷加密类的构造函数，elem是要复制的一个外界元素
   **/
  public PaperEncrypt(Element elem){
    node = elem;
    initKey();
  }
  /**
   * 初始化加密key
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
   * 得到加密key
   **/
  public String getKey(){
    return Base64.byteArrayToBase64(key.getEncoded());
  }
  /**
   * 设置加密key
   **/
  public void setKey( String keyName ){
    javax.crypto.spec.SecretKeySpec desKey=new
        javax.crypto.spec.SecretKeySpec(Base64.base64ToByteArray(keyName),"DES");
    key = desKey;
  }
  /**
   * 得到试卷根节点
   **/
  public Element getRootElement(){
    return node;
  }
  /**
    * 设置被保存到试卷内的加密的密钥
   **/
  public void setKeyName( String keyName ){
    Namespace ns = Namespace.getNamespace("ds",
        "http://www.w3.org/2000/09/xmldsig#");
    node.getChild("KeyInfo", ns).setChildText("KeyName", keyName);
  }
  /**
    * 得到要保存到试卷内的加密的密钥
   **/
  public String getKeyName(){
    Namespace ns = Namespace.getNamespace("ds",
       "http://www.w3.org/2000/09/xmldsig#");
    return node.getChild("KeyInfo", ns).getChildText("KeyName");
  }
  /**
    * 设置要被加密的信息
   **/
  public void setCipherValue( String cipherValue ){
    node.getChild("CipherData").setChildText("CipherValue", cipherValue);
  }
  /**
    * 得到被加密的信息
   **/
  public String getCipherValue(){
    return node.getChild("CipherData").getChildText("CipherValue");
  }

  /**
    * 对字符串str进行加密，并返回加密后的字符串
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
   * 对加密过的字符串str进行解密，并返回解密后的字符串
   **/
  public String decryptString( String str ) throws Exception{
    Security.addProvider(new com.sun.crypto.provider.SunJCE());

      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.DECRYPT_MODE, key);

      byte[] data = Base64.base64ToByteArray( str );
      return new String(cipher.doFinal(data));
  }
}