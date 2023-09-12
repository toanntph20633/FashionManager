package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.Supplier.request.SuppliearCreatRequest;
import com.example.fashionmanager.dto.Supplier.request.SupplierListRequest;
import com.example.fashionmanager.dto.Supplier.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.Supplier.response.SupplierResponse;

public interface ISupplierService {
    ListReponseDto<SupplierResponse> getList(SupplierListRequest request);

    ResponseDto<SupplierResponse> save(SuppliearCreatRequest request);

    ResponseDto<SupplierResponse> update(SupplierUpdateRequest request);

    ResponseDto<SupplierResponse> delete(Long id);

    ResponseDto<SupplierResponse> detail(Long id);

    ResponseDto<SupplierResponse> changeActive(Long id);

}