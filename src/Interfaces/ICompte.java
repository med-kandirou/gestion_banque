package Interfaces;

import DTO.Client;
import DTO.Compte;
import DTO.Employe;

import java.util.Optional;

public interface ICompte {
    Optional<Client> ajouter (Client client);

    Optional<Client> supprimer (Client client);

    Optional<Client> chercherbyCode (Client client);
}
