package com.bizplus.boardsaturday.global.config.jwt;

import com.bizplus.boardsaturday.global.config.auth.LoginMember;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 토큰이 "bearer" 로 시작한다면
        if (doesHeaderStartWithBearer(request, response)) {
            // 토큰 파싱
            String token = request.getHeader(JWTConstants.HEADER).replace(JWTConstants.TOKEN_PREFIX, "");
            LoginMember loginMember = JWTProcess.verify(token);

            // UsernamePasswordAuthenticationToken 은 Authentication 인터페이스를 구현하다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginMember.getUsername(), null, loginMember.getAuthorities());

            // SecurityContext 에 인증객체 넣어주기
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private boolean doesHeaderStartWithBearer(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(JWTConstants.HEADER);// Authorization 의 키값의 밸류 가져오기
        return header != null && header.startsWith(JWTConstants.TOKEN_PREFIX);
    }

}
