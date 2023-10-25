package com.example.fashionmanager.service.impl.module_san_pham.kieutui;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.Response.KieuTuiDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.Response.KieuTuiResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.CreateKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.ListKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.UpdateKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotResponse;
import com.example.fashionmanager.entity.KieuTuiEntity;
import com.example.fashionmanager.entity.LopLotEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.KieuTuiRepository;
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
public class KieuTuiServiceImpl implements KieuTuiService{
    @Autowired
    private KieuTuiRepository kieuTuiRepository;
    @Override
    public ResponseEntity<ListReponseDto<KieuTuiResponse>> getList(ListKieuTuiRequest listKieuTuiRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(listKieuTuiRequest.getPage(), listKieuTuiRequest.getSize(), sort);
        Specification<KieuTuiEntity> kieuTuiEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(listKieuTuiRequest.getTenKieuTui())){
                predicates.add(criteriaBuilder.like(root.get("tenKieuTui"), "%" + listKieuTuiRequest.getTenKieuTui() +"%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<KieuTuiEntity> kieuTuiEntities = kieuTuiRepository.findAll(kieuTuiEntitySpecification, pageable);
        List<KieuTuiResponse> kieuTuiResponses = kieuTuiEntities.stream().map(kieuTuiEntity -> mappingByResponse(kieuTuiEntity)).toList();
        ListReponseDto<KieuTuiResponse> listReponseDto = new ListReponseDto<KieuTuiResponse>();
        listReponseDto.setItems(kieuTuiResponses);
        listReponseDto.setHasNextPage(kieuTuiEntities.hasNext());
        listReponseDto.setHasPreviousPage(kieuTuiEntities.hasPrevious());
        listReponseDto.setPageCount(kieuTuiEntities.getTotalPages());
        listReponseDto.setPageSize(kieuTuiEntities.getSize());
        listReponseDto.setTotalItemCount(kieuTuiEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        KieuTuiEntity kieuTuiEntity = kieuTuiRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(kieuTuiEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateKieuTuiRequest createKieuTuiRequest) {
        KieuTuiEntity kieuTuiEntity = mappingByCreateRequest(createKieuTuiRequest);
        kieuTuiRepository.save(kieuTuiEntity);
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(UpdateKieuTuiRequest updateKieuTuiRequest) {
        if (!kieuTuiRepository.existsById(updateKieuTuiRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        kieuTuiRepository.save(mappingByUpdateRequest(updateKieuTuiRequest));
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        KieuTuiEntity kieuTuiEntity = kieuTuiRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        kieuTuiEntity.setDeleted(true);
        kieuTuiRepository.save(kieuTuiEntity);
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public KieuTuiEntity mappingByCreateRequest(CreateKieuTuiRequest createKieuTuiRequest) {
        return KieuTuiEntity.builder()
                .tenKieuTui(createKieuTuiRequest.getTenKieuTui())
                .moTa(createKieuTuiRequest.getMoTa())
                .build();
    }

    @Override
    public KieuTuiEntity mappingByUpdateRequest(UpdateKieuTuiRequest updateKieuTuiRequest) {
        return KieuTuiEntity.builder()
                .tenKieuTui(updateKieuTuiRequest.getTenKieuTui())
                .moTa(updateKieuTuiRequest.getMoTa())
                .id(updateKieuTuiRequest.getId())
                .build();
    }

    @Override
    public KieuTuiResponse mappingByResponse(KieuTuiEntity kieuTuiEntity) {
        return KieuTuiResponse.builder()
                .id(kieuTuiEntity.getId())
                .tenKieuTui(kieuTuiEntity.getTenKieuTui())
                .build();
    }

    @Override
    public KieuTuiDetailResponse mappingResponseDetail(KieuTuiEntity kieuTuiEntity) {
        return KieuTuiDetailResponse.builder()
                .id(kieuTuiEntity.getId())
                .tenKieuTui(kieuTuiEntity.getTenKieuTui())
                .moTa(kieuTuiEntity.getMoTa())
                .build();
    }
}
