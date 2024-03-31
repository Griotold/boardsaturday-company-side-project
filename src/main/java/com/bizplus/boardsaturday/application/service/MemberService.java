package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.member.MemberCreator;
import com.bizplus.boardsaturday.application.request.member.CreateMemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberCreator memberCreator;

    public void create(CreateMemberRequest request) {
        memberCreator.create(request);
    }

}
