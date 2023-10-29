package com.example.fashionmanager.service.impl.module_san_pham.veao;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.CreateVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.ListVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.UpdateVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.response.VeAoDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyveao.response.VeAoResponse;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.VeAoEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.VeAoRepository;
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
public class VeAoServiceImpl implements VeAoService{
    @Autowired
    private VeAoRepository veAoRepository;
    @Override
    public ResponseEntity<ListReponseDto<VeAoResponse>> getList(ListVeAoRequest listVeAoRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listVeAoRequest.getPage(), listVeAoRequest.getSize(), sort);

        Specification<VeAoEntity> veAoEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listVeAoRequest.getTenVeAo())) {
                predicates.add(criteriaBuilder.like(root.get("tenVeAo"), "%" + listVeAoRequest.getTenVeAo() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<VeAoEntity> veAoEntities = veAoRepository.findAll(veAoEntitySpecification, pageable);
        List<VeAoResponse> veAoResponses = veAoEntities.stream().map(kieuDangEntity -> mappingByResponse(kieuDangEntity)).toList();
        ListReponseDto<VeAoResponse> listReponseDto = new ListReponseDto<VeAoResponse>();
        listReponseDto.setItems(veAoResponses);
        listReponseDto.setHasNextPage(veAoEntities.hasNext());
        listReponseDto.setHasPreviousPage(veAoEntities.hasPrevious());
        listReponseDto.setPageCount(veAoEntities.getTotalPages());
        listReponseDto.setPageSize(veAoEntities.getSize());
        listReponseDto.setTotalItemCount(veAoEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);    }

    @Override
    public ResponseEntity<VeAoDetailResponse> getById(Long id) {
        VeAoEntity veAoEntity = veAoRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(veAoEntity));    }

    @Override
    public ResponseEntity<VeAoResponse> create(CreateVeAoRequest createVeAoRequest) {
        VeAoEntity veAoEntity = mappingByCreateRequest(createVeAoRequest);
        veAoRepository.save(veAoEntity);
        return ResponseEntity.ok(mappingByResponse(veAoEntity));
    }

    @Override
    public ResponseEntity<VeAoResponse> update(UpdateVeAoRequest updateVeAoRequest) {
        if (!veAoRepository.existsById(updateVeAoRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        VeAoEntity entity = mappingByUpdateRequest(updateVeAoRequest);
        veAoRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));    }

    @Override
    public ResponseEntity<VeAoResponse> delete(Long id) {
        VeAoEntity veAoEntity = veAoRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        veAoEntity.setDeleted(true);
        veAoRepository.save(veAoEntity);
        return ResponseEntity.ok(mappingByResponse(veAoEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public VeAoEntity mappingByCreateRequest(CreateVeAoRequest createVeAoRequest) {
        return VeAoEntity.builder().tenVeAo(createVeAoRequest.getTenVeAo()).moTa(createVeAoRequest.getMoTa()).build();
    }

    @Override
    public VeAoEntity mappingByUpdateRequest(UpdateVeAoRequest updateVeAoRequest) {
        return VeAoEntity.builder().tenVeAo(updateVeAoRequest
                .getTenVeAo()).moTa(updateVeAoRequest.getMoTa())
                .id(updateVeAoRequest.getId()).build();
    }

    @Override
    public VeAoResponse mappingByResponse(VeAoEntity veAoEntity) {
        return VeAoResponse.builder().tenVeAo(veAoEntity
                        .getTenVeAo()).moTa(veAoEntity.getMoTa()).build();
    }

    @Override
    public VeAoDetailResponse mappingResponseDetail(VeAoEntity veAoEntity) {
        return VeAoDetailResponse.builder().tenVeAo(veAoEntity
                .getTenVeAo()).moTa(veAoEntity.getMoTa()).id(veAoEntity.getId()).build();
    }
}
