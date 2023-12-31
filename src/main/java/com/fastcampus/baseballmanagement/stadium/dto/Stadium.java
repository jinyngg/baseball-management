package com.fastcampus.baseballmanagement.stadium.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {
    private int stadiumId;
    private String name;
    private Timestamp createdAt;
}