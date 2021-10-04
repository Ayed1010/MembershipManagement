package com.example.membershipmanagement.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository ;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository ;
    }

    public void addPayment( Payment payment) {
        paymentRepository.save(payment) ;

    }
    public List<Payment> findPayment() {
        return paymentRepository.findAll() ;

    }
}
