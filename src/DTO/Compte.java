package DTO;

import java.util.Date;

public class Compte {
    protected String code;
    protected double solde;
    protected Date dateCreation;
    protected String etat;

    protected Client client;

    protected Employe employe;



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

    public void setEtat(String etat) {
        this.etat = etat;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}

