package DAO;

import Config.Database;
import DTO.*;
import Enums.Etat;
import Interfaces.ICompte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImpCompte implements ICompte {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Compte> ajoutercourant(CompteCourant c) {
        try {
            String insertCompteSql = "INSERT INTO compte (code, solde, etat, client_id, Emp_mat) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(insertCompteSql);
            preparedStatement.setString(1, c.getCode());
            preparedStatement.setDouble(2, c.getSolde());
            preparedStatement.setString(3, c.getEtat().toString());
            preparedStatement.setString(4, c.getClient().getCode());
            preparedStatement.setString(5, c.getEmploye().getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsAffected > 0) {
                String insertCompteCourantSql = "INSERT INTO CompteCourant (code, decouvert) VALUES (?, ?)";
                preparedStatement = cnx.prepareStatement(insertCompteCourantSql);
                preparedStatement.setString(1, c.getCode());
                preparedStatement.setDouble(2, c.getDecouvertAutorise());
                rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                if (rowsAffected > 0) {
                    return Optional.of(c);
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public Optional<Compte> ajouterepargne(CompteEpargne c) {
        try {
            String insertCompteSql = "INSERT INTO compte (code, solde, etat, client_id, Emp_mat) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(insertCompteSql);
            preparedStatement.setString(1, c.getCode());
            preparedStatement.setDouble(2, c.getSolde());
            preparedStatement.setString(3, c.getEtat().toString());
            preparedStatement.setString(4, c.getClient().getCode());
            preparedStatement.setString(5, c.getEmploye().getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rowsAffected > 0) {
                String insertCompteCourantSql = "INSERT INTO CompteCourant (code, taux) VALUES (?, ?)";
                preparedStatement = cnx.prepareStatement(insertCompteCourantSql);
                preparedStatement.setString(1, c.getCode());
                preparedStatement.setDouble(2, c.getTauxInteret());
                rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                if (rowsAffected > 0) {
                    return Optional.of(c);
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
                cmp.setEtat(Etat.valueOf(resultSet.getString("etat")));
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
                cmp.setEtat(Etat.valueOf(resultSet.getString("etat")));
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

    @Override
    public Optional<Compte[]> afficherList() {
        List<Compte> Comptes= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM compte";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Compte compte=new Compte();
                compte.setCode(resultSet.getString("code"));
                compte.setSolde(resultSet.getDouble("solde"));
                compte.setDateCreation(resultSet.getDate("datecreation"));
                compte.setEtat(Etat.valueOf(resultSet.getString("etat")));
                Client c= new Client();
                c.setCode(resultSet.getString("client_id"));
                compte.setClient(c);
                Employe emp= new Employe();
                emp.setMatricule(resultSet.getString("emp_mat"));
                compte.setEmploye(emp);
            }
            resultSet.close();
            preparedStatement.close();
            Compte[] arrayComp = Comptes.toArray(new Compte[0]);
            return Optional.of(arrayComp);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte> update(Compte compte) {
        try {
            String updatequery = "UPDATE compte SET solde = ?,etat = ? WHERE code like ?;";
            PreparedStatement preparedStatement = cnx.prepareStatement(updatequery);
            preparedStatement.setDouble(1, compte.getSolde());
            preparedStatement.setString(2,compte.getEtat().toString());
            preparedStatement.setString(3, compte.getCode());
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
    public Optional<Compte[]> afficheParStatut(String statut) {
        List<Compte> Comptes= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM compte WHERE etat like ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            preparedStatement.setString(1, statut);
            preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Compte compte=new Compte();
                compte.setCode(resultSet.getString("code"));
                compte.setSolde(resultSet.getDouble("solde"));
                compte.setDateCreation(resultSet.getDate("datecreation"));
                compte.setEtat(Etat.valueOf(resultSet.getString("etat")));
                Client c= new Client();
                c.setCode(resultSet.getString("client_id"));
                compte.setClient(c);
                Employe emp= new Employe();
                emp.setMatricule(resultSet.getString("emp_mat"));
                compte.setEmploye(emp);
            }
            resultSet.close();
            preparedStatement.close();
            Compte[] arrayComp = Comptes.toArray(new Compte[0]);
            return Optional.of(arrayComp);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte[]> afficheParDate(Date date) {
        List<Compte> Comptes= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM compte WHERE datecreation = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Compte compte=new Compte();
                compte.setCode(resultSet.getString("code"));
                compte.setSolde(resultSet.getDouble("solde"));
                compte.setDateCreation(resultSet.getDate("datecreation"));
                compte.setEtat(Etat.valueOf(resultSet.getString("etat")));
                Client c= new Client();
                c.setCode(resultSet.getString("client_id"));
                compte.setClient(c);
                Employe emp= new Employe();
                emp.setMatricule(resultSet.getString("emp_mat"));
                compte.setEmploye(emp);
            }
            resultSet.close();
            preparedStatement.close();
            Compte[] arrayComp = Comptes.toArray(new Compte[0]);
            return Optional.of(arrayComp);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
