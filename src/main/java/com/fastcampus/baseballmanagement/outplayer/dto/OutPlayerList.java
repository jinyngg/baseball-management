package com.fastcampus.baseballmanagement.outPlayer.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import com.fastcampus.baseballmanagement.player.type.Position;
import lombok.Builder;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

public class OutPlayerList extends CustomResponse<List<OutPlayerList.OutPlayer>> {

    @Builder
    @ToString
    public static class OutPlayer {
        private int playerId;
        private String name;
        private Position position;
        private String reason;
        private Timestamp createdAt;
    }

    @Builder
    public OutPlayerList(String message, List<OutPlayerList.OutPlayer> data) {
        super(message, data);
    }

}
