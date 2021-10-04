package com.example.membershipmanagement.User;

import com.example.membershipmanagement.adhesion.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    private UserServise userServise ;

    @Autowired
  public UserController(UserServise userServise ) {
        this.userServise = userServise ;

    }

    @PostMapping("/addusers")
    public void addUsers(@RequestBody final List<User> users) {
       userServise.addUsers(users);

    }
    @PostMapping("/adduser")
    public void addUser(@RequestBody final User user) {
        userServise.addUser(user);

    }
    @GetMapping("/getusers")
    public List<User>findUsers() {
        return userServise.findUsers() ;

    }

   @GetMapping("/getUser/{userId}")
   public  Optional<User> findAdherent(@PathVariable final  String userId) {
       return    userServise.findUser(userId) ;
   }



    @GetMapping("/userByGmail/{userGmail}")
    public User findUserByGmail(@PathVariable final String userGmail) {
      return   userServise.findUserByGmail(userGmail);

    }

    @GetMapping("/userByRole/{usersRoleName}")
    public List<User> findUserByRole(@PathVariable final String usersRoleName) {
      return  userServise.findUserByRole(usersRoleName);

    }


}
