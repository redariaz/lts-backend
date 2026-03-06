package com.example.login_service.controller;

import com.example.login_service.dto.ShiftDTO;
import com.example.login_service.response.APIResponse;
import com.example.login_service.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shifts")
@CrossOrigin("*")
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public Mono<ResponseEntity<APIResponse<List<ShiftDTO>>>> getAllShifts() {
        return shiftService.getAllShifts()
                .map(shiftDTOS ->
                    ResponseEntity.ok(
                            new APIResponse<>(
                                    "Shifts fetched successfully",
                                    HttpStatus.OK.value(),
                                    shiftDTOS
                            )
                    )
                );
    }

    @PostMapping("/saveShift")
    public Mono<ResponseEntity<APIResponse<ShiftDTO>>> saveShift(
            @Validated @RequestBody ShiftDTO shiftDTO
    ) {
        return shiftService.saveShift(shiftDTO)
                .map(savedShift ->
                        ResponseEntity.status(HttpStatus.OK)
                                .body(new APIResponse<>(
                                        "Shift saved successfully",
                                        HttpStatus.OK.value(),
                                        savedShift
                                ))
                );
    }

}
