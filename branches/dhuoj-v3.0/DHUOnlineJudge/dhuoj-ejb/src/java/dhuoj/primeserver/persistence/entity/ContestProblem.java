/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dhuoj.primeserver.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "contest_problem")
@NamedQueries({
    @NamedQuery(name = "ContestProblem.findAll", query = "SELECT c FROM ContestProblem c"),
    @NamedQuery(name = "ContestProblem.findById", query = "SELECT c FROM ContestProblem c WHERE c.id = :id"),
    @NamedQuery(name = "ContestProblem.findByContestId", query = "SELECT c FROM ContestProblem c WHERE c.contestId = :contestId ORDER BY c.sequence ASC"),
    @NamedQuery(name = "ContestProblem.findByProblemId", query = "SELECT c FROM ContestProblem c WHERE c.problemId = :problemId"),
    @NamedQuery(name = "ContestProblem.findByTitle", query = "SELECT c FROM ContestProblem c WHERE c.title = :title"),
    @NamedQuery(name = "ContestProblem.findByAccepted", query = "SELECT c FROM ContestProblem c WHERE c.accepted = :accepted"),
    @NamedQuery(name = "ContestProblem.findBySubmit", query = "SELECT c FROM ContestProblem c WHERE c.submit = :submit"),
    @NamedQuery(name = "ContestProblem.findBySequence", query = "SELECT c FROM ContestProblem c WHERE c.sequence = :sequence")
})
public class ContestProblem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "contest_id")
    private int contestId;
    @Basic(optional = false)
    @Column(name = "problem_id")
    private int problemId;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "accepted")
    private int accepted;
    @Basic(optional = false)
    @Column(name = "submit")
    private int submit;
    @Basic(optional = false)
    @Column(name = "sequence")
    private int sequence;

    public ContestProblem() {
    }

    public ContestProblem(Integer id) {
        this.id = id;
    }

    public ContestProblem(Integer id, int contestId, int problemId, String title, int accepted, int submit, int sequence) {
        this.id = id;
        this.contestId = contestId;
        this.problemId = problemId;
        this.title = title;
        this.accepted = accepted;
        this.submit = submit;
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getSubmit() {
        return submit;
    }

    public void setSubmit(int submit) {
        this.submit = submit;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContestProblem)) {
            return false;
        }
        ContestProblem other = (ContestProblem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhuoj.primeserver.entity.ContestProblem[id=" + id + "]";
    }

}
