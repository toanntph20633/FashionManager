package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierCreateRequest;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierListRequest;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.supplier_manager.response.SupplierResponse;

public interface ISupplierService {
    ListReponseDto<SupplierResponse> getList(SupplierListRequest request);

    ResponseDto<SupplierResponse> save(SupplierCreateRequest request);

    ResponseDto<SupplierResponse> update(SupplierUpdateRequest request);

    ResponseDto<SupplierResponse> delete(Long id);

    ResponseDto<SupplierResponse> detail(Long id);

    ResponseDto<SupplierResponse> changeActive(Long id);

}