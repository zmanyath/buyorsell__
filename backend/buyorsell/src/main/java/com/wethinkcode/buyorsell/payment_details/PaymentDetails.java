package com.wethinkcode.buyorsell.payment_details;

import java.util.Date;

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

import com.wethinkcode.buyorsell.order_details.OrderDetails;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "payment_details")
@EntityListeners(AuditingEntityListener.class)
public class PaymentDetails {

    private int id;
    private float amount;
    private String provider;
    private String status;
    private Date created_at;
    private Date modified_at;

    public PaymentDetails() {

    }
    
    public PaymentDetails(float amount,String provider, String status,Date created_at, Date modified_at) {
        this.amount = amount;
        this.provider = provider;
        this.status = status;
        this.created_at = created_at;
        this.modified_at = modified_at;
        
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
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderDetails order_details;

    @OneToOne(mappedBy = "order_details")
    private PaymentDetails payment_details;


    @Column(name = "amount", nullable= false)
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }

    @Column(name = "provider", nullable = false)
    public String getProvider(){
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(name = "status", nullable = false)
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "created_at", nullable = false)
	@CreatedDate
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
	
	@Column(name = "modified_at", nullable = false)
	@LastModifiedDate
	public Date getModifiedAt() {
		return modified_at;
	}
	public void setModifiedAt(Date modified_at) {
		this.modified_at = modified_at;
	}

    @Override
	public String toString() {
		return "PaymentDetails [id =" + id + ", order_id =" + order_details + ", amount =" + amount + ", provider =" + provider + ", status =" + status +
        ", created_at =" + created_at + ", modified_at =" + modified_at +
        ",]";
	}
}
