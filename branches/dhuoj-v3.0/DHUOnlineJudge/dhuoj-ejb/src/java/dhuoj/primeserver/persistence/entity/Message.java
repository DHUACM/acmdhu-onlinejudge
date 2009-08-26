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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "message")
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByMessageId", query = "SELECT m FROM Message m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "Message.findByUserId", query = "SELECT m FROM Message m WHERE m.userId = :userId"),
    @NamedQuery(name = "Message.findByStatus", query = "SELECT m FROM Message m WHERE m.status = :status ORDER BY m.messageId ASC")
})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "MessagePKGen",
                    table = "sequence",
                    pkColumnName = "seq_name",
                    pkColumnValue = "MessagePKGen",
                    valueColumnName = "seq_val",
                    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MessagePKGen")
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Lob
    @Column(name = "question")
    private String question;
    @Lob
    @Column(name = "response")
    private String response;
    @Basic(optional = false)
    @Column(name = "status")
    private int status = 0;

    public Message() {
    }

    public Message(String userId, String question, String response) {
       this.userId = userId;
       this.question = question;
       this.response = response;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhuoj.primeserver.entity.Message[messageId=" + messageId + "]";
    }

}
