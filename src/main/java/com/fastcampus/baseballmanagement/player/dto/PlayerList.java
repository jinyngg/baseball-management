package com.fastcampus.baseballmanagement.player.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlayerList extends CustomResponse<List<PlayerList.PlayerInfo>> {

    @Builder
    @ToString
    public static class PlayerInfo {
        private int playerId;
        private String name;
        private String position;
        private Timestamp createdAt;
    }

    @Builder
    public PlayerList(String message, List<PlayerInfo> data) {
        super(message, data);
    }
}
