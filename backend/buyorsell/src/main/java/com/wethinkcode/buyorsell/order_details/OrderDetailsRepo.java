package com.wethinkcode.buyorsell.order_details;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {

    // OrderDetails getTotal(float total);
    
}
