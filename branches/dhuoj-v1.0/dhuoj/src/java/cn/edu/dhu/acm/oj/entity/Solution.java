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
@Table(name = "solution")
@NamedQueries({@NamedQuery(name = "Solution.findAll", query = "SELECT s FROM Solution s"), @NamedQuery(name = "Solution.findBySolutionId", query = "SELECT s FROM Solution s WHERE s.solutionId = :solutionId"), @NamedQuery(name = "Solution.findByProblemId", query = "SELECT s FROM Solution s WHERE s.problemId = :problemId"), @NamedQuery(name = "Solution.findByUserId", query = "SELECT s FROM Solution s WHERE s.userId = :userId"), @NamedQuery(name = "Solution.findByRuntime", query = "SELECT s FROM Solution s WHERE s.runtime = :runtime"), @NamedQuery(name = "Solution.findByMemory", query = "SELECT s FROM Solution s WHERE s.memory = :memory"), @NamedQuery(name = "Solution.findBySubmitDate", query = "SELECT s FROM Solution s WHERE s.submitDate = :submitDate"), @NamedQuery(name = "Solution.findByResult", query = "SELECT s FROM Solution s WHERE s.result = :result"), @NamedQuery(name = "Solution.findByLanguage", query = "SELECT s FROM Solution s WHERE s.language = :language"), @NamedQuery(name = "Solution.findByContestId", query = "SELECT s FROM Solution s WHERE s.contestId = :contestId"), @NamedQuery(name = "Solution.findByValid", query = "SELECT s FROM Solution s WHERE s.valid = :valid"), @NamedQuery(name = "Solution.findByCodeLength", query = "SELECT s FROM Solution s WHERE s.codeLength = :codeLength")})
public class Solution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "solution_id")
    private Integer solutionId;
    @Basic(optional = false)
    @Column(name = "problem_id")
    private int problemId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "runtime")
    private int runtime;
    @Basic(optional = false)
    @Column(name = "memory")
    private int memory;
    @Basic(optional = false)
    @Column(name = "submit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;
    @Basic(optional = false)
    @Column(name = "result")
    private short result;
    @Basic(optional = false)
    @Column(name = "language")
    private short language;
    @Column(name = "contest_id")
    private Integer contestId;
    @Basic(optional = false)
    @Column(name = "valid")
    private short valid;
    @Basic(optional = false)
    @Column(name = "code_length")
    private int codeLength;

    public Solution() {
    }

    public Solution(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Solution(Integer solutionId, int problemId, String userId, int runtime, int memory, Date submitDate, short result, short language, short valid, int codeLength) {
        this.solutionId = solutionId;
        this.problemId = problemId;
        this.userId = userId;
        this.runtime = runtime;
        this.memory = memory;
        this.submitDate = submitDate;
        this.result = result;
        this.language = language;
        this.valid = valid;
        this.codeLength = codeLength;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public short getResult() {
        return result;
    }

    public void setResult(short result) {
        this.result = result;
    }

    public short getLanguage() {
        return language;
    }

    public void setLanguage(short language) {
        this.language = language;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public short getValid() {
        return valid;
    }

    public void setValid(short valid) {
        this.valid = valid;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solutionId != null ? solutionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solution)) {
            return false;
        }
        Solution other = (Solution) object;
        if ((this.solutionId == null && other.solutionId != null) || (this.solutionId != null && !this.solutionId.equals(other.solutionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.edu.dhu.acm.oj.entity.Solution[solutionId=" + solutionId + "]";
    }

}
