package com.example.login_service.entity;

import com.example.login_service.enums.Gender;
import com.example.login_service.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("users")
public class Users {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Integer empCode;
    private String username;
    private String email;
    private LocalDate joiningDate;
    private String locationName;
    private String deptName;
    private String roleName;
    private String shiftTime;
    private Gender gender;
    private LocalDate dob;
    private MaritalStatus maritalStatus;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
