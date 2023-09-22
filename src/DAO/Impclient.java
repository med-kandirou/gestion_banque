package DAO;

import Config.Database;
import DTO.Client;
import DTO.Compte;
import DTO.Employe;
import Interfaces.IClient;
import Interfaces.ICompte;

import java.sql.*;
import java.util.Optional;

public class Impclient implements IClient {
    Connection cnx= Database.getconn();
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
    public Optional<Client> chercherbyCode(Client client) {
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
