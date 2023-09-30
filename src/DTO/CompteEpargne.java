package DTO;

public class CompteEpargne extends Compte {
    private double tauxInteret ;

    public CompteEpargne(Compte compte,Double tauxInteret){
        super(compte);
        this.tauxInteret=tauxInteret;
    }
    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

}
