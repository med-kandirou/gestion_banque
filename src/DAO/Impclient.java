package DAO;

import Config.Database;
import DTO.Client;
import DTO.Personne;
import Interfaces.IPersonne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Impclient implements IPersonne {
    Connection cnx= Database.getconn();
    Client client;
    @Override
    public Optional<Personne> ajouter(Personne personne) {
        client=(Client) personne;
        try {
            String insertSql = "insert into client (code, nom, prenom, datenaissance, telephone, adresse) values (?,?,?,?,?,?);";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setString(1, client.getCode());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3,client.getPrenom());
            preparedStatement.setDate(4, (Date) client.getDateNaissance());
            preparedStatement.setString(5,client.getTelephone());
            preparedStatement.setString(6,client.getAdresse());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(client);
            }
            preparedStatement.close();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Personne> supprimer(Personne personne) {
        client=(Client) personne;
        try {
            String deleteSql = "DELETE FROM compte WHERE code = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setString(1, client.getCode());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(client);
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
        Client client=new Client();
        try {
            String selectSql = "SELECT * FROM client WHERE code like '"+code+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                client.setCode(resultSet.getString("code"));
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setPrenom(resultSet.getString("datenaissance"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setDateNaissance(resultSet.getDate("datenaissance"));
                client.setAdresse(resultSet.getString("adresse"));
            }
            resultSet.close();
            preparedStatement.close();
            return Optional.ofNullable(client);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personne[]> afficherListe() {
        List<Client> Clients= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM client";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Client client=new Client();
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setDateNaissance(resultSet.getDate("datenaissance"));
                client.setAdresse(resultSet.getString("adresse"));
                Clients.add(client);
            }
            resultSet.close();
            preparedStatement.close();
            Personne[] arrayPers = Clients.toArray(new Client[0]);
            return Optional.of(arrayPers);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Personne[]> rechercheParAtt(Personne personne) {
        client=(Client) personne;
        List<Client> Clients= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM client where code like ? nom like ? Or prenom like ? OR dateNaissance like ? Or telephone like ? Or adresse like ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            preparedStatement.setString(1, client.getCode());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setDate(4,(Date) client.getDateNaissance());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setString(6, client.getAdresse());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Client client=new Client();
                client.setCode(resultSet.getString("code"));
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setDateNaissance(resultSet.getDate("datenaissance"));
                client.setAdresse(resultSet.getString("adresse"));
                Clients.add(client);
            }
            resultSet.close();
            preparedStatement.close();
            Personne[] arrayPers = Clients.toArray(new Client[0]);
            return Optional.of(arrayPers);
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
