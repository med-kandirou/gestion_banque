package DAO;

import Config.Database;
import DTO.Mission;
import DTO.Operation;
import Interfaces.IMission;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ImpMission implements IMission {

    Connection cnx= Database.getconn();
    @Override
    public Optional<Mission> jouter(Mission mission) {
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
            preparedStatement.setString(1, mission.getCode());
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
}
