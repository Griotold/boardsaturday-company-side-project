package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.application.service.CategoryService;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<CategoryResponse> all = categoryService.findAll();
        ResponseDto<List<CategoryResponse>> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), all);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
