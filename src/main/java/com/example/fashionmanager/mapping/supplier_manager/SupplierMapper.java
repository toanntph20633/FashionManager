package com.example.fashionmanager.mapping.supplier_manager;

import com.example.fashionmanager.dto.supplier_manager.request.SupplierCreateRequest;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.supplier_manager.response.SupplierResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    NhaCungCapEntity getSupplierEntity(SupplierUpdateRequest supplierUpdateRequest);

    NhaCungCapEntity getSupplierEntity(SupplierCreateRequest suppliearCreatRequest);

    SupplierResponse getSupplierResponse(NhaCungCapEntity nhaCungCapEntity);
}
