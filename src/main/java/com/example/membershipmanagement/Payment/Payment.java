package com.example.membershipmanagement.Payment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Payment {
@Id
    private String id ;
    private String dateDB ;
    private String dateFI ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDB() {
        return dateDB;
    }

    public void setDateDB(String dateDB) {
        this.dateDB = dateDB;
    }

    public String getDateFI() {
        return dateFI;
    }

    public void setDateFI(String dateFI) {
        this.dateFI = dateFI;
    }
}
