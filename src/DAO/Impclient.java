package DAO;

import Config.Database;
import DTO.Client;
import DTO.Personne;
import Interfaces.IClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Impclient implements IClient {
    Connection cnx= Database.getconn();
    Client client;
    @Override
    public Optional<Client> ajouter(Client client) {
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

    public Optional<Client> supprimer(Client client) {
        try {
            String deleteSql = "DELETE FROM client WHERE code = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setString(1, client.getCode());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(client);
            }
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> chercherbyCode(String code) {
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
    public Optional<Client[]> afficherListe() {
        List<Client> Clients= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM client";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
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
            Client[] arraycl = Clients.toArray(new Client[0]);
            return Optional.of(arraycl);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client[]> rechercheParAtt(String param) {
        List<Client> Clients= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM client WHERE code LIKE ? OR nom LIKE ? OR prenom LIKE ? OR telephone LIKE ? OR adresse LIKE ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            preparedStatement.setString(1,param);
            preparedStatement.setString(2, param);
            preparedStatement.setString(3,param);
            preparedStatement.setString(4, param);
            preparedStatement.setString(5,param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Client cl=new Client();
                cl.setCode(resultSet.getString("code"));
                cl.setNom(resultSet.getString("nom"));
                cl.setPrenom(resultSet.getString("prenom"));
                cl.setTelephone(resultSet.getString("telephone"));
                cl.setDateNaissance(resultSet.getDate("datenaissance"));
                cl.setAdresse(resultSet.getString("adresse"));
                Clients.add(cl);
            }
            resultSet.close();
            preparedStatement.close();
            Client[] arrayPers = Clients.toArray(new Client[0]);
            return Optional.of(arrayPers);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client client) {
        try {
            String updatequery = "UPDATE client SET nom = ?, prenom = ?,datenaissance = ? ,telephone = ?,adresse = ? WHERE code like ?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(updatequery);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setDate(3,(Date) client.getDateNaissance());
            preparedStatement.setString(4, client.getTelephone());
            preparedStatement.setString(5,client.getAdresse());
            preparedStatement.setString(6,client.getCode());
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


}
