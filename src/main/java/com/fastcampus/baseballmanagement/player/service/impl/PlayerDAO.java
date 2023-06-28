package com.fastcampus.baseballmanagement.player.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;
import com.fastcampus.baseballmanagement.player.type.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {

    private Connection connection;

    // SQL DML
    private final String insertQuery =
            "INSERT" +
                    " INTO player" +
                    " (team_id, name, position, created_at)" +
                    " VALUES" +
                    " (?, ?, ?, now())"
            ;

    private final String selectByTeamAndPositionQuery =
            "SELECT *" +
                    " FROM player" +
                    " WHERE team_id = ? AND position = ?";

    private final String selectByTeamQuery =
            "SELECT *" +
                    " FROM player" +
                    " WHERE team_id = ?";

    private static PlayerDAO playerDAO;

    private PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 PlayerDAO 생성
     * @return PlayerDAO
     */
    public static PlayerDAO getInstance() {
        if(playerDAO == null){
            playerDAO = new PlayerDAO(DBConnection.getInstance());
        }

        return playerDAO;
    }
    
    /** 선수 등록 */
    public PlayerRegistration registerPlayer(int teamId, String name, String position) {
        // 입력받은 포지션이 유효한지 확인
        Position playerPosition = Position.getPositionByName(position);
        if (playerPosition == null) {
            return PlayerRegistration.builder()
                    .message("유효하지 않은 포지션입니다.")
                    .data(name)
                    .build();
        }

        // 중복 포지션 확인
        if (isDuplicatePosition(teamId, playerPosition)) {
            return PlayerRegistration.builder()
                    .message("이미 동일한 포지션의 선수가 등록되어 있습니다.")
                    .data(name)
                    .build();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, playerPosition.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return PlayerRegistration.builder()
                        .message("선수 등록에 성공했습니다.")
                        .data(name)
                        .build();
            } else {
                return PlayerRegistration.builder()
                        .message("선수 등록에 실패했습니다.")
                        .data(name)
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return PlayerRegistration.builder()
                    .message("선수 등록에 실패했습니다.")
                    .data(name)
                    .build();
        }

    }

    /** 팀 ID와 포지션에 해당하는 선수를 조회한 후, 조회 결과가 있는지 여부를 반환 */
    private boolean isDuplicatePosition(int teamId, Position position) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectByTeamAndPositionQuery)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.setString(2, position.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** 팀별 선수 목록 조회 */
}
