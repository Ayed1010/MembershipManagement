package com.example.membershipmanagement.User;

//import com.example.membershipmanagement.adhesion.Adherent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {


  //Adherent findByDomaineAndAdress(String domaine , String adresse  ) ;
  User findByGmail(String Gmail );
  List<User> findByRole_Id(String RoleId) ;
  Optional<User> findByUsername(String username) ;
  List<User>findByRole_IdAndIsDeleted(String roleId, boolean isDeleted ) ;
  User findByDomaineAndAdress(String domaine , String adresse  ) ;


}
