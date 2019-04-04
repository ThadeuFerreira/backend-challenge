package com.invillia.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired OrderRepository orderRepository;

    @GetMapping("/payments")
    public List<Payment> retrievePayments(){ return paymentRepository.findAll();}

    @PostMapping("/payment/{orderid}")
    public ResponseEntity<Object> addPaymentToOrder(@RequestBody Payment payment, @PathVariable Integer orderid){
        Optional<Order> orderOptional = orderRepository.findById(orderid);

        if(!orderOptional.isPresent())
            throw new RuntimeException("id - " + orderid);
        Order order = orderOptional.get();

        payment.setOrder(order);

        Payment savedPayment = paymentRepository.save(payment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPayment.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
