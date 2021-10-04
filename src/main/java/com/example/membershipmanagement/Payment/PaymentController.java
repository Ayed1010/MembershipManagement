package com.example.membershipmanagement.Payment;

import com.example.membershipmanagement.Role.Role;
import com.example.membershipmanagement.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService ;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService ;
    }




    @PostMapping("/addpayment")
    public void addPayment(@RequestBody final Payment payment) {
        paymentService.addPayment(payment);

    }
    @GetMapping("/getpayment")
    public List<Payment>findPayment() {
        return paymentService.findPayment() ;

    }



}
