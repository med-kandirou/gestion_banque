package DTO;

import java.util.Date;

public class Compte {
    protected String code;
    protected double solde;
    protected Date dateCreation;
    protected String etat;

    // Constructeur
    public Compte() {}

    // Getters
    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getEtat() {
        return etat;
    }

    // Setters (si nécessaire)
    public void setCode(String code) {
        this.code = code;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}

