package Interfaces;

import DTO.Mission;
import DTO.Operation;

import java.util.Optional;

public interface IMission {
    Optional<Mission> jouter (Mission mission);

    Optional<Mission> supprimer (Mission mission);

    Optional<Mission[]> afficherList();

}
