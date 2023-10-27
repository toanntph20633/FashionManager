package com.example.fashionmanager.service.impl.module.phieu_giao_hang.phieugiaohang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlyhang.responst.HangResponse;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangCreateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangListRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangUpdateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.respones.PhieuGiaoHangRespones;
import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.PhieuGiaoHangRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PhieuGiaoHangServiceImpl implements  PhieuGiaoHangService{
    @Autowired
    PhieuGiaoHangRespository phieuGiaoHangRespository;
    @Autowired
    PhieuGiaoHangMapper phieuGiaoHangMapper;


    @Override
    public ListReponseDto<PhieuGiaoHangRespones> getAll( PhieuGiaoHangListRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Specification<PhieuGiaoHangEntity> employeeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //thêm điều kiện tìm kiếm theo mã
            if(request.getMaPhieuGiao() != null && request.getMaPhieuGiao().isEmpty()){
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("maPhieuGiao")), " % " + request.getMaPhieuGiao().toLowerCase() + "%"));
            }
            //thêm điều kiện tìm kiếm theo tên người nhận
            if(request.getTenNguoiNhan() != null){
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("tenNguoiNhan")), " % " + request.getTenNguoiNhan().toLowerCase() + "%"));
            }
            //thêm điều kiện tìm kiếm theo tên người giao
            if(request.getTenNguoiGiao() != null){
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("tenNguoiGiao")), " % " + request.getTenNguoiGiao().toLowerCase() + "%"));
            }

                return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        });
        Page<PhieuGiaoHangEntity> phieuGiaoHangEntities = phieuGiaoHangRespository.findAll(employeeEntitySpecification, pageable);

        List<PhieuGiaoHangRespones> phieuGiaoHangRespones = phieuGiaoHangEntities.getContent()
                .stream()
                .map(o->{
                    PhieuGiaoHangRespones phieuGiaoHangRespones1 = phieuGiaoHangMapper.getPhieuGiaoHangRespones(o);
                    return phieuGiaoHangRespones1;
                }).collect(Collectors.toList());

        ListReponseDto<PhieuGiaoHangRespones> listResponseDto = new ListReponseDto<>();
        listResponseDto.setItems(phieuGiaoHangRespones);
        listResponseDto.setHasNextPage(phieuGiaoHangEntities.hasNext());
        listResponseDto.setHasPreviousPage(phieuGiaoHangEntities.hasPrevious());
        listResponseDto.setPageCount(phieuGiaoHangEntities.getTotalPages());
        listResponseDto.setPageSize(phieuGiaoHangEntities.getSize());
        return listResponseDto;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> save(PhieuGiaoHangCreateRequest request) {
        if(phieuGiaoHangRespository.existsByMaPhieuGiaoAndDeleted(request.getMaPhieuGiao(), false)){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Phiếu giao hàng đã tồn tại"
                    )
            );
        }
        PhieuGiaoHangEntity phieuGiaoHangEntity = phieuGiaoHangMapper.getPhieuGiaoHangEntity(request);
        ResponseDto<PhieuGiaoHangRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(phieuGiaoHangMapper.getPhieuGiaoHangRespones(phieuGiaoHangRespository.save(phieuGiaoHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo phiếu giao hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> update(PhieuGiaoHangUpdateRequest request) {
        if (!phieuGiaoHangRespository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "Phiếu giao hàng có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (phieuGiaoHangRespository.existsByMaPhieuGiaoAndDeletedAndIdNot(request.getMaPhieuGiao(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        PhieuGiaoHangEntity phieuGiaoHangEntity = phieuGiaoHangMapper.getPhieuGiaoHangEntity(request);
        ResponseDto<PhieuGiaoHangRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(phieuGiaoHangMapper.getPhieuGiaoHangRespones(phieuGiaoHangRespository.save(phieuGiaoHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo phiếu giao hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> delete(Long id) {
        PhieuGiaoHangEntity phieuGiaoHangEntity = phieuGiaoHangRespository.findById(id).map(pgh -> {
            pgh.setDeleted(true);
            return pgh;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Phiếu giao hàng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<PhieuGiaoHangRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(phieuGiaoHangMapper.getPhieuGiaoHangRespones(phieuGiaoHangRespository.save(phieuGiaoHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa phiếu giao hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> detail(Long id) {
        PhieuGiaoHangEntity phieuGiaoHangEntity = phieuGiaoHangRespository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Phiếu giao hàng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<PhieuGiaoHangRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(phieuGiaoHangMapper.getPhieuGiaoHangRespones(phieuGiaoHangEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết phiếu giao hàng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> changActive(Long id) {
        PhieuGiaoHangEntity phieuGiaoHangEntity = phieuGiaoHangRespository.findById(id).map(pgh -> {
            pgh.setActive(!pgh.isActive());
            return pgh;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Phiếu giao hàng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<PhieuGiaoHangRespones> responseDto = new ResponseDto<>();
        responseDto.setContent(phieuGiaoHangMapper.getPhieuGiaoHangRespones(phieuGiaoHangRespository.save(phieuGiaoHangEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động phiếu giao hàng thành công");
        return responseDto;
    }
}
