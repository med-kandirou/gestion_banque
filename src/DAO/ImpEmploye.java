package DAO;

import Config.Database;
import DTO.Client;
import DTO.Employe;
import DTO.Personne;
import Interfaces.IEmploye;

import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            preparedStatement.setDate(4, employe.getDateNaissance());
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
    public Optional<Employe> chercherbyCode(String code) {
        Employe emp=new Employe();
        try {
            String selectSql = "SELECT * FROM employe WHERE matricule like '"+code+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                emp.setMatricule(resultSet.getString("matricule"));
                emp.setNom(resultSet.getString("nom"));
                emp.setPrenom(resultSet.getString("prenom"));
                emp.setTelephone(resultSet.getString("telephone"));
                emp.setDateNaissance(resultSet.getDate("datenaissance"));
                emp.setDateDeRecrutement(resultSet.getDate("datederecrutement"));
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

    @Override
    public Optional<Employe[]> afficherListe() {
        List<Employe> employes= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM employe";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employe emp=new Employe();
                emp.setMatricule(resultSet.getString("matricule"));
                emp.setNom(resultSet.getString("nom"));
                emp.setPrenom(resultSet.getString("prenom"));
                emp.setTelephone(resultSet.getString("telephone"));
                emp.setDateNaissance(resultSet.getDate("datenaissance"));
                emp.setAdresseEmail(resultSet.getString("adresseemail"));
                emp.setDateDeRecrutement(resultSet.getDate("datederecrutement"));
                employes.add(emp);
            }
            resultSet.close();
            preparedStatement.close();
            Employe[] arrayPers = new Employe[employes.size()];
            arrayPers=employes.toArray(arrayPers);
            return Optional.of(arrayPers);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employe[]> rechercheParAtt(String param) {
        List<Employe> employes = new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM employe WHERE matricule LIKE ? OR nom LIKE ? OR prenom LIKE ? OR dateNaissance LIKE ? OR telephone LIKE ? OR dateDeRecrutement LIKE ? OR adresseEmail LIKE ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            String wildcardParam = "%" + param + "%";
            preparedStatement.setString(1, wildcardParam);
            preparedStatement.setString(2, wildcardParam);
            preparedStatement.setString(3, wildcardParam);
            preparedStatement.setString(4, wildcardParam);
            preparedStatement.setString(5, wildcardParam);
            preparedStatement.setString(6, wildcardParam);
            preparedStatement.setString(7, wildcardParam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employe emp = new Employe();
                emp.setMatricule(resultSet.getString("matricule"));
                emp.setNom(resultSet.getString("nom"));
                emp.setPrenom(resultSet.getString("prenom"));
                emp.setTelephone(resultSet.getString("telephone"));
                emp.setDateNaissance(resultSet.getDate("dateNaissance")); // Utilisation de getString pour la date
                emp.setDateDeRecrutement(resultSet.getDate("dateDeRecrutement")); // Utilisation de getString pour la date
                emp.setAdresseEmail(resultSet.getString("adresseEmail"));
                employes.add(emp);
            }
            resultSet.close();
            preparedStatement.close();
            Employe[] arrayemp = employes.toArray(new Employe[0]);
            return Optional.of(arrayemp);
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public Optional<Employe> update(Employe employe) {
        try {
            String updatequery = "UPDATE employe SET nom = ?, prenom = ?,datenaissance = ? ,telephone = ?,datederecrutement = ?, adresseemail = ? WHERE matricule like ?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(updatequery);
            preparedStatement.setString(1, employe.getNom());
            preparedStatement.setString(2, employe.getPrenom());
            preparedStatement.setDate(3, employe.getDateNaissance());
            preparedStatement.setString(4, employe.getTelephone());
            preparedStatement.setDate(5, employe.getDateDeRecrutement());
            preparedStatement.setString(6,employe.getAdresseEmail());
            preparedStatement.setString(7,employe.getMatricule());
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


}
