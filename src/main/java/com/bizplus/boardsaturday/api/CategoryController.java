package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.category.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.request.category.OrderChangeCategoryRequest;
import com.bizplus.boardsaturday.application.request.category.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.category.CategoryDetailResponse;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.application.response.category.CategoryWithPostCountResponse;
import com.bizplus.boardsaturday.application.service.CategoryService;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
//    @GetMapping
    public ResponseEntity<?> findAll() {
        List<CategoryResponse> all = categoryService.findAll();
        ResponseDto<List<CategoryResponse>> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), all);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findAllWithPostCount() {
        List<CategoryWithPostCountResponse> all = categoryService.findAllWithPostCount();
        ResponseDto<List<CategoryWithPostCountResponse>> responseDto
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

    @PutMapping("/{id}/on")
    public ResponseEntity<?> changeStatusOn(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.changeStatusOn(id);

        ResponseDto<CategoryResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), categoryResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/off")
    public ResponseEntity<?> changeStatusOff(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.changeStatusOff(id);

        ResponseDto<CategoryResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), categoryResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneCategory(@PathVariable Long id) {
        CategoryDetailResponse categoryDetailResponse = categoryService.findOne(id);
        ResponseDto<CategoryDetailResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), categoryDetailResponse);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/order-change")
    public ResponseEntity<?> orderChange(@RequestBody @Validated OrderChangeCategoryRequest request,
                                         BindingResult bindingResult) {
        categoryService.updateDisplayOrder(request.getIds());


        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
