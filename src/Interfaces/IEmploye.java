package Interfaces;

import DTO.Client;
import DTO.Employe;

import java.util.Optional;

public interface IEmploye {
    Optional<Employe> ajouter (Employe employe);

    Optional<Employe> supprimer (Employe employe);

    Optional<Employe> chercherbyCode (String employe);

    Optional<Employe[]> afficherListe();

    Optional<Employe[]> rechercheParAtt(String param);

    Optional<Employe> update(Employe employe);
}
