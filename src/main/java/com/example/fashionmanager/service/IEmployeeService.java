package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeCreateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeListRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankListRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;

import java.util.List;

public interface IEmployeeService {
    ListReponseDto<EmployeeResponse> getList(EmployeeListRequest request);

    ResponseDto<EmployeeResponse> save(EmployeeCreateRequest request);

    ResponseDto<EmployeeResponse> update(EmployeeUpdateRequest request);

    ResponseDto<EmployeeResponse> delete(Long id);

    ResponseDto<EmployeeResponse> detail(Long id);
    ResponseDto<EmployeeResponse> changeActive(Long id);
}
