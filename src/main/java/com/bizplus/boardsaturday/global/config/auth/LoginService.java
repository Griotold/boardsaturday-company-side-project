package com.bizplus.boardsaturday.global.config.auth;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// CustomUserDetailSerivce - 구현체 이름
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 이거 예외가 이상하다. 런타임이라 상관없는데 throws 예외의 하위 예외를 실제로 던져줘야 하는데...
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository
                .findByEmail(username)
                .orElseThrow(() -> new InternalAuthenticationServiceException("인증 실패 : 회원을 찾을 수 없습니다."));
        // LoginMember loginMember = memberRepository.findByEmail(username)
        // .map(LoginMember::new)
        // .orElseThrow()
        return new LoginMember(member);
    }
}
