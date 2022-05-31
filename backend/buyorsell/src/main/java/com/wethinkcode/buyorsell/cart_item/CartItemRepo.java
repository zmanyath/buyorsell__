package com.wethinkcode.buyorsell.cart_item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Integer>{

    // public CartItem getQuantity();
    
}
