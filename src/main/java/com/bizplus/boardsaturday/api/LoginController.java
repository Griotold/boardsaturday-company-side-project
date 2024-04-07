package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.response.login.LoginJWTResponse;
import com.bizplus.boardsaturday.application.service.LoginProcessingService;
import com.bizplus.boardsaturday.global.config.jwt.LoginRequest;
import com.bizplus.boardsaturday.global.response.ResponseDto;
import com.bizplus.boardsaturday.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginProcessingService loginProcessingService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest,
                                   BindingResult bindingResult) {

        LoginJWTResponse loginJWTResponse = loginProcessingService.login(loginRequest);
        ResponseDto<LoginJWTResponse> responseDto = new ResponseDto<>(ResponseStatus.GOOD.getCode(),
                ResponseStatus.GOOD.getMessage(), loginJWTResponse);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
