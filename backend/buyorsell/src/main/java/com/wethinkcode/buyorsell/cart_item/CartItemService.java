package com.wethinkcode.buyorsell.cart_item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private CartItemRepo cartItemRepo;

    @Autowired
    public CartItemService(CartItemRepo cartItemRepo) {
        super();
        this.cartItemRepo = cartItemRepo;
    }

    public CartItem addToCart(CartItem item) {
        return cartItemRepo.save(item);
    }

    public CartItem getCartItems(int id) {
        return cartItemRepo.getById(id);
    }
    
    // public CartItem getQuantity(CartItem quantity) {
    //     return cartItemRepo.getQuantity();
    // }

    public List<CartItem> listItems() {
        List<CartItem> items = new ArrayList<CartItem>();
        for(CartItem item : cartItemRepo.findAll()) {
            items.add(item);
        }
        return items;
    }

    public void removeItem(int id) {
        cartItemRepo.deleteById(id);
    }
}
