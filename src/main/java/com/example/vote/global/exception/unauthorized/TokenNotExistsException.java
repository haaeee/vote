package com.example.vote.global.exception.unauthorized;

import com.example.vote.global.exception.unauthorized.UnAuthorizedException;

public class TokenNotExistsException extends UnAuthorizedException {

    public TokenNotExistsException() {
        super("토큰이 존재하지 않습니다.");
    }
}
