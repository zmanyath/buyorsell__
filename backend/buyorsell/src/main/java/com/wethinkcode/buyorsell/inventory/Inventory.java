package com.wethinkcode.buyorsell.inventory;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wethinkcode.buyorsell.product.Product;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "product_inventory")
@EntityListeners(AuditingEntityListener.class)
public class Inventory{

    private int id;
    private long quantity;
    private Date created_at;
    private Date modified_at;
    private Date deleted_at;

    public Inventory() {
    
}
    public Inventory(long quantity, Date created_at,Date modified_at, Date deleted_at) {
    this.quantity = quantity;
    this.created_at = created_at;
    this.modified_at = modified_at;
    this.deleted_at = deleted_at;
}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToOne(mappedBy = "product")
    public Product product;


    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "quantity", nullable= false)
    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity){
        this.quantity = quantity;
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

    @Column(name = "deletd_at", nullable = false)
	public Date getDeletedAt() {
		return deleted_at;
	}
	public void setDeletedAt(Date deleted_at) {
		this.deleted_at = deleted_at;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "ProductInventory [id =" + id + ", quantity=" + quantity + 
        ", created_at =" + created_at + ", modified_at =" + modified_at + ", deleted_at =" + deleted_at +
        "]";
    }
}

