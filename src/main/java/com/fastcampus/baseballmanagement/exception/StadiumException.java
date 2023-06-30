package com.fastcampus.baseballmanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

@Getter
@NoArgsConstructor
public class StadiumException extends SQLException {

    private String errorMessage;
    private String data;

    public StadiumException(ErrorCode errorCode) {
        this.errorMessage = errorCode.getDescription();
        this.data = null;
    }

    public StadiumException(ErrorCode errorCode, String data) {
        this.errorMessage = errorCode.getDescription();
        this.data = data;
    }

}
