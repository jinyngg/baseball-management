package com.fastcampus.baseballmanagement.team.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class TeamRegistration extends CustomResponse<String> {

    @Builder
    public TeamRegistration(String message, String data) {
        super(message, data);
    }
}
