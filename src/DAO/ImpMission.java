package DAO;

import Config.Database;
import DTO.Client;
import DTO.Mission;
import DTO.Operation;
import Interfaces.IMission;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ImpMission implements IMission {

    Connection cnx= Database.getconn();
    @Override
    public Optional<Mission> ajouter(Mission mission) {
        try {
            String insertSql = "insert into mission (nom, description) values (?,?);";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = cnx.prepareStatement(insertSql);
            preparedStatement.setString(1, mission.getNom());
            preparedStatement.setString(2, mission.getDescription());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(mission);
            }
            preparedStatement.close();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Mission> supprimer(Mission mission) {
        try {
            String deleteSql = "DELETE FROM mission WHERE code = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(deleteSql);
            preparedStatement.setInt(1,mission.getCode());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.ofNullable(mission);
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Mission[]> afficherList() {
        ArrayList<Mission> missions = new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM mission";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Mission mission =new Mission();
                mission.setCode(resultSet.getInt("code"));
                mission.setNom(resultSet.getString("nom"));
                mission.setDescription(resultSet.getString("description"));
                missions.add(mission);
            }
            resultSet.close();
            preparedStatement.close();
            Mission[] arrayMission = missions.toArray(new Mission[0]);
            return Optional.ofNullable(arrayMission);
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return Optional.empty();
    }
}
