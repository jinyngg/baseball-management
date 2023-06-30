package com.fastcampus.baseballmanagement.stadium.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.exception.StadiumException;
import com.fastcampus.baseballmanagement.exception.TeamException;
import com.fastcampus.baseballmanagement.stadium.dto.Stadium;
import com.fastcampus.baseballmanagement.stadium.dto.StadiumRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.fastcampus.baseballmanagement.exception.ErrorCode.SQL_EXCEPTION;

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

    public StadiumRegistration registerStadium(String name) throws StadiumException {
        StadiumRegistration response = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, name);

            response = new StadiumRegistration("경기장 등록에 성공했습니다.", name);

        } catch (SQLException e) {
            throw new StadiumException(SQL_EXCEPTION, name);
        }

        return response;
    }

    public List<Stadium> getAllStadiums() throws StadiumException {
        List<Stadium> stadiums = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();

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

        } catch (SQLException e) {
            throw new StadiumException(SQL_EXCEPTION);
        }
        return stadiums;
    }

}
