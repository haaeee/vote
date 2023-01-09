package com.example.vote.global.exception.forbidden;

import com.example.vote.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
