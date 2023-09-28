package DAO;

import Config.Database;
import DTO.*;
import Interfaces.IAffectation;
import java.sql.*;
import java.util.ArrayList;
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
            preparedStatement.setInt(2, affectation.getMission().getCode());
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
    @Override
    public Optional<Affectation[]> Historique(String emp_mat) {
        ArrayList<Affectation> affectations= new ArrayList<>();
        try {
            String sql = "SELECT * FROM affectation WHERE emp_mat = ? ";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, emp_mat);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Affectation affectation= new Affectation();
                affectation.setId(resultSet.getInt("id"));
                affectation.setDateChangement(resultSet.getDate("datechangement"));
                Employe emp= new Employe();
                emp.setMatricule(resultSet.getString("emp_mat"));
                affectation.setEmploye(emp);
                Mission mission= new Mission();
                mission.setCode(resultSet.getInt("codemission"));
                affectation.setMission(mission);
                affectations.add(affectation);
            }
            resultSet.close();
            statement.close();
            Affectation[] arrayAffec= affectations.toArray(new Affectation[0]);
            return Optional.ofNullable(arrayAffec);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

}
