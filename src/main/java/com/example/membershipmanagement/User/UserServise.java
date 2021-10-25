package com.example.membershipmanagement.User;

import com.example.membershipmanagement.Payment.Payment;
import com.example.membershipmanagement.Payment.PaymentService;
import com.example.membershipmanagement.Role.Role;
import com.example.membershipmanagement.Role.RoleRepository;
import com.example.membershipmanagement.Role.RoleService;
import com.example.membershipmanagement.Security.JwtUtils;
import com.example.membershipmanagement.Security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServise {
    private final UserRepository userRepository ;
    private final   RoleService roleService ;
    private final PaymentService paymentService ;
   @Autowired
    AuthenticationManager authenticationManager ;
    @Autowired
    JwtUtils jwtUtils ;
    @Autowired
    RoleRepository roleRepository;


   private MyUserDetailsService myUserDetailsService ;
    


    public ResponseEntity<?> signIn (String username , String password ) {


        String jwt = jwtUtils.generateToken(username);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));


            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(jwt,HttpStatus.ACCEPTED);
        }




    public ResponseEntity<?> addUser( User user) {

       String password = passwordEncoder().encode(user.getPassword());
        user.setPassword(password);
        Role role = roleRepository.findById("61674b643e59f71e1142a4cb").get() ;

        user.setRole(role);
        userRepository.save(user);
        System.out.print(user.getRole().getName());
        return new ResponseEntity<>(user.getRole().getName().toString(),HttpStatus.ACCEPTED);
    }

    public void addAdherent( User user) {
        String password = passwordEncoder().encode(user.getPassword());
        user.setPassword(password);
        Role role = new Role() ;
        role.setId("61674b703e59f71e1142a4cc");
        user.setRole(role);
        userRepository.save(user);


    }


    public List<User>findAdherentByIsDeleted( Boolean isDeleted) {
        return userRepository.findByRole_IdAndIsDeleted("61674b703e59f71e1142a4cc",isDeleted) ;

    }

    public User findUserById(String adherentId) {
        return userRepository.findById(adherentId).orElseGet(User::new);
    }



    public ResponseEntity<String> acceptNewAdherent(String MemberId, String adminId) {
        Optional<User> adherentData = userRepository.findById(MemberId);
        Optional<User> currentAdmin = userRepository.findById(adminId);

        if (currentAdmin.isPresent()) {
            User _currentAdmin = currentAdmin.get();

            if (adherentData.isPresent()) {
                User _tutorial = adherentData.get();
                _tutorial.setMembeer(true);
                _tutorial.setUser(_currentAdmin);
                userRepository.save(_tutorial);
                return new ResponseEntity<>("Successfully Accepted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public Boolean paymentVerfication(String adherentId) {

        Boolean isPayed = true ;
        Optional<User> adherentData = userRepository.findById(adherentId);

        if (adherentData.isPresent()) {

            User _adherentData = adherentData.get();

            Optional<List<Payment>> PaymentList1 = Optional.ofNullable(_adherentData.getPayment());
            List<Payment> PaymentList = PaymentList1.get();
            if (PaymentList.isEmpty()) {
                isPayed = false ;
            } else {
                Payment lastPaymentData = PaymentList.get(PaymentList.size() - 1);
                String dateFI1 = lastPaymentData.getDateFI();
                LocalDateTime dateNow1 = LocalDateTime.now();

                LocalDateTime dateFI = LocalDateTime.parse(dateFI1);
                LocalDateTime dateNow = LocalDateTime.parse(dateNow1.toString());
                if (dateFI.isAfter(dateNow)) {
                    isPayed = true ;
                } else if (!dateFI.isAfter(dateNow)) {
                    isPayed = true ;
                }


            }

        } else {
            isPayed = false ;
        }

        return isPayed ;
    }



    public ResponseEntity<String> paySubscription(String adherentId) {
        Optional<User> adherentData = userRepository.findById(adherentId);

        if (adherentData.isPresent()) {
            Payment firstPayment = new Payment();
            User _adherentData = adherentData.get();

            Optional<List<Payment>> PaymentList1 = Optional.ofNullable(_adherentData.getPayment());
            List<Payment> PaymentList = PaymentList1.get();


            if (PaymentList.isEmpty()) {




                LocalDateTime dateDB = LocalDateTime.now();
                LocalDateTime dateFI = dateDB.plusYears(1);
                firstPayment.setDateDB(dateDB.toString());
                firstPayment.setDateFI(dateFI.toString());
                paymentService.addPayment(firstPayment);

                //  PaymentList.add(firstPayment) ;
                _adherentData.setPayment(firstPayment);

                userRepository.save(_adherentData);

                return new ResponseEntity<>("Successfull Payment", HttpStatus.OK);


            } else {
                Boolean isPayed = this.paymentVerfication(adherentId) ;
                if (isPayed) {
                    return new ResponseEntity<>("You are Already Payed", HttpStatus.OK);
                }else {



                    LocalDateTime dateDB = LocalDateTime.now();
                    LocalDateTime dateFI = dateDB.plusYears(1);
                    firstPayment.setDateDB(dateDB.toString());
                    firstPayment.setDateFI(dateFI.toString());
                    paymentService.addPayment(firstPayment);

                    //  PaymentList.add(firstPayment) ;
                    _adherentData.setPayment(firstPayment);

                    userRepository.save(_adherentData);
                    return new ResponseEntity<>("Successfull Payment", HttpStatus.OK);
                }

            }


        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<User> DeleteAdherent(String id) {
        Optional<User> adherentData = userRepository.findById(id);

        if (adherentData.isPresent()) {
            User _tutorial = adherentData.get();
            _tutorial.setDeleted(true);

            return new ResponseEntity<>(userRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



    public ResponseEntity<User> updateAdherent(String id, User adherent) {
        Optional<User> adherentData = userRepository.findById(id);

        if (adherentData.isPresent()) {
            User _tutorial = adherentData.get();
            _tutorial.setEmail(adherent.getEmail());
            _tutorial.setCategorie(adherent.getCategorie());
            _tutorial.setDomaine(adherent.getDomaine());
            _tutorial.setNationalite(adherent.getNationalite());
            _tutorial.setSecteur(adherent.getSecteur());
            _tutorial.setStatut(adherent.getStatut());
            _tutorial.setTaille(adherent.getTaille());
            _tutorial.setAdress(adherent.getAdress());

            return new ResponseEntity<>(userRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public User findAdherentt(String domaine, String adress) {
        return userRepository.findByDomaineAndAdress(domaine, adress);
    }








    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }










    public Optional<User> findUser( String userId) {
        return    userRepository.findById(userId);
    }

















}

