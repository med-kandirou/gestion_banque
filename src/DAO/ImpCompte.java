package DAO;

import Config.Database;
import DTO.Compte;
import Interfaces.ICompte;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Optional;

public class ImpCompte implements ICompte {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Compte> ajouter(Compte compte) {
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
        }
        return Optional.empty();*/

        return null;
    }

    @Override
    public Optional<Compte> supprimer(Compte compte) {
        return Optional.empty();
    }

    @Override
    public Optional<Compte> chercherbyCode(Compte compte) {
        return Optional.empty();
    }
}
