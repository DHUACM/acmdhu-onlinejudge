package cn.edu.dhu.acm.oj.common.paperdistribute;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;

import cn.edu.dhu.acm.oj.common.util.Cryptograph;

/**
 * The class provides the function of distributing a paper. One distributed
 * paper is encrypted with a key composed of two parts: the paper's
 * password and the user's password.
 * 
 * @author Zhu Kai
 * 
 * @version SVN 96
 * 
 * @since SVN 93
 */
public class PaperDistributor {

    /**
     * Distributing a paper's XML file to each user. Each user will have a
     * paper file encrypted with a key combined by his own password and a
     * public password. All the encrypted files will be output to the
     * specific directory, each named <i>userName.pae</i> while
     * <i>userName</i> is the name of a user given by the {@code userIDs}
     * parameter.
     * 
     * @param paperFile XML file of the paper to be distributed.
     * @param outputDirectory the directory where the encrypted paper files
     *            are put.
     * @param paperPassword the public password to encrypt/decrypt the
     *            paper.
     * @param userIDs names and passwords of the users to whom the paper is
     *            distributed.
     * 
     * @throws IOException if any error occurs during file writing.
     */
    public static void distributePaper(
        File paperFile, File outputDirectory,
        String paperPassword, PaperUserID[] userIDs )
    throws IOException {
        for (PaperUserID userID: userIDs) {
            encryptPaperFile( paperFile, outputDirectory, paperPassword,
                              userID.getUserName(), userID.getPassword()
                            );
        }
    }

    /**
     * The stub main method.
     */
    public static void main( String[] args ) throws Exception {
        String filename = args[0];
        String dirName = args[1];
        String paperPassword = args[2];
        String userName = args[3];
        String userPassword = args[4];
        
        File paperFile = new File(filename);
        File outputDirectory = new File(dirName);
        
        PaperDistributor.encryptPaperFile( paperFile, outputDirectory,
                                           paperPassword,
                                           userName, userPassword );
        
        File encryptedFile = new File(outputDirectory, userName + ".pae");
        
        PaperDistributor.decryptPaperFile( encryptedFile, outputDirectory,
                                           paperPassword, userPassword);
    }





    /**
     * Encrypting a paper for a specific user. The paper file is encrypted
     * with AES algorithm, and output to the specific directory. The output
     * filename is <i>userName.pae</i> while <i>userName</i> is the name of
     * the user, specified by the {@code userName} parameter. The
     * encryption key is the MD5 digest generated from the concatenation of
     * the paper's password and the user's password.
     * 
     * @param paperFile the paper file to encrypt.
     * @param outputDirectory the directory to put the encrypted file.
     * @param paperPassword the password of the paper.
     * @param userName the user's name.
     * @param userPassword the user's password.
     * 
     * @throws IOException if any error occur during file I/O.
     */
    private static void encryptPaperFile(
        File paperFile, File outputDirectory,
        String paperPassword, String userName, String userPassword)
    throws IOException {
        String concat = paperPassword.concat(userPassword);
        byte[] key = Cryptograph.stringMD5(concat);
        
        if ( !outputDirectory.isDirectory() ) {
            outputDirectory.mkdirs();
        }
        
        File outFile = new File(outputDirectory, userName + ".pae");
        
        BufferedInputStream inputStream = new BufferedInputStream(
            new FileInputStream(paperFile) );
        BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(outFile) );
        
        try {
            Cryptograph.aesEncrypt(inputStream, key, outputStream);
        } catch (InvalidKeyException ike) {
            // It can't happen, the key must be valid.
            ike.printStackTrace();
        }
    }





    /**
     * Decrypting a paper, just for test.
     */
    private static void decryptPaperFile(
        File encryptedFile, File outputDirectory,
        String paperPassword, String userPassword)
    throws IOException {
        String concat = paperPassword.concat(userPassword);
        byte[] key = Cryptograph.stringMD5(concat);
        
        if ( !outputDirectory.isDirectory() ) {
            outputDirectory.mkdirs();
        }
        
        File outFile = new File(outputDirectory, "recover");
        
        BufferedInputStream inputStream = new BufferedInputStream(
            new FileInputStream(encryptedFile) );
        BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(outFile) );
        
        try {
            Cryptograph.aesDecrypt(inputStream, key, outputStream);
        } catch (InvalidKeyException ike) {
            // It can't happen, the key must be valid.
            ike.printStackTrace();
        }
    }

}
