package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.category_manager.request.CategoryCreateRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryListRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryUpdateRequest;
import com.example.fashionmanager.dto.category_manager.response.CategoryReponse;


public interface ICategoryService {
    ListReponseDto<CategoryReponse> getList(CategoryListRequest request);

    ResponseDto<CategoryReponse> save(CategoryCreateRequest request);

    ResponseDto<CategoryReponse> update(CategoryUpdateRequest request);

    ResponseDto<CategoryReponse> delete(Long id);

    ResponseDto<CategoryReponse> detail(Long id);
    ResponseDto<CategoryReponse> changeActive(Long id);
}
