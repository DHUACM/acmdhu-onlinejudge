/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dhuoj.primeserver.persistence.entity;

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
 * @author Administrator
 */
@Entity
@Table(name = "compileinfo")
@NamedQueries({
    @NamedQuery(name = "Compileinfo.findAll", query = "SELECT c FROM Compileinfo c"),
    @NamedQuery(name = "Compileinfo.findBySolutionId", query = "SELECT c FROM Compileinfo c WHERE c.solutionId = :solutionId")
})
public class Compileinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "solution_id")
    private Integer solutionId;
    @Lob
    @Column(name = "error")
    private String error;

    public Compileinfo() {
    }

    public Compileinfo(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
        if (!(object instanceof Compileinfo)) {
            return false;
        }
        Compileinfo other = (Compileinfo) object;
        if ((this.solutionId == null && other.solutionId != null) || (this.solutionId != null && !this.solutionId.equals(other.solutionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhuoj.primeserver.entity.Compileinfo[solutionId=" + solutionId + "]";
    }

}
