package Interfaces;

import DTO.Employe;

import java.util.Optional;

public interface IEmploye {
    Optional<Employe> ajouter (Employe employe);
}
