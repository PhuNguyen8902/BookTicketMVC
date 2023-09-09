/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "ticket2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket2.findAll", query = "SELECT t FROM Ticket2 t"),
    @NamedQuery(name = "Ticket2.findById", query = "SELECT t FROM Ticket2 t WHERE t.id = :id"),
    @NamedQuery(name = "Ticket2.findBySeat", query = "SELECT t FROM Ticket2 t WHERE t.seat = :seat"),
    @NamedQuery(name = "Ticket2.findByPrice", query = "SELECT t FROM Ticket2 t WHERE t.price = :price"),
    @NamedQuery(name = "Ticket2.findByTicketType", query = "SELECT t FROM Ticket2 t WHERE t.ticketType = :ticketType"),
    @NamedQuery(name = "Ticket2.findByCusName", query = "SELECT t FROM Ticket2 t WHERE t.cusName = :cusName"),
    @NamedQuery(name = "Ticket2.findByIsGet", query = "SELECT t FROM Ticket2 t WHERE t.isGet = :isGet")})
public class Ticket2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "seat")
    private Short seat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "ticket_type")
    private Short ticketType;
    @Size(max = 100)
    @Column(name = "cus_name")
    private String cusName;
    @Column(name = "is_get")
    private Short isGet;
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    @ManyToOne
    private Trip tripId;
    @OneToMany(mappedBy = "ticketId")
    private Set<OrderOnline> orderOnlineSet;

    public Ticket2() {
    }

    public Ticket2(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getSeat() {
        return seat;
    }

    public void setSeat(Short seat) {
        this.seat = seat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Short getTicketType() {
        return ticketType;
    }

    public void setTicketType(Short ticketType) {
        this.ticketType = ticketType;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Short getIsGet() {
        return isGet;
    }

    public void setIsGet(Short isGet) {
        this.isGet = isGet;
    }

    public Trip getTripId() {
        return tripId;
    }

    public void setTripId(Trip tripId) {
        this.tripId = tripId;
    }

    @XmlTransient
    public Set<OrderOnline> getOrderOnlineSet() {
        return orderOnlineSet;
    }

    public void setOrderOnlineSet(Set<OrderOnline> orderOnlineSet) {
        this.orderOnlineSet = orderOnlineSet;
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
        if (!(object instanceof Ticket2)) {
            return false;
        }
        Ticket2 other = (Ticket2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookticket.pojo.Ticket2[ id=" + id + " ]";
    }
    
}
