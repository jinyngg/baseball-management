package com.fastcampus.baseballmanagement.exception;

import com.fastcampus.baseballmanagement.common.CustomResponse;
import com.fastcampus.baseballmanagement.core.annotation.MyExceptionHandler;

// TODO Handler 변경 가능하다면..?
public class GlobalExceptionHandler {

    @MyExceptionHandler("TeamException")
    public void teamExceptionRun(TeamException e) {
        CustomResponse customResponse = new CustomResponse(e.getErrorMessage(), e.getData());
        System.out.println(customResponse);
    }

    @MyExceptionHandler("PlayerException")
    public void playerException(PlayerException e) {
        CustomResponse customResponse = new CustomResponse(e.getErrorMessage(), e.getData());
        System.out.println(customResponse);
    }
}
