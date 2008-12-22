/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.oj.entity;

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
 * @author yhu
 */
@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"), @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"), @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"), @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"), @NamedQuery(name = "Users.findByNick", query = "SELECT u FROM Users u WHERE u.nick = :nick"), @NamedQuery(name = "Users.findBySchool", query = "SELECT u FROM Users u WHERE u.school = :school"), @NamedQuery(name = "Users.findBySubmit", query = "SELECT u FROM Users u WHERE u.submit = :submit"), @NamedQuery(name = "Users.findBySolved", query = "SELECT u FROM Users u WHERE u.solved = :solved"), @NamedQuery(name = "Users.findByDefunct", query = "SELECT u FROM Users u WHERE u.defunct = :defunct"), @NamedQuery(name = "Users.findByIp", query = "SELECT u FROM Users u WHERE u.ip = :ip"), @NamedQuery(name = "Users.findByAccesstime", query = "SELECT u FROM Users u WHERE u.accesstime = :accesstime"), @NamedQuery(name = "Users.findByLanguage", query = "SELECT u FROM Users u WHERE u.language = :language"), @NamedQuery(name = "Users.findByRegTime", query = "SELECT u FROM Users u WHERE u.regTime = :regTime"), @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role")})
public class Users implements Serializable {
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
    private Integer submit;
    @Column(name = "solved")
    private Integer solved;
    @Basic(optional = false)
    @Column(name = "defunct")
    private short defunct;
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

    public Users() {
    }

    public Users(String userId) {
        this.userId = userId;
    }

    public Users(String userId, String password, String nick, String school, short defunct, String ip, int language, int role) {
        this.userId = userId;
        this.password = password;
        this.nick = nick;
        this.school = school;
        this.defunct = defunct;
        this.ip = ip;
        this.language = language;
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

    public short getDefunct() {
        return defunct;
    }

    public void setDefunct(short defunct) {
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.edu.dhu.acm.oj.entity.Users[userId=" + userId + "]";
    }

}
