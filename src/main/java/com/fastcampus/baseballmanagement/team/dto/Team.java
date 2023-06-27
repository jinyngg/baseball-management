package com.fastcampus.baseballmanagement.team.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    private int teamId;
    private int stadiumId;
    private String name;
    private Timestamp createdAt;
}
