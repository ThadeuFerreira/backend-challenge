package com.invillia.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/payment/refund/{orderid}")
    public ResponseEntity<Object> refundOrder(@PathVariable Integer orderid){
        Optional<Order> orderOptional = orderRepository.findById(orderid);

        if(!orderOptional.isPresent())
            throw new RuntimeException("id - " + orderid);
        Order order = orderOptional.get();

        List<Payment> payments = order.getPayments();
        Optional<Payment> p = payments.stream().max(Comparator.comparing(Payment::getPaymentDate));

        if(!p.isPresent()){
            throw  new RuntimeException("paymentdate error");
        }
        Date date = new Date();
        Long diff = p.get().getPaymentDate().getTime() - date.getTime();
        Integer daysDiff = Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        if(daysDiff <= 10 && order.getStatus().equals("Concluded")){
            return  ResponseEntity.ok("Refund success");
        }

        return ResponseEntity.ok("Could not refund");
    }
}
