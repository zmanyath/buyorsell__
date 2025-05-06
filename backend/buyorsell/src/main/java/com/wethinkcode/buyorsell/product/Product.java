package com.wethinkcode.buyorsell.product;

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

import com.wethinkcode.buyorsell.cart_item.CartItem;
import com.wethinkcode.buyorsell.category.Category;
import com.wethinkcode.buyorsell.inventory.Inventory;
import com.wethinkcode.buyorsell.order_items.OrderItems;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    private int id;
    private String name;
    private int SKU;
    private String product_image;
    private String description;
    private float price;
    private Date created_at;
    private Date modified_at;
    private Date deleted_at;

    public Product() {
		
	}
	
	public Product(String name,int SKU, String product_image, String description, float price, Date created_at, Date modified_at, Date deleted_at) {
		this.name = name;
        this.SKU = SKU;
        this.product_image = product_image;
        this.description = description;
        this.price = price;
		this.created_at = created_at;
		this.modified_at = modified_at;
        this.deleted_at = deleted_at;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToOne(mappedBy = "cart_item")
    public CartItem cart_item;

    @OneToOne(mappedBy = "order_items")
    public OrderItems orderItems;

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

    @Column(name = "SKU", nullable= false)
    public int getSku() {
        return SKU;
    }
    public void setSku(int SKU){
        this.SKU = SKU;
    }

    @Column(name = "product_image", nullable= false)
    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    @Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category product_category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    public Inventory product_inventory;

    @Column(name = "price", nullable= false)
    public float getPrice() {
        return price;
    }
    public void setPrice(float price){
        this.price = price;
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
	public Date getDeletedAt() {
		return deleted_at;
	}
	public void setDeletedAt(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	@Override
	public String toString() {
		return "Product [id =" + id + ", name =" + name + ", SKU =" + SKU + ", category_id =" + product_category + ", product_image='" + product_image + 
		", description =" + description + ", inventory_id ="+ product_inventory +", price =" + price + ", created_at =" + created_at + ", modified_at =" + modified_at +  ", deleted_at =" + deleted_at +
        ",]";
	}
    
}
