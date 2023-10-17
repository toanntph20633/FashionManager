package com.example.fashionmanager.service.impl.module_san_pham.cautruckhuy;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.CreateCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.ListCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.UpdateCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.response.CauTrucKhuyDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.response.CauTrucKhuyResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.entity.CauTrucKhuyEntity;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.CauTrucKhuyRepository;
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
public class CauTrucKhuyServiceImpl implements CauTrucKhuyService{
    @Autowired
    private CauTrucKhuyRepository cauTrucKhuyRepository;
    @Override
    public ResponseEntity<ListReponseDto<CauTrucKhuyResponse>> getList(ListCauTrucKhuyRequest listCauTrucKhuyRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listCauTrucKhuyRequest.getPage(), listCauTrucKhuyRequest.getSize(), sort);
        Specification<CauTrucKhuyEntity> cauTrucKhuyEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listCauTrucKhuyRequest.getTenCauTrucKhuy())) {
                predicates.add(criteriaBuilder.like(root.get("tenCauTrucKhuy"), "%" + listCauTrucKhuyRequest.getTenCauTrucKhuy() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<CauTrucKhuyEntity> cauTrucKhuyEntities = cauTrucKhuyRepository.findAll(cauTrucKhuyEntitySpecification, pageable);
        List<CauTrucKhuyResponse> cauTrucKhuyResponses = cauTrucKhuyEntities.stream().map(cauTrucKhuyEntity -> mappingByResponse(cauTrucKhuyEntity)).toList();
        ListReponseDto<CauTrucKhuyResponse> listReponseDto = new ListReponseDto<CauTrucKhuyResponse>();
        listReponseDto.setItems(cauTrucKhuyResponses);
        listReponseDto.setHasNextPage(cauTrucKhuyEntities.hasNext());
        listReponseDto.setHasPreviousPage(cauTrucKhuyEntities.hasPrevious());
        listReponseDto.setPageCount(cauTrucKhuyEntities.getTotalPages());
        listReponseDto.setPageSize(cauTrucKhuyEntities.getSize());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        CauTrucKhuyEntity cauTrucKhuyEntity = cauTrucKhuyRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(cauTrucKhuyEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateCauTrucKhuyRequest createCauTrucKhuyRequest) {
        CauTrucKhuyEntity cauTrucKhuyEntity = mappingByCreateRequest(createCauTrucKhuyRequest);
        cauTrucKhuyRepository.save(cauTrucKhuyEntity);
        return ResponseEntity.ok(mappingByResponse(cauTrucKhuyEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateCauTrucKhuyRequest updateCauTrucKhuyRequest) {
        if (!cauTrucKhuyRepository.existsById(updateCauTrucKhuyRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        CauTrucKhuyEntity entity = mappingByUpdateRequest(updateCauTrucKhuyRequest);
        cauTrucKhuyRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        CauTrucKhuyEntity cauTrucKhuyEntity = cauTrucKhuyRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        cauTrucKhuyEntity.setDeleted(true);
        cauTrucKhuyRepository.save(cauTrucKhuyEntity);
        return ResponseEntity.ok(mappingByResponse(cauTrucKhuyEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public CauTrucKhuyEntity mappingByCreateRequest(CreateCauTrucKhuyRequest createCauTrucKhuyRequest) {
        return CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy(createCauTrucKhuyRequest.getTenCauTrucKhuy())
                .moTa(createCauTrucKhuyRequest.getMoTa()).build();
    }

    @Override
    public CauTrucKhuyEntity mappingByUpdateRequest(UpdateCauTrucKhuyRequest updateCauTrucKhuyRequest) {
        return CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy(updateCauTrucKhuyRequest.getTenCauTrucKhuy())
                .moTa(updateCauTrucKhuyRequest.getMoTa())
                .id(updateCauTrucKhuyRequest.getId()).build();
    }

    @Override
    public CauTrucKhuyResponse mappingByResponse(CauTrucKhuyEntity cauTrucKhuyEntity) {
        return CauTrucKhuyResponse.builder()
                .tenCauTrucKhuy(cauTrucKhuyEntity.getTenCauTrucKhuy())
                .moTa(cauTrucKhuyEntity.getMoTa())
                .id(cauTrucKhuyEntity.getId())
                .build();
    }

    @Override
    public CauTrucKhuyDetailResponse mappingResponseDetail(CauTrucKhuyEntity cauTrucKhuyEntity) {
        return CauTrucKhuyDetailResponse.builder()
                .tenCauTrucKhuy(cauTrucKhuyEntity.getTenCauTrucKhuy())
                .moTa(cauTrucKhuyEntity.getMoTa())
                .id(cauTrucKhuyEntity.getId())
                .build();
    }
}
