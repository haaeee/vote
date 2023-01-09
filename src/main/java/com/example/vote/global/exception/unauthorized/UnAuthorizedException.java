package com.example.vote.global.exception.unauthorized;

import com.example.vote.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends CustomException {

    public UnAuthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
