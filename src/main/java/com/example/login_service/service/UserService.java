package com.example.login_service.service;

import com.example.login_service.dto.UserDTO;
import com.example.login_service.entity.Users;
import com.example.login_service.mapper.EntityMapper;
import com.example.login_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Users> createUser(UserDTO userDTO) {
        return userRepository.existsByEmpCode(userDTO.getEmpCode())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Employee code already exists"
                                )
                        );
                    }
                    return userRepository.existsByUsername(userDTO.getUsername());
                })
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Employee username already exists"
                                )
                        );
                    }
                    return userRepository.existsByEmail(userDTO.getEmail());
                })
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Employee email already exists"
                                )
                        );
                    }
                    Users users = EntityMapper.toUserEntity(userDTO);
                    System.out.println(users + "users");
                    return userRepository.save(users);
                });
    }

    public Mono<Users> getUserByEmpCode(Long empCode) {
        return userRepository.findByEmpCode(empCode)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found with empCode: " + empCode
                        )
                ));
    }

}
