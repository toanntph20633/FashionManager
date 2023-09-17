package com.example.fashionmanager.mapping.color_manager;

import com.example.fashionmanager.dto.color_manager.request.ColorCreateRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorUpdateRequest;

import com.example.fashionmanager.dto.color_manager.response.ColorReponse;
import com.example.fashionmanager.entity.ColorEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    ColorEntity getColorEntity(ColorUpdateRequest colorUpdateRequest);

    ColorEntity getColorEntity(ColorCreateRequest colorCreateRequest);

    ColorReponse getColorReponse(ColorEntity colorEntity);
}
