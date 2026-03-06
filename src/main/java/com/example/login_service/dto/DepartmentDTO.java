package com.example.login_service.dto;

import com.example.login_service.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long deptId;
    @NotBlank(message = "Department name is required")
    private String deptName;
    private List<RoleDTO> roleList;

}
