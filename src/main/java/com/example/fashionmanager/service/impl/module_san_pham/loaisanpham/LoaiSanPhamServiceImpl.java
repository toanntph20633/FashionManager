package com.example.fashionmanager.service.impl.module_san_pham.loaisanpham;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.CreateLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.ListLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.UpdateLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.response.LoaiSanPhamDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.response.LoaiSanPhamResponse;
import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.LoaiSanPhamRepository;
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
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService{
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;
    @Override
    public ResponseEntity<ListReponseDto<LoaiSanPhamResponse>> getList(ListLoaiSanPhamRequest listLoaiSanPhamRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listLoaiSanPhamRequest.getPage(), listLoaiSanPhamRequest.getSize(), sort);

        Specification<LoaiSanPhamEntity> loaiSanPhamEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listLoaiSanPhamRequest.getTenLoai())) {
                predicates.add(criteriaBuilder.like(root.get("tenLoai"), "%" + listLoaiSanPhamRequest.getTenLoai() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<LoaiSanPhamEntity> loaiSanPhamEntities = loaiSanPhamRepository.findAll(loaiSanPhamEntitySpecification, pageable);
        List<LoaiSanPhamResponse> loaiSanPhamResponses = loaiSanPhamEntities.stream().map(loaiSanPhamEntity -> mappingByResponse(loaiSanPhamEntity)).toList();
        ListReponseDto<LoaiSanPhamResponse> listReponseDto = new ListReponseDto<LoaiSanPhamResponse>();
        listReponseDto.setItems(loaiSanPhamResponses);
        listReponseDto.setHasNextPage(loaiSanPhamEntities.hasNext());
        listReponseDto.setHasPreviousPage(loaiSanPhamEntities.hasPrevious());
        listReponseDto.setPageCount(loaiSanPhamEntities.getTotalPages());
        listReponseDto.setPageSize(loaiSanPhamEntities.getSize());
        listReponseDto.setTotalItemCount(loaiSanPhamEntities.getTotalElements());

        return ResponseEntity.ok(listReponseDto);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        LoaiSanPhamEntity loaiSanPhamEntity = loaiSanPhamRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(loaiSanPhamEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateLoaiSanPhamRequest createLoaiSanPhamRequest) {
        LoaiSanPhamEntity loaiSanPhamEntity = mappingByCreateRequest(createLoaiSanPhamRequest);
        loaiSanPhamRepository.save(loaiSanPhamEntity);
        return ResponseEntity.ok(mappingByResponse(loaiSanPhamEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest) {
        if (!loaiSanPhamRepository.existsById(updateLoaiSanPhamRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        LoaiSanPhamEntity entity = mappingByUpdateRequest(updateLoaiSanPhamRequest);
        loaiSanPhamRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        LoaiSanPhamEntity loaiSanPhamEntity = loaiSanPhamRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        loaiSanPhamEntity.setDeleted(true);
        loaiSanPhamRepository.save(loaiSanPhamEntity);
        return ResponseEntity.ok(mappingByResponse(loaiSanPhamEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public LoaiSanPhamEntity mappingByCreateRequest(CreateLoaiSanPhamRequest createLoaiSanPhamRequest) {
        return LoaiSanPhamEntity.builder().maLoai(createLoaiSanPhamRequest.getMaLoai()).tenLoai(createLoaiSanPhamRequest.getTenLoai()).build();
    }

    @Override
    public LoaiSanPhamEntity mappingByUpdateRequest(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest) {
        return LoaiSanPhamEntity.builder().maLoai(updateLoaiSanPhamRequest.getMaLoai())
                .tenLoai(updateLoaiSanPhamRequest.getTenLoai())
                .id(updateLoaiSanPhamRequest.getId()).build();
    }

    @Override
    public LoaiSanPhamResponse mappingByResponse(LoaiSanPhamEntity loaiSanPhamEntity) {
        return LoaiSanPhamResponse.builder().maLoai(loaiSanPhamEntity.getMaLoai()).id(loaiSanPhamEntity.getId()).tenLoai(loaiSanPhamEntity.getTenLoai()).build();
    }

    @Override
    public LoaiSanPhamDetailResponse mappingResponseDetail(LoaiSanPhamEntity loaiSanPhamEntity) {
        return LoaiSanPhamDetailResponse.builder().maLoai(loaiSanPhamEntity.getMaLoai()).id(loaiSanPhamEntity.getId()).tenLoai(loaiSanPhamEntity.getTenLoai()).build();
    }
}
