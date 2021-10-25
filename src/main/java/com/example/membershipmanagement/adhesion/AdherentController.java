/*package com.example.membershipmanagement.adhesion;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adhesion")
public class AdherentController {
    private AdherentService adherentService ;


    @Autowired
    public AdherentController(AdherentService adherentService) {
        this.adherentService = adherentService ;
    }


    @PostMapping("/addAdherents")
    public void addAdhernts(@RequestBody final List<Adherent> adherents) {
        adherentService.addAdhernts(adherents);

    }
    @PostMapping("/addAdherent")
    public void addAdhernt(@RequestBody final Adherent adherent) {
        adherentService.addAdhernt(adherent);

    }
    @GetMapping("/getAdherents/{isDeleted}")
    public List<Adherent>findProducts(@PathVariable final Boolean isDeleted) {
        return adherentService.findProducts(isDeleted) ;

    }

    @GetMapping("getAdhernt/{adherentId}")
    public Adherent findAdherent(@PathVariable final  String adherentId) {
        return    adherentService.findAdherent(adherentId) ;
    }

    @PutMapping( "/acceptNewAdherent/{memberId}/{userId}")
    public ResponseEntity<String>acceptNewAdherent(@PathVariable final String memberId,  @PathVariable final String userId ) {
     return  adherentService.acceptNewAdherent(memberId,userId) ;
    }
    @PutMapping( "/paySubscription/{memberId}")
    public ResponseEntity<String>paySubscription(@PathVariable final String memberId) {
        return  adherentService.paySubscription(memberId) ;
    }
    @GetMapping("paymentVerfication/{adherentId}")
    public Boolean paymentVerfication(@PathVariable final  String adherentId) {
        return    adherentService.paymentVerfication(adherentId) ;
    }

    @PutMapping("/DeleteAdherent/{id}")
    public ResponseEntity<Adherent> DeleteAdherent(@PathVariable final String id) {
        return  adherentService.DeleteAdherent(id) ;
    }



    @PutMapping("/updateAdherent/{id}")
    public ResponseEntity<Adherent>updateAdherent(@PathVariable final String id  , @RequestBody Adherent adherent) {
        return  adherentService.updateAdherent(id,adherent) ;
    }


    @GetMapping("getAdherntByDoamaineAndAdress{domaine}/{adress}")
    public Adherent findAdherentt(@PathVariable final String domaine, @PathVariable final String adress) {
        return    adherentService.findAdherentt(domaine,adress) ;
    }







}
*/