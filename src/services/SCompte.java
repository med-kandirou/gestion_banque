package services;

import DAO.ImpCompte;
import DAO.ImpMission;
import DTO.Compte;
import DTO.Employe;

import java.util.Optional;
import java.util.Scanner;

public class SCompte {
    ImpCompte impCompte= new ImpCompte();
    Scanner sc = new Scanner(System.in);
    Compte compte;
    public void supprierCompte() {
        compte=new Compte();
        System.out.print("Entrer le code du compte :");
        compte.setCode(sc.nextLine());
        Optional<Compte> optionalEmp = impCompte.supprimer(compte);
        optionalEmp.ifPresent(v -> System.out.println(String.format("*****  COMPTE SUPPRIME  *****")));
    }
}
