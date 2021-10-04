package com.example.membershipmanagement.Payment;

import com.example.membershipmanagement.adhesion.Adherent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment,String> {
}
