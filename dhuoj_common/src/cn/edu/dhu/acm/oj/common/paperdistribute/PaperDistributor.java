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
 * @version SVN 107
 * 
 * @since SVN 93
 */
public class PaperDistributor {
    
    /**
     * The main method to encrypt a file.
     */
    public static void main( String[] args ) {
        if (3 != args.length) {
            System.err.println(
                "Usage java cn.edu.dhu.acm.oj.common.paperdistribute" +
                ".PaperDistributor filename output-directory password");
            
            System.exit(1);
        }
        try {
            File paperFile = new File( args[0] );
            File outputDirectory = new File( args[1] );
            String password = args[2];
            
            distributePaper(paperFile, outputDirectory, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    /**
     * Encrypting a paper file with a password. The paper file is encrypted
     * with AES algorithm, and output to the specific directory. The output
     * filename is like the original paper filename, only the extension
     * name changed to {@code pae}.
     * 
     * @param paperFile XML file of the paper to be distributed.
     * @param outputDirectory the directory where the encrypted paper file
     *            are put.
     * @param password the password to encrypt/decrypt the paper.
     * 
     * @throws IOException if any error occurs during file writing.
     */
    public static void distributePaper(
        File paperFile, File outputDirectory, String password)
    throws IOException {
        // Changing the filename extension.
        String filename = paperFile.getName();
        int dotIndex = filename.lastIndexOf('.');
        if ( -1 != dotIndex ) {
            filename = filename.substring(0, dotIndex + 1) + "pae";
        }
        File outFile = new File(outputDirectory, filename);
        
        encryptFile(paperFile, outFile, password);
    }
    
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
     * Encrypting a file with a password, using AES algorithm. The result
     * file is output to the specific directory, and the output filename is
     * like the original paper filename, only the extension name changed to
     * {@code pae}.The encryption key is the MD5 digest generated from the
     * password.
     * 
     * @param inFile the file to encrypt.
     * @param outFile the output file.
     * @param password the password used in encryption.
     * 
     * @throws IOException if any error occurs during file I/O.
     */
    private static void encryptFile(
        File inFile, File outFile, String password)
    throws IOException {
        byte[] key = Cryptograph.stringMD5(password);
        
        File dir = outFile.getParentFile();
        if ( !dir.isDirectory() ) {
            dir.mkdirs();
        }
        
        BufferedInputStream inputStream = new BufferedInputStream(
            new FileInputStream(inFile) );
        BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(outFile) );
        
        try {
            Cryptograph.aesEncrypt(inputStream, key, outputStream);
        } catch (InvalidKeyException ike) {
            // It can't happen, the key must be valid.
            ike.printStackTrace();
        }
        
        inputStream.close();
        //the output stream is closed by the aesEncrypt method, so needn't
        //close it here.
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
     * @throws IOException if any error occurs during file I/O.
     */
    private static void encryptPaperFile(
        File paperFile, File outputDirectory,
        String paperPassword, String userName, String userPassword)
    throws IOException {
        String filename = userName + ".pae";
        File outFile = new File(outputDirectory, filename);
        String concat = paperPassword.concat(userPassword);
        encryptFile(paperFile, outFile, concat);
    }

}
