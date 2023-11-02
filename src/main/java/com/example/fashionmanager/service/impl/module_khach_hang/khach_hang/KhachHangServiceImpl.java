package com.example.fashionmanager.service.impl.module_khach_hang.khach_hang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangListRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.responst.KhachHangResponse;
import com.example.fashionmanager.entity.KhachHangEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.HangRepository;
import com.example.fashionmanager.repository.KhachHangRepository;
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
import java.util.stream.Collectors;

@Service
public class KhachHangServiceImpl implements KhachHangService{
    @Autowired
    private KhachHangRepository repository;
    @Autowired
    private KhachHangMapper khachHangMapper;
    @Override
    public ListReponseDto<KhachHangResponse> getList(KhachHangListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<KhachHangEntity> khachHangEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("ma"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("ten"), "%" + request.getName() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<KhachHangEntity> khachHangEntities = repository.findAll(khachHangEntitySpecification, pageable);
        List<KhachHangResponse> khachHangResponses = khachHangEntities.stream()
//                .map(khachHang -> khachHangMapper.getKhachHangResponse(khachHang)).toList();
                .map(khachHang -> {
                    KhachHangResponse response = khachHangMapper.getKhachHangResponse(khachHang);
//                    if(khachHang.getHangEntity() !=null){
//                        response.setTenHang(khachHang.getHangEntity().getTenHang());
//                    }
                    return response;
                }).collect(Collectors.toList());
        ListReponseDto<KhachHangResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(khachHangResponses);
        listReponseDto.setPageIndex(khachHangEntities.getTotalPages());
        listReponseDto.setHasNextPage(khachHangEntities.hasNext());
        listReponseDto.setHasPreviousPage(khachHangEntities.hasPrevious());
        listReponseDto.setPageCount(khachHangEntities.getTotalPages());
        listReponseDto.setPageSize(khachHangEntities.getSize());
        listReponseDto.setTotalItemCount(khachHangEntities.getTotalElements());
        return listReponseDto;
    }
    @Override
    public ResponseDto<KhachHangResponse> getByID(Long id) {
        KhachHangEntity khachHangEntity = repository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Khách hàng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<KhachHangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(khachHangMapper.getKhachHangResponse(khachHangEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết khách hàng thành công");
        return responseDto;
    }
    @Override
    public ResponseDto<KhachHangResponse> create(KhachHangCreateResquest request) {
        if (repository.existsByMaKhachHangAndDeleted(request.getMaKhachHang(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        KhachHangEntity khachHangEntity = khachHangMapper.getKhachHangEntity(request);
        ResponseDto<KhachHangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(khachHangMapper.getKhachHangResponse(repository.save(khachHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo khách hàng thành công");
        return responseDto;
    }
    @Override
    public ResponseDto<KhachHangResponse> update(KhachHangUpdateRequest request) {
        if (!repository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "Khách hàng có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (repository.existsByMaKhachHangAndDeletedAndIdIsNot(request.getMaKhachHang(), false, request.getId())){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        KhachHangEntity khachHangEntity = khachHangMapper.getKhachHangEntity(request);
        ResponseDto<KhachHangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(khachHangMapper.getKhachHangResponse(repository.save(khachHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật khách hàng thành công");
        return responseDto;
    }
    @Override
    public ResponseDto<KhachHangResponse> delete(Long id) {
        KhachHangEntity khachHangEntity = repository.findById(id).map(khachHang -> {
            khachHang.setDeleted(true);
            return khachHang;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Khách hàng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<KhachHangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(khachHangMapper.getKhachHangResponse(repository.save(khachHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa khách hàng thành công");
        return responseDto;
    }
    @Override
    public ResponseDto<KhachHangResponse> changeActive(Long id) {
        KhachHangEntity khachHangEntity = repository.findById(id).map(khachHang -> {
            khachHang.setActive(!khachHang.isActive());
            return khachHang;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Khách hàng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<KhachHangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(khachHangMapper.getKhachHangResponse(repository.save(khachHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động khách hàng thành công");
        return responseDto;
    }
}
