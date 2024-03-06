package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    public Member(String name,
                  String email,
                  ActiveStatus activeStatus,
                  DeleteStatus deleteStatus) {
        this.name = name;
        this.email = email;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
    }
}
