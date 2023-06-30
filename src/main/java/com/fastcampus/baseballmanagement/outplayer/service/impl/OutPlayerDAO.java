package com.fastcampus.baseballmanagement.outplayer.service.impl;

import com.fastcampus.baseballmanagement.config.DBConnection;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerList;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerRegistration;
import com.fastcampus.baseballmanagement.player.type.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {

    private static OutPlayerDAO outPlayerDAO;
    private Connection connection;

    private OutPlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public static OutPlayerDAO getInstance() {
        if (outPlayerDAO == null) {
            outPlayerDAO = new OutPlayerDAO(DBConnection.getInstance());
        }
        return outPlayerDAO;
    }

    private final String insertQuery =
            "INSERT" +
                    " INTO out_player" +
                    " (player_id, reason, created_at)" +
                    " VALUES (?, ?, now())";

    private final String updatePlayerQuery =
            "UPDATE" +
                    " player" +
                    " SET" +
                    " team_id = NULL, position = NULL" +
                    " WHERE player_id = ?";

    private final String selectOutPlayerListQuery =
            "SELECT" +
                    " p.player_id, p.name, p.position, o.reason, o.created_at" +
                    " FROM player p LEFT OUTER JOIN out_player o" +
                    " ON p.player_id = o.player_id";

    private final String selectByPlayerIdQuery =
            "SELECT *" +
                    " FROM player" +
                    " WHERE player_id = ?";

    public OutPlayerRegistration registerOutPlayer(int playerId, String reason) {
        try (PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);
             PreparedStatement updatePreparedStatement = connection.prepareStatement(updatePlayerQuery)
        ) {
            if(!isPlayer(playerId))
                return OutPlayerRegistration.builder()
                        .message("등록되어 있지 않은 선수입니다.")
                        .data(playerId)
                        .build();

            connection.setAutoCommit(false);

            insertPreparedStatement.setInt(1, playerId);
            insertPreparedStatement.setString(2, reason);
            insertPreparedStatement.executeUpdate();

            updatePreparedStatement.setInt(1, playerId);
            updatePreparedStatement.executeUpdate();

            connection.commit();

            return OutPlayerRegistration.builder()
                    .message("퇴출 등록에 성공하셨습니다.")
                    .data(playerId)
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return OutPlayerRegistration.builder()
                    .message("퇴출 등록에 실패하셨습니다.")
                    .data(playerId)
                    .build();
        }
    }

    public OutPlayerList getOutPlayerList() {
        List<OutPlayerList.OutPlayer> outPlayers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectOutPlayerListQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                String reason = resultSet.getString("reason");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                OutPlayerList.OutPlayer outPlayer = OutPlayerList.OutPlayer.builder()
                        .playerId(playerId)
                        .name(name)
                        .position(Position.getPositionByName(position))
                        .reason(reason)
                        .createdAt(createdAt)
                        .build();

                outPlayers.add(outPlayer);
            }

            return OutPlayerList.builder()
                    .message("목록을 조회합니다.")
                    .data(outPlayers)
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            return OutPlayerList.builder()
                    .message("목록 조회에 실패하셨습니다.")
                    .data(outPlayers)
                    .build();
        }
    }

    private boolean isPlayer(int playerId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectByPlayerIdQuery)) {
            preparedStatement.setInt(1, playerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
