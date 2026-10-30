package com.example.login_service.dto;

import com.example.login_service.enums.Gender;
import com.example.login_service.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTableDTO {

    private String fullName;
    private Integer empCode;
    private String email;
    private LocalDate joiningDate;
    private String locationName;
    private String deptName;
    private String roleName;
    private String shiftTime;
    private Gender gender;
    private LocalDate dob;
    private MaritalStatus maritalStatus;
}
