package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.application.request.UpdateCategoryRequest;
import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.CategoryStatus;
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
@ToString
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CategoryStatus status;

    public Category(String name, String description, int displayOrder, CategoryStatus status) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
        this.status = status;
    }

    public void update(UpdateCategoryRequest request) {
        // todo 쿼리 스트링 id와 requestBody의 id가 일치한 지 검증하는 로직
        name = request.getName();
        description = request.getDescription();
    }
}
