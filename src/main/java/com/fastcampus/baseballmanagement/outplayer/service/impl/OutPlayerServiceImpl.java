package com.fastcampus.baseballmanagement.outPlayer.service.impl;

import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;
import com.fastcampus.baseballmanagement.outPlayer.service.OutPlayerService;
import com.fastcampus.baseballmanagement.outPlayer.dto.OutPlayerList;
import com.fastcampus.baseballmanagement.outPlayer.dto.OutPlayerRegistration;

public class OutPlayerServiceImpl implements OutPlayerService {

    private static OutPlayerDAO outPlayerDAO;

    public OutPlayerServiceImpl() {
        outPlayerDAO = OutPlayerDAO.getInstance();
    }

    @Override
    @MyRequestMapping("퇴출등록")
    public OutPlayerRegistration registerOutPlayer(int playerId, String reason) {
        OutPlayerRegistration outPlayerRegistration = outPlayerDAO.registerOutPlayer(playerId, reason);
        System.out.println(outPlayerRegistration);
        return outPlayerRegistration;
    }

    @Override
    @MyRequestMapping("퇴출목록")
    public OutPlayerList getOutPlayerList() {
        OutPlayerList outPlayerList = outPlayerDAO.getOutPlayerList();
        System.out.println(outPlayerList);
        return outPlayerList;
    }
}
