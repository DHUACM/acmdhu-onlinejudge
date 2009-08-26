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
@Table(name = "contest_reservation")
@NamedQueries({
    @NamedQuery(name = "ContestReservation.findAll", query = "SELECT c FROM ContestReservation c"),
    @NamedQuery(name = "ContestReservation.findById", query = "SELECT c FROM ContestReservation c WHERE c.id = :id"),
    @NamedQuery(name = "ContestReservation.findByContestId", query = "SELECT c FROM ContestReservation c WHERE c.contestId = :contestId"),
    @NamedQuery(name = "ContestReservation.findByUserId", query = "SELECT c FROM ContestReservation c WHERE c.userId = :userId")
})
public class ContestReservation implements Serializable {
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
    @Column(name = "user_id")
    private String userId;

    public ContestReservation() {
    }

    public ContestReservation(Integer id) {
        this.id = id;
    }

    public ContestReservation(Integer id, int contestId, String userId) {
        this.id = id;
        this.contestId = contestId;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        if (!(object instanceof ContestReservation)) {
            return false;
        }
        ContestReservation other = (ContestReservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhuoj.primeserver.entity.ContestReservation[id=" + id + "]";
    }

}
