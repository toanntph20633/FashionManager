package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.producer_manager.request.ProducerCreateRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerListRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerUpdateRequest;
import com.example.fashionmanager.dto.producer_manager.response.ProducerResponse;

public interface IProducerService {

    ListReponseDto<ProducerResponse> getList(ProducerListRequest request);

    ResponseDto<ProducerResponse> save(ProducerCreateRequest request);

    ResponseDto<ProducerResponse> update(ProducerUpdateRequest request);

    ResponseDto<ProducerResponse> delete(Long id);

    ResponseDto<ProducerResponse> details(Long id);

    ResponseDto<ProducerResponse> changeActive(Long id);

}
