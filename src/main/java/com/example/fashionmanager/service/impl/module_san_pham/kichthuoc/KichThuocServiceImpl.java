package com.example.fashionmanager.service.impl.module_san_pham.kichthuoc;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request.CreateKichThuocRequest;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request.ListKichThuocRequest;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request.UpdateKichThuocRequest;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.response.KichThuocDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.response.KichThuocResponse;
import com.example.fashionmanager.dto.sanpham.quanlymausac.response.MauSacResponse;
import com.example.fashionmanager.entity.KichThuocEntity;
import com.example.fashionmanager.entity.MauSacEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.KichThuocRepository;
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
public class KichThuocServiceImpl implements KichThuocService{
    @Autowired
    private KichThuocRepository kichThuocRepository;
    @Override
    public ResponseEntity<ListReponseDto<KichThuocResponse>> getList(ListKichThuocRequest listKichThuocRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listKichThuocRequest.getPage(), listKichThuocRequest.getSize(), sort);

        Specification<KichThuocEntity> kichThuocEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listKichThuocRequest.getTenKichThuoc())) {
                predicates.add(criteriaBuilder.like(root.get("tenKichThuoc"), "%" + listKichThuocRequest.getTenKichThuoc() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<KichThuocEntity> kichThuocEntities = kichThuocRepository.findAll(kichThuocEntitySpecification, pageable);
        List<KichThuocResponse> kichThuocResponses = kichThuocEntities.stream().map(kichThuocEntity -> mappingByResponse(kichThuocEntity)).toList();
        ListReponseDto<KichThuocResponse> listReponseDto = new ListReponseDto<KichThuocResponse>();
        listReponseDto.setItems(kichThuocResponses);
        listReponseDto.setHasNextPage(kichThuocEntities.hasNext());
        listReponseDto.setHasPreviousPage(kichThuocEntities.hasPrevious());
        listReponseDto.setPageCount(kichThuocEntities.getTotalPages());
        listReponseDto.setPageSize(kichThuocEntities.getSize());
        listReponseDto.setTotalItemCount(kichThuocEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        KichThuocEntity kichThuocEntity = kichThuocRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(kichThuocEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateKichThuocRequest createKichThuocRequest) {
        KichThuocEntity kichThuocEntity = mappingByCreateRequest(createKichThuocRequest);
        kichThuocRepository.save(kichThuocEntity);
        return ResponseEntity.ok(mappingByResponse(kichThuocEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateKichThuocRequest updateKichThuocRequest) {
        if (!kichThuocRepository.existsById(updateKichThuocRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        KichThuocEntity entity = mappingByUpdateRequest(updateKichThuocRequest);
        kichThuocRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        KichThuocEntity kichThuocEntity = kichThuocRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        kichThuocEntity.setDeleted(true);
        kichThuocRepository.save(kichThuocEntity);
        return ResponseEntity.ok(mappingByResponse(kichThuocEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public KichThuocEntity mappingByCreateRequest(CreateKichThuocRequest createKichThuocRequest) {
        return KichThuocEntity.builder()
                .maKichThuoc(createKichThuocRequest.getMaKichThuoc())
                .tenKichThuoc(createKichThuocRequest.getTenKichThuoc())
                .coTay(createKichThuocRequest.getCoTay())
                .vongCo(createKichThuocRequest.getVongCo())
                .vongMong(createKichThuocRequest.getVongMong())
                .nach(createKichThuocRequest.getNach())
                .soDoBapTay(createKichThuocRequest.getSoDoBapTay())
                .soDaiCanhTay(createKichThuocRequest.getSoDaiCanhTay())
                .doDaiVai(createKichThuocRequest.getDoDaiVai())
                .vongEo(createKichThuocRequest.getVongEo())
                .vongNguc(createKichThuocRequest.getVongNguc())
                .doDaiAo(createKichThuocRequest.getDoDaiAo()).build();
    }

    @Override
    public KichThuocEntity mappingByUpdateRequest(UpdateKichThuocRequest updateKichThuocRequest) {
        return KichThuocEntity.builder()
                .maKichThuoc(updateKichThuocRequest.getMaKichThuoc())
                .tenKichThuoc(updateKichThuocRequest.getTenKichThuoc())
                .coTay(updateKichThuocRequest.getCoTay())
                .vongCo(updateKichThuocRequest.getVongCo())
                .vongMong(updateKichThuocRequest.getVongMong())
                .nach(updateKichThuocRequest.getNach())
                .soDoBapTay(updateKichThuocRequest.getSoDoBapTay())
                .soDaiCanhTay(updateKichThuocRequest.getSoDaiCanhTay())
                .doDaiVai(updateKichThuocRequest.getDoDaiVai())
                .vongEo(updateKichThuocRequest.getVongEo())
                .vongNguc(updateKichThuocRequest.getVongNguc())
                .doDaiAo(updateKichThuocRequest.getDoDaiAo())
                .id(updateKichThuocRequest.getId()).build();    }

    @Override
    public KichThuocResponse mappingByResponse(KichThuocEntity kichThuocEntity) {
        return KichThuocResponse.builder()
                .maKichThuoc(kichThuocEntity.getMaKichThuoc())
                .tenKichThuoc(kichThuocEntity.getTenKichThuoc())
                .coTay(kichThuocEntity.getCoTay())
                .vongCo(kichThuocEntity.getVongCo())
                .vongMong(kichThuocEntity.getVongMong())
                .nach(kichThuocEntity.getNach())
                .soDoBapTay(kichThuocEntity.getSoDoBapTay())
                .soDaiCanhTay(kichThuocEntity.getSoDaiCanhTay())
                .doDaiVai(kichThuocEntity.getDoDaiVai())
                .vongEo(kichThuocEntity.getVongEo())
                .vongNguc(kichThuocEntity.getVongNguc())
                .doDaiAo(kichThuocEntity.getDoDaiAo())
                .id(kichThuocEntity.getId())
                .build();
    }

    @Override
    public KichThuocDetailResponse mappingResponseDetail(KichThuocEntity kichThuocEntity) {
        return KichThuocDetailResponse.builder()
                .maKichThuoc(kichThuocEntity.getMaKichThuoc())
                .tenKichThuoc(kichThuocEntity.getTenKichThuoc())
                .coTay(kichThuocEntity.getCoTay())
                .vongCo(kichThuocEntity.getVongCo())
                .vongMong(kichThuocEntity.getVongMong())
                .nach(kichThuocEntity.getNach())
                .soDoBapTay(kichThuocEntity.getSoDoBapTay())
                .soDaiCanhTay(kichThuocEntity.getSoDaiCanhTay())
                .doDaiVai(kichThuocEntity.getDoDaiVai())
                .vongEo(kichThuocEntity.getVongEo())
                .vongNguc(kichThuocEntity.getVongNguc())
                .doDaiAo(kichThuocEntity.getDoDaiAo())
                .id(kichThuocEntity.getId())
                .build();    }
}
