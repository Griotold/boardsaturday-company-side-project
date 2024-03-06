package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.application.request.post.UpdatePostRequest;
import com.bizplus.boardsaturday.application.response.post.PostResponse;
import com.bizplus.boardsaturday.application.service.PostService;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> findAllWithPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                             @RequestParam(value = "status", required = false) ActiveStatus activeStatus,
                                             @RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "body", required = false) String body,
                                             @PageableDefault(size = 5) Pageable pageable) {
        Page<PostResponse> all = postService.searchWithPage(categoryId, activeStatus, title, body, pageable);
        ResponseDto<Page<PostResponse>> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), all);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated CreatePostRequest request,
                                    BindingResult bindingResult) {
        postService.create(request);
        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/on")
    public ResponseEntity<?> changeStatusOn(@PathVariable Long id) {
        postService.changeStatusOn(id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/off")
    public ResponseEntity<?> changeStatusOff(@PathVariable Long id) {
        postService.changeStatusOff(id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.delete(id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@RequestBody @Validated UpdatePostRequest request,
                                        BindingResult bindingResult,
                                        @PathVariable Long id) {
        postService.update(request, id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOnePost(@PathVariable Long id) {
        PostResponse postResponse = postService.findOneWithFetch(id);

        ResponseDto<PostResponse> responseDto =
                new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), postResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
