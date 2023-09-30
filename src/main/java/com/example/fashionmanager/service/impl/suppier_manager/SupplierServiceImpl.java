package com.example.fashionmanager.service.impl.suppier_manager;


import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierCreateRequest;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierListRequest;
import com.example.fashionmanager.dto.supplier_manager.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.supplier_manager.response.SupplierResponse;
import com.example.fashionmanager.entity.NhaCungCapEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.supplier_manager.SupplierMapper;
import com.example.fashionmanager.repository.SupplierRepository;
import com.example.fashionmanager.service.ISupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;


import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Autowired
    private SupplierMapper supplierMapper;


    @Override
    public ListReponseDto<SupplierResponse> getList(SupplierListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<NhaCungCapEntity> supplierEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("suppilerCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("suppilerName"), "%" + request.getName() + "%"));
            }
            if (request.getActive() != null) {
                predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        Page<NhaCungCapEntity> supplierEntities = supplierRepository.findAll(supplierEntitySpecification, pageable);
        List<SupplierResponse> supplierResponses = supplierEntities.stream().map(sup -> supplierMapper.getSupplierResponse(sup)).toList();
        ListReponseDto<SupplierResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(supplierResponses);
        listReponseDto.setHasNextPage(supplierEntities.hasNext());
        listReponseDto.setHasPreviousPage(supplierEntities.hasPrevious());
        listReponseDto.setPageCount(supplierEntities.getTotalPages());
        listReponseDto.setPageSize(supplierResponses.size());
        return listReponseDto;
    }

    @Override
    public ResponseDto<SupplierResponse> save(SupplierCreateRequest request) {
        if (supplierRepository.existsBySuppilerCodeAndDeleted(request.getSuppilerCode(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR, "Mã code đã tồn tại"
                    )
            );
        }

        NhaCungCapEntity nhaCungCapEntity = supplierMapper.getSupplierEntity(request);
        ResponseDto<SupplierResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(supplierMapper.getSupplierResponse(supplierRepository.save(nhaCungCapEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo nhà cung cấp thành công!");
        return responseDto;
    }

    @Override
    public ResponseDto<SupplierResponse> update(SupplierUpdateRequest request) {
        if (!supplierRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND,
                            "Nhà cung cấp có id = " + request.getId() + "không tồn tại!"
                    )
            );
        }

        if (supplierRepository.existsBySuppilerCodeAndDeletedAndIdNot(request.getSuppilerCode(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Mã code đã tồn tại!"
                    )
            );
        }
        NhaCungCapEntity nhaCungCapEntity = supplierMapper.getSupplierEntity(request);
        ResponseDto<SupplierResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(supplierMapper.getSupplierResponse(supplierRepository.save(nhaCungCapEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật nhà cung cấp thành công!");
        return responseDto;
    }

    @Override
    public ResponseDto<SupplierResponse> delete(Long id) {
        NhaCungCapEntity nhaCungCapEntity = supplierRepository.findById(id).map(sup -> {
            sup.setDeleted(true);
            return sup;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Nhà cung cấp có id = " + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<SupplierResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(supplierMapper.getSupplierResponse(supplierRepository.save(nhaCungCapEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa nhà cung cấp thành công!");
        return responseDto;
    }

    @Override
    public ResponseDto<SupplierResponse> detail(Long id) {
        NhaCungCapEntity nhaCungCapEntity = supplierRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Nhà cung cấp có id = " + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<SupplierResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(supplierMapper.getSupplierResponse(supplierRepository.save(nhaCungCapEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị nhà cung cấp thành công!");
        return responseDto;
    }

    @Override
    public ResponseDto<SupplierResponse> changeActive(Long id) {
        NhaCungCapEntity nhaCungCapEntity = supplierRepository.findById(id).map(sup -> {
            sup.setActive(!sup.isActive());
            return sup;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Nhà cung cấp có id = " + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<SupplierResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(supplierMapper.getSupplierResponse(supplierRepository.save(nhaCungCapEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động nhà cung cấp thành công!");
        return responseDto;
    }

}

