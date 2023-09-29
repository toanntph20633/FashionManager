package com.example.fashionmanager.service.impl.category_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.category_manager.request.CategoryCreateRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryListRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryUpdateRequest;
import com.example.fashionmanager.dto.category_manager.response.CategoryReponse;
import com.example.fashionmanager.dto.color_manager.response.ColorReponse;
import com.example.fashionmanager.entity.CategoryEntity;
import com.example.fashionmanager.entity.ColorEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.category_manager.CategoryMapper;
import com.example.fashionmanager.repository.CategoryRepository;
import com.example.fashionmanager.service.ICategoryService;
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
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ListReponseDto<CategoryReponse> getList(CategoryListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id")
                , new Sort.Order(Sort.Direction.DESC, "categoryEntities"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<CategoryEntity> categoryEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("categoryCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("categoryName"), "%" + request.getName() + "%"));
            }
            if (request.getActive() != null) {
                predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(categoryEntitySpecification, pageable);
        List<CategoryReponse> categoryReponses = categoryEntities.stream().map(category -> categoryMapper.getCategoryReponse(category)).toList();
        ListReponseDto<CategoryReponse> listReponseDto = new ListReponseDto<CategoryReponse>();
        listReponseDto.setItems(categoryReponses);
        listReponseDto.setHasNextPage(categoryEntities.hasNext());
        listReponseDto.setHasPreviousPage(categoryEntities.hasPrevious());
        listReponseDto.setPageCount(categoryEntities.getTotalPages());
        listReponseDto.setPageSize(categoryEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<CategoryReponse> save(CategoryCreateRequest request) {
        if (categoryRepository.existsByCategoryCodeAndDeleted(request.getCategoryCode(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        CategoryEntity categoryEntity = categoryMapper.getCategoryEntity(request);
        ResponseDto<CategoryReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryMapper.getCategoryReponse(categoryRepository.save(categoryEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo danh mục thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<CategoryReponse> update(CategoryUpdateRequest request) {
        if (!categoryRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "danh mục có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (categoryRepository.existsByCategoryCodeAndDeletedAndIdNot(request.getCategoryCode(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        CategoryEntity categoryEntity = categoryMapper.getCategoryEntity(request);
        ResponseDto<CategoryReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryMapper.getCategoryReponse(categoryRepository.save(categoryEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật danh mục thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<CategoryReponse> delete(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).map(color -> {
            color.setDeleted(true);
            return color;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "danh mục có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<CategoryReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryMapper.getCategoryReponse(categoryRepository.save(categoryEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa danh mục thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<CategoryReponse> detail(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "danh mục có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<CategoryReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryMapper.getCategoryReponse(categoryEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị danh mục thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<CategoryReponse> changeActive(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).map(category -> {
            category.setActive(!category.isActive());
            return category;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "danh mục có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<CategoryReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(categoryMapper.getCategoryReponse(categoryRepository.save(categoryEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng tháidanh mục thành công");
        return responseDto;
    }

}
