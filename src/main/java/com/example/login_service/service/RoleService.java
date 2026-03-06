package com.example.login_service.service;

import com.example.login_service.dto.RoleDTO;
import com.example.login_service.entity.Role;
import com.example.login_service.mapper.EntityMapper;
import com.example.login_service.repository.DepartmentRepository;
import com.example.login_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    public Mono<List<RoleDTO>> getAllRoles() {
        return roleRepository.findAllByOrderByRoleIdAsc()
                .collectList()
                .map(EntityMapper::toRoleDTOList);
    }

    public Mono<List<RoleDTO>> getRolesByDepartmentId(Long deptId) {
        return departmentRepository.findById(deptId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Department not found"
                        )
                ))
                .flatMap(department ->
                    roleRepository.findByDeptIdOrderByRoleIdAsc(deptId)
                            .collectList()
                )
                .map(EntityMapper::toRoleDTOList);
    }

    public Mono<RoleDTO> createRoleByDeptId(Long deptId, RoleDTO roleDTO) {
        return departmentRepository.findById(deptId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Department not found"
                        )
                ))
                .flatMap(department ->
                        roleRepository.existsByRoleNameAndDeptId(roleDTO.getRoleName(), deptId)
                                .flatMap(exists -> {
                                    if (exists) {
                                        return Mono.error(
                                                new ResponseStatusException(
                                                        HttpStatus.CONFLICT,
                                                        "Role already exists in this department"
                                                )
                                        );
                                    }
                                    Role role = new Role();
                                    role.setRoleName(roleDTO.getRoleName());
                                    role.setDeptId(deptId);
                                    return roleRepository.save(role);
                                })
                )
                .map(EntityMapper::toRoleDTO);
    }

}
