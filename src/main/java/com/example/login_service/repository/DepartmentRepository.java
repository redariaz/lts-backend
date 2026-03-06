package com.example.login_service.repository;

import com.example.login_service.entity.Department;
import com.example.login_service.entity.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Long> {

    Mono<Boolean> existsByDeptName(String deptName);

    Flux<Role> findByDeptId(Long deptId);

    Flux<Department> findAllByOrderByDeptIdAsc();

}
