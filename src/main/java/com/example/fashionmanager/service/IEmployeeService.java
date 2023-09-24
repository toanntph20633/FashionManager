package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeListRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;

public interface IEmployeeService {
    ListReponseDto<EmployeeResponse> getList(EmployeeListRequest request);

    ListReponseDto<EmployeeResponse> getActiveEmployees(int pageIndex);
    ResponseDto<EmployeeResponse> save(EmployeeUserCreateRequest request);

    ResponseDto<EmployeeResponse> update(EmployeeUpdateRequest request);

    ResponseDto<EmployeeResponse> delete(Long id);

    ResponseDto<EmployeeResponse> detail(Long id);
    ResponseDto<EmployeeResponse> changeActive(Long id);
}
