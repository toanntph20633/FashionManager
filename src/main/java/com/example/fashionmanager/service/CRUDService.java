package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.ListReponseDto;
import org.springframework.http.ResponseEntity;

public interface CRUDService<Entity, CreateRequest, UpdateRequest, ListRequest, Response, ResponseDetail> {
    ResponseEntity<ListReponseDto<Response>> getList(ListRequest listRequest);

    ResponseEntity<ResponseDetail> getById(Long id);

    ResponseEntity<Response> create(CreateRequest createRequest);

    ResponseEntity<Response> update(UpdateRequest updateRequest);

    ResponseEntity<Response> delete(Long id);

    ResponseEntity<Response> changeActive(Long id);

    Entity mappingByCreateRequest(CreateRequest createRequest);
    Entity mappingByUpdateRequest(UpdateRequest updateRequest);

    Response mappingByResponse(Entity entity);
    ResponseDetail mappingResponseDetail(Entity entity);
}
