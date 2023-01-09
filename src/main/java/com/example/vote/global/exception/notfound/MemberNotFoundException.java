package com.example.vote.global.exception.notfound;

import com.example.vote.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException() {
        super("회원을 찾을 수 없습니다.");
    }
}
