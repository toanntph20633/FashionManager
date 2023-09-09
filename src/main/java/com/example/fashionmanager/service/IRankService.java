package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankListRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import org.springframework.http.ResponseEntity;

public interface IRankService {
    ListReponseDto<RankReponse> getList(RankListRequest request);

    ResponseDto<RankReponse> save(RankCreateRequest request);

    ResponseDto<RankReponse> update(RankUpdateRequest request);

    ResponseDto<RankReponse> delete(Long id);

    ResponseDto<RankReponse> detail(Long id);
    ResponseDto<RankReponse> changeActive(Long id);
}
