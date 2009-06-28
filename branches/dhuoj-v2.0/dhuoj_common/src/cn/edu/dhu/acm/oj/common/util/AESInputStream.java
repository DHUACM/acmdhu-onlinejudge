package cn.edu.dhu.acm.oj.common.util;

import java.awt.image.CropImageFilter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * With help of this stream, reading data encrypted with AES algorithm
 * is just like reading plain binary data.
 * 
 * @author Zhu Kai
 * 
 * @since SVN 96
 */
public class AESInputStream extends CipherInputStream {

    /**
     * Constructing an AES input stream by giving the stream of cipher and the
     * key.
     * 
     * @param in the input stream containing the cipher.
     * @param key the encryption key of the cipher.
     * 
     * @throws InvalidKeyException if the given key is not 16-byte. 
     */
    public AESInputStream(InputStream in, byte[] key)
    throws InvalidKeyException {
        super( in, Cryptograph.getAESCipher(key, Cipher.DECRYPT_MODE) );
    }

}
