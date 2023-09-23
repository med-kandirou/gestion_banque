package Interfaces;

import DTO.Client;
import DTO.Personne;

import java.util.List;
import java.util.Optional;

public interface IPersonne {
    Optional<Personne> ajouter (Personne personne);

    Optional<Personne> supprimer (Personne personne);

    Optional<Personne> chercherbyCode (Personne personne);

    Optional<Personne[]> afficherListe ();

    Optional<Personne> update();

}