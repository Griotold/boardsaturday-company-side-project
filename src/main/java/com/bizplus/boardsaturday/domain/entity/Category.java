package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.domain.type.CategoryStatus;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CategoryStatus status;
}
