package Interfaces;

import DTO.Client;
import DTO.Personne;

import java.util.Optional;

public interface IClient {

    Optional<Client> ajouter (Client client);

    Optional<Client> supprimer (Client client);

    Optional<Client> chercherbyCode (String code);

    Optional<Client[]> afficherListe();

    Optional<Client[]> rechercheParAtt(String param);

    Optional<Client> update(Client client);
}
