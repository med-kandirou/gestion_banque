package DAO;

import Config.Database;
import DTO.Client;
import DTO.Personne;
import Interfaces.IPersonne;

import java.nio.file.attribute.AclEntry;
import java.sql.*;
import java.util.Optional;

public class Impclient implements IPersonne {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Personne> ajouter(Personne personne) {
        Client client=(Client) personne;
        /*try {
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
        }*/
        System.out.printf(client.getCode());
        System.out.printf(client.getNom());
        System.out.printf(client.getPrenom());
        System.out.printf(client.getAdresse());
        return Optional.empty();
    }

    public Optional<Personne> supprimer(Personne personne) {
        Client client=(Client) personne;
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
    public Optional<Personne> chercherbyCode(Personne personne) {
        Client client=(Client) personne;
        try {
            String selectSql = "SELECT * FROM client WHERE code like '"+client.getCode()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
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

}
