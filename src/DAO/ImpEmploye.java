package DAO;

import Config.Database;
import DTO.Employe;
import Interfaces.IEmploye;

import java.sql.*;

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
            preparedStatement.setDate(4, (Date) employe.getDateNaissance());
            preparedStatement.setString(5,employe.getTelephone());
            preparedStatement.setDate(6, (Date) employe.getDateDeRecrutement());
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

    public Optional<Employe> supprimer(Employe employe) {
        try {
            String deleteSql = "DELETE FROM employe WHERE matricule = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setString(1, employe.getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(employe);
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employe> chercherbyMat(Employe employe) {
        try {
            String selectSql = "SELECT * FROM employe WHERE matricule like '"+employe.getMatricule()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                employe.setNom(resultSet.getString("nom"));
                employe.setPrenom(resultSet.getString("prenom"));
                employe.setPrenom(resultSet.getString("datenaissance"));
                employe.setTelephone(resultSet.getString("telephone"));
                employe.setDateNaissance(resultSet.getDate("datederecrutement"));
                employe.setAdresseEmail(resultSet.getString("adresseemail"));
            }
            resultSet.close();
            preparedStatement.close();
            return Optional.ofNullable(employe);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
