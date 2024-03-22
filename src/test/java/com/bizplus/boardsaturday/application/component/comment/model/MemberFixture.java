package com.bizplus.boardsaturday.application.component.comment.model;

import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;

public class MemberFixture {

    public static Member create() {
        return new Member(1L, "griotold",
                "griotold@email.com",
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING);
    }
}
