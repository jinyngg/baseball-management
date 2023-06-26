package com.fastcampus.baseballmanagement.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    private int id;
    private int stadiumId;
    private String name;
    private LocalDateTime createdAt;
}
