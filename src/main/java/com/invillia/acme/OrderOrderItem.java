package com.invillia.acme;

import java.util.ArrayList;
import java.util.List;

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