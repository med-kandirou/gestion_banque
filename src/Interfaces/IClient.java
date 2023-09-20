package Interfaces;

import DTO.Client;

public interface IClient {
    Client ajouter();
    int supprimer();
    Client[] afficher();
    Client update();
    Client findByCode();
    Client findByAttribute();
}
