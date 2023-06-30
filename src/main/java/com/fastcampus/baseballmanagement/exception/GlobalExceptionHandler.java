package com.fastcampus.baseballmanagement.exception;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import com.fastcampus.baseballmanagement.core.annotation.MyExceptionHandler;

public class GlobalExceptionHandler {

    @MyExceptionHandler("TeamException")
    public void teamExceptionRun(TeamException e) {
        CustomResponse<String> customResponse = new CustomResponse<>(e.getErrorMessage(), e.getData());
        System.out.println(customResponse);
    }

    @MyExceptionHandler("PlayerException")
    public void playerException(PlayerException e) {
        CustomResponse<String> customResponse = new CustomResponse<>(e.getErrorMessage(), e.getData());
        System.out.println(customResponse);
    }

    @MyExceptionHandler("StadiumException")
    public void stadiumException(StadiumException e) {
        CustomResponse<String> customResponse = new CustomResponse<>(e.getErrorMessage(), e.getData());
        System.out.println(customResponse);
    }

    @MyExceptionHandler("OutPlayerException")
    public void outPlayerException(OutPlayerException e) {
        CustomResponse<String> customResponse = new CustomResponse<>(e.getErrorMessage(), e.getData());
        System.out.println(customResponse);
    }
}
