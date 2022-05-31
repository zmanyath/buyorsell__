package com.wethinkcode.buyorsell.category;

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
@Table(name = "product_category")
@EntityListeners(AuditingEntityListener.class)
public class Category{

    private int id;
    private String name;
    private Date created_at;
    private Date modified_at;
    private Date deleted_at;

    public Category() {
		
	}
	
	public Category(String name,Date created_at, Date modified_at, Date deleted_at) {
		this.name = name;
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
    private Product product;

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

    @Column(name = "deleted_at", nullable = false)
	@LastModifiedDate
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
        return "ProductCategory [id =" + id + ", name =" + name +
        ", created_at =" + created_at + ", modified_at =" + modified_at + ", deleted_at =" + deleted_at +
        "]";
    }
}
