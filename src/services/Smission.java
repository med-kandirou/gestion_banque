package services;

import DAO.ImpMission;
import DAO.Impclient;
import DTO.Client;
import DTO.Mission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Smission {
    ImpMission impMission= new ImpMission();
    Scanner sc = new Scanner(System.in);
    Mission mission;
    public void ajouterMission() {
        mission = new Mission();
        System.out.print("Nom :");
        mission.setNom(sc.nextLine());
        System.out.print("Description :");
        mission.setDescription(sc.nextLine());
        Optional<Mission> optionalM = impMission.ajouter(mission);
        optionalM.ifPresent(v -> System.out.println(String.format("*****   AJOUT D'UN MISSION  *****")));
    }
}
