package com.fastcampus.baseballmanagement.player.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlayerRegistration extends CustomResponse<String> {

    @Builder
    public PlayerRegistration(String message, String data) {
        super(message, data);
    }
}