package DAO;

import Config.Database;
import DTO.Employe;
import DTO.Personne;
import Interfaces.IPersonne;

import java.sql.*;

import java.util.Optional;


public class ImpEmploye implements IPersonne {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Personne> ajouter(Personne personne) {
        Employe emp=(Employe) personne;
        try {
            String insertSql = "INSERT INTO employe (matricule,nom, prenom, dateNaissance, telephone,dateDeRecrutement,adresseEmail) VALUES (?, ?, ?, ?,?,?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setString(1, emp.getMatricule());
            preparedStatement.setString(2, emp.getNom());
            preparedStatement.setString(3,emp.getPrenom());
            preparedStatement.setDate(4, (Date) emp.getDateNaissance());
            preparedStatement.setString(5,emp.getTelephone());
            preparedStatement.setDate(6, (Date) emp.getDateDeRecrutement());
            preparedStatement.setString(7,emp.getAdresseEmail());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(emp);
            }
            preparedStatement.close();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Personne> supprimer(Personne personne) {
        Employe emp=(Employe) personne;
        try {
            String deleteSql = "DELETE FROM employe WHERE matricule = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setString(1, emp.getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(emp);
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personne> chercherbyCode(Personne personne) {
        Employe emp=(Employe) personne;
        try {
            String selectSql = "SELECT * FROM employe WHERE matricule like '"+emp.getMatricule()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                emp.setNom(resultSet.getString("nom"));
                emp.setPrenom(resultSet.getString("prenom"));
                emp.setPrenom(resultSet.getString("datenaissance"));
                emp.setTelephone(resultSet.getString("telephone"));
                emp.setDateNaissance(resultSet.getDate("datederecrutement"));
                emp.setAdresseEmail(resultSet.getString("adresseemail"));
            }
            resultSet.close();
            preparedStatement.close();
            return Optional.ofNullable(emp);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
