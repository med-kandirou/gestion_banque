package services;

import DAO.ImpEmploye;
import DTO.Employe;
import DTO.Personne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class SEmploye {

    ImpEmploye impEmploye= new ImpEmploye();
    public void ajouterEmploye() {
        Scanner sc = new Scanner(System.in);
        Employe emp = new Employe();
        System.out.print("matricule :");
        emp.setMatricule(sc.nextLine());
        System.out.print("nom :");
        emp.setNom(sc.nextLine());
        System.out.print("prenom :");
        emp.setPrenom(sc.nextLine());
        System.out.println("Veuillez saisir la date de naissance de l'employé:(format: yyyy-mm-dd) ");
        String dateN = sc.nextLine();
        Date dateNaissance = null;
        try {
            dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateN);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        emp.setDateNaissance(new java.sql.Date(dateNaissance.getTime()));
        System.out.print("telephone :");
        emp.setTelephone(sc.nextLine());
        System.out.print("adresse email :");
        emp.setAdresseEmail(sc.nextLine());
        System.out.print("Date de recrutement (aaaa-mm-jj): ");
        String dateR = sc.nextLine();
        Date dateRecru = null;
        try {
            dateRecru = new SimpleDateFormat("yyyy-MM-dd").parse(dateR);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        emp.setDateDeRecrutement(new java.sql.Date(dateRecru.getTime()));
        Optional<Personne> optionalEmp = impEmploye.ajouter(emp);
        optionalEmp.ifPresent(v -> System.out.println(String.format("*****   AJOUT D'UN EMPLOI  *****")));

    }

}
