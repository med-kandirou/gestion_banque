package DTO;

import java.util.Date;

public class MissionEmploye {

    private Employe employe;
    private Mission mission;
    private Date dateChangement;

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
