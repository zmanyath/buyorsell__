package com.wethinkcode.buyorsell.user_payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserPaymentService {

    private UserPaymentRepo userPaymentRepo;

    @Autowired
    public UserPaymentService(UserPaymentRepo userPaymentRepo) {
        super();
        this.userPaymentRepo = userPaymentRepo;
    }

    public UserPayment saveUserPayment(UserPayment userPayment) {

        return userPaymentRepo.save(userPayment);
    
    }

    public List<UserPayment> getAllPayments() {
        List<UserPayment> userPayments = new ArrayList<UserPayment>();
        for(UserPayment payment : userPaymentRepo.findAll()) {
            userPayments.add(payment);
        }

        return userPayments;
    }

    public void deleteUserPayment(int id) {
        userPaymentRepo.deleteById(id);
    }


    
}
