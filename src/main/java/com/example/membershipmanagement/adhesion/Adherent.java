/*package com.example.membershipmanagement.adhesion;

import com.example.membershipmanagement.Payment.Payment;

import com.example.membershipmanagement.User.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Adherent {
    @Id
    private String id;
    @DBRef
    private User user ;
    @DBRef
    private List<Payment> payment  = new ArrayList<>();

    private String password ;
    private String username ;



    private String categorie;
    private String secteur;
    private String statut;
    private String domaine;
    private String nationalite;
    private int taille;
    private String adress;
    private String email;
    private boolean isDeleted  = false;
    private boolean isMembeer = false ;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment.add(payment);
    }

    public boolean getIsDeleted() {
        return isDeleted ;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMembeer() {
        return isMembeer;
    }

    public void setMembeer(boolean membeer) {
        isMembeer = membeer;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
*/