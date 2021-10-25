package com.example.membershipmanagement.User;

//import com.example.membershipmanagement.adhesion.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority({'admin'})")
    @PostMapping("/adduser/admin")
    public void addUser(@RequestBody final User user) {
        userServise.addUser(user);

    }
    //@PreAuthorize("hasAnyAuthority({'adherent','admin'})")
    @PostMapping("/auth/adduser/adherent")
    public void addAdherent(@RequestBody final User user) {
        userServise.addAdherent(user);
    }

    @GetMapping("/getAdherents/{isDeleted}")
    public List<User>findUserByIsDeleted(@PathVariable final Boolean isDeleted) {
        return userServise.findAdherentByIsDeleted(isDeleted) ;

    }

    @GetMapping("getAdhernt/{adherentId}")
    public User findUserById(@PathVariable final  String adherentId) {
        return    userServise.findUserById(adherentId) ;
    }

    @PutMapping( "/acceptNewAdherent/{adherentId}/{adminId}")
    public ResponseEntity<String>acceptNewAdherent(@PathVariable final String adherentId,  @PathVariable final String adminId ) {
        return  userServise.acceptNewAdherent(adherentId,adminId) ;
    }
    @PreAuthorize("hasAnyAuthority({'adherent'})")
    @PutMapping( "/paySubscription")
    public ResponseEntity<String>paySubscription(@RequestParam  String adherentId) {
        return  userServise.paySubscription(adherentId) ;
    }

    @GetMapping("paymentVerfication/{adherentId}")
    public Boolean paymentVerfication(@PathVariable final  String adherentId) {
        return    userServise.paymentVerfication(adherentId) ;
    }
    @PutMapping("/DeleteAdherent/{id}")
    public ResponseEntity<User> DeleteAdherent(@PathVariable final String id) {
        return  userServise.DeleteAdherent(id) ;
    }
    @PutMapping("/updateAdherent/{id}")
    public ResponseEntity<User>updateAdherent(@PathVariable final String id  , @RequestBody User adherent) {
        return  userServise.updateAdherent(id,adherent) ;
    }
    @GetMapping("getAdherntByDoamaineAndAdress{domaine}/{adress}")
    public User findAdherentt(@PathVariable final String domaine, @PathVariable final String adress) {
        return    userServise.findAdherentt(domaine,adress) ;
    }








    @PostMapping("/auth/signIn")
    public ResponseEntity<?> signIn(@RequestParam  String username , @RequestParam  String password ){
        return userServise.signIn(username,password);
    }


}
