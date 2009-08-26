/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dhuoj.primeserver.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.solved DESC"),
    @NamedQuery(name = "User.findByUserIdAndPassword", query = "SELECT u FROM User u WHERE u.userId = :userId AND u.password = :password"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByNick", query = "SELECT u FROM User u WHERE u.nick = :nick"),
    @NamedQuery(name = "User.findBySchool", query = "SELECT u FROM User u WHERE u.school = :school"),
    @NamedQuery(name = "User.findBySubmit", query = "SELECT u FROM User u WHERE u.submit = :submit"),
    @NamedQuery(name = "User.findBySolved", query = "SELECT u FROM User u WHERE u.solved = :solved"),
    @NamedQuery(name = "User.findByDefunct", query = "SELECT u FROM User u WHERE u.defunct = :defunct"),
    @NamedQuery(name = "User.findByIp", query = "SELECT u FROM User u WHERE u.ip = :ip"),
    @NamedQuery(name = "User.findByAccesstime", query = "SELECT u FROM User u WHERE u.accesstime = :accesstime"),
    @NamedQuery(name = "User.findByLanguage", query = "SELECT u FROM User u WHERE u.language = :language"),
    @NamedQuery(name = "User.findByRegTime", query = "SELECT u FROM User u WHERE u.regTime = :regTime"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "nick")
    private String nick;
    @Basic(optional = false)
    @Column(name = "school")
    private String school;
    @Column(name = "submit")
    private Integer submit = 0;
    @Column(name = "solved")
    private Integer solved = 0;
    @Basic(optional = false)
    @Column(name = "defunct")
    private int defunct = 0;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;
    @Column(name = "accesstime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accesstime;
    @Basic(optional = false)
    @Column(name = "language")
    private int language;
    @Column(name = "reg_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regTime;
    @Basic(optional = false)
    @Column(name = "role")
    private int role;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String password, String email, String nick,
            String school, String ip, Date accesstime, Date regTime, int role) {
       this.userId = userId;
       this.password = password;
       this.email = email;
       this.nick = nick;
       this.school = school;
       this.ip = ip;
       this.accesstime = accesstime;
       this.regTime = regTime;
       this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public int getDefunct() {
        return defunct;
    }

    public void setDefunct(int defunct) {
        this.defunct = defunct;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhuoj.primeserver.entity.User[userId=" + userId + "]";
    }

}
