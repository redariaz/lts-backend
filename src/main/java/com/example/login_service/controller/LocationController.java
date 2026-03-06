package com.example.login_service.controller;

import com.example.login_service.dto.LocationDTO;
import com.example.login_service.response.APIResponse;
import com.example.login_service.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
@CrossOrigin("*")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public Mono<ResponseEntity<APIResponse<List<LocationDTO>>>> getAllLocations() {
        return locationService.getAllLocations()
                .map(locations ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "Locations fetched successfully",
                                        HttpStatus.OK.value(),
                                        locations
                                )
                        )
                );
    }

    @PostMapping("/saveLocation")
    public Mono<ResponseEntity<APIResponse<LocationDTO>>> saveLocation(
            @Validated @RequestBody LocationDTO locationDTO
    ) {
        return locationService.saveLocation(locationDTO)
                .map(savedLocation ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(new APIResponse<>(
                                        "Location saved successfully",
                                        HttpStatus.CREATED.value(),
                                        savedLocation
                                ))
                );
    }

}
