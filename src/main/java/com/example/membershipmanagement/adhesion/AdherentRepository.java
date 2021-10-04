package com.example.membershipmanagement.adhesion;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdherentRepository extends MongoRepository<Adherent,String> {

    Adherent findByDomaineAndAdress(String domaine , String adresse  ) ;
    List<Adherent> findByIsDeletedAndIsMembeer(Boolean isDeleted,Boolean isMemeber) ;

}
