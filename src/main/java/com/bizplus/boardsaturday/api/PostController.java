package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.application.request.post.UpdatePostRequest;
import com.bizplus.boardsaturday.application.response.post.PostResponse;
import com.bizplus.boardsaturday.application.service.PostService;
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
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<PostResponse> all = postService.findAll();
        ResponseDto<List<PostResponse>> responseDto
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

}
