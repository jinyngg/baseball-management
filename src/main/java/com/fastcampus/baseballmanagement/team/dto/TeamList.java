package com.fastcampus.baseballmanagement.team.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamList extends CustomResponse<Team> {

    @Builder
    public TeamList(String message, Team data) {
        super(message, data);
    }
}
