package com.fastcampus.baseballmanagement.outplayer.service;

import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerList;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerRegistration;

public interface OutPlayerService {

    // 퇴출 선수 등록
    OutPlayerRegistration registerOutPlayer(int playerId, String reason);

    // 퇴출 선수 목록 조회
    OutPlayerList getOutPlayerList();

}
