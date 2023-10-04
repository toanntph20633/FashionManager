package com.example.fashionmanager.mapping.employee;

import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.entity.NhanVienEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    NhanVienEntity getEmployeeEntity(EmployeeUpdateRequest employeeUpdateRequest);
    NhanVienEntity getEmployeeEntity(EmployeeUserCreateRequest employeeCreateRequest);
    EmployeeResponse getEmployeeResponse(NhanVienEntity employeeEntity);
}
