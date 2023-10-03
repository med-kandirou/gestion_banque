package services;

import DAO.ImpEmploye;
import DTO.Client;
import DTO.Employe;
import DTO.Personne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SEmploye {

    ImpEmploye impEmploye= new ImpEmploye();
    Employe emp;
    Scanner sc = new Scanner(System.in);
    public void ajouterEmploye() {
        emp = new Employe();
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
        Optional<Employe> optionalEmp = impEmploye.ajouter(emp);
        optionalEmp.ifPresent(v -> System.out.println(String.format("*****   AJOUT D'UN EMPLOI  *****")));
    }

    public void supprierEmploye() {
        emp= new Employe();
        System.out.print("Entrer matricule :");
        emp.setMatricule(sc.nextLine());
        Optional<Employe> optionalEmp = impEmploye.supprimer(emp);
        optionalEmp.ifPresent(v -> System.out.println(String.format("*****  EMPLOI SUPPRIME  *****")));
    }
    public void chercherEmploye() {
        emp= new Employe();
        System.out.print("Entrer matricule :");
        String matricule=sc.nextLine();
        Optional<Employe> optionalEmp = impEmploye.chercherbyCode(matricule);
        Optional<Employe> optionalEmploye = optionalEmp.map(emp -> (Employe) emp);
        optionalEmploye.ifPresent(employe -> {
            System.out.println(String.format(employe.getMatricule()+" "+employe.getNom()+" "+employe.getPrenom()+" "+employe.getDateNaissance()+" "+employe.getDateDeRecrutement()));
        });
    }
    public void afficherListe() {
        Optional<Employe[]> optionalEmp = impEmploye.afficherListe();
        optionalEmp.ifPresent(employes -> {
            for (Employe emp:employes) {
                System.out.println(String.format(
                        "%s %s %s %s %s %s %s",
                        emp.getMatricule(),
                        emp.getNom(),
                        emp.getPrenom(),
                        emp.getTelephone(),
                        emp.getDateNaissance(),
                        emp.getAdresseEmail(),
                        emp.getDateDeRecrutement()
                ));
            }
        });

    }

    public void chercherParAtt() {
        emp= new Employe();
        System.out.print("Entrer une information :");
        String info=sc.nextLine();
        Optional<Employe[]> optionalemp = impEmploye.rechercheParAtt(info);
        optionalemp.ifPresent(employes -> {
            for (Employe emp : employes) {
                System.out.println(String.format(emp.getMatricule()+" "+emp.getNom()+" "+emp.getPrenom()+" "+emp.getTelephone()+" "+emp.getDateNaissance()+" "+emp.getAdresseEmail()+" "+emp.getDateDeRecrutement()));
            }
        });
    }

}
