package services;

import DAO.ImpEmploye;
import DAO.Impclient;
import DTO.Client;
import DTO.Employe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class SClient {
    Impclient impclient= new Impclient();
    Client client;
    Scanner sc = new Scanner(System.in);
    public void ajouterClient() {
        client = new Client();
        System.out.print("code :");
        client.setCode(sc.nextLine());
        System.out.print("nom :");
        client.setNom(sc.nextLine());
        System.out.print("prenom :");
        client.setPrenom(sc.nextLine());
        System.out.println("Veuillez saisir la date de naissance de l'employé:(format: yyyy-mm-dd) ");
        String dateN = sc.nextLine();
        Date dateNaissance = null;
        try {
            dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateN);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        client.setDateNaissance(new java.sql.Date(dateNaissance.getTime()));
        System.out.print("telephone :");
        client.setTelephone(sc.nextLine());
        System.out.print("adresse :");
        client.setAdresse(sc.nextLine());
        Optional<Client> optionalcl = impclient.ajouter(client);
        optionalcl.ifPresent(v -> System.out.println(String.format("*****   AJOUT D'UN CLIENT  *****")));
    }
    public void supprierClient() {
        client= new Client();
        System.out.print("Entrer matricule :");
        client.setCode(sc.nextLine());
        Optional<Client> optionalcl = impclient.supprimer(client);
        optionalcl.ifPresent(v -> System.out.println(String.format("*****  CLIENT SUPPRIME  *****")));
    }
    public void chercherClientParCode() {
        client= new Client();
        System.out.print("Entrer code :");
        String code=sc.nextLine();
        Optional<Client> optionalcl = impclient.chercherbyCode(code);
        optionalcl.ifPresent(client -> {
            System.out.println(String.format(client.getCode()+" "+client.getNom()+" "+client.getPrenom()+" "+client.getDateNaissance()+" "+client.getAdresse()));
        });
    }
    public void afficherListe() {
        Optional<Client[]> optionalcl = impclient.afficherListe();
        optionalcl.ifPresent(clients -> {
            for (Client cl : clients) {
                System.out.println(String.format(
                        "%s %s %s %s %s",
                        cl.getCode(),
                        cl.getNom(),
                        cl.getPrenom(),
                        cl.getDateNaissance(),
                        cl.getAdresse()
                ));
            }
        });
    }

}
