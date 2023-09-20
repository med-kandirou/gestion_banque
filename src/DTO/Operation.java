package DTO;

import Enums.Type;

import java.util.Date;

import java.util.Date;

public class Operation {
    private String numero;
    private Date dateCreation;
    private Double montant;
    private Type type;
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    // Constructeur
    public Operation() {
    }

    // Méthodes d'accès (getters)
    public String getNumero() {
        return numero;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontant() {
        return montant;
    }
}
