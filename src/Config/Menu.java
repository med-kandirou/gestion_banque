package Config;

import DTO.Employe;
import services.SClient;
import services.SEmploye;

import java.util.Scanner;

public class Menu {
    SEmploye semploye= new SEmploye();
    SClient sclient= new SClient();
    public void menu(){
        boolean quitter=false;
        System.out.printf("Welcome to EasyBank!");
        do {
            System.out.println("Veuillez choisir une option: ");
            System.out.println("1. Administration des employés");
            System.out.println("2. Administration des clients");
            System.out.println("3. Administration des comptes");
            System.out.println("4. Administration des Opérations");
            System.out.println("5. Administration des missions");
            System.out.println("6. Quitter");
            System.out.println("Votre choix: ");
            //get the user input
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("Veuillez choisir une option: ");
                        System.out.println("1. Ajouter un employé");
                        System.out.println("2. Modifier un employé");
                        System.out.println("3. Supprimer un employé");
                        System.out.println("4. Afficher tous les employés");
                        System.out.println("5. Chercher un employé par matricule");
                        System.out.println("6. Chercher un employé");
                        System.out.println("7. Retour");
                        System.out.println("Votre choix: ");
                        Scanner sc1 = new Scanner(System.in);
                        int choice1 = sc1.nextInt();
                        switch (choice1) {
                            case 1:
                                semploye.ajouterEmploye();
                                break;
                            case 2:
                                System.out.println("Modifier un employé");
                                break;
                            case 3:
                                semploye.supprierEmploye();
                                break;
                            case 4:
                                semploye.afficherListe();
                                break;
                            case 5:
                                semploye.chercherEmploye();
                                break;
                            case 6:
                                System.out.println("Chercher un employé");
                                break;
                            case 7:
                                System.out.println("Retour");
                                break;
                            default:
                                System.out.println("Choix invalide");
                                break;
                        }
                        if (choice1 == 7) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("< Administration des clients >");
                    while (true){
                        System.out.println("Veuillez choisir une option: ");
                        System.out.println("1. Ajouter un client");
                        System.out.println("2. Modifier un client");
                        System.out.println("3. Supprimer un client");
                        System.out.println("4. Afficher tous les clients");
                        System.out.println("5. Chercher un client par code");
                        System.out.println("6. Chercher un client");
                        System.out.println("7. Retour");
                        System.out.println("Votre choix: ");
                        Scanner sc2 = new Scanner(System.in);
                        int choice2 = sc2.nextInt();
                        switch (choice2) {
                            case 1:
                                sclient.ajouterClient();
                                break;
                            case 2:
                                System.out.println("Modifier un client");
                                break;
                            case 3:
                                sclient.supprierClient();
                                break;
                            case 4:
                                System.out.println("Afficher tous les clients");
                                break;
                            case 5:
                                sclient.chercherClientParCode();
                                break;
                            case 6:
                                System.out.println("Chercher un client");
                                break;
                            case 7:
                                System.out.println("Retour");
                                break;
                            default:
                                System.out.println("Choix invalide");
                                break;
                        }
                        if (choice2 == 7) {
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("< Administration des comptes >");
                    do {
                        System.out.println("Veuillez choisir une option: ");
                        System.out.println("1. Ajouter un compte");
                        System.out.println("2. Modifier un compte");
                        System.out.println("3. Changement de statut");
                        System.out.println("4. Supprimer un compte");
                        System.out.println("5. Afficher tous les comptes");
                        System.out.println("6. Afficher les comptes par statut");
                        System.out.println("7. Afficher les comptes par Date de création");
                        System.out.println("8. Chercher un compte par client");
                        System.out.println("9. Chercher un compte par numéro d'opération");
                        System.out.println("10. Retour");
                        System.out.println("Votre choix: ");
                        //get the user input
                        Scanner sc3 = new Scanner(System.in);
                        int choice3 = sc3.nextInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Ajouter un compte");
                                break;
                            case 2:
                                System.out.println("Modifier un compte");
                                break;
                            case 3:
                                System.out.println("Changement de statut");
                                break;
                            case 4:
                                System.out.println("Supprimer un compte");
                                break;
                            case 5:
                                System.out.println("Afficher tous les comptes");
                                break;
                            case 6:
                                System.out.println("Afficher les comptes par statut");
                                break;
                            case 7:
                                System.out.println("Afficher les comptes par Date de création");
                                break;
                            case 8:
                                System.out.println("Chercher un compte par client");
                                break;
                            case 9:
                                System.out.println("Chercher un compte par numéro d'opération");
                                break;
                            case 10:
                                System.out.println("Retour");
                                break;
                            default:
                                System.out.println("Choix invalide");
                                break;
                        }
                        if (choice3 == 10) {
                            break;
                        }
                    }while (true);
                    break;
                case 4:
                    System.out.println("< Administration des Opérations >");
                    while (true){
                        System.out.println("Veuillez choisir une option: ");
                        System.out.println("1. Ajouter une opération");
                        System.out.println("2. Supprimer une opération");
                        System.out.println("3. Chercher une opération par numéro");
                        System.out.println("4. Retour");
                        System.out.println("Votre choix: ");
                        //get the user input
                        Scanner sc4 = new Scanner(System.in);
                        int choice4 = sc4.nextInt();
                        switch (choice4) {
                            case 1:
                                System.out.println("Ajouter une opération");
                                break;
                            case 2:
                                System.out.println("Supprimer une opération");
                                break;
                            case 3:
                                System.out.println("Chercher une opération par numéro");
                                break;
                            case 4:
                                System.out.println("Retour");
                                break;
                            default:
                                System.out.println("Choix invalide");
                                break;
                        }
                        if (choice4 == 4) {
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println(" < Administration des missions >");
                    while (true){
                        System.out.println("Veuillez choisir une option: ");
                        System.out.println("1. Ajouter une mission");
                        System.out.println("2. Supprimer une mission");
                        System.out.println("3. Afficher toutes les missions");
                        System.out.println("4. Ajouter nouvelle affectation");
                        System.out.println("5. Supprimer une affectation");
                        System.out.println("6. Afficher l'historique des affectations d'une employé");
                        System.out.println("7. Statistiques des affectations");
                        System.out.println("8. Retour");
                        System.out.println("Votre choix: ");
                        //get the user input
                        Scanner sc5 = new Scanner(System.in);
                        int choice5 = sc5.nextInt();
                        switch (choice5) {
                            case 1:
                                System.out.println("Ajouter une mission");
                                break;
                            case 2:
                                System.out.println("Supprimer une mission");
                                break;
                            case 3:
                                System.out.println("Afficher toutes les missions");
                                break;
                            case 4:
                                System.out.println("Ajouter nouvelle affectation");
                                break;
                            case 5:
                                System.out.println("Supprimer une affectation");
                                break;
                            case 6:
                                System.out.println("Afficher l'historique des affectations d'une employé");
                                break;
                            case 7:
                                System.out.println("Statistiques des affectations");
                                break;
                            case 8:
                                System.out.println("Retour");
                                break;
                            default:
                                System.out.println("Choix invalide");
                                break;
                        }
                        if (choice5 == 8) {
                            break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Quitter");
                    quitter=true;
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }while (quitter==false);
    }
}
