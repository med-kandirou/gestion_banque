package DTO;

public class Client extends Personne{
    private String code;
    private String adresse;

    // Constructeur
    public Client() {}

    // Getters et setters pour l'attribut "code"
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}