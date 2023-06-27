package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.team.dto.Team;
import com.fastcampus.baseballmanagement.team.dto.TeamRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /** 팀 등록 */
    public TeamRegistration registerTeam(String name, int stadiumId)  {
        TeamRegistration response = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, stadiumId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("팀 등록 성공 : " + name);
                response = new TeamRegistration("팀 등록에 성공했습니다.", null);
            } else {
                System.out.println("팀 등록 실패");
                response = new TeamRegistration("팀 등록에 실패했습니다.", null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    /** 전체 팀 목록 */
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllQuery);

            while (resultSet.next()) {
                int teamId = resultSet.getInt("team_id");
                int stadiumId = resultSet.getInt("stadium_id");
                String name = resultSet.getString("name");
                String createdAt = resultSet.getString("created_at");

                Team team = Team.builder()
                        .teamId(teamId)
                        .stadiumId(stadiumId)
                        .name(name)
                        .createdAt(Timestamp.valueOf(createdAt))
                        .build();

                teams.add(team);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }
}
