package DAO;

import Config.Database;
import DTO.Client;
import DTO.Employe;
import DTO.Personne;
import Interfaces.IPersonne;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
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
    public Optional<Personne> chercherbyCode(String code) {
        Employe emp=new Employe();
        try {
            String selectSql = "SELECT * FROM employe WHERE matricule like '"+code+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                emp.setMatricule(resultSet.getString("matricule"));
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

    @Override
    public Optional<Personne[]> afficherListe() {
        List<Employe> employes= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM employe";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Employe emp=new Employe();
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
            Personne[] arrayPers = employes.toArray(new Employe[0]);
            return Optional.of(arrayPers);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personne[]> rechercheParAtt(Personne personne) {
        Employe emp=(Employe) personne;
        List<Employe> employes= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM employe where matricule like ? nom like ? Or prenom like ? OR dateNaissance like ? Or telephone like ? Or dateDeRecrutement like ? Or adresseEmail like ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            preparedStatement.setString(1, emp.getMatricule());
            preparedStatement.setString(2, emp.getNom());
            preparedStatement.setString(3, emp.getPrenom());
            preparedStatement.setDate(4,(Date) emp.getDateNaissance());
            preparedStatement.setString(5, emp.getTelephone());
            preparedStatement.setDate(6,(Date) emp.getDateDeRecrutement());
            preparedStatement.setString(7,emp.getAdresseEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Employe employe=new Employe();
                employe.setMatricule(resultSet.getString("matricule"));
                employe.setNom(resultSet.getString("nom"));
                employe.setPrenom(resultSet.getString("prenom"));
                employe.setTelephone(resultSet.getString("telephone"));
                employe.setDateNaissance(resultSet.getDate("datenaissance"));
                employe.setDateNaissance(resultSet.getDate("datederecrutement"));
                employe.setAdresseEmail(resultSet.getString("adresseemail"));
                employes.add(employe);
            }
            resultSet.close();
            preparedStatement.close();
            Personne[] arrayemp = employes.toArray(new Client[0]);
            return Optional.of(arrayemp);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personne> update() {
        return Optional.empty();
    }


}
