package com.example.login_service.service;

import com.example.login_service.dto.DepartmentDTO;
import com.example.login_service.dto.LocationDTO;
import com.example.login_service.entity.Location;
import com.example.login_service.mapper.EntityMapper;
import com.example.login_service.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Mono<List<LocationDTO>> getAllLocations() {
        return locationRepository.findAllByOrderByLocIdAsc()
                .collectList()
                .map(EntityMapper::toLocationDTOList);
    }

    public Mono<LocationDTO> saveLocation(LocationDTO locationDTO) {
        return locationRepository.existsByLocationName(locationDTO.getLocationName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Location already exists"
                                )
                        );
                    }
                    Location location = EntityMapper.toLocationEntity(locationDTO);
                    location.setLocId(null);
                    return locationRepository.save(location)
                            .map(EntityMapper::toLocationDTO);
                });
    }

}
