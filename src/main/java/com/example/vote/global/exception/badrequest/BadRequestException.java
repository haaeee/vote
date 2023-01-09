package com.example.vote.global.exception.badrequest;

import com.example.vote.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
