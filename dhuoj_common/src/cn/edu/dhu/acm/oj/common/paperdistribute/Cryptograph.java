package cn.edu.dhu.acm.oj.common.paperdistribute;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
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
 * @since SVN 94
 */
public class Cryptograph {

    /**
     * Encrypting some binary data using AES algorithm with specific key.
     * 
     * @param origin The data to be encrypted.
     * @param key The 128-bit key.
     * 
     * @return Bytes of the cipher.
     * 
     * @throws IllegalArgumentException Size of the key is not 16 bytes.
     */
    public static byte[] aesEncrypt( byte[] origin, byte[] key ) {
        if (16 != key.length) {
            throw new IllegalArgumentException(
                "Wrong key size: " + key.length + "!" +
                " Must be 16 bytes." );
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] cipherBytes = cipher.doFinal(origin);
            return cipherBytes;
        } catch (GeneralSecurityException gse) {
            return origin;
        }
    }

    /**
     * Encrypting a data stream using AES algorithm with specific key and
     * outputting to the specific stream.
     * <p/>
     * After encryption, both the input and output streams are
     * {@code close}d.
     *
     * @param originStream Stream of the data to be encrypted.
     * @param key The 128-bit key.
     * @param outputStream The stream to which the cipher is output.
     * 
     * @throws IllegalArgumentException Size of the key is not 16 bytes.
     * @throws IOException if an I/O error occurs.
     */
    public static void aesEncrypt( InputStream originStream, byte[] key,
                                   OutputStream outputStream )
    throws IOException {
        if (16 != key.length) {
            throw new IllegalArgumentException(
                "Wrong key size: " + key.length + "!" +
                " Must be 16 bytes." );
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        CipherOutputStream cipherOutputStream = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            cipherOutputStream =
                new CipherOutputStream(outputStream, cipher);
        } catch (GeneralSecurityException gse) {
            //It can't happen.
        }
        
        int b = originStream.read();
        while (-1 != b) {
            cipherOutputStream.write(b);
            b = originStream.read();
        }
        
        originStream.close();
        cipherOutputStream.close();
    }

    /**
     * Decrypting some binary data using AES algorithm with specific key.
     * 
     * @param cipherBytes The data to be decrypted.
     * @param key The 128-bit key.
     * 
     * @return Bytes of the original data.
     * 
     * @throws IllegalArgumentException Size of the key is not 16 bytes.
     */
    public static byte[] aesDecrypt( byte[] cipherBytes, byte[] key ) {
        if (16 != key.length) {
            throw new IllegalArgumentException(
                "Wrong key size: " + key.length + "!" +
                " Must be 16 bytes." );
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] origin = cipher.doFinal(cipherBytes);
            return origin;
        } catch (GeneralSecurityException gse) {
            return cipherBytes;
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
     * @throws IllegalArgumentException size of the key is not 16 bytes.
     * @throws IOException if an I/O error occurs.
     */
    public static void aesDecrypt( InputStream cipherStream, byte[] key,
                                   OutputStream outputStream )
    throws IOException {
        if (16 != key.length) {
            throw new IllegalArgumentException(
                "Wrong key size: " + key.length + "!" +
                " Must be 16 bytes." );
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        CipherOutputStream cipherOutputStream = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            cipherOutputStream =
                new CipherOutputStream(outputStream, cipher);
        } catch (GeneralSecurityException gse) {
            //It can't happen.
        }
        
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
