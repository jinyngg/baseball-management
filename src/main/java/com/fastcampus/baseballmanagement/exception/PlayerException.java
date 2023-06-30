package com.fastcampus.baseballmanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class PlayerException extends SQLException {

    private String errorMessage;
    private String data;

    public PlayerException(ErrorCode errorCode) {
        this.errorMessage = errorCode.getDescription();
        this.data = null;
    }

    public PlayerException(ErrorCode errorCode, String data) {
        this.errorMessage = errorCode.getDescription();
        this.data = data;
    }

}
