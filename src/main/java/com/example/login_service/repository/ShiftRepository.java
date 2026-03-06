package com.example.login_service.repository;

import com.example.login_service.entity.Shift;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ShiftRepository extends ReactiveCrudRepository<Shift, Long> {

    Flux<Shift> findAllByOrderByShiftIdAsc();

    Mono<Boolean> existsByShiftName(String shiftName);

}
