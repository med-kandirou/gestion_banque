package services;

import DAO.ImpCompte;
import DAO.ImpMission;
import DTO.*;
import Enums.Etat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class SCompte {
    ImpCompte impCompte= new ImpCompte();
    Scanner sc = new Scanner(System.in);
    Compte compte;

    public void ajouterCompte(int choix) {
        compte=new Compte();
        System.out.print("Entrer le code du compte :");
        compte.setCode(sc.nextLine());
        System.out.print("Entrer le solde :");
        compte.setSolde(sc.nextDouble());
        System.out.print("Entrer l'etat du compte : 1-active\n2-desactive\n");
        int etat = sc.nextInt();
        if(etat==1){
            compte.setEtat(Etat.active);
            System.out.printf(compte.getEtat().toString());
        }
        else{
            compte.setEtat(Etat.desactive);
            System.out.printf(compte.getEtat().toString());
        }
        Client c= new Client();
        System.out.print("Entrer le code du client  :");
        c.setCode(sc.next());
        compte.setClient(c);
        System.out.print("Entrer le code du employe  :");
        Employe emp= new Employe();
        emp.setMatricule(sc.next());
        compte.setEmploye(emp);
        if(choix==1){
            System.out.print("Entrer le decouvert autorise  :");
            Double decouvertAutorise= sc.nextDouble();
            CompteCourant compteCourant= new CompteCourant(compte,decouvertAutorise);
            Optional<Compte> courant=impCompte.ajoutercourant(compteCourant);
            courant.ifPresent(val->{
                System.out.println(String.format("*****   AJOUT D'UN COMPTE COURANT  *****"));
            });
        }
        else{
            System.out.print("Entrer le Taux :");
            Double taux= sc.nextDouble();
            CompteEpargne epargne= new CompteEpargne(compte,taux);
            Optional<Compte> comptepargne=impCompte.ajouterepargne(epargne);
            comptepargne.ifPresent(val->{
                System.out.println(String.format("*****   AJOUT D'UN COMPTE EPARGNE  *****"));
            });
        }
    }
    public void supprierCompte() {
        compte=new Compte();
        System.out.print("Entrer le code du compte :");
        compte.setCode(sc.nextLine());
        Optional<Compte> optionalEmp = impCompte.supprimer(compte);
        optionalEmp.ifPresent(v -> System.out.println(String.format("*****  COMPTE SUPPRIME  *****")));
    }

    public void afficherList(){
        Optional<Compte[]> optComptes=impCompte.afficherList();
        optComptes.ifPresent(comptes->{
            for (Compte cl : comptes) {
                System.out.println(String.format(cl.getCode()+" "+cl.getEtat()+" "+cl.getSolde()+" "+cl.getClient().getCode()+" "+cl.getEmploye().getMatricule()+" "+cl.getDateCreation()));
            }
        });
    }

    public void afficherParStatut(){
        System.out.print("1-active\n2-desactive :");
        int choix=sc.nextInt();
        Optional<Compte[]> optComptes;
        if(choix==1){
            optComptes=impCompte.afficheParStatut("active");
        }
        else{
            optComptes=impCompte.afficheParStatut("desactive");
        }
        optComptes.ifPresent(comptes->{
            for (Compte cl : comptes) {
                System.out.println(String.format(cl.getCode()+" "+cl.getEtat()+" "+cl.getSolde()+" "+cl.getClient().getCode()+" "+cl.getEmploye().getMatricule()+" "+cl.getDateCreation()));
            }
        });
    }


    public void afficherPardateCreation(){
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input = sc.nextLine();
        Date dt = null;
        try {
            dt = new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        Optional<Compte[]> optComptes =impCompte.afficheParDate(new java.sql.Date(dt.getTime()));
        optComptes.ifPresent(comptes->{
            for (Compte cl : comptes) {
                System.out.println(String.format(cl.getCode()+" "+cl.getEtat()+" "+cl.getSolde()+" "+cl.getClient().getCode()+" "+cl.getEmploye().getMatricule()+" "+cl.getDateCreation()));
            }
        });
    }

}
