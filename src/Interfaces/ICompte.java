package Interfaces;

import DTO.Client;
import DTO.Compte;
import DTO.Employe;

import java.sql.Date;
import java.util.Optional;

public interface ICompte {
    Optional<Compte> ajoutercourant (Compte c);

    Optional<Compte> ajouterepargne (Compte c);

    Optional<Compte> supprimer (Compte compte);

    Optional<Compte[]> chercherbyClient (Client client);
    Optional<Compte> changerStatut (Compte compte);

    Optional<Compte[]> afficherList ();

    Optional<Compte> update(Compte compte);

    Optional<Compte[]> afficheParStatut(String statut);
    Optional<Compte[]> afficheParDate(Date date);
}
