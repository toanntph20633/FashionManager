package com.example.fashionmanager.service.impl.module_san_pham.chatlieu;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ListRequestDto;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.request.CreateChatLieuRequest;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.request.ListChatLieuRequest;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.request.UpdateChatLieuRequest;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.response.ChatLieuDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.response.ChatLieuResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotResponse;
import com.example.fashionmanager.entity.ChatLieuEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.ChatLieuRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatLieuServiceImpl implements ChatLieuService{
    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public ResponseEntity<ListReponseDto<ChatLieuResponse>> getList(ListChatLieuRequest listChatLieuRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(listChatLieuRequest.getPage(), listChatLieuRequest.getSize(), sort);
        Specification<ChatLieuEntity> chatLieuEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(listChatLieuRequest.getTenChatLieu())){
                predicates.add(criteriaBuilder.like(root.get("tenChatLieu"), "%" + listChatLieuRequest.getTenChatLieu() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<ChatLieuEntity> chatLieuEntities = chatLieuRepository.findAll(chatLieuEntitySpecification, pageable);
        List<ChatLieuResponse> chatLieuResponses = chatLieuEntities.stream().map(chatLieuEntity -> mappingByResponse(chatLieuEntity)).toList();
        ListReponseDto<ChatLieuResponse> listReponseDto = new ListReponseDto<ChatLieuResponse>();
        listReponseDto.setItems(chatLieuResponses);
        listReponseDto.setHasNextPage(chatLieuEntities.hasNext());
        listReponseDto.setHasPreviousPage(chatLieuEntities.hasPrevious());
        listReponseDto.setPageCount(chatLieuEntities.getTotalPages());
        listReponseDto.setPageSize(chatLieuEntities.getSize());
        return ResponseEntity.ok(listReponseDto);
    };

    @Override
    public ResponseEntity<?> getById(Long id) {
        ChatLieuEntity chatLieuEntity = chatLieuRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(chatLieuEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateChatLieuRequest createChatLieuRequest) {
        ChatLieuEntity chatLieuEntity = mappingByCreateRequest(createChatLieuRequest);
        chatLieuRepository.save(chatLieuEntity);
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(UpdateChatLieuRequest updateChatLieuRequest) {
        if(!chatLieuRepository.existsById(updateChatLieuRequest.getId())){
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND,"Không tìm thấy thực thể"));
        }
        chatLieuRepository.save(mappingByUpdateRequest(updateChatLieuRequest));
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        ChatLieuEntity chatLieuEntity = chatLieuRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        chatLieuEntity.setDeleted(true);
        chatLieuRepository.save(chatLieuEntity);
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public ChatLieuEntity mappingByCreateRequest(CreateChatLieuRequest createChatLieuRequest) {
        return ChatLieuEntity.builder()
                .tenChatLieu(createChatLieuRequest.getTenChatLieu())
                .moTa(createChatLieuRequest.getMoTa()).build();
    }

    @Override
    public ChatLieuEntity mappingByUpdateRequest(UpdateChatLieuRequest updateChatLieuRequest) {
        return ChatLieuEntity.builder()
                .tenChatLieu(updateChatLieuRequest.getTenChatLieu())
                .moTa(updateChatLieuRequest.getMoTa())
                .id(updateChatLieuRequest.getId())
                .build();
    }

    @Override
    public ChatLieuResponse mappingByResponse(ChatLieuEntity chatLieuEntity) {
        return ChatLieuResponse.builder()
                .id(chatLieuEntity.getId())
                .tenChatLieu(chatLieuEntity.getTenChatLieu())
                .build();
    }

    @Override
    public ChatLieuDetailResponse mappingResponseDetail(ChatLieuEntity chatLieuEntity) {
        return ChatLieuDetailResponse.builder()
                .id(chatLieuEntity.getId())
                .tenChatLieu(chatLieuEntity.getTenChatLieu())
                .moTa(chatLieuEntity.getMoTa())
                .build();
    }
}
