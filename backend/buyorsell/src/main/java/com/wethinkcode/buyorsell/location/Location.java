package com.wethinkcode.buyorsell.location;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wethinkcode.buyorsell.user.User;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "location")
@EntityListeners(AuditingEntityListener.class)
public class Location {

    private int id;
    private String address_line1;
    private String address_line2;
    private String city;
    private long postal_code;
    private String country;

    public Location() {
		
	}
	
	public Location(String address_line1, String address_line2, String city,long postal_code, String country){
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
        this.city = city;
		this.postal_code = postal_code;
		this.country = country;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user;

    @Column(name = "address_line1", nullable = false)
    public String getAddressLine1() {
        return address_line1;
    }
    
    public void setAddressLine1(String address_line1) {
        this.address_line1 = address_line1;
    }

    @Column(name = "address_line2", nullable = false)
    public String getAddressLine2() {   
        return address_line2;
    }

    public void setAddressLine2(String address_line2) {
        this.address_line2 = address_line2;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "postal_code", nullable = false)
    public long getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(long postal_code) {
        this.postal_code = postal_code;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location [id =" + id + ", user_id =" + user + ", address_line1 =" + address_line1 + 
        ", address_line2 =" + address_line2 + ", city =" + city + ", postal_code =" + postal_code + ", country =" + country + ",]";
    }
}
