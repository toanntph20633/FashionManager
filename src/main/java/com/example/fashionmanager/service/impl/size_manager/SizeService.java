package com.example.fashionmanager.service.impl.size_manager;

import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeDetailResponse;
import com.example.fashionmanager.entity.SizeEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.SizeRepository;
import com.example.fashionmanager.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService implements ISizeService {

    @Autowired
    private SizeRepository repository;

    @Override
    public List<SizeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public SizeDetailResponse getOne(Long id) throws Exception {

        SizeEntity size = repository.findById(id).orElseThrow(() -> new Exception("Mã kích thước này không tồn tại"));

        return SizeDetailResponse
                .builder()
                .sizeCode(size.getSizeCode())
                .sizeName(size.getSizeName())
                .id(size.getId())
                .build();
    }

    @Override
    public void save(SizeCreateRequest request) {
        SizeEntity sizeEntity = SizeEntity.builder()
                .sizeCode(request.getSizeCode())
                .sizeName(request.getSizeName()).build();
        repository.save(sizeEntity);
    }

    @Override
    public void update(SizeUpdateRequest request) {
        repository.findById(request.getId()).map(size -> {
            size.setSizeCode(request.getSizeCode());
            size.setSizeName(request.getSizeName());
            return repository.save(size);
        }).orElseThrow(() -> FashionManagerException
                .builder()
                .errorResponse(ErrorResponse
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Không tìm thấy kích thước có size id = "+request.getId())
                        .build())
                .build());
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
