package com.example.membershipmanagement.User;

import com.example.membershipmanagement.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServise {
    private UserRepository userRepository ;
    private RoleService roleService ;
    @Autowired
    public UserServise(UserRepository userRepository,RoleService roleService) {

        this.userRepository = userRepository ;
        this.roleService = roleService ;
    }

    public void addUsers( List<User> users) {
        userRepository.saveAll(users);
    }

    public void addUser( User user) {
        userRepository.save(user);

    }

    public List<User>findUsers() {
        return userRepository.findAll() ;

    }

 /*   public Optional<User> findUser( String userId) {
        return    userRepository.findById(userId);
    }*/
  public Optional<User> findUser(String userId) {
      System.out.println(userId + "heyyyyyyyy");
      return    userRepository.findById(userId) ;
  }








    public User findUserByGmail( String userGmail) {
        return  userRepository.findByGmail(userGmail) ;

    }

    public List<User> findUserByRole( String roleName) {
        String roleId = roleService.findRoleIdByName(roleName) ;
        System.out.println(roleId);
            return  userRepository.findByRole_Id(roleId);

    }


}

