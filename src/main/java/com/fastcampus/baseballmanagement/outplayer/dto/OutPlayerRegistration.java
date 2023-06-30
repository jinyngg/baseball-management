package com.fastcampus.baseballmanagement.outplayer.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;

public class OutPlayerRegistration extends CustomResponse<Integer> {

    @Builder
    public OutPlayerRegistration(String message, Integer data) {
        super(message, data);
    }

}
