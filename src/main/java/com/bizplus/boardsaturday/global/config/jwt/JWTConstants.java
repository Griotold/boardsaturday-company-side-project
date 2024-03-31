package com.bizplus.boardsaturday.global.config.jwt;

public interface JWTConstants {

    String SECRET = "ThreeBodyProblems";
    int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER = "Authorization";
}
