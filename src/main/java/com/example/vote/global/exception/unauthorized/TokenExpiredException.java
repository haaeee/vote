package com.example.vote.global.exception.unauthorized;

import com.example.vote.global.exception.unauthorized.UnAuthorizedException;

public class TokenExpiredException extends UnAuthorizedException {

    public TokenExpiredException() {
        super("토큰이 만료되었습니다.");
    }
}
