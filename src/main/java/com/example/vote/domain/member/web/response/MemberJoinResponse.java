package com.example.vote.domain.member.web.response;

import com.example.vote.domain.member.entity.Member;
import com.example.vote.domain.member.entity.Role;
import lombok.Builder;


public class MemberJoinResponse {

    private final Long id;

    private final String email;

    private final Role role;

    @Builder
    private MemberJoinResponse(final Long id, final String email, final Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public static MemberJoinResponse from(Member member) {
        return MemberJoinResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .role(member.getRole())
                .build();
    }
}
