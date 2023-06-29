package com.fastcampus.baseballmanagement.player.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.player.dto.PlayerList;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;
import com.fastcampus.baseballmanagement.player.dto.PositionTeamPlayer;
import com.fastcampus.baseballmanagement.player.type.Position;

import java.sql.*;
import java.util.*;

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

    private final String selectByPlayerAndPositionQuery =
            "SELECT" +
                    " t.name AS team_name, p.name AS player_name , p.position" +
                    " FROM player p" +
                    " join team t on p.team_id = t.team_id";

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
        String message;

        // 입력받은 포지션이 유효한지 확인
        Position playerPosition = Position.getPositionByName(position);
        if (playerPosition == null) {
            message = "유효하지 않은 포지션입니다.";
            return PlayerRegistration.builder()
                    .message(message)
                    .data(name)
                    .build();
        }

        // 중복 포지션 확인
        if (isDuplicatePosition(teamId, playerPosition)) {
            message = "이미 동일한 포지션의 선수가 등록되어 있습니다.";
            return PlayerRegistration.builder()
                    .message(message)
                    .data(name)
                    .build();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, playerPosition.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            message = (rowsAffected > 0) ? "선수 등록에 성공했습니다." : "선수 등록에 실패했습니다.";

        } catch (SQLException e) {
            e.printStackTrace();
            message = "선수 등록에 실패했습니다. (SQLException)";
        }

        return PlayerRegistration.builder()
                .message(message)
                .data(name)
                .build();
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
    public PlayerList getPlayerListByTeam(int teamId) {
        List<PlayerList.PlayerInfo> playerList = new ArrayList<>();
        String message;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectByTeamQuery)) {
            preparedStatement.setInt(1, teamId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                String playerName = resultSet.getString("name");
                String position = resultSet.getString("position");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                PlayerList.PlayerInfo playerInfo = PlayerList.PlayerInfo.builder()
                        .playerId(playerId)
                        .name(playerName)
                        .position(position)
                        .createdAt(createdAt)
                        .build();

                playerList.add(playerInfo);
            }

            message = "팀별 선수 목록 조회에 성공했습니다.";
        } catch (SQLException e) {
            e.printStackTrace();
            message = "팀별 선수 목록 조회에 실패했습니다.";
        }

        return PlayerList.builder()
                .message(message)
                .data(playerList)
                .build();
    }

    /** 포지션별 팀 야구 선수 조회 */
    public PositionTeamPlayer getPositionTeamPlayers() {
        Map<String, Map<String, String>> positionTeamPlayers = new HashMap<>();
        String message;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectByPlayerAndPositionQuery);

            while (resultSet.next()) {
                String teamName = resultSet.getString("team_name");
                String playerName = resultSet.getString("player_name");
                String position = resultSet.getString("position");

                Map<String, String> teamPlayers = positionTeamPlayers.getOrDefault(position, new HashMap<>());
                teamPlayers.put(teamName, playerName);

                positionTeamPlayers.put(position, teamPlayers);
            }

            message = "포지션별 팀 야구 선수 조회에 성공했습니다.";
        } catch (SQLException e) {
            e.printStackTrace();
            message = "포지션별 팀 야구 선수 조회에 실패했습니다.";
        }

        printPosition(positionTeamPlayers);
        return PositionTeamPlayer.builder()
                .message(message)
                .data(positionTeamPlayers)
                .build();
    }

    private static void printPosition(Map<String, Map<String, String>> positionTeamPlayers) {
        StringBuilder header = new StringBuilder(String.format("| %-8s |", "포지션"));
        Set<String> teams = new HashSet<>();

        for (Map<String, String> teamPlayers : positionTeamPlayers.values()) {
            teams.addAll(teamPlayers.keySet());
        }

        List<String> sortedTeams = new ArrayList<>(teams);
        Collections.sort(sortedTeams);

        for (String team : sortedTeams) {
            header.append(String.format(" %-8s |", team));
        }

        String separator = String.format("|%s|", "-".repeat(header.length() - 2));

        System.out.println(header);
        System.out.println(separator);

        for (Map.Entry<String, Map<String, String>> entry : positionTeamPlayers.entrySet()) {
            String position = entry.getKey();
            Map<String, String> teamPlayers = entry.getValue();
            StringBuilder positionPlayers = new StringBuilder(String.format("| %-8s |", position));

            for (String team : sortedTeams) {
                String player = teamPlayers.getOrDefault(team, "");
                positionPlayers.append(String.format(" %-8s |", player));
            }

            System.out.println(positionPlayers);
        }
    }
}
