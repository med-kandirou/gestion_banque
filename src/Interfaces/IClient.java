package Interfaces;

import DTO.Client;

public interface IClient {
    Client ajouter(Client client);
    int supprimer(Client client);
}
