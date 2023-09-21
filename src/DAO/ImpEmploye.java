package DAO;

import Config.Database;
import DTO.Employe;
import Interfaces.IEmploye;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class ImpEmploye implements IEmploye {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Employe> ajouter(Employe employe) {
        try {
            String insertSql = "INSERT INTO employe (matricule,nom, prenom, dateNaissance, telephone,dateDeRecrutement,adresseEmail) VALUES (?, ?, ?, ?,?,?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setString(1, employe.getMatricule());
            preparedStatement.setString(2, employe.getNom());
            preparedStatement.setString(3,employe.getPrenom());
            preparedStatement.setString(4,employe.getDateNaissance());
            preparedStatement.setString(5,employe.getTelephone());
            preparedStatement.setDate(6,employe.getDateDeRecrutement());
            preparedStatement.setString(7,employe.getAdresseEmail());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(employe);
            }
            preparedStatement.close();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
