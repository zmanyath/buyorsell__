package com.wethinkcode.buyorsell.user;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wethinkcode.buyorsell.location.Location;
import com.wethinkcode.buyorsell.order_details.OrderDetails;
import com.wethinkcode.buyorsell.user_payment.UserPayment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "[user]")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private long phone_number;
    private String password;
    private Date created_at;
    private Date modified_at;

	public User() {
	}
	
	public User(String first_name, String last_name, String email,long phone_number, String password, Date created_at, Date modified_at) {
		// super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

    // public User() {
    // }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

	@OneToOne(mappedBy = "location")
    private Location location;

	@OneToOne(mappedBy = "payment")
	private UserPayment userPayment;

	@OneToOne(mappedBy = "order_details")
	private OrderDetails orderDetails;

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone_number", nullable = false)
	public long getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(long phone_number) {
		this.phone_number = phone_number;
	}

	@Transient
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "created_at", nullable = true)
	@CreatedDate
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
	
	@Column(name = "modified_at")
	@LastModifiedDate
	public Date getModified_at() {
		return modified_at;
	}
	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}

	@Override
	public String toString() {
		return "User [id =" + id + ", first_name =" + first_name + ", last_name =" + last_name + ", email =" + email + ", phone_number =" + phone_number +
		", password =" + password + ", created_at =" + created_at + ", modified_at =" + modified_at + ",]";
	}


}

