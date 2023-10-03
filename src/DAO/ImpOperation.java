package DAO;

import Config.Database;
import DTO.Client;
import DTO.Compte;
import DTO.Employe;
import DTO.Operation;
import Enums.Type;
import Interfaces.IOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImpOperation implements IOperation {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Operation> ajouter(Operation operation) {
        System.out.print(operation.getType());
        try {
            String insertSql = "insert into operation (montant, type, compte_id, emp_mat) values (?,?,?,?);";
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setDouble(1, operation.getMontant());
            preparedStatement.setString(2,operation.getType().toString());
            preparedStatement.setString(3,operation.getCompte().getCode());
            preparedStatement.setString(4,operation.getEmploye().getMatricule());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(operation);
            }
            preparedStatement.close();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Operation> supprimer(Operation operation) {
        try {
            String deleteSql = "DELETE FROM operation WHERE numero = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setInt(1, operation.getNumero());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(operation);
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Operation> chercherbyNum(Operation operation) {
        try {
            String selectSql = "SELECT * FROM operation WHERE numero = '"+operation.getNumero()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Employe employe =new Employe();
                Compte clt = new Compte();
                operation.setDateOperation(resultSet.getDate("dateoperation"));
                operation.setMontant(resultSet.getDouble("montant"));
                if(resultSet.getString("type")=="retrait"){
                    operation.setType(Type.retrait);
                }
                else {
                    operation.setType(Type.versement);
                }
                employe.setMatricule(resultSet.getString("emp_mat"));
                clt.setCode(resultSet.getString("compte_id"));
                operation.setEmploye(employe);
                operation.setCompte(clt);
                return Optional.ofNullable(operation);
            }
            resultSet.close();
            preparedStatement.close();
            return Optional.empty();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
