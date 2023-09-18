package com.example.fashionmanager.mapping.employee;

import com.example.fashionmanager.dto.employee.request.EmployeeCreateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity getEmployeeEntity(EmployeeUpdateRequest employeeUpdateRequest);
    EmployeeEntity getEmployeeEntity(EmployeeCreateRequest employeeCreateRequest);
    EmployeeResponse getEmployeeResponse(EmployeeEntity employeeEntity);
}
