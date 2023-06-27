package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamDAO {

    private Connection connection;

    // SQL DML
    private final String insertQuery =
            "INSERT" +
                    " INTO team" +
                    " (team_id, name, stadium_id, created_at)" +
                    " VALUES" +
                    " ((SELECT NO FROM (SELECT IFNULL(MAX(team_id), 0) + 1 AS NO FROM team) t), ?, ?, now())"
            ;

    private final String selectAllQuery =
            "SELECT" +
                    " team_id, stadium_id, name, created_at" +
                    " FROM team"
            ;

    private static TeamDAO teamDAO;

    private TeamDAO(Connection connection){
        this.connection = connection;
    }

    /**
     * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 TeamDAO 생성
     * @return TeamDAO
     */
    public static TeamDAO getInstance() {
        if(teamDAO == null){
            teamDAO = new TeamDAO(DBConnection.getInstance());
        }

        return teamDAO;
    }

    public void registerTeam(String name, int stadiumId)  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, stadiumId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("팀 등록 성공 : " + name);
            } else {
                System.out.println("팀 등록 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
