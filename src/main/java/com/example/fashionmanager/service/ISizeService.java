package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeDetailResponse;
import com.example.fashionmanager.entity.SizeEntity;

import java.util.List;

public interface ISizeService {
    List<SizeEntity> getAll();

    SizeDetailResponse getOne(Long id) throws Exception;

    void save(SizeCreateRequest request);
    void update(SizeUpdateRequest request);

    void delete(Long id);
}
