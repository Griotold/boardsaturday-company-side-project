package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.CategoryQuery;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryQuery categoryQuery;

    public List<CategoryResponse> findAll() {
        return categoryQuery.findAll();
    }

}
