package com.bizplus.boardsaturday.global.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.global.config.auth.LoginMember;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JWTProcess {

    // 토큰 생성
    public static String create(LoginMember loginMember) {
        String jwtToken = JWT.create()
                .withSubject("board")
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME))
                .withClaim("id", loginMember.getMember().getId())
                .withClaim("role", loginMember.getMember().getRole().name())
                .sign(Algorithm.HMAC512(JWTConstants.SECRET));
        return JWTConstants.TOKEN_PREFIX + jwtToken;
    }

    // 토큰 검증
    public static LoginMember verify(String token) {
        DecodedJWT decodedJWT = JWT
                .require(Algorithm.HMAC512(JWTConstants.SECRET))
                .build()
                .verify(token);

        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        Member member = new Member(id, role);
        LoginMember loginMember = new LoginMember(member);
        return loginMember;
    }
}
