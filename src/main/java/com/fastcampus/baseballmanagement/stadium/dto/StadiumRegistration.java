package com.fastcampus.baseballmanagement.stadium.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StadiumRegistration extends CustomResponse<Stadium> {

    @Builder
    public StadiumRegistration(String message, Stadium data) {
        super(message, data);
    }

}
