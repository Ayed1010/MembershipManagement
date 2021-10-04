package com.example.membershipmanagement.Role;

import com.example.membershipmanagement.adhesion.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository ;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository  ;
    }
    public void addRoles( List<Role> roles) {
        roleRepository.saveAll(roles) ;

    }

    public void addRole( Role role) {
        roleRepository.save(role) ;

    }

    public List<Role>findRole() {
        return roleRepository.findAll() ;

    }

    public Role findRole(String roleId) {
        return    roleRepository.findById(roleId).orElseGet(Role::new) ;
    }


    public String findRoleIdByName(String RoleName) {
        Optional<Role> RoleData = Optional.ofNullable(roleRepository.findByName(RoleName));
        if(RoleData.isPresent()) {
            Role _role = RoleData.get();
            return _role.getId() ;

        } else {

            return "no data" ;
        }


        }

}
