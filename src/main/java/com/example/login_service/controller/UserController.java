package com.example.login_service.controller;

import com.example.login_service.dto.UserDTO;
import com.example.login_service.entity.Users;
import com.example.login_service.repository.UserRepository;
import com.example.login_service.response.APIResponse;
import com.example.login_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

//    @GetMapping("/test")
//    public Mono<String> test() {
//        return userRepository.count()
//                .map(count -> "Connected! Users count: " + count);
////        return "Working!";
//    }

    @GetMapping("/getAllUsers")
    public Mono<ResponseEntity<APIResponse<List<Users>>>> getAllUsers() {
        return userService.getAllUsers()
                .collectList()
                .map(users ->
                        ResponseEntity.status(HttpStatus.OK)
                                .body(new APIResponse<>(
                                        "Users fetched successfully",
                                        HttpStatus.OK.value(),
                                        users
                                ))
                );
    }

    @PostMapping("/create-user")
    public Mono<ResponseEntity<APIResponse<Users>>> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO)
                .map(savedUser ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(new APIResponse<>(
                                        "User created successfully",
                                        HttpStatus.CREATED.value(),
                                        savedUser
                                ))
                );
    }

    @GetMapping("/{empCode}")
    public Mono<ResponseEntity<APIResponse<Users>>> getUserByEmpCode(@PathVariable Long empCode) {
        return userService.getUserByEmpCode(empCode)
                .map(user ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "User fetched successfully",
                                        HttpStatus.OK.value(),
                                        user
                                )
                        ));
    }

}
