package com.example.fashionmanager.mapping.size_manager;

import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeResponse;
import com.example.fashionmanager.entity.SizeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper {
    SizeEntity getSizeEntity(SizeUpdateRequest sizeUpdateRequest);

    SizeEntity getSizeEntity(SizeCreateRequest sizeCreateRequest);

    SizeResponse getSizeResponse(SizeEntity sizeEntity);
}
