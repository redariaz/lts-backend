package com.example.login_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {

    private Long shiftId;
    private String shiftName;
    private String shiftTime;

}
