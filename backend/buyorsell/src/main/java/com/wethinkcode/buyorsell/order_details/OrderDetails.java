package com.wethinkcode.buyorsell.order_details;

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

import com.wethinkcode.buyorsell.payment_details.PaymentDetails;
import com.wethinkcode.buyorsell.user.User;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "order_details")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetails {

    private int id;
    private float total;
    private Date created_at;
    private Date modified_at;

    public OrderDetails() {

    }

    public OrderDetails(float total, Date created_at, Date modified_at){
        this.total = total;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user;

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "payment_details")
    public OrderDetails order_details;

    @Column(name = "total", nullable= false)
    public float getTotal() {
        return total;
    }
    public void setTotal(float total){
        this.total = total;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    public PaymentDetails payment_id;

    	
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "OrderDetails [id =" + id + ", user_id=" + user + ", total=" + total + ", payment_id =" + payment_id  +  
        ", created_at=" + created_at + ", modified_at=" + modified_at + "]";        
        
    }
}
