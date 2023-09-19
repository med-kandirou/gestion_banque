package DTO;

import java.util.Date;

public class Employe {
    private String matricule;
    private Date dateDeRecrutement;
    private String adresseEmail;

    // Constructeur
    public Employe() {}

    // Getters et setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDateDeRecrutement() {
        return dateDeRecrutement;
    }

    public void setDateDeRecrutement(Date dateDeRecrutement) {
        this.dateDeRecrutement = dateDeRecrutement;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

}
