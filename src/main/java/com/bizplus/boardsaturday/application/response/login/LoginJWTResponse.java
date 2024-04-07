package com.bizplus.boardsaturday.application.response.login;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.global.config.jwt.JWTConstants;
import com.bizplus.boardsaturday.global.util.DateFormatUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginJWTResponse {
    private String token;
    private String expiresAt;
    private String tokenType;

    public LoginJWTResponse(Member member, String jwtToken) {
        this.token = jwtToken;
        this.expiresAt = DateFormatUtil.toStringFormat(LocalDateTime.now().plusDays(7));
        this.tokenType = JWTConstants.TOKEN_PREFIX;
    }
}
