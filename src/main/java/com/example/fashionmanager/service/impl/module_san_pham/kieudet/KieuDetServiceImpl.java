package com.example.fashionmanager.service.impl.module_san_pham.kieudet;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.CreateKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.ListKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.UpdateKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.response.KieuDetDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.response.KieuDetResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotResponse;
import com.example.fashionmanager.entity.KieuDetEntity;
import com.example.fashionmanager.entity.KieuTuiEntity;
import com.example.fashionmanager.entity.LopLotEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.KieuDetResponsitory;
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
public class KieuDetServiceImpl implements KieuDetService{
    @Autowired
    private KieuDetResponsitory kieuDetResponsitory;
    @Override
    public ResponseEntity<ListReponseDto<KieuDetResponse>> getList(ListKieuDetRequest listKieuDetRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(listKieuDetRequest.getPage(), listKieuDetRequest.getSize(), sort);
        Specification<KieuDetEntity> kieuDetEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listKieuDetRequest.getTenKieuDet())) {
                predicates.add(criteriaBuilder.like(root.get("tenKieuDet"), "%" + listKieuDetRequest.getTenKieuDet() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<KieuDetEntity> kieuDetEntities = kieuDetResponsitory.findAll(kieuDetEntitySpecification, pageable);
        List<KieuDetResponse> kieuDetResponses = kieuDetEntities.stream().map(kieuDetEntity -> mappingByResponse(kieuDetEntity)).toList();
        ListReponseDto<KieuDetResponse> listReponseDto = new ListReponseDto<KieuDetResponse>();
        listReponseDto.setItems(kieuDetResponses);
        listReponseDto.setHasNextPage(kieuDetEntities.hasNext());
        listReponseDto.setHasPreviousPage(kieuDetEntities.hasPrevious());
        listReponseDto.setPageCount(kieuDetEntities.getTotalPages());
        listReponseDto.setPageSize(kieuDetEntities.getSize());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        KieuDetEntity kieuDetEntity = kieuDetResponsitory.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(kieuDetEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateKieuDetRequest createKieuDetRequest) {
        KieuDetEntity kieuDetEntity = mappingByCreateRequest(createKieuDetRequest);
        kieuDetResponsitory.save(kieuDetEntity);
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(UpdateKieuDetRequest updateKieuDetRequest) {
        if (!kieuDetResponsitory.existsById(updateKieuDetRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        kieuDetResponsitory.save(mappingByUpdateRequest(updateKieuDetRequest));
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        KieuDetEntity kieuDetEntity = kieuDetResponsitory.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        kieuDetEntity.setDeleted(true);
        kieuDetResponsitory.save(kieuDetEntity);
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public KieuDetEntity mappingByCreateRequest(CreateKieuDetRequest createKieuDetRequest) {
        return KieuDetEntity.builder()
                .tenKieuDet(createKieuDetRequest.getTenKieuDet())
                .moTa(createKieuDetRequest.getMoTa())
                .build();
    }

    @Override
    public KieuDetEntity mappingByUpdateRequest(UpdateKieuDetRequest updateKieuDetRequest) {
        return KieuDetEntity.builder()
                .tenKieuDet(updateKieuDetRequest.getTenKieuDet())
                .moTa(updateKieuDetRequest.getMoTa())
                .id(updateKieuDetRequest.getId())
                .build();
    }

    @Override
    public KieuDetResponse mappingByResponse(KieuDetEntity kieuDetEntity) {
        return KieuDetResponse.builder()
                .tenKieuDet(kieuDetEntity.getTenKieuDet())
                .moTa(kieuDetEntity.getMoTa())
                .id(kieuDetEntity.getId())
                .build();
    }

    @Override
    public KieuDetDetailResponse mappingResponseDetail(KieuDetEntity kieuDetEntity) {
        return  KieuDetDetailResponse.builder()
                .tenKieuDet(kieuDetEntity.getTenKieuDet())
                .moTa(kieuDetEntity.getMoTa())
                .id(kieuDetEntity.getId())
                .build();
    }
}
