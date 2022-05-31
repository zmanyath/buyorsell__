package com.wethinkcode.buyorsell.user_payment;

import lombok.Data;

@Data
public class UserPaymentDTO {

    private long id;
    private String payment_type;
    private String provider;
    private long account_no;
    private long expiry;
    private long cvc;
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return the payment_type
     */
    public String getPayment_type() {
        return payment_type;
    }
    /**
     * @param payment_type the payment_type to set
     */
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }
    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }
    /**
     * @return the account_no
     */
    public long getAccount_no() {
        return account_no;
    }
    /**
     * @param account_no the account_no to set
     */
    public void setAccount_no(long account_no) {
        this.account_no = account_no;
    }
    /**
     * @return the expiry
     */
    public long getExpiry() {
        return expiry;
    }
    /**
     * @param expiry the expiry to set
     */
    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }
    /**
     * @return the cvc
     */
    public long getCvc() {
        return cvc;
    }
    /**
     * @param cvc the cvc to set
     */
    public void setCvc(long cvc) {
        this.cvc = cvc;
    }
    
}
