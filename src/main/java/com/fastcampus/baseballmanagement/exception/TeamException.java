package com.fastcampus.baseballmanagement.exception;

import lombok.*;

import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class TeamException extends SQLException {

    private String errorMessage;
    private String data;

    public TeamException(ErrorCode errorCode) {
        this.errorMessage = errorCode.getDescription();
        this.data = null;
    }

    public TeamException(ErrorCode errorCode, String data) {
        this.errorMessage = errorCode.getDescription();
        this.data = data;
    }

}
