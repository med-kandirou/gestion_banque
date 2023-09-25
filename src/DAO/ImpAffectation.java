package DAO;

import Config.Database;
import DTO.Affectation;
import Interfaces.IAffectation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ImpAffectation implements IAffectation {
    Connection cnx= Database.getconn();
    @Override
    public Optional<Affectation> ajouter(Affectation affectation) {
        try {
            String insertSql = "insert into affectation (emp_mat,codemission, datechangement) values (?,?,?);";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setString(1, affectation.getEmploye().getMatricule());
            preparedStatement.setString(2, affectation.getMission().getCode());
            preparedStatement.setDate(3,(Date) affectation.getDateChangement());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(affectation);
            }
            preparedStatement.close();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public Optional<Integer> supprimer(Affectation affectation) {
        try {
            String deleteSql = "DELETE FROM affectation WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setInt(1, affectation.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(1);
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
