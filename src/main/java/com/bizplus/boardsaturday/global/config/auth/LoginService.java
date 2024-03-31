package com.bizplus.boardsaturday.global.config.auth;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository
                .findByEmail(username)
                .orElseThrow(() -> new InternalAuthenticationServiceException("인증 실패 : 회원을 찾을 수 없습니다."));

        return new LoginMember(member);
    }
}
