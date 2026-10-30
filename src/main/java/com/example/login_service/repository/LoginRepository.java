package com.example.login_service.repository;

import com.example.login_service.entity.Login;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LoginRepository extends ReactiveCrudRepository<Login, Long> {

    Mono<Login> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);

    Mono<Login> findByUserId(Long userId);

}
