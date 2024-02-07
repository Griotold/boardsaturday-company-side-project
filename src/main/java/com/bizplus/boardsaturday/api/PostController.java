package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.response.post.PostResponse;
import com.bizplus.boardsaturday.application.service.PostService;
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
}
