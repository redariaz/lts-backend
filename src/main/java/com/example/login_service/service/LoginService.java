package com.example.login_service.service;

import com.example.login_service.dto.LoginDTO;
import com.example.login_service.entity.Login;
import com.example.login_service.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<Long> authenticate(LoginDTO loginDTO) {

        return loginRepository.findByEmail(loginDTO.getEmail())
                .filter(login ->
                        passwordEncoder.matches(
                                loginDTO.getPassword(),
                                login.getPassword()))
                .map(Login::getUserId)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.UNAUTHORIZED,
                                        "Invalid email or password"
                                )
                        )
                );
    }
}
