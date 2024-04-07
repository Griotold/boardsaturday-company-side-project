package com.bizplus.boardsaturday.global.config;

import com.bizplus.boardsaturday.global.config.jwt.JWTAuthenticationFilter;
import com.bizplus.boardsaturday.global.config.jwt.JWTAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); // iframe 허용X
        http.csrf().disable(); // enable 이면 postman 작동 안함.
        http.cors().configurationSource(configurationSource()); // 다른 서버에 자바스크립트 요청 허용

        // jSessionId 를 백엔드에서 관리하지 안겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable(); // form login X
        http.httpBasic().disable(); // httpBasic : 브라우저가 팝업창으로 인증을 진행 -> 이거 허용 X

        http.apply(new CustomSecurityFilterManager());

        // todo 인증 실패시 -> http.exceptionHandling().authenticationEntryPoint((request, response, authException))

        // todo 권한 실패시 -> http.exceptionHandling().accessDeniedHandler(request, response, e)
        // 일단 모든 url 요청 인증 안하겠다
        http.authorizeRequests()
                .anyRequest().permitAll();

        return http.build();
    }

    private class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(new JWTAuthenticationFilter(authenticationManager));

            // JWTAuthorizationFilter 등록!
            builder.addFilter(new JWTAuthorizationFilter(authenticationManager));
            super.configure(builder);
        }
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
