package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeCreateRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeListRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeUpdateRequest;
import com.example.fashionmanager.dto.product_type_manager.response.ProductTypeRespones;

public interface IProductTypeService {

    ListReponseDto<ProductTypeRespones> getList(ProductTypeListRequest request);

    ResponseDto<ProductTypeRespones> save(ProductTypeCreateRequest request);

    ResponseDto<ProductTypeRespones> update(ProductTypeUpdateRequest request);

    ResponseDto<ProductTypeRespones> delete(Long id);

    ResponseDto<ProductTypeRespones> details(Long id);

    ResponseDto<ProductTypeRespones> changeActive(Long id);
}
