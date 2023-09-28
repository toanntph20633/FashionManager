package com.example.fashionmanager.service.impl.product_type_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeCreateRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeListRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeUpdateRequest;
import com.example.fashionmanager.dto.product_type_manager.response.ProductTypeRespones;
import com.example.fashionmanager.entity.ProductTypeEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.product_type_manager.ProductTypeMapper;
import com.example.fashionmanager.repository.ProductTypeRepository;
import com.example.fashionmanager.service.IProductTypeService;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductTypeService implements IProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductTypeMapper productTypeMapper;


    @Override
    public ListReponseDto<ProductTypeRespones> getList(ProductTypeListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id")
        );
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<ProductTypeEntity> productTypeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("productTypeCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("productTypeName"), "%" + request.getName() + "%"));
            }
            if(request.getActive() != null ){
                predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
//            predicates.add(criteriaBuilder.equal(root.get("active"), request.isActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<ProductTypeEntity> productTypeEntities = productTypeRepository.findAll(productTypeEntitySpecification, pageable);
        List<ProductTypeRespones> productTypeRespones = productTypeEntities
                .stream()
                .map(productType -> productTypeMapper.getProductTypeRespones(productType))
                .toList();
        ListReponseDto<ProductTypeRespones> listReponseDto = new ListReponseDto<ProductTypeRespones>();
        listReponseDto.setItems(productTypeRespones);
        listReponseDto.setHasNextPage(productTypeEntities.hasNext());
        listReponseDto.setHasPreviousPage(productTypeEntities.hasPrevious());
        listReponseDto.setPageCount(productTypeEntities.getTotalPages());
        listReponseDto.setPageSize(productTypeEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<ProductTypeRespones> save(ProductTypeCreateRequest request) {
        if(productTypeRepository.existsByProductTypeCodeAndDeleted(request.getProductTypeCode(),false)){
            throw new FashionManagerException(
                    new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Mã đã tồn tại")
            );
        }
        ProductTypeEntity productTypeEntity = productTypeMapper.getProductTypeEntityCreate(request);
        ResponseDto<ProductTypeRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(productTypeMapper.getProductTypeRespones(productTypeRepository.save(productTypeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo loại hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProductTypeRespones> update(ProductTypeUpdateRequest request) {
        if(!productTypeRepository.existsById(request.getId())){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            ,"Loại hàng có id =" + request.getId() + "không tồn tại"
                    )
            );
        }
        if(productTypeRepository.existsByProductTypeCodeAndDeletedAndIdNot(request.getProductTypeCode(),false,request.getId())){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,"Mã code đã tồn tại"
                    )
            );
        }
        ProductTypeEntity productTypeEntity = productTypeMapper.getProductTypeUpdateRequest(request);
        ResponseDto<ProductTypeRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(productTypeMapper.getProductTypeRespones(productTypeRepository.save(productTypeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhập loại hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProductTypeRespones> delete(Long id) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findById(id).map(productTypeEntity1 -> {
            productTypeEntity1.setDeleted(true);
            return productTypeEntity1;
        }).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND
                        ,"Loại hàng có id = " + id + "Không tồn tại"
                )
        ));
        ResponseDto<ProductTypeRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(productTypeMapper.getProductTypeRespones(productTypeRepository.save(productTypeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xoá loại hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProductTypeRespones> details(Long id) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findById(id).orElseThrow(
                ()-> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Loại hàng có id = " + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<ProductTypeRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(productTypeMapper.getProductTypeRespones(productTypeEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết loại hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProductTypeRespones> changeActive(Long id) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findById(id).map(producer -> {
            producer.setActive(!producer.isActive());
            return producer;
        }).orElseThrow(
                () -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Loại hàng có id =" + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<ProductTypeRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(productTypeMapper.getProductTypeRespones(productTypeRepository.save(productTypeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động loại hàng thành công");
        return responseDto;
    }
}
