package com.example.login_service.repository;

import com.example.login_service.entity.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    Flux<Role> findByDeptId(Long deptId);

    Mono<Boolean> existsByRoleNameAndDeptId(String roleName, Long deptId);

    Flux<Role> findAllByOrderByRoleIdAsc();

    Flux<Role> findByDeptIdOrderByRoleIdAsc(Long deptId);

}
