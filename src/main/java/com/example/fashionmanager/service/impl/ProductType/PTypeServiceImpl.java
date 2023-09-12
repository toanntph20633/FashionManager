package com.example.fashionmanager.service.impl.ProductType;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ProductType.request.PTypeCreateRequest;
import com.example.fashionmanager.dto.ProductType.request.PTypeListRequest;
import com.example.fashionmanager.dto.ProductType.request.PTypeUpdateRequest;
import com.example.fashionmanager.dto.ProductType.response.PTypeResponse;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.entity.ProductTypeEntity;
import com.example.fashionmanager.entity.RankEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.ProductType.ProductTypeMapper;
import com.example.fashionmanager.repository.ProducerRepository;
import com.example.fashionmanager.repository.ProductTypeRepository;
import com.example.fashionmanager.service.IProductTypeService;
import org.apache.commons.lang3.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
//import java.util.function.Predicate;

public class PTypeServiceImpl implements IProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public ListReponseDto<PTypeResponse> getList(PTypeListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dataCreate"),
                new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<ProductTypeEntity> productTypeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("PTypeCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("PTypeName"), "%" + request.getName() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            predicates.add(criteriaBuilder.equal(root.get("active"), request.isActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<ProductTypeEntity> productTypeEntities = productTypeRepository.findAll(productTypeEntitySpecification, pageable);
        List<PTypeResponse> pTypeResponses = productTypeEntities.stream().map(ptype -> productTypeMapper.getProductTypeReponse(ptype)).toList();
        ListReponseDto<PTypeResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(pTypeResponses);
        listReponseDto.setHasNextPage(productTypeEntities.hasNext());
        listReponseDto.setHasPreviousPage(productTypeEntities.hasPrevious());
        listReponseDto.setPageCount(productTypeEntities.getTotalPages());
        listReponseDto.setPageSize(productTypeEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<PTypeResponse> save(PTypeCreateRequest request) {
        if (productTypeRepository.existsByProductTypeAndDeleted(request.getProductTypeCode(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Mã code đã tồn tại!"
                    )
            );
        }
        ProductTypeEntity productTypeEntity = productTypeMapper.getProductTypeEntity(request);
        ResponseDto<PTypeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(productTypeMapper.getProductTypeReponse(productTypeRepository.save(productTypeEntity)));
        responseDto.setMessage("Tạo loại hàng thành công");
        return responseDto;

    }

    @Override
    public ResponseDto<PTypeResponse> update(PTypeUpdateRequest request) {
        return null;
    }

    @Override
    public ResponseDto<PTypeResponse> delete(Long id) {
        return null;
    }

    @Override
    public ResponseDto<PTypeResponse> detail(Long id) {
        return null;
    }

    @Override
    public ResponseDto<PTypeResponse> changeActive(Long id) {
        return null;
    }
}
