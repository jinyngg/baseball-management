package com.fastcampus.baseballmanagement.stadium;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter @Setter
@ToString @Builder
public class Stadium {
    private int id;
    private String name;
    private Timestamp createdAt;
}