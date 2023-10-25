package com.example.fashionmanager.service.impl.module_san_pham.loplot;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotResponse;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.LopLotEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.LopLotRepository;
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
public class LopLotServiceImpl implements LopLotService {
    @Autowired
    private LopLotRepository lopLotRepository;

    @Override
    public ResponseEntity<ListReponseDto<LopLotResponse>> getList(ListLopLotRequest listLopLotRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(listLopLotRequest.getPage(), listLopLotRequest.getSize(), sort);
        Specification<LopLotEntity> lopLotEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listLopLotRequest.getTenLopLot())) {
                predicates.add(criteriaBuilder.like(root.get("tenLopLot"), "%" + listLopLotRequest.getTenLopLot() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<LopLotEntity> lopLotEntities = lopLotRepository.findAll(lopLotEntitySpecification, pageable);
        List<LopLotResponse> lopLotResponses = lopLotEntities.stream().map(lopLotEntity -> mappingByResponse(lopLotEntity)).toList();
        ListReponseDto<LopLotResponse> listReponseDto = new ListReponseDto<LopLotResponse>();
        listReponseDto.setItems(lopLotResponses);
        listReponseDto.setHasNextPage(lopLotEntities.hasNext());
        listReponseDto.setHasPreviousPage(lopLotEntities.hasPrevious());
        listReponseDto.setPageCount(lopLotEntities.getTotalPages());
        listReponseDto.setPageSize(lopLotEntities.getSize());
        listReponseDto.setTotalItemCount(lopLotEntities.getTotalElements());
        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        LopLotEntity lopLotEntity = lopLotRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(lopLotEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateLopLotRequest createLopLotRequest) {
        LopLotEntity lopLotEntity = mappingByCreateRequest(createLopLotRequest);
        lopLotRepository.save(lopLotEntity);
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> update(UpdateLopLotRequest updateLopLotRequest) {
        if (!lopLotRepository.existsById(updateLopLotRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        lopLotRepository.save(mappingByUpdateRequest(updateLopLotRequest));
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        LopLotEntity lopLotEntity = lopLotRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        lopLotEntity.setDeleted(true);
        lopLotRepository.save(lopLotEntity);
        return ResponseEntity.ok("DELETED");
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public LopLotEntity mappingByCreateRequest(CreateLopLotRequest createLopLotRequest) {

        return LopLotEntity.builder()
                .tenLopLot(createLopLotRequest.getTenLopLot())
                .moTa(createLopLotRequest.getMota())
                .build();
    }

    @Override
    public LopLotEntity mappingByUpdateRequest(UpdateLopLotRequest updateLopLotRequest) {

        return LopLotEntity.builder()
                .tenLopLot(updateLopLotRequest.getTenLopLot())
                .moTa(updateLopLotRequest.getMota())
                .id(updateLopLotRequest.getId())
                .build();

    }

    @Override
    public LopLotResponse mappingByResponse(LopLotEntity lopLotEntity) {
        return LopLotResponse.builder()
                .id(lopLotEntity.getId())
                .tenLopLot(lopLotEntity.getTenLopLot())
                .build();
    }

    @Override
    public LopLotDetailResponse mappingResponseDetail(LopLotEntity lopLotEntity) {
        return LopLotDetailResponse.builder()
                .id(lopLotEntity.getId())
                .tenLopLot(lopLotEntity.getTenLopLot())
                .moTa(lopLotEntity.getMoTa())
                .build();
    }
}
