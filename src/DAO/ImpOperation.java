package DAO;

import Config.Database;
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
        try {
            String insertSql = "insert into operation (dateoperation, montant, type, compte_id, emp_mat) values (?,?,?,?,?);";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setDate(1,(Date) operation.getDateOperation());
            preparedStatement.setDouble(2, operation.getMontant());
            preparedStatement.setString(3,operation.getType().toString());
            preparedStatement.setString(4,operation.getCompte().getCode());
            preparedStatement.setString(5,operation.getEmploye().getMatricule());
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
            preparedStatement.setString(1, operation.getNumero());
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
    public Optional<List<Operation>> chercherbyNum(Operation operation) {
        List<Operation> ops= new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM operation WHERE numero like '"+operation.getNumero()+"'";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Operation op =new Operation();
                Compte compte =new Compte();
                Compte client =new Compte();
                Employe employe =new Employe();
                op.setDateOperation(resultSet.getDate("dateoperation"));
                op.setMontant(resultSet.getDouble("montant"));
                if(resultSet.getString("type")=="retrait"){
                    operation.setType(Type.retrait);
                }
                else {
                    operation.setType(Type.versement);
                }
                return Optional.empty();
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
