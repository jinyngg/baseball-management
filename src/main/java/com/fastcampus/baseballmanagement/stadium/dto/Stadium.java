package com.fastcampus.baseballmanagement.stadium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {
    private int stadiumId;
    private String name;
    private Timestamp createdAt;
}