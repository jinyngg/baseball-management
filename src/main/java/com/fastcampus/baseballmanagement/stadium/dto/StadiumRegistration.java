package com.fastcampus.baseballmanagement.stadium.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StadiumRegistration extends CustomResponse<String> {

    @Builder
    public StadiumRegistration(String message, String data) {
        super(message, data);
    }

}
