package DTO;

public class Mission {
    private int code;
    private String nom;
    private String description;

    // Constructeur
    public Mission() {}

    // Getters et setters pour les attributs
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

