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
            String insertSql = "insert into affectation (emp_mat,codemission) values (?,?);";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setString(1, affectation.getEmploye().getMatricule());
            preparedStatement.setInt(2, affectation.getMission().getCode());
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
            String sql = "select m.nom,m.description,a.datechangement from affectation a inner join mission m on m.code = a.codemission where emp_mat=?;";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, emp_mat);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Affectation affectation= new Affectation();
                affectation.setDateChangement(resultSet.getDate("datechangement"));
                Mission mission= new Mission();
                mission.setNom(resultSet.getString("nom"));
                mission.setDescription(resultSet.getString("description"));
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

    @Override
    public Optional<Integer> NbrMissEmp(String mat) {
        try {
            String sql = "SELECT count(*) AS count FROM affectation WHERE emp_mat = ?";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, mat);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return Optional.of(count);
            } else {
                return Optional.of(0);
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

}
