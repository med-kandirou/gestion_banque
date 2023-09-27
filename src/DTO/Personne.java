package DTO;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Personne {
    protected String nom;

    protected String prenom;
    protected Date dateNaissance;

    protected String telephone;

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public java.sql.Date getDateNaissance() {
        return (java.sql.Date) dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }



}
