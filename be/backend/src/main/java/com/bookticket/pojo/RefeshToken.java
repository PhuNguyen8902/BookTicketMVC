/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "refesh_token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefeshToken.findAll", query = "SELECT r FROM RefeshToken r"),
    @NamedQuery(name = "RefeshToken.findById", query = "SELECT r FROM RefeshToken r WHERE r.id = :id"),
    @NamedQuery(name = "RefeshToken.findByExpiryDate", query = "SELECT r FROM RefeshToken r WHERE r.expiryDate = :expiryDate"),
    @NamedQuery(name = "RefeshToken.findByToken", query = "SELECT r FROM RefeshToken r WHERE r.token = :token")})
public class RefeshToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Size(max = 255)
    @Column(name = "token")
    private String token;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne
    @JsonIgnore
    private User userId;

    public RefeshToken() {
    }

    public RefeshToken(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
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
        if (!(object instanceof RefeshToken)) {
            return false;
        }
        RefeshToken other = (RefeshToken) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookticket.pojo.RefeshToken[ id=" + id + " ]";
    }
    
}
