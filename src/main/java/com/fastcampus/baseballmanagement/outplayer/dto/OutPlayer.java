package com.fastcampus.baseballmanagement.outPlayer.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutPlayer {
    private int outPlayerId;
    private int playerId;
    private String reason;
    private Timestamp createdAt;
}
