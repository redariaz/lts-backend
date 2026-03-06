package com.example.login_service.repository;

import com.example.login_service.entity.Location;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LocationRepository extends ReactiveCrudRepository<Location, Long> {

    Flux<Location> findAllByOrderByLocIdAsc();

    Mono<Boolean> existsByLocationName(String locationName);

}
