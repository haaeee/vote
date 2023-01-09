package com.example.vote.global.jwt.filter;

import com.example.vote.domain.member.service.MemberService;
import com.example.vote.global.exception.unauthorized.TokenExpiredException;
import com.example.vote.global.exception.unauthorized.TokenInvalidFormatException;
import com.example.vote.global.exception.unauthorized.TokenNotExistsException;
import com.example.vote.global.jwt.JwtProvider;
import com.example.vote.global.jwt.MemberPrincipal;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_TYPE = "Bearer";

    private final String key;
    private final MemberService memberService;

    public JwtTokenFilter(final String key, final MemberService memberService) {
        this.key = key;
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(header)) {
            throw new TokenNotExistsException();
        }
        if (!header.startsWith((TOKEN_TYPE))) {
            throw new TokenInvalidFormatException();
        }

        final String token = header.split(" ")[1].trim();

        if (JwtProvider.isTokenExpired(token, key)) {
            throw new TokenExpiredException();
        }

        String username = JwtProvider.getUsername(token, key);
        MemberPrincipal memberPrincipal = memberService.loadMemberByEmail(username);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                memberPrincipal, null, memberPrincipal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}