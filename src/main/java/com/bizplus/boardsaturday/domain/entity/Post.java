package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.application.request.post.UpdatePostRequest;
import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 100)
    private String title;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @OneToMany(mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();

    public Post(String title, String body, Category category, ActiveStatus activeStatus, DeleteStatus deleteStatus) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
    }

    // 연관관계 편의 메서드
    public void addPostTag(PostTag postTag) {
        postTags.add(postTag);
    }

    public void update(UpdatePostRequest request) {
        title = request.getTitle();
        body = request.getBody();

    }

    public void update(UpdatePostRequest request, Category category) {
        if (this.category.getId().longValue() != category.getId().longValue()) {
            this.category = category;
        }
        title = request.getTitle();
        body = request.getBody();
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
