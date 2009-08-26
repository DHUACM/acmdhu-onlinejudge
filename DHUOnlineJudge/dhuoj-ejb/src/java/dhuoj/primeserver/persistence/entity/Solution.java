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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "solution")
@NamedQueries({
    @NamedQuery(name = "Solution.findAll", query = "SELECT s FROM Solution s ORDER BY s.solutionId DESC"),
    @NamedQuery(name = "Solution.findBySolutionId", query = "SELECT s FROM Solution s WHERE s.solutionId = :solutionId"),
    @NamedQuery(name = "Solution.findByProblemId", query = "SELECT s FROM Solution s WHERE s.problemId = :problemId"),
    @NamedQuery(name = "Solution.findByUserId", query = "SELECT s FROM Solution s WHERE s.userId = :userId"),
    @NamedQuery(name = "Solution.findByRuntime", query = "SELECT s FROM Solution s WHERE s.runtime = :runtime"),
    @NamedQuery(name = "Solution.findByMemory", query = "SELECT s FROM Solution s WHERE s.memory = :memory"),
    @NamedQuery(name = "Solution.findBySubmitDate", query = "SELECT s FROM Solution s WHERE s.submitDate = :submitDate"),
    @NamedQuery(name = "Solution.findByResult", query = "SELECT s FROM Solution s WHERE s.result = :result"),
    @NamedQuery(name = "Solution.findByLanguage", query = "SELECT s FROM Solution s WHERE s.language = :language"),
    @NamedQuery(name = "Solution.findByContestId", query = "SELECT s FROM Solution s WHERE s.contestId = :contestId ORDER BY s.solutionId"),
    @NamedQuery(name = "Solution.findByValid", query = "SELECT s FROM Solution s WHERE s.valid = :valid"),
    @NamedQuery(name = "Solution.findByLocalJudgeResult", query = "SELECT s FROM Solution s WHERE s.localJudgeResult = :localJudgeResult"),
    @NamedQuery(name = "Solution.findByPercentage", query = "SELECT s FROM Solution s WHERE s.percentage = :percentage"),
    @NamedQuery(name = "Solution.findUnjudgedSolution", query = "SELECT s FROM Solution s WHERE s.result = 0 ORDER BY s.solutionId"),
    @NamedQuery(name = "Solution.findContestLoginStatus", query = "SELECT s FROM Solution s WHERE s.contestId = :contestId AND s.problemId = 1000 ORDER BY s.solutionId")
})
public class Solution implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "SolutionPKGen",
                    table = "sequence",
                    pkColumnName = "seq_name",
                    pkColumnValue = "SolutionPKGen",
                    valueColumnName = "seq_val",
                    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SolutionPKGen")
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
    private int result;
    @Basic(optional = false)
    @Column(name = "language")
    private int language;
    @Basic(optional = false)
    @Column(name = "contest_id")
    private int contestId;
    @Basic(optional = false)
    @Column(name = "valid")
    private int valid;
    @Basic(optional = false)
    @Column(name = "local_judge_result")
    private int localJudgeResult;
    @Basic(optional = false)
    @Column(name = "percentage")
    private int percentage;

    public Solution() {
    }

    public Solution(int problemId, String userId, int runtime, int memory, Date submitDate, int result, int language, int contestId, int valid, int localJudgeResult, int percentage) {
        this.problemId = problemId;
        this.userId = userId;
        this.runtime = runtime;
        this.memory = memory;
        this.submitDate = submitDate;
        this.result = result;
        this.language = language;
        this.contestId = contestId;
        this.valid = valid;
        this.localJudgeResult = localJudgeResult;
        this.percentage = percentage;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getLocalJudgeResult() {
        return localJudgeResult;
    }

    public void setLocalJudgeResult(int localJudgeResult) {
        this.localJudgeResult = localJudgeResult;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
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
        return "dhuoj.primeserver.entity.Solution[solutionId=" + solutionId + "]";
    }

}
