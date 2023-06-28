package com.fastcampus.baseballmanagement.player.dto;

import com.fastcampus.baseballmanagement.player.type.Position;
import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    private int playerId;
    private int teamId;
    private String name;
    private Position position;
    private Timestamp createdAt;
}
