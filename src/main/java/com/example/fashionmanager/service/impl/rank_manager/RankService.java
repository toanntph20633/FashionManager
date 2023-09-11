package com.example.fashionmanager.service.impl.rank_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankListRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.entity.RankEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.rank_manager.RankMapper;
import com.example.fashionmanager.repository.RankRepository;
import com.example.fashionmanager.service.IRankService;
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
public class RankService implements IRankService {
    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private RankMapper rankMapper;

    @Override
    public ListReponseDto<RankReponse> getList(RankListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<RankEntity> rankEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("rankCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("rankName"), "%" + request.getName() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<RankEntity> rankEntities = rankRepository.findAll(rankEntitySpecification, pageable);
        List<RankReponse> rankReponses = rankEntities.stream().map(rank -> rankMapper.getRankReponse(rank)).toList();
        ListReponseDto<RankReponse> listReponseDto = new ListReponseDto<RankReponse>();
        listReponseDto.setItems(rankReponses);
        listReponseDto.setHasNextPage(rankEntities.hasNext());
        listReponseDto.setHasPreviousPage(rankEntities.hasPrevious());
        listReponseDto.setPageCount(rankEntities.getTotalPages());
        listReponseDto.setPageSize(rankEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<RankReponse> save(RankCreateRequest request) {
        if (rankRepository.existsByRankCodeAndDeleted(request.getRankCode(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        RankEntity rankEntity = rankMapper.getRankEntity(request);
        ResponseDto<RankReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(rankMapper.getRankReponse(rankRepository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<RankReponse> update(RankUpdateRequest request) {
        if (!rankRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "Thứ hạng có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (rankRepository.existsByRankCodeAndDeletedAndIdNot(request.getRankCode(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        RankEntity rankEntity = rankMapper.getRankEntity(request);
        ResponseDto<RankReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(rankMapper.getRankReponse(rankRepository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật thứ hạng thành công");
        return responseDto;

    }

    @Override
    public ResponseDto<RankReponse> delete(Long id) {
        RankEntity rankEntity = rankRepository.findById(id).map(rank -> {
            rank.setDeleted(true);
            return rank;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<RankReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(rankMapper.getRankReponse(rankRepository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<RankReponse> detail(Long id) {
        RankEntity rankEntity = rankRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<RankReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(rankMapper.getRankReponse(rankEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết thứ hạng thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<RankReponse> changeActive(Long id) {
        RankEntity rankEntity = rankRepository.findById(id).map(rank -> {
            rank.setActive(!rank.isActive());
            return rank;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<RankReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(rankMapper.getRankReponse(rankRepository.save(rankEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động thứ hạng thành công");
        return responseDto;
    }
}
