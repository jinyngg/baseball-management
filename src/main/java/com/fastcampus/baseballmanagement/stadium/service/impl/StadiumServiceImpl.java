package com.fastcampus.baseballmanagement.stadium.service.impl;

import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;
import com.fastcampus.baseballmanagement.exception.StadiumException;
import com.fastcampus.baseballmanagement.stadium.dto.Stadium;
import com.fastcampus.baseballmanagement.stadium.dto.StadiumRegistration;
import com.fastcampus.baseballmanagement.stadium.service.StadiumService;

import java.util.List;

public class StadiumServiceImpl implements StadiumService {

    private static StadiumDAO stadiumDAO;

    public StadiumServiceImpl() {
        stadiumDAO = StadiumDAO.getInstance();
    }

    @Override
    @MyRequestMapping("야구장등록")
    public StadiumRegistration registerStadium(String name) throws StadiumException {
        StadiumRegistration response = stadiumDAO.registerStadium(name);
        System.out.println(response);
        return response;
    }

    @Override
    @MyRequestMapping("야구장목록")
    public List<Stadium> getStadiumList() throws StadiumException {
        List<Stadium> stadiums = stadiumDAO.getAllStadiums();
        System.out.println(stadiums);
        return stadiums;
    }
}
