package com.example.vote.domain.member.service;

import com.example.vote.domain.member.entity.Member;
import com.example.vote.domain.member.repository.MemberRepository;
import com.example.vote.domain.member.web.response.MemberJoinResponse;
import com.example.vote.global.exception.badrequest.DuplicatedEmailException;
import com.example.vote.global.exception.notfound.MemberNotFoundException;
import com.example.vote.global.jwt.MemberPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final String secretKey;
    private final Long expiredTimeMs;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    public MemberService(@Value("${security.jwt.secret-key}") final String secretKey,
                         @Value("${security.jwt.expired-time-ms}") final Long expiredTimeMs,
                         final PasswordEncoder passwordEncoder,
                         final MemberRepository memberRepository) {
        this.secretKey = secretKey;
        this.expiredTimeMs = expiredTimeMs;
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    public MemberPrincipal loadMemberByEmail(final String email) {
        Member member = findMember(email);
        return MemberPrincipal.from(member);
    }

    private Member findMember(final String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public MemberJoinResponse join(String email, String password, String nickname) {
        validateEmail(email);

        memberRepository.save(Member.createShareHolder())
    }

    private void validateEmail(String email) {
        memberRepository.findByEmail(email).ifPresent(it -> {
            throw new DuplicatedEmailException();
        });
    }
}
