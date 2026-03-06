package com.example.login_service.dto;

import com.example.login_service.enums.Gender;
import com.example.login_service.enums.MaritalStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private Integer empCode;
    private String username;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;
    private String locationName;
    private String deptName;
    private String roleName;
    private String shiftTime;
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private MaritalStatus maritalStatus;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
