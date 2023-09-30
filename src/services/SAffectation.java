package services;

import DAO.ImpAffectation;
import DAO.ImpMission;
import DTO.Affectation;
import DTO.Employe;
import DTO.Mission;

import java.util.Optional;
import java.util.Scanner;

public class SAffectation {
    ImpAffectation impAffectation= new ImpAffectation();
    Scanner sc = new Scanner(System.in);
    Affectation affectation;
    public void ajouterMission() {
        affectation = new Affectation();
        System.out.print("Code Mission :");
        Mission mission = new Mission();
        mission.setCode(sc.nextInt());
        affectation.setMission(mission);
        System.out.print("matricule employe :");
        Employe emp= new Employe();
        emp.setMatricule(sc.next());
        affectation.setEmploye(emp);
        Optional<Affectation> optionalAff = impAffectation.ajouter(affectation);
        optionalAff.ifPresent(v -> System.out.println(String.format("*****   AJOUT D'UNE AFFECTATION  *****")));
    }
}
