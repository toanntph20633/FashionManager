package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeListRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeResponse;

public interface ISizeService {
    ListReponseDto<SizeResponse> getList(SizeListRequest request);

    ResponseDto<SizeResponse> save(SizeCreateRequest request);

    ResponseDto<SizeResponse> update(SizeUpdateRequest request);

    ResponseDto<SizeResponse> delete(Long id);

    ResponseDto<SizeResponse> detail(Long id);

    ResponseDto<SizeResponse> changeActive(Long id);
}
