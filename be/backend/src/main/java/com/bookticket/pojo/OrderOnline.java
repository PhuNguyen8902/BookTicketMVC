/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.pojo;

import com.bookticket.enums.Payment;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "order_online")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderOnline.findAll", query = "SELECT o FROM OrderOnline o"),
    @NamedQuery(name = "OrderOnline.findById", query = "SELECT o FROM OrderOnline o WHERE o.id = :id"),
    @NamedQuery(name = "OrderOnline.findByPayment", query = "SELECT o FROM OrderOnline o WHERE o.payment = :payment"),
    @NamedQuery(name = "OrderOnline.findByOrderDate", query = "SELECT o FROM OrderOnline o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "OrderOnline.findByPrice", query = "SELECT o FROM OrderOnline o WHERE o.price = :price")})
public class OrderOnline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
//    @Size(max = 100)
    @Column(name = "payment")
    @Enumerated(EnumType.STRING)

    private Payment payment;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @JoinColumn(name = "increased_price_id", referencedColumnName = "id")
    @ManyToOne
    private IncreasedPrice increasedPriceId;
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @ManyToOne
    private Ticket2 ticketId;
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne
    private User empId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public IncreasedPrice getIncreasedPriceId() {
        return increasedPriceId;
    }

    public void setIncreasedPriceId(IncreasedPrice increasedPriceId) {
        this.increasedPriceId = increasedPriceId;
    }

    public Ticket2 getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket2 ticketId) {
        this.ticketId = ticketId;
    }

    public User getEmpId() {
        return empId;
    }

    public void setEmpId(User empId) {
        this.empId = empId;
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
