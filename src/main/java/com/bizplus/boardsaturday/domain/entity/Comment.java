package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    public Comment(String content,
                   ActiveStatus activeStatus,
                   DeleteStatus deleteStatus,
                   Post post,
                   Member member,
                   Comment parent) {
        this.content = content;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
        this.post = post;
        this.member = member;
        this.parent = parent;
    }

    public void changeStatusOn() {
        this.activeStatus = ActiveStatus.ACTIVE;
    }

    public void changeStatusOff() {
        this.activeStatus = ActiveStatus.INACTIVE;
    }

    public void delete() {
        this.deleteStatus = DeleteStatus.DELETED;
    }
}
