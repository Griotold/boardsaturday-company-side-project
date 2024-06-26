package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.application.request.category.UpdateCategoryRequest;
import com.bizplus.boardsaturday.domain.common.BaseTimeEntity;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(of = {"id"})
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    private String description;

    private int displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActiveStatus status;

    public Category(String name, String description, int displayOrder, ActiveStatus status) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
        this.status = status;
    }

    public void update(UpdateCategoryRequest request) {
        name = request.getName();
        description = request.getDescription();
    }

    public void changeStatusOn() {
        this.status = ActiveStatus.ACTIVE;
    }

    public void changeStatusOff() {
        this.status = ActiveStatus.INACTIVE;
    }

    public void updateDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
