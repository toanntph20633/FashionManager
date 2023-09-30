package com.example.fashionmanager.mapping.rank_manager;

import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.entity.HangEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankMapper {

    HangEntity getRankEntity(RankUpdateRequest rankUpdateRequest);

    HangEntity getRankEntity(RankCreateRequest rankCreateRequest);

    RankReponse getRankReponse(HangEntity hangEntity);

}
