package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import com.bizplus.boardsaturday.application.service.CommentService;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated CreateCommentRequest request,
                                    BindingResult bindingResult) {
        commentService.create(request);
        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/on")
    public ResponseEntity<?> changeStatusOn(@PathVariable Long id) {
        commentService.changeStatusOn(id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(), ResponseStatus.GOOD.getMessage(), null);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/off")
    public ResponseEntity<?> changeStatusOff(@PathVariable Long id) {
        commentService.changeStatusOff(id);

        ResponseDto<Object> responseDto
                = new ResponseDto<>(ResponseStatus.GOOD.getCode(),
                    ResponseStatus.GOOD.getMessage(),
                    null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
}
