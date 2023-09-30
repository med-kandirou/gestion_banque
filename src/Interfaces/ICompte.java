package Interfaces;

import DTO.*;

import java.sql.Date;
import java.util.Optional;

public interface ICompte {
    Optional<Compte> ajoutercourant (CompteCourant c);

    Optional<Compte> ajouterepargne (CompteEpargne c);

    Optional<Compte> supprimer (Compte compte);

    Optional<Compte[]> chercherbyClient (Client client);
    Optional<Compte> changerStatut (Compte compte);

    Optional<Compte[]> afficherList ();

    Optional<Compte> update(Compte compte);

    Optional<Compte[]> afficheParStatut(String statut);
    Optional<Compte[]> afficheParDate(java.sql.Date date);
}
