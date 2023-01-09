package com.example.vote.global.exception.unauthorized;

public class TokenInvalidFormatException extends UnAuthorizedException {

    public TokenInvalidFormatException() {
        super("토큰이 잘못된 형식입니다.");
    }
}
