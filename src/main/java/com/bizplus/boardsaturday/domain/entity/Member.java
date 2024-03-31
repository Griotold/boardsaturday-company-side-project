package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import com.bizplus.boardsaturday.domain.type.UserEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 20)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserEnum role;    // ADMIN, CUSTOMER

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    public Member(String name,
                  String email,
                  String password,
                  ActiveStatus activeStatus,
                  DeleteStatus deleteStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
        this.role = UserEnum.ADMIN;
    }

    public Member(Long id, String role) {
        this.id = id;
        this.role = UserEnum.valueOf(role);
    }
}
