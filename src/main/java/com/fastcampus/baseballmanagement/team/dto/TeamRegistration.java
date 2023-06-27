package com.fastcampus.baseballmanagement.team.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamRegistration extends CustomResponse<Team> {

    @Builder
    public TeamRegistration(String message, Team data) {
        super(message, data);
    }
}
