package com.fastcampus.baseballmanagement.outplayer.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;

public class OutPlayerRegistration extends CustomResponse<String> {

    @Builder
    public OutPlayerRegistration(String message, String data) {
        super(message, data);
    }

}
