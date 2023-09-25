package com.example.fashionmanager.mapping.produceer_manager;

import com.example.fashionmanager.dto.producer_manager.request.ProducerCreateRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerUpdateRequest;
import com.example.fashionmanager.dto.producer_manager.response.ProducerResponse;
import com.example.fashionmanager.entity.ProducerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    ProducerEntity getProducerEntityCreate(ProducerCreateRequest producerCreateRequest);

    ProducerEntity getProducerEntityUpdate(ProducerUpdateRequest producerUpdateRequest);

    ProducerResponse getProducerResponse(ProducerEntity producerEntity);
}
