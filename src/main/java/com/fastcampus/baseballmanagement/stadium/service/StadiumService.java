package com.fastcampus.baseballmanagement.stadium.service;

import com.fastcampus.baseballmanagement.exception.StadiumException;
import com.fastcampus.baseballmanagement.stadium.dto.Stadium;
import com.fastcampus.baseballmanagement.stadium.dto.StadiumRegistration;

import java.util.List;

public interface StadiumService {

    // 경기장 등록
    StadiumRegistration registerStadium(String name) throws StadiumException;

    // 경기장 목록 조회
    List<Stadium> getStadiumList() throws StadiumException;
}
