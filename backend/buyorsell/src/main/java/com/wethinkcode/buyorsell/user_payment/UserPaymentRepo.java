package com.wethinkcode.buyorsell.user_payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentRepo extends JpaRepository<UserPayment, Integer> {
    
}
