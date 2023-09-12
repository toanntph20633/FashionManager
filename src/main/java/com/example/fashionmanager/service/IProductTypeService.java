package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ProductType.request.PTypeCreateRequest;
import com.example.fashionmanager.dto.ProductType.request.PTypeListRequest;
import com.example.fashionmanager.dto.ProductType.request.PTypeUpdateRequest;
import com.example.fashionmanager.dto.ProductType.response.PTypeResponse;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;

public interface IProductTypeService {
    ListReponseDto<PTypeResponse> getList(PTypeListRequest request);

    ResponseDto<PTypeResponse> save(PTypeCreateRequest request);

    ResponseDto<PTypeResponse> update(PTypeUpdateRequest request);

    ResponseDto<PTypeResponse> delete(Long id);

    ResponseDto<PTypeResponse> detail(Long id);

    ResponseDto<PTypeResponse> changeActive(Long id);

}
