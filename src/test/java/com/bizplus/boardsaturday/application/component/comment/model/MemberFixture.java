package com.bizplus.boardsaturday.application.component.comment.model;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import com.bizplus.boardsaturday.domain.type.UserEnum;

public class MemberFixture {

    public static Member create() {
        return new Member(1L, "griotold",
                "password",
                "griotold@email.com",
                UserEnum.ADMIN,
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING);
    }
}
