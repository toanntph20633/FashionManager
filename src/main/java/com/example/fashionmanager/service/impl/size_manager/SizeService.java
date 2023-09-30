package com.example.fashionmanager.service.impl.size_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeListRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeResponse;
import com.example.fashionmanager.entity.KichThuocEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.size_manager.SizeMapper;
import com.example.fashionmanager.repository.SizeRepository;
import com.example.fashionmanager.service.ISizeService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
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
public class SizeService implements ISizeService {

    @Autowired
    private SizeRepository repository;

    @Autowired
    private SizeMapper sizeMapper;
    @Override
    public ListReponseDto<SizeResponse> getList(SizeListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC,"dateCreate"),
                new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSizePage(), sort);
        Specification<KichThuocEntity> sizeEntitySpecification = (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(request.getSizeCode())){
                predicates.add(criteriaBuilder.like(root.get("sizeCode"), "%" + request.getSizeCode() + "%"));
            }
            if(StringUtils.isNotBlank(request.getSizeName())){
                predicates.add(criteriaBuilder.like(root.get("sizeName"), "%" + request.getSizeName() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
        Page<KichThuocEntity> sizeEntities = repository.findAll(sizeEntitySpecification, pageable);
        List<SizeResponse> sizeResponses = sizeEntities.stream().map(size -> sizeMapper.getSizeResponse(size)).toList();
        ListReponseDto<SizeResponse> listReponseDto = new ListReponseDto<SizeResponse>();
        listReponseDto.setItems(sizeResponses);
        listReponseDto.setHasNextPage(sizeEntities.hasNext());
        listReponseDto.setHasPreviousPage(sizeEntities.hasPrevious());
        listReponseDto.setPageCount(sizeEntities.getTotalPages());
        listReponseDto.setPageSize(sizeEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<SizeResponse> save(SizeCreateRequest request) {
        if(repository.existsBySizeCodeAndDeleted(request.getSizeCode(), false)){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR, "Mã code đã tồn tại"
                    )
            );
        }
        KichThuocEntity kichThuocEntity = sizeMapper.getSizeEntity(request);
        ResponseDto<SizeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(sizeMapper.getSizeResponse(repository.save(kichThuocEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo size thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<SizeResponse> update(SizeUpdateRequest request) {
        if(!repository.existsById(request.getId())){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND, "Size có id = " + request.getId()
                    )
            );
        }
        if(repository.existsBySizeCodeAndDeletedAndIdNot(request.getSizeCode(),false, request.getId() )){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR, " Mã code đã tồn tại"
                    )
            );
        }
        KichThuocEntity kichThuocEntity = sizeMapper.getSizeEntity(request);
        ResponseDto<SizeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(sizeMapper.getSizeResponse(repository.save(kichThuocEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật size thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<SizeResponse> delete(Long id) {
        KichThuocEntity kichThuocEntity = repository.findById(id).map(size -> {
            size.setDeleted(true);
            return size;
        }).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND, "Size có id = " + id + "không tồn tại"
                )
        ));
        ResponseDto<SizeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(sizeMapper.getSizeResponse(repository.save(kichThuocEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa size thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<SizeResponse> detail(Long id) {
        KichThuocEntity kichThuocEntity = repository.findById(id).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND, "Size có id = " + id + "không tồn tại"
                )
        ));
        ResponseDto<SizeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(sizeMapper.getSizeResponse(kichThuocEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết size thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<SizeResponse> changeActive(Long id) {
        KichThuocEntity kichThuocEntity = repository.findById(id).map(size -> {
            size.setActive(!size.isActive());
            return size;
        }).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND, "Size có id = " + id + "không tồn tại"
                )
        ));
        ResponseDto<SizeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(sizeMapper.getSizeResponse(kichThuocEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động size thành công");
        return responseDto;
    }
}
