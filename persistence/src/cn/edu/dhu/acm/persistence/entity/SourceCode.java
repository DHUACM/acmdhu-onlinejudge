/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yhu
 */
@Entity
@Table(name = "source_code")
@NamedQueries({@NamedQuery(name = "SourceCode.findAll", query = "SELECT s FROM SourceCode s"), @NamedQuery(name = "SourceCode.findBySolutionId", query = "SELECT s FROM SourceCode s WHERE s.solutionId = :solutionId")})
public class SourceCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "solution_id")
    private Integer solutionId;
    @Lob
    @Column(name = "source")
    private String source;

    public SourceCode() {
    }

    public SourceCode(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        if (!(object instanceof SourceCode)) {
            return false;
        }
        SourceCode other = (SourceCode) object;
        if ((this.solutionId == null && other.solutionId != null) || (this.solutionId != null && !this.solutionId.equals(other.solutionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.edu.dhu.acm.persistence.entity.SourceCode[solutionId=" + solutionId + "]";
    }

}
