package com.wethinkcode.buyorsell.payment_details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsService {

    private PaymentDetailsRepo paymentDetailsRepo;

    @Autowired
    public PaymentDetailsService(PaymentDetailsRepo paymentDetailsRepo) {
        super();
        this.paymentDetailsRepo = paymentDetailsRepo;
    }

    public PaymentDetails addDetails(PaymentDetails paymentDetails) {
        return paymentDetailsRepo.save(paymentDetails);
    }

    public PaymentDetails getDetails(int id) {
        return paymentDetailsRepo.getById(id);
    }

    public List<PaymentDetails> listPaymentDetails() {
        List<PaymentDetails> details = new ArrayList<PaymentDetails>();
		for(PaymentDetails detail: paymentDetailsRepo.findAll()) {
			details.add(detail);
		}
		return details;
    }
    
    
}
