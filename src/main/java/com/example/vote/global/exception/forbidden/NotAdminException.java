package com.example.vote.global.exception.forbidden;

import com.example.vote.global.exception.forbidden.ForbiddenException;

public class NotAdminException extends ForbiddenException {

    public NotAdminException() {
        super("관리자가 아닙니다.");
    }
}
