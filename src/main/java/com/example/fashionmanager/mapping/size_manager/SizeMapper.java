package com.example.fashionmanager.mapping.size_manager;

import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeResponse;
import com.example.fashionmanager.entity.KichThuocEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper {
    KichThuocEntity getSizeEntity(SizeUpdateRequest sizeUpdateRequest);

    KichThuocEntity getSizeEntity(SizeCreateRequest sizeCreateRequest);

    SizeResponse getSizeResponse(KichThuocEntity kichThuocEntity);
}
