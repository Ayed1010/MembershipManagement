/*package com.example.membershipmanagement.adhesion;

import com.example.membershipmanagement.Payment.Payment;
import com.example.membershipmanagement.Payment.PaymentService;
import com.example.membershipmanagement.User.User;
import com.example.membershipmanagement.User.UserServise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Configuration
@Service
public class AdherentService {
    private AdherentRepository adherentRepository;
    private UserServise userServise;
    private PaymentService paymentService;
    private  PasswordEncoder passwordEncoder ;

    @Autowired
    public AdherentService(AdherentRepository adherentRepository, UserServise userServise, PaymentService paymentService) {
        this.userServise = userServise;
        this.adherentRepository = adherentRepository;
        this.paymentService = paymentService;

    }

    public void addAdhernts(List<Adherent> adherents) {
        adherentRepository.saveAll(adherents);

    }

    public void addAdhernt(Adherent adherent) {
       adherent.setPassword(passwordEncoder.encode(adherent.getPassword()));
        adherentRepository.save(adherent);
    }

    public List<Adherent> findProducts(Boolean isDeleted) {
        return adherentRepository.findByIsDeletedAndIsMembeer(isDeleted, true);
    }


    public Adherent findAdherent(String adherentId) {
        return adherentRepository.findById(adherentId).orElseGet(Adherent::new);
    }


    public ResponseEntity<String> acceptNewAdherent(String MemberId, String userId) {
        Optional<Adherent> adherentData = adherentRepository.findById(MemberId);
        Optional<User> currentUser = userServise.findUser(userId);

        if (currentUser.isPresent()) {
            User _currentUser = currentUser.get();

            if (adherentData.isPresent()) {
                Adherent _tutorial = adherentData.get();
                _tutorial.setMembeer(true);
                _tutorial.setUser(_currentUser);
                adherentRepository.save(_tutorial);
                return new ResponseEntity<>("Successfully Accepted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Adherent> DeleteAdherent(String id) {
        Optional<Adherent> adherentData = adherentRepository.findById(id);

        if (adherentData.isPresent()) {
            Adherent _tutorial = adherentData.get();
            _tutorial.setIsDeleted(true);

            return new ResponseEntity<>(adherentRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<Adherent> updateAdherent(String id, Adherent adherent) {
        Optional<Adherent> adherentData = adherentRepository.findById(id);

        if (adherentData.isPresent()) {
            Adherent _tutorial = adherentData.get();
            _tutorial.setEmail(adherent.getEmail());
            _tutorial.setCategorie(adherent.getCategorie());
            _tutorial.setDomaine(adherent.getDomaine());
            _tutorial.setNationalite(adherent.getNationalite());
            _tutorial.setSecteur(adherent.getSecteur());
            _tutorial.setStatut(adherent.getStatut());
            _tutorial.setTaille(adherent.getTaille());
            _tutorial.setAdress(adherent.getAdress());

            return new ResponseEntity<>(adherentRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }





    public Adherent findAdherentt(String domaine, String adress) {
        return adherentRepository.findByDomaineAndAdress(domaine, adress);
    }









    public ResponseEntity<String> paySubscription(String memberId) {
        Optional<Adherent> adherentData = adherentRepository.findById(memberId);

        if (adherentData.isPresent()) {
            Payment firstPayment = new Payment();
            Adherent _adherentData = adherentData.get();

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

                adherentRepository.save(_adherentData);

                return new ResponseEntity<>("Successfull Payment", HttpStatus.OK);


            } else {
              Boolean isPayed = this.paymentVerfication(memberId) ;
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

                  adherentRepository.save(_adherentData);
                  return new ResponseEntity<>("Successfull Payment", HttpStatus.OK);
              }

            }


        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }









    public Boolean paymentVerfication(String adherentId) {

        Boolean isPayed = true ;
        Optional<Adherent> adherentData = adherentRepository.findById(adherentId);

        if (adherentData.isPresent()) {

            Adherent _adherentData = adherentData.get();

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



    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

}
*/