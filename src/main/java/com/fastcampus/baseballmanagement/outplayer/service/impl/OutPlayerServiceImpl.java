package com.fastcampus.baseballmanagement.outplayer.service.impl;

import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;
import com.fastcampus.baseballmanagement.exception.OutPlayerException;
import com.fastcampus.baseballmanagement.outplayer.service.OutPlayerService;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerList;
import com.fastcampus.baseballmanagement.outplayer.dto.OutPlayerRegistration;

import java.sql.SQLException;

public class OutPlayerServiceImpl implements OutPlayerService {

    private static OutPlayerDAO outPlayerDAO;

    public OutPlayerServiceImpl() {
        outPlayerDAO = OutPlayerDAO.getInstance();
    }

    @Override
    @MyRequestMapping("퇴출등록")
    public OutPlayerRegistration registerOutPlayer(int playerId, String reason) throws SQLException {
        OutPlayerRegistration outPlayerRegistration = outPlayerDAO.registerOutPlayer(playerId, reason);
        System.out.println(outPlayerRegistration);
        return outPlayerRegistration;
    }

    @Override
    @MyRequestMapping("퇴출목록")
    public OutPlayerList getOutPlayerList() throws OutPlayerException {
        OutPlayerList outPlayerList = outPlayerDAO.getOutPlayerList();
        System.out.println(outPlayerList);
        return outPlayerList;
    }
}
