package com.example.fashionmanager.service.impl.module_san_pham.xeta;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.CreateXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.ListXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.UpdateXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.response.XeTaDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.response.XeTaResponse;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.XetaEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.XeTaRepository;
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
public class XeTaServiceImpl implements XeTaService{
    @Autowired
    private XeTaRepository xeTaRepository;


    @Override
    public ResponseEntity<ListReponseDto<XeTaResponse>> getList(ListXeTaRequest listXeTaRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listXeTaRequest.getPage(), listXeTaRequest.getSize(), sort);

        Specification<XetaEntity> xetaEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listXeTaRequest.getTenXeTa())) {
                predicates.add(criteriaBuilder.like(root.get("tenXeTa"), "%" + listXeTaRequest.getTenXeTa() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<XetaEntity> xetaEntities = xeTaRepository.findAll(xetaEntitySpecification, pageable);
        List<XeTaResponse> xeTaResponses = xetaEntities.stream().map(xetaEntity -> mappingByResponse(xetaEntity)).toList();
        ListReponseDto<XeTaResponse> listReponseDto = new ListReponseDto<XeTaResponse>();
        listReponseDto.setItems(xeTaResponses);
        listReponseDto.setHasNextPage(xetaEntities.hasNext());
        listReponseDto.setHasPreviousPage(xetaEntities.hasPrevious());
        listReponseDto.setPageCount(xetaEntities.getTotalPages());
        listReponseDto.setPageSize(xetaEntities.getSize());
        listReponseDto.setTotalItemCount(xetaEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        XetaEntity xetaEntity = xeTaRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(xetaEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateXeTaRequest createXeTaRequest) {
        XetaEntity xetaEntity = mappingByCreateRequest(createXeTaRequest);
        xeTaRepository.save(xetaEntity);
        return ResponseEntity.ok(mappingByResponse(xetaEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateXeTaRequest updateXeTaRequest) {
        if (!xeTaRepository.existsById(updateXeTaRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        XetaEntity xetaEntity = mappingByUpdateRequest(updateXeTaRequest);
        xeTaRepository.save(xetaEntity);
        return ResponseEntity.ok(mappingByResponse(xetaEntity));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        XetaEntity xetaEntity = xeTaRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        xetaEntity.setDeleted(true);
        xeTaRepository.save(xetaEntity);
        return ResponseEntity.ok(mappingByResponse(xetaEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public XetaEntity mappingByCreateRequest(CreateXeTaRequest createXeTaRequest) {
        return XetaEntity.builder().tenXeTa(createXeTaRequest.getTenXeTa()).moTa(createXeTaRequest.getMoTa()).build();
    }

    @Override
    public XetaEntity mappingByUpdateRequest(UpdateXeTaRequest updateXeTaRequest) {
        return XetaEntity.builder().tenXeTa(updateXeTaRequest.getTenXeTa())
                .moTa(updateXeTaRequest.getMoTa())
                .id(updateXeTaRequest.getId()).build();
    }

    @Override
    public XeTaResponse mappingByResponse(XetaEntity xetaEntity) {
        return XeTaResponse.builder()
                .tenXeTa(xetaEntity.getTenXeTa()).id(xetaEntity.getId()).build();
    }

    @Override
    public XeTaDetailResponse mappingResponseDetail(XetaEntity xetaEntity) {
        return XeTaDetailResponse.builder()
                .tenXeTa(xetaEntity.getTenXeTa())
                .moTa(xetaEntity.getMoTa()).id(xetaEntity.getId()).build();
    }
}
