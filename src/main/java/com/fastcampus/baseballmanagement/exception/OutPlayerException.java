package com.fastcampus.baseballmanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

@Getter
@NoArgsConstructor
public class OutPlayerException extends SQLException {

    private String errorMessage;
    private String data;

    public OutPlayerException(ErrorCode errorCode) {
        this.errorMessage = errorCode.getDescription();
        this.data = null;
    }

    public OutPlayerException(ErrorCode errorCode, String data) {
        this.errorMessage = errorCode.getDescription();
        this.data = data;
    }

}
