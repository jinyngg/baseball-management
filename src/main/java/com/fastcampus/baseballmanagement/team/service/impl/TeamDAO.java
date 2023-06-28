package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.team.dto.TeamList;
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

    private final String selectTeamListQuery =
            "SELECT" +
                    " t.team_id, t.stadium_id, t.name AS team_name, s.name AS stadium_name, t.created_at" +
                    " FROM team t" +
                    " JOIN stadium s ON t.stadium_id = s.stadium_id";

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
//                response = new TeamRegistration("팀 등록에 성공했습니다.", name);
                return TeamRegistration.builder()
                        .message("팀 등록에 성공했습니다.")
                        .data(name)
                        .build();
            } else {
//                response = new TeamRegistration("팀 등록에 실패했습니다.", name);
                return TeamRegistration.builder()
                        .message("팀 등록에 실패했습니다.")
                        .data(name)
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return TeamRegistration.builder()
                    .message("팀 등록에 실패했습니다.")
                    .data(name)
                    .build();
        }

//        return response;
    }

    /** 전체 팀 목록 */
    public TeamList getTeamList() {
        List<TeamList.TeamStadium> teamStadiums = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectTeamListQuery)) {

            while (resultSet.next()) {
                int teamId = resultSet.getInt("team_id");
                int stadiumId = resultSet.getInt("stadium_id");
                String teamName = resultSet.getString("team_name");
                String stadiumName = resultSet.getString("stadium_name");
                String createdAt = resultSet.getString("created_at");

                TeamList.TeamStadium team = TeamList.TeamStadium.builder()
                        .teamId(teamId)
                        .stadiumId(stadiumId)
                        .teamName(teamName)
                        .stadiumName(stadiumName)
                        .createdAt(Timestamp.valueOf(createdAt))
                        .build();

                teamStadiums.add(team);
            }

//            return new TeamList("팀 조회에 성공했습니다.", teamStadiums);
            return TeamList.builder()
                    .message("팀 조회에 성공했습니다.")
                    .data(teamStadiums)
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
//            return new TeamList("팀 조회에 실패했습니다.", teamStadiums);
            return TeamList.builder()
                    .message("팀 조회에 실패했습니다.")
                    .data(teamStadiums)
                    .build();
        }

    }

}
