package com.invillia.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/oderitems")
    public List<OrderItem> retrieveOrderItems(){ return orderItemRepository.findAll();}
}
