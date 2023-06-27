package com.fastcampus.baseballmanagement.stadium.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.stadium.dto.Stadium;
import com.fastcampus.baseballmanagement.stadium.dto.StadiumRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {

    private static StadiumDAO stadiumDAO;
    private Connection connection;

    private StadiumDAO(Connection connection){
        this.connection = connection;
    }

    public static StadiumDAO getInstance() {
        if(stadiumDAO == null)
            stadiumDAO = new StadiumDAO(DBConnection.getInstance());
        return stadiumDAO;
    }

    // SQL DML
    private final String insertQuery =
            "INSERT" +
                    " INTO stadium" +
                    " (name, created_at)" +
                    " VALUES" +
                    " (?, now())"
            ;

    private final String selectAllQuery =
            "SELECT" +
                    " stadium_id, name, created_at" +
                    " FROM stadium"
            ;

    public StadiumRegistration registerStadium(String name) {
        StadiumRegistration response = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, name);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("경기장 등록 성공 : " + name);
                response = new StadiumRegistration("경기장 등록에 성공했습니다.", null);
            } else {
                System.out.println("경기장 등록 실패");
                response = new StadiumRegistration("경기장 등록에 실패했습니다.", null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public List<Stadium> getAllStadiums() {
        List<Stadium> stadiums = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllQuery);

            while (resultSet.next()) {
                int stadiumId = resultSet.getInt("stadium_id");
                String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                Stadium stadium = Stadium.builder()
                        .stadiumId(stadiumId)
                        .name(name)
                        .createdAt(createdAt)
                        .build();

                stadiums.add(stadium);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stadiums;
    }


//    Stadium findById(int id);
//    int delete(int id);
//    int update(Stadium stadium);

}
