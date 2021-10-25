package com.example.membershipmanagement.Role;

import com.example.membershipmanagement.User.User;
//import com.example.membershipmanagement.adhesion.Adherent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role,String> {
    Role findByName(String RoleName) ;
}
