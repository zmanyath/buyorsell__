package com.wethinkcode.buyorsell.order_details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {
    
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    public OrderDetailsService(OrderDetailsRepo orderDetailsRepo) {
        super();
        this.orderDetailsRepo = orderDetailsRepo;
    }

    public OrderDetails createOrder(OrderDetails orderDetails) {
        return orderDetailsRepo.save(orderDetails);
    }

    public OrderDetails getOrder(int id) {
        return orderDetailsRepo.getById(id);
    }


    public List<OrderDetails> listOrders() {
        List<OrderDetails> orders = new ArrayList<OrderDetails>();
        for(OrderDetails order : orderDetailsRepo.findAll()) {
            orders.add(order);
        }
        return orders;
    }

    public void removeOrder(int id) {
        orderDetailsRepo.deleteById(id);
    }
}