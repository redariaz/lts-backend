package com.example.login_service.mapper;

import com.example.login_service.dto.*;
import com.example.login_service.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EntityMapper {

    private EntityMapper() {}

    public static DepartmentDTO toDepartmentDTO(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptId(department.getDeptId());
        departmentDTO.setDeptName(department.getDeptName());
        return departmentDTO;
    }
    public static Department toDepartmentEntity(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) {
            return null;
        }
        Department department = new Department();
        department.setDeptId(departmentDTO.getDeptId());
        department.setDeptName(departmentDTO.getDeptName());
        return department;
    }
    public static List<DepartmentDTO> toDepartmentDTOList(List<Department> departments) {
        return departments.stream()
                .map(EntityMapper::toDepartmentDTO)
                .collect(Collectors.toList());
    }
    public static List<Department> toDepartmentList(List<DepartmentDTO> departmentDTOs) {
        return departmentDTOs.stream()
                .map(EntityMapper::toDepartmentEntity)
                .collect(Collectors.toList());
    }

    // Role Mapping

    public static RoleDTO toRoleDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setDeptId(role.getDeptId());
        return roleDTO;
    }
    public static Role toRoleEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        role.setDeptId(roleDTO.getDeptId());
        return role;
    }
    public static List<RoleDTO> toRoleDTOList(List<Role> roles) {
        return roles.stream()
                .map(EntityMapper::toRoleDTO)
                .collect(Collectors.toList());
    }
    public static List<Role> toRoleList(List<RoleDTO> rolesDTOs) {
        return rolesDTOs.stream()
                .map(EntityMapper::toRoleEntity)
                .collect(Collectors.toList());
    }

    // User Mapping

    public static Users toUserEntity(UserDTO userDTO) {
        Users users = new Users();
        users.setFirstName(userDTO.getFirstName());
        users.setLastName(userDTO.getLastName());
        users.setEmpCode(userDTO.getEmpCode());
        users.setUsername(userDTO.getUsername());
        users.setEmail(userDTO.getEmail());
        users.setJoiningDate(LocalDate.now());
        users.setLocationName(userDTO.getLocationName());
        users.setDeptName(userDTO.getDeptName());
        users.setRoleName(userDTO.getRoleName());
        users.setShiftTime(userDTO.getShiftTime());
        users.setGender(userDTO.getGender());
        users.setDob(userDTO.getDob());
        users.setMaritalStatus(userDTO.getMaritalStatus());
        users.setCreatedDate(LocalDateTime.now());
        users.setModifiedDate(userDTO.getModifiedDate());
        return users;
    }

    // Location Mapping

    public static Location toLocationEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setLocId(locationDTO.getLocId());
        location.setLocationName(locationDTO.getLocationName());
        return location;
    }
    public static LocationDTO toLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocId(location.getLocId());
        locationDTO.setLocationName(location.getLocationName());
        return locationDTO;
    }
    public static List<LocationDTO> toLocationDTOList(List<Location> location) {
        return location.stream()
                .map(EntityMapper::toLocationDTO)
                .collect(Collectors.toList());
    }

    // Shift Mapping

    public static Shift toShiftEntity(ShiftDTO shiftDTO) {
        Shift shift = new Shift();
        shift.setShiftId(shiftDTO.getShiftId());
        shift.setShiftName(shiftDTO.getShiftName());
        shift.setShiftTime(shiftDTO.getShiftTime());
        return shift;
    }
    public static ShiftDTO toShiftDTO(Shift shift) {
        ShiftDTO shiftDTO = new ShiftDTO();
        shiftDTO.setShiftId(shift.getShiftId());
        shiftDTO.setShiftName(shift.getShiftName());
        shiftDTO.setShiftTime(shift.getShiftTime());
        return shiftDTO;
    }
    public static List<ShiftDTO> toShiftDTOList(List<Shift> shifts) {
        return shifts.stream()
                .map(EntityMapper::toShiftDTO)
                .collect(Collectors.toList());
    }
}
