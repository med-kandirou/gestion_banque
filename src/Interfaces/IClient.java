package Interfaces;

import DTO.Client;

import java.util.Optional;

public interface IClient {
    Optional<Client> ajouter (Client client);

    Optional<Client> supprimer (Client client);

    Optional<Client> chercherbyCode (Client client);
}
