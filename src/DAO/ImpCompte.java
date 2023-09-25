package DAO;

import Config.Database;
import DTO.*;
import Interfaces.ICompte;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ImpCompte implements ICompte {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Compte> ajoutercourant(Compte c) {
        CompteCourant compte = (CompteCourant) c;
        try {
            String insertCompteSql = "INSERT INTO compte (code, solde, dateCreation, etat, client_id, Emp_mat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(insertCompteSql);
            preparedStatement.setString(1, compte.getCode());
            preparedStatement.setDouble(2, compte.getSolde());
            preparedStatement.setDate(3, (Date) compte.getDateCreation());
            preparedStatement.setString(4, compte.getEtat());
            preparedStatement.setString(5, compte.getClient().getCode());
            preparedStatement.setString(6, compte.getEmploye().getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsAffected > 0) {
                String insertCompteCourantSql = "INSERT INTO CompteCourant (code, decouvert) VALUES (?, ?)";
                preparedStatement = cnx.prepareStatement(insertCompteCourantSql);
                preparedStatement.setString(1, compte.getCode());
                preparedStatement.setDouble(2, compte.getDecouvertAutorise());
                rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                if (rowsAffected > 0) {
                    return Optional.of(compte);
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public Optional<Compte> ajouterepargne(Compte c) {
        CompteEpargne compte = (CompteEpargne) c;
        try {
            String insertCompteSql = "INSERT INTO compte (code, solde, dateCreation, etat, client_id, Emp_mat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(insertCompteSql);
            preparedStatement.setString(1, compte.getCode());
            preparedStatement.setDouble(2, compte.getSolde());
            preparedStatement.setDate(3, (Date) compte.getDateCreation());
            preparedStatement.setString(4, compte.getEtat());
            preparedStatement.setString(5, compte.getClient().getCode());
            preparedStatement.setString(6, compte.getEmploye().getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsAffected > 0) {
                String insertCompteCourantSql = "INSERT INTO CompteCourant (code, taux) VALUES (?, ?)";
                preparedStatement = cnx.prepareStatement(insertCompteCourantSql);
                preparedStatement.setString(1, compte.getCode());
                preparedStatement.setDouble(2, compte.getTauxInteret());
                rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                if (rowsAffected > 0) {
                    return Optional.of(compte);
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte> supprimer(Compte compte) {
        try {
            String deleteSql = "DELETE FROM compte WHERE code = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setString(1, compte.getCode());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(compte);
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte[]> chercherbyClient(Client client) {
        ArrayList<Compte> comptes=new ArrayList();
        try {
            String selectSql = "SELECT * FROM compte WHERE client_id like '"+client.getCode()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Compte cmp=new Compte();
                cmp.setDateCreation(resultSet.getDate("datecreation"));
                cmp.setSolde(resultSet.getDouble("solde"));
                cmp.setEtat(resultSet.getString("etat"));
                Employe emp= new Employe();
                emp.setMatricule(resultSet.getString("emp_mat"));
                cmp.setEmploye(emp);
                comptes.add(cmp);
            }
            resultSet.close();
            preparedStatement.close();
            Compte[] arrayCompte= comptes.toArray(new Compte[0]);
            return Optional.ofNullable(arrayCompte);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte> changerStatut(Compte compte) {
        try {
            String selectSql = "SELECT * FROM compte WHERE client_id like '"+compte.getCode()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Compte cmp=new Compte();
                cmp.setDateCreation(resultSet.getDate("datecreation"));
                cmp.setSolde(resultSet.getDouble("solde"));
                cmp.setEtat(resultSet.getString("etat"));
                Employe emp= new Employe();
                emp.setMatricule(resultSet.getString("emp_mat"));
                cmp.setEmploye(emp);
            }
            resultSet.close();
            preparedStatement.close();
            //Compte[] arrayCompte= comptes.toArray(new Compte[0]);
            //return Optional.ofNullable(arrayCompte);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
