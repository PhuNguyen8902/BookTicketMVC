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
@Table(name = "increased_price")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncreasedPrice.findAll", query = "SELECT i FROM IncreasedPrice i"),
    @NamedQuery(name = "IncreasedPrice.findById", query = "SELECT i FROM IncreasedPrice i WHERE i.id = :id"),
    @NamedQuery(name = "IncreasedPrice.findByEventName", query = "SELECT i FROM IncreasedPrice i WHERE i.eventName = :eventName"),
    @NamedQuery(name = "IncreasedPrice.findByIncreasedPercentage", query = "SELECT i FROM IncreasedPrice i WHERE i.increasedPercentage = :increasedPercentage")})
public class IncreasedPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "increased_percentage")
    private Short increasedPercentage;

    public IncreasedPrice() {
    }

    public IncreasedPrice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Short getIncreasedPercentage() {
        return increasedPercentage;
    }

    public void setIncreasedPercentage(Short increasedPercentage) {
        this.increasedPercentage = increasedPercentage;
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
        if (!(object instanceof IncreasedPrice)) {
            return false;
        }
        IncreasedPrice other = (IncreasedPrice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookticket.pojo.IncreasedPrice[ id=" + id + " ]";
    }
    
}
