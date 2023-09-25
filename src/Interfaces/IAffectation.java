package Interfaces;

import DTO.Affectation;

import java.util.Optional;

public interface IAffectation {

    Optional<Affectation> ajouter(Affectation affectation);
    Optional<Integer> supprimer(Affectation affectation);

}
