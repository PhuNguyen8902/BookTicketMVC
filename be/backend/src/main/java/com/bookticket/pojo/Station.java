/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author vegar
 */
@Entity
@Table(name = "station")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s"),
    @NamedQuery(name = "Station.findById", query = "SELECT s FROM Station s WHERE s.id = :id"),
    @NamedQuery(name = "Station.findByName", query = "SELECT s FROM Station s WHERE s.name = :name"),
    @NamedQuery(name = "Station.findByDescription", query = "SELECT s FROM Station s WHERE s.description = :description"),
    @NamedQuery(name = "Station.findByIsActive", query = "SELECT s FROM Vehicle s WHERE s.isActive = :isActive")})
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "is_active")
    private Short isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "endStationId")
    private Set<Route> routeSet;
    @JsonIgnore
    @OneToMany(mappedBy = "startStationId")
    private Set<Route> routeSet1;
    @JsonIgnore
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne
    private Address addressId;
    @JsonIgnore
    @OneToMany(mappedBy = "stationId")
    private Set<StationRoute> stationRouteSet;

    public Station() {
    }

    public Station(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Set<Route> getRouteSet() {
        return routeSet;
    }

    public void setRouteSet(Set<Route> routeSet) {
        this.routeSet = routeSet;
    }

    @XmlTransient
    public Set<Route> getRouteSet1() {
        return routeSet1;
    }

    public void setRouteSet1(Set<Route> routeSet1) {
        this.routeSet1 = routeSet1;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    @XmlTransient
    public Set<StationRoute> getStationRouteSet() {
        return stationRouteSet;
    }

    public void setStationRouteSet(Set<StationRoute> stationRouteSet) {
        this.stationRouteSet = stationRouteSet;
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
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bookticket.pojo.Station[ id=" + id + " ]";
    }
    
}
