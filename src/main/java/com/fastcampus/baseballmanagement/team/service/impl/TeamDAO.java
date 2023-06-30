package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.exception.TeamException;
import com.fastcampus.baseballmanagement.team.dto.TeamList;
import com.fastcampus.baseballmanagement.team.dto.TeamRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.fastcampus.baseballmanagement.exception.ErrorCode.SQL_EXCEPTION;

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

    /** 팀 등록
     * refactor(23.06.28)
     * 1. try-with-resources -> Statement 리소스 누수 방지
     * 2. rowsAffected -> message 관리
     * 3. 예외시에도 메세지 응답 처리
     */
    public TeamRegistration registerTeam(String name, int stadiumId) throws TeamException {
        String message;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, stadiumId);

            int rowsAffected = preparedStatement.executeUpdate();
            message = (rowsAffected > 0) ? "팀 등록에 성공했습니다." : "팀 등록에 실패했습니다.";

        } catch (SQLException e) {
            throw new TeamException(SQL_EXCEPTION, e.getMessage());
        }

        return TeamRegistration.builder()
                .message(message)
                .data(name)
                .build();
    }

    /** 전체 팀 목록
     * refactor(23.06.28)
     * 1. try-with-resources -> Statement 리소스 누수 방지
     * */
    public TeamList getTeamList() throws TeamException {
        List<TeamList.TeamStadium> teamStadiums = new ArrayList<>();
        String message;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectTeamListQuery)) {

            while (resultSet.next()) {
                int teamId = resultSet.getInt("team_id");
                int stadiumId = resultSet.getInt("stadium_id");
                String teamName = resultSet.getString("team_name");
                String stadiumName = resultSet.getString("stadium_name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                TeamList.TeamStadium team = TeamList.TeamStadium.builder()
                        .teamId(teamId)
                        .stadiumId(stadiumId)
                        .teamName(teamName)
                        .stadiumName(stadiumName)
                        .createdAt(createdAt)
                        .build();

                teamStadiums.add(team);
            }

            message = "팀 조회에 성공했습니다.";
        } catch (SQLException e) {
            throw new TeamException(SQL_EXCEPTION, e.getMessage());
        }

        return TeamList.builder()
                .message(message)
                .data(teamStadiums)
                .build();
    }

}
