package com.bizplus.boardsaturday.global.config.jwt;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.global.util.DateFormatUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private Long id;
    private String email;
    private String createdAt;

    public LoginResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.createdAt = DateFormatUtil.toStringFormat(member.getCreatedAt());
    }

}
