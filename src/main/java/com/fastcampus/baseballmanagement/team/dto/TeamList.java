package com.fastcampus.baseballmanagement.team.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class TeamList extends CustomResponse<List<TeamList.TeamStadium>> {

    @Builder
    @ToString
    public static class TeamStadium {
        private int teamId;
        private int stadiumId;
        private String teamName;
        private String stadiumName;
        private Timestamp createdAt;
    }

    @Builder
    public TeamList(String message, List<TeamStadium> data) {
        super(message, data);
    }
}
