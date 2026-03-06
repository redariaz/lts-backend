package com.example.login_service.repository;

import com.example.login_service.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {

    Mono<Users> findByUsername(String username);

    Mono<Users> findByEmpCode(Long empCode);

    Mono<Boolean> existsByEmpCode(Integer empCode);

    Mono<Boolean> existsByUsername(String username);

    Mono<Boolean> existsByEmail(String email);

}
