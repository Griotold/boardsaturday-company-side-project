package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.tag.CreateTagRequest;
import com.bizplus.boardsaturday.application.response.tag.TagResponse;
import com.bizplus.boardsaturday.application.service.TagService;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody @Validated CreateTagRequest request,
                                       BindingResult bindingResult) {
        TagResponse tagResponse = tagService.create(request);

        ResponseDto<TagResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), tagResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // todo 질문 1. url에 한글이 들어가게 해도 괜찮을지
    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        TagResponse tagResponse = tagService.findByName(name);

        ResponseDto<TagResponse> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), tagResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
