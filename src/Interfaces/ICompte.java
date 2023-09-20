package Interfaces;

import DTO.Compte;

public interface ICompte {
    Compte ajouter();
    int supprimer();
    Compte statusUpdate();
    Compte[] showAll();
    Compte update();
    Compte[] findAllByClient();
    Compte[] showAllByStatus();
    Compte[] showAllByCreDate();
    Compte findByOpNum();
}
