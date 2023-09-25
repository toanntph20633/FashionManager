package com.example.fashionmanager.mapping.supplier_manager;

import com.example.fashionmanager.dto.supplier_manager.request.SupplierCreateRequest;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.supplier_manager.response.SupplierResponse;
import com.example.fashionmanager.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierEntity getSupplierEntity(SupplierUpdateRequest supplierUpdateRequest);

    SupplierEntity getSupplierEntity(SupplierCreateRequest suppliearCreatRequest);

    SupplierResponse getSupplierResponse(SupplierEntity supplierEntity);
}
