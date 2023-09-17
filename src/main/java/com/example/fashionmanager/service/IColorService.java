package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.color_manager.request.ColorCreateRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorListRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorUpdateRequest;
import com.example.fashionmanager.dto.color_manager.response.ColorReponse;



public interface IColorService {
    ListReponseDto<ColorReponse> getList(ColorListRequest request);

    ResponseDto<ColorReponse> save(ColorCreateRequest request);

    ResponseDto<ColorReponse> update(ColorUpdateRequest request);

    ResponseDto<ColorReponse> delete(Long id);

    ResponseDto<ColorReponse> detail(Long id);
    ResponseDto<ColorReponse> changeActive(Long id);
}
