package com.fastcampus.baseballmanagement.team.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class TeamList extends CustomResponse<List<TeamList.Team>> {

    @Builder
    @ToString
    public static class Team {
        private int teamId;
        private int stadiumId;
        private String teamName;
        private String stadiumName;
        private Timestamp createdAt;
    }


    public TeamList(String message, List<Team> data) {
        super(message, data);
    }
}
