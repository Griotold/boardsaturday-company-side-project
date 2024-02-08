package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    public Post(String title, String body, Category category, ActiveStatus activeStatus, DeleteStatus deleteStatus) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
    }


}
