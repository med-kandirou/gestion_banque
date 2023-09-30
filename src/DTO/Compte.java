package DTO;

import Enums.Etat;

import java.util.Date;

public class Compte {
    protected String code;
    protected double solde;
    protected Date dateCreation;
    protected Etat etat;

    protected Client client;

    protected Employe employe;

    public Compte() {}

    public Compte(Compte compte) {
        this.code = compte.getCode();
        this.solde = compte.getSolde();
        this.etat = compte.getEtat();
        this.client = compte.getClient();
        this.employe = compte.getEmploye();
    }

    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public java.sql.Date getDateCreation() {
        return (java.sql.Date)dateCreation;
    }



    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}

