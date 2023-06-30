package com.fastcampus.baseballmanagement.outplayer.service;

import com.fastcampus.baseballmanagement.exception.OutPlayerException;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerList;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerRegistration;

import java.sql.SQLException;

public interface OutPlayerService {

    // 퇴출 선수 등록
    OutPlayerRegistration registerOutPlayer(int playerId, String reason) throws SQLException;

    // 퇴출 선수 목록 조회
    OutPlayerList getOutPlayerList() throws OutPlayerException;

}
