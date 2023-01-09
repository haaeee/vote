package com.example.vote.global.exception.notfound;

import com.example.vote.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
