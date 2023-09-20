package Interfaces;

import DTO.Employe;

public interface IEmploye {
    public interface EmployeRepository {
        Employe ajouter();
        int supprimer();
        Employe[] showAll();
        Employe update();
        Employe findByMatricule();
        Employe findByAtt();
    }

}
