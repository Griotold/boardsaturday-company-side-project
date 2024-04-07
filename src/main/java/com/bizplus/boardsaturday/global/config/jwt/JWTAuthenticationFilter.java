package com.bizplus.boardsaturday.global.config.jwt;

import com.bizplus.boardsaturday.global.config.auth.LoginMember;
import com.bizplus.boardsaturday.global.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    // Post : /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("info : attemptAuthentication 호출됨");
        try {
            ObjectMapper om = new ObjectMapper();
            LoginRequest loginRequest = om.readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword());

            // UserDetailsService 의 loadUserByUsername 호출
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    // attemptAuthentication 이 성공하면 실행되는 메서드
    // 얘가 호출되면 로그인이 된 거
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("info : successfulAuthentication 호출됨");
        LoginMember loginMember = (LoginMember) authResult.getPrincipal();
        String jwtToken = JWTProcess.create(loginMember);
        response.addHeader(JWTConstants.HEADER, jwtToken);

        LoginResponse loginResponse = new LoginResponse(loginMember.getMember());
        ResponseUtil.success(response, loginResponse);
    }

    // attemptAuthentication 이 실패하면 실행되는 메소드
    // catch 블록을 타면 아래 메서드가 실행된다.
    // 얘가 호출되면 로그인이 실패

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("info : unsuccessfulAuthentication 호출됨");
        ResponseUtil.fail(response);
    }
}
