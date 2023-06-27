package com.fastcampus.baseballmanagement.team.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TeamRegistration {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        String name;
        int stadiumId;
    }

    public static class Response extends CustomResponse<Team> {

        @Builder
        public Response(String message, Team data) {
            super(message, data);
        }
    }
}
