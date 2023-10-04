package com.example.fashionmanager.service.impl.producer_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.producer_manager.request.ProducerCreateRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerListRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerUpdateRequest;
import com.example.fashionmanager.dto.producer_manager.response.ProducerResponse;
import com.example.fashionmanager.entity.NhaSanXuatEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.produceer_manager.ProducerMapper;
import com.example.fashionmanager.repository.ProducerRepository;
import com.example.fashionmanager.service.IProducerService;
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
public class ProducerService implements IProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProducerMapper producerMapper;

    @Override
    public ListReponseDto<ProducerResponse> getList(ProducerListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC,"dateCreate")
                , new Sort.Order(Sort.Direction.DESC,"id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<NhaSanXuatEntity> producerEntitySpecification = ((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("producerCode"), "%" + request.getCode() + "%"));
            }
            if (StringUtils.isNotBlank(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("producerName"), "%" + request.getName() + "%"));
            }
            if (request.getActive() != null){
                predicates.add(criteriaBuilder.equal(root.get("active"), request.getActive()));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
//            predicates.add(criteriaBuilder.equal(root.get("active"),request.isActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<NhaSanXuatEntity> producerEntities = producerRepository.findAll(producerEntitySpecification,pageable);
        List<ProducerResponse> producerResponses = producerEntities
                .stream()
                .map(producer -> producerMapper.getProducerResponse(producer))
                .toList();
        ListReponseDto<ProducerResponse> listReponseDto = new ListReponseDto<ProducerResponse>();
        listReponseDto.setItems(producerResponses);
        listReponseDto.setHasNextPage(producerEntities.hasNext());
        listReponseDto.setHasPreviousPage(producerEntities.hasPrevious());
        listReponseDto.setPageCount(producerEntities.getTotalPages());
        listReponseDto.setPageSize(producerEntities.getSize());

        return listReponseDto;
    }

    @Override
    public ResponseDto<ProducerResponse> save(ProducerCreateRequest request) {
        if(producerRepository.existsByProducerCodeAndDeleted(request.getProducerCode(),false)){
            throw new FashionManagerException(
                    new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Mã đã tồn tại")
            );
        }
        NhaSanXuatEntity nhaSanXuatEntity = producerMapper.getProducerEntityCreate(request);
        ResponseDto<ProducerResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(producerMapper.getProducerResponse(producerRepository.save(nhaSanXuatEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("tạo nhà sản xuất thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProducerResponse> update(ProducerUpdateRequest request) {
        if(!producerRepository.existsById(request.getId())){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            ,"Nhà sản xuất có id =" + request.getId() + "không tồn tại"
                    )
            );
        }
        if(producerRepository.existsByProducerCodeAndDeletedAndIdNot(request.getProducerCode(),false,request.getId())){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,"Mã code đã tồn tại"
                    )
            );
        }
        NhaSanXuatEntity nhaSanXuatEntity = producerMapper.getProducerEntityUpdate(request);
        ResponseDto<ProducerResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(producerMapper.getProducerResponse(producerRepository.save(nhaSanXuatEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhập nhà sản xuất thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProducerResponse> delete(Long id) {
        NhaSanXuatEntity nhaSanXuatEntity = producerRepository.findById(id).map(producer -> {
            producer.setDeleted(true);
            return producer;
        }).orElseThrow(() -> new FashionManagerException(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND
                        ,"Nhà sản xuất có id = " + id + "Không tồn tại"
                )
        ));
        ResponseDto<ProducerResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(producerMapper.getProducerResponse(producerRepository.save(nhaSanXuatEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xoá nhà sản xuất thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProducerResponse> details(Long id) {
        NhaSanXuatEntity nhaSanXuatEntity = producerRepository.findById(id).orElseThrow(
                ()-> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Nhà sản xuất có id = " + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<ProducerResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(producerMapper.getProducerResponse(nhaSanXuatEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết nhà sản xuất thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<ProducerResponse> changeActive(Long id) {
        NhaSanXuatEntity nhaSanXuatEntity = producerRepository.findById(id).map(producer -> {
            producer.setActive(!producer.isActive());
            return producer;
        }).orElseThrow(
                () -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND,
                                "Nhà sản xuất có id =" + id + "không tồn tại"
                        )
                )
        );
        ResponseDto<ProducerResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(producerMapper.getProducerResponse(producerRepository.save(nhaSanXuatEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động nhà sản xuất thành công");
        return responseDto;
    }
}
