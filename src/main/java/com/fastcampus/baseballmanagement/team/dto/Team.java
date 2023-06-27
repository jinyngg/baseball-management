package com.fastcampus.baseballmanagement.team.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    private int teamId;
    private int stadiumId;
    private String name;
    private LocalDateTime createdAt;
}
