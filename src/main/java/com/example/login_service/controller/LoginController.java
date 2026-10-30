package com.example.login_service.controller;

import com.example.login_service.dto.LoginDTO;
import com.example.login_service.entity.Login;
import com.example.login_service.response.APIResponse;
import com.example.login_service.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Mono<ResponseEntity<APIResponse<LoginDTO>>> login(@RequestBody LoginDTO loginDTO) {
        return loginService.authenticate(loginDTO)
                .map(userId ->
                        ResponseEntity.status(HttpStatus.OK)
                            .body(new APIResponse<>(
                                    "Login successful",
                                    HttpStatus.OK.value(),
                                    loginDTO
                            ))
                );
    }

}
