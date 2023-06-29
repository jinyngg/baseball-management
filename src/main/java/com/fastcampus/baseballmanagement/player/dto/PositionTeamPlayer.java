package com.fastcampus.baseballmanagement.player.dto;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class PositionTeamPlayer extends CustomResponse<Map<String, Map<String, String>>> {

    @Builder
    public PositionTeamPlayer(String message, Map<String, Map<String, String>> data) {
        super(message, data);
    }
}
