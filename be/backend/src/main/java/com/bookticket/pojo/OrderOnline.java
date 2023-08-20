/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.pojo;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "order_online")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderOnline.findAll", query = "SELECT o FROM OrderOnline o"),
    @NamedQuery(name = "OrderOnline.findById", query = "SELECT o FROM OrderOnline o WHERE o.id = :id"),
    @NamedQuery(name = "OrderOnline.findByCode", query = "SELECT o FROM OrderOnline o WHERE o.code = :code"),
    @NamedQuery(name = "OrderOnline.findByMessage", query = "SELECT o FROM OrderOnline o WHERE o.message = :message"),
    @NamedQuery(name = "OrderOnline.findByUserId", query = "SELECT o FROM OrderOnline o WHERE o.userId = :userId")})
public class OrderOnline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private Integer code;
    @Size(max = 100)
    @Column(name = "message")
    private String message;
    @Size(max = 50)
    @Column(name = "user_id")
    private String userId;

    public OrderOnline() {
    }

    public OrderOnline(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        if (!(object instanceof OrderOnline)) {
            return false;
        }
        OrderOnline other = (OrderOnline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookticket.pojo.OrderOnline[ id=" + id + " ]";
    }
    
}
