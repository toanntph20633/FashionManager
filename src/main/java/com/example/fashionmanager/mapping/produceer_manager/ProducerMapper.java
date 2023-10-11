package com.example.fashionmanager.mapping.produceer_manager;

import com.example.fashionmanager.dto.producer_manager.request.ProducerCreateRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerUpdateRequest;
import com.example.fashionmanager.dto.producer_manager.response.ProducerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    NhaSanXuatEntity getProducerEntityCreate(ProducerCreateRequest producerCreateRequest);

    NhaSanXuatEntity getProducerEntityUpdate(ProducerUpdateRequest producerUpdateRequest);

    ProducerResponse getProducerResponse(NhaSanXuatEntity nhaSanXuatEntity);
}
