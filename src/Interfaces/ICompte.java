package Interfaces;

import DTO.Client;
import DTO.Compte;
import DTO.Employe;

import java.util.Optional;

public interface ICompte {
    Optional<Compte> ajoutercourant (Compte c);

    Optional<Compte> ajouterepargne (Compte c);

    Optional<Compte> supprimer (Compte compte);

    Optional<Compte[]> chercherbyClient (Client client);
    Optional<Compte> changerStatut (Compte compte);


}
