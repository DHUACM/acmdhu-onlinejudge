package cn.edu.dhu.acm.oj.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class provides some cryptographic functions.
 * 
 * @author Zhu Kai
 *
 * @since SVN 97
 */
public class Cryptograph {

    /**
     * Encrypting some binary data using AES algorithm with specific key.
     * 
     * @param origin The data to be encrypted.
     * @param key The 128-bit key.
     * 
     * @return Bytes of the cipher, or {@code null} if encryption failed.
     * 
     * @throws InvalidKeyException if the given key is not 16-byte.
     */
    public static byte[] aesEncrypt( byte[] origin, byte[] key )
    throws InvalidKeyException {
        Cipher cipher = getAESCipher(key, Cipher.ENCRYPT_MODE);
        try {
            return cipher.doFinal(origin);
        } catch (GeneralSecurityException gse) {
            return null;
        }
    }

    /**
     * Encrypting a data stream using AES algorithm with specific key and
     * outputting to the specific stream.
     * <p/>
     * After encryption, both the output streams is {@code close}d.
     *
     * @param originStream Stream of the data to be encrypted.
     * @param key The 128-bit key.
     * @param outputStream The stream to which the cipher is output.
     * 
     * @throws InvalidKeyException if the given key is not 16-byte.
     * @throws IOException if an I/O error occurs.
     */
    public static void aesEncrypt( InputStream originStream, byte[] key,
                                   OutputStream outputStream )
    throws InvalidKeyException, IOException {
        CipherOutputStream cipherOutputStream = new CipherOutputStream(
            outputStream, getAESCipher(key, Cipher.ENCRYPT_MODE) );
        
        int b = originStream.read();
        while (-1 != b) {
            cipherOutputStream.write(b);
            b = originStream.read();
        }
        
        cipherOutputStream.close();
    }

    /**
     * Decrypting some binary data using AES algorithm with specific key.
     * 
     * @param cipherBytes The data to be decrypted.
     * @param key The 128-bit key.
     * 
     * @return Bytes of the original data, or {@code null} if decryption
     *         failed.
     * 
     * @throws InvalidKeyException if the given key is not 16-byte.
     */
    public static byte[] aesDecrypt( byte[] cipherBytes, byte[] key )
    throws InvalidKeyException {
        Cipher cipher = getAESCipher(key, Cipher.DECRYPT_MODE);
        try {
            return cipher.doFinal(cipherBytes);
        } catch (GeneralSecurityException gse) {
            return null;
        }
    }

    /**
     * Decrypting a data stream using AES algorithm with specific key, and
     * outputting to the specific stream.
     * <p/>
     * After decryption, both the input and output streams are
     * {@code close}d.
     *
     * @param cipherStream stream of the data to be encrypted.
     * @param key the 128-bit AES key.
     * @param outputStream the stream to which the cipher is output.
     * 
     * @throws InvalidKeyException if the given key is not 16-byte.
     * @throws IOException if an I/O error occurs.
     */
    public static void aesDecrypt( InputStream cipherStream, byte[] key,
                                   OutputStream outputStream )
    throws InvalidKeyException, IOException {
        CipherOutputStream cipherOutputStream = new CipherOutputStream(
            outputStream, getAESCipher(key, Cipher.DECRYPT_MODE) );
        
        int b = cipherStream.read();
        while (-1 != b) {
            cipherOutputStream.write(b);
            b = cipherStream.read();
        }
        
        cipherStream.close();
        cipherOutputStream.close();
    }

    /**
     * Getting a 16-bit MD5 digest of an ASCII string.
     * 
     * @param message The string to be digested.
     * 
     * @return The digest of the string.
     */
    public static byte[] stringMD5(String message) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            //It can't happen.
        }

        byte[] digest = messageDigest.digest( message.getBytes(ASCII) );

        return digest;
    }

    /**
     * Getting an initialized cipher object for AES encryption/decryption.
     * 
     * @param key the encryption key, must be 16-byte.
     * @param mode the operation mode of the wanted cipher (this is one of
     *            the following: {@code Cipher.ENCRYPT_MODE}, {@code
     *            Cipher.DECRYPT_MODE}, {@code Cipher.WRAP_MODE} or {@code
     *            Cipher.UNWRAP_MODE})
     * 
     * @return a cipher object with specific key and operation mode.
     * 
     * @throws InvalidKeyException if the given key is not 16-byte.
     * 
     * @since SVN 96
     */
    public static Cipher getAESCipher( byte[] key, int mode )
    throws InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (GeneralSecurityException e) {
            //It can't happen, because the name "AES" is correct.
        }
        
        cipher.init(mode, secretKeySpec);
        
        return cipher;
    }




    /**
     * A stub main method.
     */
    public static void main(String[] args) throws Exception {
        String keyString = args[0];
        byte[] keyBytes = stringMD5(keyString);

        String originString = args[1];
        byte[] originBytes = originString.getBytes(ASCII);

        byte[] cipherBytes = aesEncrypt(originBytes, keyBytes);
        for (byte b : cipherBytes)
            System.out.printf("%02X", b);
        System.out.println();

        byte[] newOriginBytes = aesDecrypt(cipherBytes, keyBytes);
        String newOriginString = new String(newOriginBytes, ASCII);
        System.out.println("\"" + newOriginString + "\"");
    }

    private static final Charset ASCII = Charset.forName("ASCII");
    
}
