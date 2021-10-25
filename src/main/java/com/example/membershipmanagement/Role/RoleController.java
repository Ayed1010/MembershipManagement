package com.example.membershipmanagement.Role;


import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;*/
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService ;
    @Autowired
    public RoleController(RoleService roleService ) {
        this.roleService = roleService ;
    }


    @PostMapping("/addroles")
    public void addRoles(@RequestBody final List<Role> roles) {
        roleService.addRoles(roles);

    }
   // @PreAuthorize("hasAnyAuthority({'admin'})")
    @PostMapping("/addrole")
    public void addRole(@RequestBody final Role role) {
        roleService.addRole(role);

    }
  //  @PreAuthorize("hasAnyAuthority({'adherent'})")
    @GetMapping("/getroles")
    public List<Role>findRole() {
        return roleService.findRole() ;

    }
    @GetMapping("getRole/{roleId}")
    public Role findRole(@PathVariable final  String roleId) {
        return   roleService.findRole(roleId) ;
    }




}
