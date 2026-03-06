package com.example.login_service.controller;

import com.example.login_service.dto.RoleDTO;
import com.example.login_service.entity.Role;
import com.example.login_service.repository.DepartmentRepository;
import com.example.login_service.repository.RoleRepository;
import com.example.login_service.response.APIResponse;
import com.example.login_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public Mono<ResponseEntity<APIResponse<List<RoleDTO>>>> getAllRoles() {
        return roleService.getAllRoles()
                .map(roles -> {
                    if (roles.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                                .body(new APIResponse<>(
                                        "No roles found",
                                        HttpStatus.NO_CONTENT.value(),
                                        null
                                ));
                    }
                        return ResponseEntity.ok(
                                new APIResponse<>(
                                        "Roles fetched successfully",
                                        HttpStatus.OK.value(),
                                        roles
                                )
                        );
                });
    }

    @GetMapping("/department/{id}")
    public Mono<ResponseEntity<APIResponse<List<RoleDTO>>>> getRolesByDepartmentId(@PathVariable Long id) {
        return roleService.getRolesByDepartmentId(id)
                .map(roles ->
                        ResponseEntity.ok(
                                new APIResponse<>(
                                        "Roles fetched successfully",
                                        HttpStatus.OK.value(),
                                        roles
                                )
                        )
                );
    }

    @PostMapping("/{deptId}")
    public Mono<ResponseEntity<APIResponse<RoleDTO>>> createRoleByDeptId(
            @PathVariable Long deptId,
            @Validated @RequestBody RoleDTO roleDTO
            ) {
        return roleService.createRoleByDeptId(deptId, roleDTO)
                .map(savedRole ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(new APIResponse<>(
                                        "Role created successfully",
                                        HttpStatus.CREATED.value(),
                                        savedRole
                                ))
                );
    }

}
