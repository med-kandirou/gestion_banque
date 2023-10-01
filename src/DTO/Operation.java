package DTO;

import Enums.Type;

import java.util.Date;

import java.util.Date;

public class Operation {
    private int numero;
    private Date dateOperation;
    private Double montant;
    private Type type;

    private Employe employe;

    private Compte compte;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    // Constructeur
    public Operation() {
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDateOperation() {
        return dateOperation;
    }


    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontant() {
        return montant;
    }
}
