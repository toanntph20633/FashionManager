package com.example.fashionmanager.service.impl.module_san_pham.danhmuc;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.CreateDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.ListDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.UpdateDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.response.DanhMucDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.response.DanhMucResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.entity.DanhMucEntity;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.DanhMucRepository;
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
public class DanhMucServiceImpl implements DanhMucService{
    @Autowired
    private DanhMucRepository danhMucRepository;
    @Override
    public ResponseEntity<?> getList(ListDanhMucRequest listDanhMucRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listDanhMucRequest.getPage(), listDanhMucRequest.getSize(), sort);

        Specification<DanhMucEntity> danhMucEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listDanhMucRequest.getTenDanhMuc())) {
                predicates.add(criteriaBuilder.like(root.get("tenDanhMuc"), "%" + listDanhMucRequest.getTenDanhMuc() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<DanhMucEntity> danhMucEntities = danhMucRepository.findAll(danhMucEntitySpecification, pageable);
        List<DanhMucResponse> danhMucResponses = danhMucEntities.stream().map(danhMucEntity ->  mappingByResponse(danhMucEntity)).toList();
        ListReponseDto<DanhMucResponse> listReponseDto = new ListReponseDto<DanhMucResponse>();
        listReponseDto.setItems(danhMucResponses);
        listReponseDto.setHasNextPage(danhMucEntities.hasNext());
        listReponseDto.setHasPreviousPage(danhMucEntities.hasPrevious());
        listReponseDto.setPageCount(danhMucEntities.getTotalPages());
        listReponseDto.setPageSize(danhMucEntities.getSize());
        listReponseDto.setTotalItemCount(danhMucEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        DanhMucEntity danhMucEntity = danhMucRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(danhMucEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateDanhMucRequest createDanhMucRequest) {
        DanhMucEntity danhMucEntity = mappingByCreateRequest(createDanhMucRequest);
        danhMucRepository.save(danhMucEntity);
        return ResponseEntity.ok(mappingByResponse(danhMucEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateDanhMucRequest updateDanhMucRequest) {
        if (!danhMucRepository.existsById(updateDanhMucRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        DanhMucEntity entity = mappingByUpdateRequest(updateDanhMucRequest);
        danhMucRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        DanhMucEntity danhMucEntity = danhMucRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        danhMucEntity.setDeleted(true);
        danhMucRepository.save(danhMucEntity);
        return ResponseEntity.ok(mappingByResponse(danhMucEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public DanhMucEntity mappingByCreateRequest(CreateDanhMucRequest createDanhMucRequest) {
        return DanhMucEntity.builder().maDanhMuc(createDanhMucRequest.getMaDanhMuc()).tenDanhMuc(createDanhMucRequest.getTenDanhMuc()).build();
    }

    @Override
    public DanhMucEntity mappingByUpdateRequest(UpdateDanhMucRequest updateDanhMucRequest) {
        return DanhMucEntity.builder().maDanhMuc(updateDanhMucRequest.getMaDanhMuc())
                .tenDanhMuc(updateDanhMucRequest.getTenDanhMuc())
                .id(updateDanhMucRequest.getId()).build();
    }

    @Override
    public DanhMucResponse mappingByResponse(DanhMucEntity danhMucEntity) {
        return DanhMucResponse.builder().maDanhMuc(danhMucEntity.getMaDanhMuc())
                .tenDanhMuc(danhMucEntity.getTenDanhMuc())
                .id(danhMucEntity.getId()).build();
    }

    @Override
    public DanhMucDetailResponse mappingResponseDetail(DanhMucEntity danhMucEntity) {
        return DanhMucDetailResponse.builder().maDanhMuc(danhMucEntity.getMaDanhMuc())
                .tenDanhMuc(danhMucEntity.getTenDanhMuc())
                .id(danhMucEntity.getId()).build();
    }
}
