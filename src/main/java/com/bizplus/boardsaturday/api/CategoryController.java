package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.request.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.application.service.CategoryService;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Validated CreateCategoryRequest request,
                                           BindingResult bindingResult) {
        CategoryResponse categoryResponse = categoryService.create(request);

        ResponseDto<CategoryResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), categoryResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody @Validated UpdateCategoryRequest request,
                                            BindingResult bindingResult,
                                            @PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.update(request, id);

        ResponseDto<CategoryResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), categoryResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
