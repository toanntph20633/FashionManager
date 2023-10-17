//package com.example.fashionmanager.service.impl.module_san_pham.kieudang;
//
//import com.example.fashionmanager.dto.ListReponseDto;
//import com.example.fashionmanager.dto.ListRequestDto;
//import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
//import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
//import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
//import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangDetailResponse;
//import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
//import com.example.fashionmanager.entity.KieuDangEntity;
//import com.example.fashionmanager.repository.KieuDangRepository;
//import com.example.fashionmanager.service.CRUDSerivce;
//import jakarta.persistence.criteria.Predicate;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class KieuDangServiceImpl implements CRUDSerivce<KieuDangEntity, CreateKieuDangRequest, UpdateKieuDangRequest
//        , ListKieuDangRequest, KieuDangResponse, KieuDangDetailResponse> {
//    @Autowired
//    private KieuDangRepository kieuDangRepository;
//
//    @Override
//    public ResponseEntity<ListReponseDto<KieuDangResponse>> getList(ListKieuDangRequest listKieuDangRequest) {
//        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
//
//        Pageable pageable = PageRequest.of(listKieuDangRequest.getPage(), listKieuDangRequest.getSize(), sort);
//
//        Specification<KieuDangEntity> kieuDangEntitySpecification = (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (StringUtils.isNotBlank(listKieuDangRequest.getTenKieuDang())) {
//                predicates.add(criteriaBuilder.like(root.get("tenKieuDang"), "%" + listKieuDangRequest.getTenKieuDang() + "%"));
//            }
//            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
//            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
//        };
//        Page<KieuDangEntity> kieuDangEntities = kieuDangRepository.findAll(kieuDangEntitySpecification, pageable);
//        List<KieuDangResponse> kieuDangResponses = kieuDangEntities.stream().map(kieuDangEntity -> KieuDangResponse
//                .builder()
//                .tenKieuDang(kieuDangEntity.getTenKieuDang())
//                .id(kieuDangEntity.getId())
//                .moTa(kieuDangEntity.getMoTa())
//                .build()).toList();
//        ListReponseDto<KieuDangResponse> listReponseDto = new ListReponseDto<KieuDangResponse>();
//        listReponseDto.setItems(kieuDangResponses);
//        listReponseDto.setHasNextPage(kieuDangEntities.hasNext());
//        listReponseDto.setHasPreviousPage(kieuDangEntities.hasPrevious());
//        listReponseDto.setPageCount(kieuDangEntities.getTotalPages());
//        listReponseDto.setPageSize(kieuDangEntities.getSize());
//
//        return ResponseEntity.ok(listReponseDto);
//    }
//
//    @Override
//    public ResponseEntity<KieuDangDetailResponse> getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<KieuDangResponse> create(CreateKieuDangRequest createKieuDangRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<KieuDangResponse> update(UpdateKieuDangRequest updateKieuDangRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<KieuDangResponse> delete(Long id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<KieuDangResponse> changeActive(Long id) {
//        return null;
//    }
//}
