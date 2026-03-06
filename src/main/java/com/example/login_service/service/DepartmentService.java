package com.example.login_service.service;

import com.example.login_service.dto.DepartmentDTO;
import com.example.login_service.dto.RoleDTO;
import com.example.login_service.entity.Department;
import com.example.login_service.entity.Role;
import com.example.login_service.mapper.EntityMapper;
import com.example.login_service.repository.DepartmentRepository;
import com.example.login_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public Mono<Department> saveDept(DepartmentDTO departmentDTO) {
        return departmentRepository.existsByDeptName(departmentDTO.getDeptName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.CONFLICT,
                                        "Department already exists"
                                )
                        );
                    }
                    Department deptEntity = EntityMapper.toDepartmentEntity(departmentDTO);
                    departmentDTO.setDeptId(null);
                    return departmentRepository.save(deptEntity);
                });
    }

    public Flux<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAllByOrderByDeptIdAsc()
                .flatMap(dept ->
                        roleRepository.findByDeptIdOrderByRoleIdAsc(dept.getDeptId())
                                .map(role -> new RoleDTO(
                                        role.getRoleId(),
                                        role.getRoleName(),
                                        role.getDeptId()
                                ))
                                .collectList()
                                .map(roleList -> {
                                    DepartmentDTO departmentDTO = new DepartmentDTO();
                                    departmentDTO.setDeptId(dept.getDeptId());
                                    departmentDTO.setDeptName(dept.getDeptName());
                                    departmentDTO.setRoleList(roleList);
                                    return departmentDTO;
                                })
                );
    }

    public Mono<DepartmentDTO> getDepartmentWithRoles(Long deptId) {
        Mono<Department> departmentMono = departmentRepository.findById(deptId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Department not found"
                        )
                ));
        Mono<List<RoleDTO>> rolesMono = roleRepository.findByDeptId(deptId)
                .map(role -> new RoleDTO(
                        role.getRoleId(),
                        role.getRoleName(),
                        role.getDeptId()
                ))
                .collectList();
        return Mono.zip(departmentMono, rolesMono)
                .map(tuple -> {
                    Department department = tuple.getT1();
                    List<RoleDTO> roleList = tuple.getT2();
                    DepartmentDTO departmentDTO = new DepartmentDTO();
                    departmentDTO.setDeptId(department.getDeptId());
                    departmentDTO.setDeptName(department.getDeptName());
                    departmentDTO.setRoleList(roleList);
                    return departmentDTO;
                });
    }

    public Flux<Department> getAllDeptWithoutRoles() {
        return departmentRepository.findAllByOrderByDeptIdAsc();
    }

    public Mono<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(EntityMapper::toDepartmentDTO)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Department not found"
                        )
                ));
    }

}
