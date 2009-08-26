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
@Table(name = "problem")
@NamedQueries({
    @NamedQuery(name = "Problem.findAll", query = "SELECT p FROM Problem p ORDER BY p.problemId ASC"),
    @NamedQuery(name = "Problem.findByProblemId", query = "SELECT p FROM Problem p WHERE p.problemId = :problemId"),
    @NamedQuery(name = "Problem.findBySource", query = "SELECT p FROM Problem p WHERE p.source = :source"),
    @NamedQuery(name = "Problem.findByTitle", query = "SELECT p FROM Problem p WHERE p.title = :title"),
    @NamedQuery(name = "Problem.findByProblemPath", query = "SELECT p FROM Problem p WHERE p.problemPath = :problemPath"),
    @NamedQuery(name = "Problem.findByInputPath", query = "SELECT p FROM Problem p WHERE p.inputPath = :inputPath"),
    @NamedQuery(name = "Problem.findByOutputPath", query = "SELECT p FROM Problem p WHERE p.outputPath = :outputPath"),
    @NamedQuery(name = "Problem.findByStdcodePath", query = "SELECT p FROM Problem p WHERE p.stdcodePath = :stdcodePath"),
    @NamedQuery(name = "Problem.findByCreateDate", query = "SELECT p FROM Problem p WHERE p.createDate = :createDate"),
    @NamedQuery(name = "Problem.findByTimeLimit", query = "SELECT p FROM Problem p WHERE p.timeLimit = :timeLimit"),
    @NamedQuery(name = "Problem.findByCaseTimeLimit", query = "SELECT p FROM Problem p WHERE p.caseTimeLimit = :caseTimeLimit"),
    @NamedQuery(name = "Problem.findByMemoryLimit", query = "SELECT p FROM Problem p WHERE p.memoryLimit = :memoryLimit"),
    @NamedQuery(name = "Problem.findByDefunct", query = "SELECT p FROM Problem p WHERE p.defunct = :defunct"),
    @NamedQuery(name = "Problem.findByAccepted", query = "SELECT p FROM Problem p WHERE p.accepted = :accepted"),
    @NamedQuery(name = "Problem.findBySubmit", query = "SELECT p FROM Problem p WHERE p.submit = :submit"),
    @NamedQuery(name = "Problem.findByDifficulty", query = "SELECT p FROM Problem p WHERE p.difficulty = :difficulty"),
    @NamedQuery(name = "Problem.findBySpecial", query = "SELECT p FROM Problem p WHERE p.special = :special"),
    @NamedQuery(name = "Problem.findByCategory", query = "SELECT p FROM Problem p WHERE p.category = :category")
})
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
    @Column(name = "problem_path")
    private String problemPath;
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
    private int defunct;
    @Column(name = "accepted")
    private Integer accepted;
    @Column(name = "submit")
    private Integer submit;
    @Basic(optional = false)
    @Column(name = "difficulty")
    private int difficulty;
    @Basic(optional = false)
    @Column(name = "special")
    private int special;
    @Basic(optional = false)
    @Column(name = "category")
    private int category;

    public Problem() {
    }

    public Problem(Integer problemId) {
        this.problemId = problemId;
    }

    public Problem(Integer problemId, String title, int timeLimit, int caseTimeLimit, int memoryLimit, int defunct, int difficulty, int special, int category) {
        this.problemId = problemId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.caseTimeLimit = caseTimeLimit;
        this.memoryLimit = memoryLimit;
        this.defunct = defunct;
        this.difficulty = difficulty;
        this.special = special;
        this.category = category;
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

    public String getProblemPath() {
        return problemPath;
    }

    public void setProblemPath(String problemPath) {
        this.problemPath = problemPath;
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

    public int getDefunct() {
        return defunct;
    }

    public void setDefunct(int defunct) {
        this.defunct = defunct;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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
        return "dhuoj.primeserver.entity.Problem[problemId=" + problemId + "]";
    }

}
