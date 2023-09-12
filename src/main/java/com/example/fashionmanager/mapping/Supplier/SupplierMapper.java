package com.example.fashionmanager.mapping.Supplier;

import com.example.fashionmanager.dto.Supplier.request.SuppliearCreatRequest;
import com.example.fashionmanager.dto.Supplier.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.Supplier.response.SupplierResponse;
import com.example.fashionmanager.entity.SupplierEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierEntity getSupplierEntity(SupplierUpdateRequest supplierUpdateRequest);

    SupplierEntity getSupplierEntity(SuppliearCreatRequest suppliearCreatRequest);

    SupplierResponse getSupplierResponse(SupplierEntity supplierEntity);
}
