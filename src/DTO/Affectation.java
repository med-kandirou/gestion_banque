package DTO;

import java.util.Date;

public class Affectation {
    private Integer id;
    private Employe employe;
    private Mission mission;
    private Date dateChangement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Date getDateChangement() {
        return dateChangement;
    }
    public void setDateChangement(Date dateChangement) {
        this.dateChangement = dateChangement;
    }

}
