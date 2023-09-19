package DTO;

public class Mission {
    private String code;
    private String nom;
    private String description;

    // Constructeur
    public Mission() {}

    // Getters et setters pour les attributs
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

