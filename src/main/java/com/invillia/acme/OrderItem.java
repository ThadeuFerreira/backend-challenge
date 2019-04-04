package com.invillia.acme;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Currency;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer Id;
    private String Description;
    private Currency UnitPrice;
    private Integer Quantity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Order order;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Currency getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Currency unitPrice) {
        UnitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
