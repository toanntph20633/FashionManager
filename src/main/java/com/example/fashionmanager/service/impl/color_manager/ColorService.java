package com.example.fashionmanager.service.impl.color_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.color_manager.request.ColorCreateRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorListRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorUpdateRequest;
import com.example.fashionmanager.dto.color_manager.response.ColorReponse;
import com.example.fashionmanager.entity.ColorEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.color_manager.ColorMapper;
import com.example.fashionmanager.repository.ColorRepository;
import com.example.fashionmanager.service.IColorService;
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
public class ColorService implements IColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    ColorMapper colorMapper;
    @Override
    public ListReponseDto<ColorReponse> getList(ColorListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<ColorEntity> colorEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("colorCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("colorName"), "%" + request.getName() + "%"));
            }
            if (request.getActive() != null) {
                predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<ColorEntity> colorEntities = colorRepository.findAll(colorEntitySpecification, pageable);
        List<ColorReponse> colorReponses = colorEntities.stream().map(color -> colorMapper.getColorReponse(color)).toList();
        ListReponseDto<ColorReponse> listReponseDto = new ListReponseDto<ColorReponse>();
        listReponseDto.setItems(colorReponses);
        listReponseDto.setHasNextPage(colorEntities.hasNext());
        listReponseDto.setHasPreviousPage(colorEntities.hasPrevious());
        listReponseDto.setPageCount(colorEntities.getTotalPages());
        listReponseDto.setPageSize(colorEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<ColorReponse> save(ColorCreateRequest request) {
        if (colorRepository.existsByColorCodeAndDeleted(request.getColorCode(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        ColorEntity colorEntity = colorMapper.getColorEntity(request);
        ResponseDto<ColorReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(colorMapper.getColorReponse(colorRepository.save(colorEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo màu sắc thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ColorReponse> update(ColorUpdateRequest request) {
        if (!colorRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "màu sắc có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (colorRepository.existsByColorCodeAndDeletedAndIdNot(request.getColorCode(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        ColorEntity colorEntity = colorMapper.getColorEntity(request);
        ResponseDto<ColorReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(colorMapper.getColorReponse(colorRepository.save(colorEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật màu sắc thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ColorReponse> delete(Long id) {
        ColorEntity colorEntity = colorRepository.findById(id).map(color -> {
            color.setDeleted(true);
            return color;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Màu sắc có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<ColorReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(colorMapper.getColorReponse(colorRepository.save(colorEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa màu sắc thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ColorReponse> detail(Long id) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Màu sắc có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<ColorReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(colorMapper.getColorReponse(colorEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị màu sắc thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ColorReponse> changeActive(Long id) {
        ColorEntity colorEntity = colorRepository.findById(id).map(color -> {
            color.setActive(!color.isActive());
            return color;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Màu sắc có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<ColorReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(colorMapper.getColorReponse(colorRepository.save(colorEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái màu sắc thành công");
        return responseDto;
    }
}
