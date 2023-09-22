package Interfaces;

import DTO.Client;
import DTO.Compte;
import DTO.Employe;

import java.util.Optional;

public interface ICompte {
    Optional<Compte> ajouter (Compte compte);

    Optional<Compte> supprimer (Compte compte);

    Optional<Compte> chercherbyCode (Compte compte);
}
