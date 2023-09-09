package com.example.fashionmanager.mapping.rank_manager;

import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.entity.RankEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankMapper {

    RankEntity getRankEntity(RankUpdateRequest rankUpdateRequest);

    RankEntity getRankEntity(RankCreateRequest rankCreateRequest);

    RankReponse getRankReponse(RankEntity rankEntity);

}
