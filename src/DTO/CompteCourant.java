package DTO;

import java.util.Date;

public class CompteCourant extends Compte {
    private Double decouvertAutorise;

    public CompteCourant(Compte compte,Double decouvertAutorise){
        super(compte);
        this.decouvertAutorise=decouvertAutorise;
    }
    public Double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(Double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

}
