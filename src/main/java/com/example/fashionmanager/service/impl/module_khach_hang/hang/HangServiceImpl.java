package com.example.fashionmanager.service.impl.module_khach_hang.hang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangListRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.responst.HangResponse;
import com.example.fashionmanager.entity.HangEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.HangRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class HangServiceImpl implements HangService {
    @Autowired
    private HangRepository repository;

    @Autowired
    private HangMapper hangMapper;

    @Override
    public ListReponseDto<HangResponse> getList(HangListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<HangEntity> rankEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("ma"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("ten"), "%" + request.getName() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
//            predicates.add(criteriaBuilder.equal(root.get("active"), request.isActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<HangEntity> rankEntities = repository.findAll(rankEntitySpecification, pageable);
        List<HangResponse> rankReponses = rankEntities.stream().map(rank -> hangMapper.getHangReponse(rank)).toList();
        ListReponseDto<HangResponse> listReponseDto = new ListReponseDto<>();
        listReponseDto.setItems(rankReponses);
        listReponseDto.setPageIndex(rankEntities.getTotalPages());
        listReponseDto.setHasNextPage(rankEntities.hasNext());
        listReponseDto.setHasPreviousPage(rankEntities.hasPrevious());
        listReponseDto.setPageCount(rankEntities.getTotalPages());
        listReponseDto.setPageSize(rankEntities.getSize());
        listReponseDto.setTotalItemCount(rankEntities.getTotalElements());
        return listReponseDto;
    }

    @Override
    public ResponseDto<HangResponse> getByID(Long id) {
        HangEntity rankEntity = repository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<HangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(hangMapper.getHangReponse(rankEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<HangResponse> create(HangCreateResquest request) {
        if (repository.existsByMaHangAndDeleted(request.getMaHang(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        HangEntity rankEntity = hangMapper.getHangEntity(request);
        ResponseDto<HangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(hangMapper.getHangReponse(repository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<HangResponse> update(HangUpdateRequest request) {
        if (!repository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "Thứ hạng có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (repository.existsByMaHangAndDeletedAndIdNot(request.getMaHang(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        HangEntity rankEntity = hangMapper.getHangEntity(request);
        ResponseDto<HangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(hangMapper.getHangReponse(repository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<HangResponse> delete(Long id) {
        HangEntity rankEntity = repository.findById(id).map(rank -> {
            rank.setDeleted(true);
            return rank;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<HangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(hangMapper.getHangReponse(repository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<HangResponse> changeActive(Long id) {
        HangEntity rankEntity = repository.findById(id).map(rank -> {
            rank.setActive(!rank.isActive());
            return rank;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<HangResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(hangMapper.getHangReponse(repository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động thứ hạng thành công");
        return responseDto;
    }

}
