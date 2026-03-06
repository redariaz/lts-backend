package com.example.login_service.controller;

import com.example.login_service.dto.DepartmentDTO;
import com.example.login_service.entity.Department;
import com.example.login_service.response.APIResponse;
import com.example.login_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/saveDept")
    public Mono<ResponseEntity<APIResponse<Department>>> saveDept(
            @Validated @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.saveDept(departmentDTO)
                .map(savedDepartment ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(new APIResponse<>(
                                        "Department created successfully",
                                        HttpStatus.CREATED.value(),
                                        savedDepartment
                                ))
                );
    }

    @GetMapping
    public Mono<ResponseEntity<APIResponse<List<DepartmentDTO>>>> getAllDepartments() {
        return departmentService.getAllDepartments()
                .collectList()
                .map(departments ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "Departments fetched successfully",
                                        HttpStatus.OK.value(),
                                        departments
                                )
                        )
                );
    }

    @GetMapping("/{deptId}")
    public Mono<ResponseEntity<APIResponse<DepartmentDTO>>> getDepartmentWithRoles(
            @PathVariable Long deptId
    ) {
        return departmentService.getDepartmentWithRoles(deptId)
                .map(departmentDTO ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "Department fetched successfully",
                                        HttpStatus.OK.value(),
                                        departmentDTO
                                )
                        )
                );
    }

    @GetMapping("/deptWithoutRoles")
    public Mono<ResponseEntity<APIResponse<List<Department>>>> getAllDepartmentsWithoutRoles() {
        return departmentService.getAllDeptWithoutRoles()
                .collectList()
                .map(departments ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "Departments fetched successfully",
                                        HttpStatus.OK.value(),
                                        departments
                                )
                        )
                );
    }

    @GetMapping("/getDeptById/{deptId}")
    public Mono<ResponseEntity<APIResponse<DepartmentDTO>>> getDepartmentById(@PathVariable Long deptId) {
        return departmentService.getDepartmentById(deptId)
                .map(departmentDTO ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "Department fetched successfully",
                                        HttpStatus.OK.value(),
                                        departmentDTO
                                )
                        ));
    }

}
