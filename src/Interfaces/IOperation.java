package Interfaces;

import DTO.Compte;
import DTO.Operation;

import java.util.List;
import java.util.Optional;

public interface IOperation {
    Optional<Operation> ajouter (Operation operation);

    Optional<Operation> supprimer (Operation operation);

    Optional<Operation> chercherbyNum (Operation operation);
}
