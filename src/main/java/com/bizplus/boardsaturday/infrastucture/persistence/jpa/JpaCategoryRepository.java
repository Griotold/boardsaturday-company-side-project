package com.bizplus.boardsaturday.infrastucture.persistence.jpa;

import com.bizplus.boardsaturday.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
