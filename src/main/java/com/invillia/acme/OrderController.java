package com.invillia.acme;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/orders")
    public List<Order> retrieveOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@RequestBody Order order){
        Order savedOrder = orderRepository.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/order/{id}/orderitem")
    public ResponseEntity<Object> addOrderItem(@RequestBody OrderItem orderItem, @PathVariable Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id);

        if(!orderOptional.isPresent())
            throw new RuntimeException("id - " + id);
        Order order = orderOptional.get();

        orderItem.setOrder(order);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public class OrderOrderItem{
        private Order order;
        private List<OrderItem> orderItems;
        public OrderOrderItem(){
            orderItems = new ArrayList<>();
            order = new Order();
        }

        public OrderOrderItem(Order order, List<OrderItem> orderItems) {
            this.order = order;
            this.orderItems = orderItems;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }


        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }
    }
    @PostMapping("/orderwithitems")
    public ResponseEntity<Object> addOrderWithItems(@RequestBody OrderOrderItem orderOrderItem){
        Order order = orderOrderItem.getOrder();
        List<OrderItem> orderItems = orderOrderItem.getOrderItems();

        Order savedOrder = orderRepository.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();

        for(OrderItem oi : orderItems){
            oi.setOrder(order);

            OrderItem savedOrderItem = orderItemRepository.save(oi);
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedOrderItem.getId())
                    .toUri();
        }

        return ResponseEntity.created(location).build();

    }
}
