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
@Table(name = "problem")
@NamedQueries({@NamedQuery(name = "Problem.findAll", query = "SELECT p FROM Problem p"), @NamedQuery(name = "Problem.findByProblemId", query = "SELECT p FROM Problem p WHERE p.problemId = :problemId"), @NamedQuery(name = "Problem.findBySource", query = "SELECT p FROM Problem p WHERE p.source = :source"), @NamedQuery(name = "Problem.findByTitle", query = "SELECT p FROM Problem p WHERE p.title = :title"), @NamedQuery(name = "Problem.findByInputPath", query = "SELECT p FROM Problem p WHERE p.inputPath = :inputPath"), @NamedQuery(name = "Problem.findByOutputPath", query = "SELECT p FROM Problem p WHERE p.outputPath = :outputPath"), @NamedQuery(name = "Problem.findByStdcodePath", query = "SELECT p FROM Problem p WHERE p.stdcodePath = :stdcodePath"), @NamedQuery(name = "Problem.findByCreateDate", query = "SELECT p FROM Problem p WHERE p.createDate = :createDate"), @NamedQuery(name = "Problem.findByTimeLimit", query = "SELECT p FROM Problem p WHERE p.timeLimit = :timeLimit"), @NamedQuery(name = "Problem.findByCaseTimeLimit", query = "SELECT p FROM Problem p WHERE p.caseTimeLimit = :caseTimeLimit"), @NamedQuery(name = "Problem.findByMemoryLimit", query = "SELECT p FROM Problem p WHERE p.memoryLimit = :memoryLimit"), @NamedQuery(name = "Problem.findByDefunct", query = "SELECT p FROM Problem p WHERE p.defunct = :defunct"), @NamedQuery(name = "Problem.findByContestId", query = "SELECT p FROM Problem p WHERE p.contestId = :contestId"), @NamedQuery(name = "Problem.findByAccepted", query = "SELECT p FROM Problem p WHERE p.accepted = :accepted"), @NamedQuery(name = "Problem.findBySubmit", query = "SELECT p FROM Problem p WHERE p.submit = :submit"), @NamedQuery(name = "Problem.findByError", query = "SELECT p FROM Problem p WHERE p.error = :error"), @NamedQuery(name = "Problem.findByDifficulty", query = "SELECT p FROM Problem p WHERE p.difficulty = :difficulty"), @NamedQuery(name = "Problem.findBySubmitUser", query = "SELECT p FROM Problem p WHERE p.submitUser = :submitUser"), @NamedQuery(name = "Problem.findBySolvedUser", query = "SELECT p FROM Problem p WHERE p.solvedUser = :solvedUser")})
public class Problem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "problem_id")
    private Integer problemId;
    @Column(name = "source")
    private String source;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "input_path")
    private String inputPath;
    @Column(name = "output_path")
    private String outputPath;
    @Column(name = "stdcode_path")
    private String stdcodePath;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "time_limit")
    private int timeLimit;
    @Basic(optional = false)
    @Column(name = "case_time_limit")
    private int caseTimeLimit;
    @Basic(optional = false)
    @Column(name = "memory_limit")
    private int memoryLimit;
    @Basic(optional = false)
    @Column(name = "defunct")
    private short defunct;
    @Column(name = "contest_id")
    private Integer contestId;
    @Column(name = "accepted")
    private Integer accepted;
    @Column(name = "submit")
    private Integer submit;
    @Column(name = "error")
    private Integer error;
    @Basic(optional = false)
    @Column(name = "difficulty")
    private short difficulty;
    @Column(name = "submit_user")
    private Integer submitUser;
    @Column(name = "solved_user")
    private Integer solvedUser;

    public Problem() {
    }

    public Problem(Integer problemId) {
        this.problemId = problemId;
    }

    public Problem(Integer problemId, String title, int timeLimit, int caseTimeLimit, int memoryLimit, short defunct, short difficulty) {
        this.problemId = problemId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.caseTimeLimit = caseTimeLimit;
        this.memoryLimit = memoryLimit;
        this.defunct = defunct;
        this.difficulty = difficulty;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getStdcodePath() {
        return stdcodePath;
    }

    public void setStdcodePath(String stdcodePath) {
        this.stdcodePath = stdcodePath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getCaseTimeLimit() {
        return caseTimeLimit;
    }

    public void setCaseTimeLimit(int caseTimeLimit) {
        this.caseTimeLimit = caseTimeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public short getDefunct() {
        return defunct;
    }

    public void setDefunct(short defunct) {
        this.defunct = defunct;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public short getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(Integer submitUser) {
        this.submitUser = submitUser;
    }

    public Integer getSolvedUser() {
        return solvedUser;
    }

    public void setSolvedUser(Integer solvedUser) {
        this.solvedUser = solvedUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (problemId != null ? problemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Problem)) {
            return false;
        }
        Problem other = (Problem) object;
        if ((this.problemId == null && other.problemId != null) || (this.problemId != null && !this.problemId.equals(other.problemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.edu.dhu.acm.oj.entity.Problem[problemId=" + problemId + "]";
    }

}
