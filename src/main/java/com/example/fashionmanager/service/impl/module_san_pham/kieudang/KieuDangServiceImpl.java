package com.example.fashionmanager.service.impl.module_san_pham.kieudang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ListRequestDto;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.KieuDangRepository;
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
public class KieuDangServiceImpl implements KieuDangService {
    @Autowired
    private KieuDangRepository kieuDangRepository;

    @Override
    public ResponseEntity<ListReponseDto<KieuDangResponse>> getList(ListKieuDangRequest listKieuDangRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listKieuDangRequest.getPage(), listKieuDangRequest.getSize(), sort);

        Specification<KieuDangEntity> kieuDangEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listKieuDangRequest.getTenKieuDang())) {
                predicates.add(criteriaBuilder.like(root.get("tenKieuDang"), "%" + listKieuDangRequest.getTenKieuDang() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<KieuDangEntity> kieuDangEntities = kieuDangRepository.findAll(kieuDangEntitySpecification, pageable);
        List<KieuDangResponse> kieuDangResponses = kieuDangEntities.stream().map(kieuDangEntity -> mappingByResponse(kieuDangEntity)).toList();
        ListReponseDto<KieuDangResponse> listReponseDto = new ListReponseDto<KieuDangResponse>();
        listReponseDto.setItems(kieuDangResponses);
        listReponseDto.setHasNextPage(kieuDangEntities.hasNext());
        listReponseDto.setHasPreviousPage(kieuDangEntities.hasPrevious());
        listReponseDto.setPageCount(kieuDangEntities.getTotalPages());
        listReponseDto.setPageSize(kieuDangEntities.getSize());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<KieuDangDetailResponse> getById(Long id) {
        KieuDangEntity kieuDangEntity = kieuDangRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(kieuDangEntity));
    }

    @Override
    public ResponseEntity<KieuDangResponse> create(CreateKieuDangRequest createKieuDangRequest) {
        KieuDangEntity kieuDangEntity = mappingByCreateRequest(createKieuDangRequest);
        kieuDangRepository.save(kieuDangEntity);
        return ResponseEntity.ok(mappingByResponse(kieuDangEntity));
    }

    @Override
    public ResponseEntity<KieuDangResponse> update(UpdateKieuDangRequest updateKieuDangRequest) {
        if (!kieuDangRepository.existsById(updateKieuDangRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        KieuDangEntity entity = mappingByUpdateRequest(updateKieuDangRequest);
        kieuDangRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<KieuDangResponse> delete(Long id) {
        KieuDangEntity kieuDangEntity = kieuDangRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        kieuDangEntity.setDeleted(true);
        kieuDangRepository.save(kieuDangEntity);
        return ResponseEntity.ok(mappingByResponse(kieuDangEntity));
    }

    @Override
    public ResponseEntity<KieuDangResponse> changeActive(Long id) {
        return null;
    }

    @Override
    public KieuDangEntity mappingByCreateRequest(CreateKieuDangRequest createKieuDangRequest) {

        return KieuDangEntity.builder().tenKieuDang(createKieuDangRequest.getTenKieuDang()).moTa(createKieuDangRequest.getMoTa()).build();
    }

    @Override
    public KieuDangEntity mappingByUpdateRequest(UpdateKieuDangRequest updateKieuDangRequest) {
        return KieuDangEntity.builder().tenKieuDang(updateKieuDangRequest.getTenKieuDang()).moTa(updateKieuDangRequest.getMoTa()).id(updateKieuDangRequest.getId()).build();
    }

    @Override
    public KieuDangResponse mappingByResponse(KieuDangEntity kieuDangEntity) {
        return KieuDangResponse.builder().tenKieuDang(kieuDangEntity.getTenKieuDang()).id(kieuDangEntity.getId()).moTa(kieuDangEntity.getMoTa()).build();
    }

    @Override
    public KieuDangDetailResponse mappingResponseDetail(KieuDangEntity kieuDangEntity) {
        return KieuDangDetailResponse.builder().tenKieuDang(kieuDangEntity.getTenKieuDang()).id(kieuDangEntity.getId()).moTa(kieuDangEntity.getMoTa()).build();
    }
}
