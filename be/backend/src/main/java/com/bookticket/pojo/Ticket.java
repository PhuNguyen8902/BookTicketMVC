///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.bookticket.pojo;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//
///**
// *
// * @author ADMIN
// */
//@Entity
//@Table(name = "ticket")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
//    @NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
//    @NamedQuery(name = "Ticket.findBySeat", query = "SELECT t FROM Ticket t WHERE t.seat = :seat"),
//    @NamedQuery(name = "Ticket.findByPrice", query = "SELECT t FROM Ticket t WHERE t.price = :price"),
//    @NamedQuery(name = "Ticket.findByIsActive", query = "SELECT t FROM Ticket t WHERE t.isActive = :isActive"),
//    @NamedQuery(name = "Ticket.findByIsGet", query = "SELECT t FROM Ticket t WHERE t.isGet = :isGet"),
//    @NamedQuery(name = "Ticket.findByType", query = "SELECT t FROM Ticket t WHERE t.type = :type"),
//    @NamedQuery(name = "Ticket.findByPayment", query = "SELECT t FROM Ticket t WHERE t.payment = :payment"),
//    @NamedQuery(name = "Ticket.findByDate", query = "SELECT t FROM Ticket t WHERE t.date = :date"),
//
//    @NamedQuery(name = "Ticket.findByName", query = "SELECT t FROM Ticket t WHERE t.name = :name")
//  
//
//})
//
//public class Ticket implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "seat")
//    private Short seat;
//    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "price")
//    private Double price;
//    @Column(name = "is_active")
//    private Short isActive;
//
//     @Column(name = "is_get")
//
//
//    private Short isGet;
//    @Size(max = 100)
//    @Column(name = "type")
//    private String type;
//    @Size(max = 100)
//    @Column(name = "payment")
//    private String payment;
//    @Column(name = "date")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date date;
//    @Size(max = 100)
//    @Column(name = "name")
//    private String name;
////    @Size(max = 100)
////    @Column(name = "employee_name")
////    private String employeeName;
//    @JoinColumn(name = "increased_price_id", referencedColumnName = "id")
//    @ManyToOne
//    private IncreasedPrice increasedPriceId;
//    @JoinColumn(name = "trip_id", referencedColumnName = "id")
//    @ManyToOne
//    private Trip tripId;
//    @JoinColumn(name = "employee_id", referencedColumnName = "id")
//    @ManyToOne
//    private User employeeId;
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @ManyToOne
//    private User userId;
//
//    public Ticket() {
//    }
//
//    public Ticket(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Short getSeat() {
//        return seat;
//    }
//
//    public void setSeat(Short seat) {
//        this.seat = seat;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Short getIsActive() {
//        return isActive;
//    }
//
//    public void setIsActive(Short isActive) {
//        this.isActive = isActive;
//    }
//     public Short getIsGet() {
//        return isGet;
//    }
//
//
//    public void setIsGet(Short isGet) {
//        this.isGet = isGet;
//    }
//
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getPayment() {
//        return payment;
//    }
//
//    public void setPayment(String payment) {
//        this.payment = payment;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
////    public String getEmployeeName() {
////        return employeeName;
////    }
////
////    public void setEmployeeName(String employeeName) {
////        this.employeeName = employeeName;
////    }
//    public IncreasedPrice getIncreasedPriceId() {
//        return increasedPriceId;
//    }
//
//    public void setIncreasedPriceId(IncreasedPrice increasedPriceId) {
//        this.increasedPriceId = increasedPriceId;
//    }
//
//    public Trip getTripId() {
//        return tripId;
//    }
//
//    public void setTripId(Trip tripId) {
//        this.tripId = tripId;
//    }
//
//    public User getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(User employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    public User getUserId() {
//        return userId;
//    }
//
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Ticket)) {
//            return false;
//        }
//        Ticket other = (Ticket) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.bookticket.pojo.Ticket[ id=" + id + " ]";
//    }
//
//}
