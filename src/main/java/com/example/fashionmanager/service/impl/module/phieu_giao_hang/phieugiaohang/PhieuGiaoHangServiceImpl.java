package com.example.fashionmanager.service.impl.module.phieu_giao_hang.phieugiaohang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangCreateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangListRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangUpdateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.respones.PhieuGiaoHangRespones;
import com.example.fashionmanager.entity.PhieuGiaoHangEntity;
import com.example.fashionmanager.repository.PhieuGiaoHangRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class PhieuGiaoHangServiceImpl implements  PhieuGiaoHangService{
    @Autowired
    PhieuGiaoHangRespository phieuGiaoHangRespository;
    @Autowired
    PhieuGiaoHangMapper phieuGiaoHangMapper;


    @Override
    public ListReponseDto<PhieuGiaoHangRespones> getAll(int pageIndex, PhieuGiaoHangListRequest request) {
       int pageSize = 5;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Specification<PhieuGiaoHangEntity> employeeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add((Predicate) criteriaBuilder.isTrue(root.get("active"))); //điều kiện active =

            //thêm điều kiện tìm kiếm theo mã
            if(request.getMaPhieuGiao() != null && request.getMaPhieuGiao().isEmpty()){
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("MaPhieuGiao")), " % " + request.getMaPhieuGiao().toLowerCase() + "%"));
            }
//            if()


                return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        });
        return null;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> save(PhieuGiaoHangCreateRequest request) {
        return null;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> update(PhieuGiaoHangUpdateRequest request) {
        return null;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> delete(Long id) {
        return null;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> detail(Long id) {
        return null;
    }

    @Override
    public ResponseDto<PhieuGiaoHangRespones> changActive(Long id) {
        return null;
    }
}
