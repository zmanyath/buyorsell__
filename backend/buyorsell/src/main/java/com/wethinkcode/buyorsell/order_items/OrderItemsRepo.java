package com.wethinkcode.buyorsell.order_items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Long>{
    
}
