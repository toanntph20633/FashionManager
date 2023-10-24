package com.example.fashionmanager.service.impl.module_san_pham.hoatiet;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.CreateHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.ListHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.UpdateHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.response.HoaTietDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.response.HoaTietResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.entity.HoaTietEntity;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.HoaTietRepository;
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
public class HoaTietServiceImpl implements HoaTietService{

    @Autowired
    private HoaTietRepository hoaTietRepository;
    @Override
    public ResponseEntity<ListReponseDto<HoaTietResponse>> getList(ListHoaTietRequest listHoaTietRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listHoaTietRequest.getPage(), listHoaTietRequest.getSize(), sort);

        Specification<HoaTietEntity> hoaTietEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listHoaTietRequest.getTenHoaTiet())) {
                predicates.add(criteriaBuilder.like(root.get("tenHoaTiet"), "%" + listHoaTietRequest.getTenHoaTiet() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<HoaTietEntity> hoaTietEntities = hoaTietRepository.findAll(hoaTietEntitySpecification, pageable);
        List<HoaTietResponse> hoaTietResponses = hoaTietEntities.stream().map(hoaTietEntity -> mappingByResponse(hoaTietEntity)).toList();
        ListReponseDto<HoaTietResponse> listReponseDto = new ListReponseDto<HoaTietResponse>();
        listReponseDto.setItems(hoaTietResponses);
        listReponseDto.setHasNextPage(hoaTietEntities.hasNext());
        listReponseDto.setHasPreviousPage(hoaTietEntities.hasPrevious());
        listReponseDto.setPageCount(hoaTietEntities.getTotalPages());
        listReponseDto.setPageSize(hoaTietEntities.getSize());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        HoaTietEntity hoaTietEntity = hoaTietRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(hoaTietEntity));
    }

    @Override
    public ResponseEntity<HoaTietResponse> create(CreateHoaTietRequest createHoaTietRequest) {
        HoaTietEntity hoaTietEntity = mappingByCreateRequest(createHoaTietRequest);
        hoaTietRepository.save(hoaTietEntity);
        return ResponseEntity.ok(mappingByResponse(hoaTietEntity));
    }

    @Override
    public ResponseEntity<HoaTietResponse> update(UpdateHoaTietRequest updateHoaTietRequest) {
        if (!hoaTietRepository.existsById(updateHoaTietRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        HoaTietEntity entity = mappingByUpdateRequest(updateHoaTietRequest);
        hoaTietRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<HoaTietResponse> delete(Long id) {
        HoaTietEntity hoaTietEntity = hoaTietRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        hoaTietEntity.setDeleted(true);
        hoaTietRepository.save(hoaTietEntity);
        return ResponseEntity.ok(mappingByResponse(hoaTietEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public HoaTietEntity mappingByCreateRequest(CreateHoaTietRequest createHoaTietRequest) {
        return HoaTietEntity.builder().tenHoaTiet(createHoaTietRequest.getTenHoaTiet()).moTa(createHoaTietRequest.getMoTa()).build();
    }

    @Override
    public HoaTietEntity mappingByUpdateRequest(UpdateHoaTietRequest updateHoaTietRequest) {
        return HoaTietEntity.builder()
                .tenHoaTiet(updateHoaTietRequest.getTenHoaTiet())
                .moTa(updateHoaTietRequest.getMoTa())
                .id(updateHoaTietRequest.getId()).build();
    }

    @Override
    public HoaTietResponse mappingByResponse(HoaTietEntity hoaTietEntity) {
        return HoaTietResponse.builder()
                .tenHoaTiet(hoaTietEntity.getTenHoaTiet())
                .moTa(hoaTietEntity.getMoTa())
                .id(hoaTietEntity.getId()).build();
    }

    @Override
    public HoaTietDetailResponse mappingResponseDetail(HoaTietEntity hoaTietEntity) {
        return HoaTietDetailResponse.builder()
                .tenHoaTiet(hoaTietEntity.getTenHoaTiet())
                .moTa(hoaTietEntity.getMoTa())
                .id(hoaTietEntity.getId()).build();
    }
}
