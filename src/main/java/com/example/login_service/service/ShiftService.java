package com.example.login_service.service;

import com.example.login_service.dto.ShiftDTO;
import com.example.login_service.entity.Shift;
import com.example.login_service.mapper.EntityMapper;
import com.example.login_service.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public Mono<List<ShiftDTO>> getAllShifts() {
        return shiftRepository.findAllByOrderByShiftIdAsc()
                .collectList()
                .map(EntityMapper::toShiftDTOList);
    }

    public Mono<ShiftDTO> saveShift(ShiftDTO shiftDTO) {
        return shiftRepository.existsByShiftName(shiftDTO.getShiftName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "There cannot be multiple shifts of same name"
                                )
                        );
                    }
                    Shift shift = EntityMapper.toShiftEntity(shiftDTO);
                    shift.setShiftId(null);
                    shift.setShiftName(shiftDTO.getShiftName());
                    shift.setShiftTime(shiftDTO.getShiftTime());
                    return shiftRepository.save(shift)
                            .map(EntityMapper::toShiftDTO);
                });
    }

}
