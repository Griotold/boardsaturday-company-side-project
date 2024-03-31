package com.bizplus.boardsaturday.application.component.member;

import com.bizplus.boardsaturday.application.request.member.CreateMemberRequest;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import com.bizplus.boardsaturday.global.error.ErrorCode;
import com.bizplus.boardsaturday.global.error.ex.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberCreator {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(CreateMemberRequest request) {
        // 1. 동일 이메일 존재 검사
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 2. 비밀번호와 비밀번호 확인이 일치하는 지 검사
        if (request.isPasswordMismatch()) {
            throw new BusinessException(ErrorCode.PASSWORD_NOT_CONFIRMED);
        }

        // 3. 패스워드 인코딩
        Member member = request.toEntity(passwordEncoder);
        return memberRepository.create(member);
    }
}
