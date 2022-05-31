package com.wethinkcode.buyorsell.user_payment;

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
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class UserPayment {

    private int id;
    private String payment_type;
    private String provider;
    private long account_no;
    private long expiry;
    private long cvc;

    public UserPayment() {
		
	}
	
	public UserPayment(String payment_type, String provider,long account_no,long expiry, long cvc) {
		// super();
		this.payment_type = payment_type;
        this.provider = provider;
        this.account_no = account_no;
        this.expiry = expiry;
        this.cvc = cvc;
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

    @Column(name = "payment_type", nullable = false)
	public String getPaymentType() {
		return payment_type;
	}
	public void setPaymentType(String payment_type) {
		this.payment_type = payment_type;
	}

    @Column(name = "provider", nullable = false)
    public String getProvider(){
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(name = "account_no", nullable= false)
    public long getAccountNumber() {
        return account_no;
    }
    public void setAccountNumber(long account_no){
        this.account_no = account_no;
    }

    @Column(name = "expiry", nullable= false)
    public long getExpiry() {
        return expiry;
    }
    public void setExpiry(long expiry){
        this.expiry = expiry;
    }

    @Column(name = "cvc", nullable= false)
    public long getCvc() {
        return cvc;
    }
    public void setCvc(long cvc){
        this.cvc = cvc;
    }

    @Override
	public String toString() {
		return "UserPayment [id =" + id + ", user_id =" + user + ", payment_type =" + payment_type + ", provider =" + 
        provider + ", account_no =" + account_no + ", expiry =" + expiry +", cvc =" + cvc +
        ",]";
	}

    
}

