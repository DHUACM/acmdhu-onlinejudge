package cn.edu.dhu.acm.oj.common.paperdistribute;

/**
 * Specifying the name and password of a user.
 * 
 * @author Zhu Kai
 * 
 * @since SVN 94
 */
public class PaperUserID {
    
    /**
     * Constructing a new instance with specific user name and password.
     * 
     * @param userName the user's name.
     * @param password the user's password.
     */
    public PaperUserID(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }
    
    /**
     * Getting the user's name.
     * 
     * @return the user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setting the user's name.
     * 
     * @param userName the user's name.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Getting the user's password.
     * 
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setting the user's password.
     * 
     * @param userName the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
    private String userName;
    private String password;
}
