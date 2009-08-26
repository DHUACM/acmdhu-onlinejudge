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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "contest")
@NamedQueries({
    @NamedQuery(name = "Contest.findAll", query = "SELECT c FROM Contest c WHERE c.contestId > 0 ORDER BY c.contestId DESC"),
    @NamedQuery(name = "Contest.findByContestId", query = "SELECT c FROM Contest c WHERE c.contestId = :contestId"),
    @NamedQuery(name = "Contest.findByTitle", query = "SELECT c FROM Contest c WHERE c.title = :title"),
    @NamedQuery(name = "Contest.findByStartTime", query = "SELECT c FROM Contest c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "Contest.findByEndTime", query = "SELECT c FROM Contest c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "Contest.findByDefunct", query = "SELECT c FROM Contest c WHERE c.defunct = :defunct"),
    @NamedQuery(name = "Contest.findByPrivate1", query = "SELECT c FROM Contest c WHERE c.private1 = :private1"),
    @NamedQuery(name = "Contest.findByStatus", query = "SELECT c FROM Contest c WHERE c.contestId > 0 AND c.status = :status"),
    @NamedQuery(name = "Contest.findByPaperPath", query = "SELECT c FROM Contest c WHERE c.paperPath = :paperPath"),
    @NamedQuery(name = "Contest.findByPaperKey", query = "SELECT c FROM Contest c WHERE c.paperKey = :paperKey")
})
public class Contest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contest_id")
    private Integer contestId;
    @Column(name = "title")
    private String title;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "defunct")
    private int defunct;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "private")
    private int private1;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Column(name = "paper_path")
    private String paperPath;
    @Column(name = "paper_key")
    private String paperKey;

    public Contest() {
    }

    public Contest(Integer contestId) {
        this.contestId = contestId;
    }

    public Contest(Integer contestId, int defunct, int private1, int status) {
        this.contestId = contestId;
        this.defunct = defunct;
        this.private1 = private1;
        this.status = status;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDefunct() {
        return defunct;
    }

    public void setDefunct(int defunct) {
        this.defunct = defunct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrivate() {
        return private1;
    }

    public void setPrivate(int private1) {
        this.private1 = private1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaperPath() {
        return paperPath;
    }

    public void setPaperPath(String paperPath) {
        this.paperPath = paperPath;
    }

    public String getPaperKey() {
        return paperKey;
    }

    public void setPaperKey(String paperKey) {
        this.paperKey = paperKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contestId != null ? contestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contest)) {
            return false;
        }
        Contest other = (Contest) object;
        if ((this.contestId == null && other.contestId != null) || (this.contestId != null && !this.contestId.equals(other.contestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhuoj.primeserver.entity.Contest[contestId=" + contestId + "]";
    }

}
