package com.fastcampus.baseballmanagement.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {

    private String message;
    private T data;

}