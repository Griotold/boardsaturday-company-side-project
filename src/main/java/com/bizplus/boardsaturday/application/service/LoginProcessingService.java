package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.response.login.LoginJWTResponse;
import com.bizplus.boardsaturday.global.config.auth.LoginMember;
import com.bizplus.boardsaturday.global.config.jwt.JWTProcess;
import com.bizplus.boardsaturday.global.config.jwt.LoginRequest;
import com.bizplus.boardsaturday.global.error.ex.LoginFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginProcessingService {
    private final AuthenticationManager authenticationManager;

    public LoginJWTResponse login(LoginRequest loginRequest) {
        log.info("info : LoginProcessingService.login 호출됨");
        Authentication authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new LoginFailException("로그인 실패", e);
        }

        log.info("info : LoginProcessingSerivce.successfulAuthentication 의 로직 시작");
        LoginMember loginMember = (LoginMember) authentication.getPrincipal();
        String jwtToken = JWTProcess.create(loginMember);

        return new LoginJWTResponse(loginMember.getMember(), jwtToken);
    }
}
