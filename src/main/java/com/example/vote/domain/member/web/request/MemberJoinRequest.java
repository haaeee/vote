package com.example.vote.domain.member.web.request;

import lombok.Getter;

@Getter
public class MemberJoinRequest {

    private String email;

    private String password;

    private String nickname;

    private MemberJoinRequest() {
    }

    public MemberJoinRequest(final String email, final String password, final String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
