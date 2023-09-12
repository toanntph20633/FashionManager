package com.example.fashionmanager.mapping.ProductType;

import com.example.fashionmanager.dto.ProductType.request.PTypeCreateRequest;
import com.example.fashionmanager.dto.ProductType.request.PTypeUpdateRequest;
import com.example.fashionmanager.dto.ProductType.response.PTypeResponse;
import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.entity.ProductTypeEntity;
import com.example.fashionmanager.entity.RankEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductTypeEntity getProductTypeEntity(PTypeUpdateRequest pTypeUpdateRequest);

    ProductTypeEntity getProductTypeEntity(PTypeCreateRequest pTypeCreateRequest);

    PTypeResponse getProductTypeReponse(ProductTypeEntity productTypeEntity);
}
