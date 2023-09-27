package DTO;


import java.util.Date;

public class Employe extends Personne{
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

    public java.sql.Date getDateDeRecrutement() {
        return (java.sql.Date) this.dateDeRecrutement;
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
