package com.example.fashionmanager.mapping.category_manager;

import com.example.fashionmanager.dto.category_manager.request.CategoryCreateRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryUpdateRequest;
import com.example.fashionmanager.dto.category_manager.response.CategoryReponse;
import com.example.fashionmanager.dto.color_manager.request.ColorCreateRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorUpdateRequest;
import com.example.fashionmanager.dto.color_manager.response.ColorReponse;
import com.example.fashionmanager.entity.CategoryEntity;
import com.example.fashionmanager.entity.ColorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity getCategoryEntity(CategoryUpdateRequest categoryUpdateRequest);

    CategoryEntity getCategoryEntity(CategoryCreateRequest categoryCreateRequest);

    CategoryReponse getCategoryReponse(CategoryEntity categoryEntity);
}
