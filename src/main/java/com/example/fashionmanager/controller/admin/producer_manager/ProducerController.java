package com.example.fashionmanager.controller.admin.producer_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.producer_manager.request.ProducerCreateRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerListRequest;
import com.example.fashionmanager.dto.producer_manager.request.ProducerUpdateRequest;
import com.example.fashionmanager.dto.producer_manager.response.ProducerResponse;
import com.example.fashionmanager.dto.rank_manager.request.RankListRequest;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.IProducerService;
import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.mutation.spi.BindingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/producer-manager")
public class ProducerController {
    @Autowired
    private IProducerService producerService;

    @GetMapping("/list")
    public ListReponseDto<ProducerResponse> getList(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "active",defaultValue = "true") Boolean active,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "code",required = false) String code){
        ProducerListRequest request = ProducerListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();
        return producerService.getList(request);
    }

    @PostMapping("/create")
    public ResponseDto<ProducerResponse> create(
            @RequestBody @Valid ProducerCreateRequest request, BindingResult bindingResult
            ){
        if(bindingResult.hasErrors()){
            throw new FashionManagerException(
                    ErrorResponse.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(bindingResult.getAllErrors().stream()
                                    .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString()).build()
            );
        }
        return producerService.save(request);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<ProducerResponse> update(
            @PathVariable Long id, @RequestBody @Valid ProducerUpdateRequest request, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            throw new FashionManagerException(
                    ErrorResponse
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(
                                    bindingResult.getAllErrors().stream()
                                    .map(o -> o.getDefaultMessage())
                                    .collect(Collectors.toList()).toString()
                            )
                            .build()
            );
        }
        request.setId(id);
        return producerService.update(request);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<ProducerResponse> detail(@PathVariable Long id) {
        return producerService.details(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<ProducerResponse> delete(@PathVariable Long id){
        return producerService.delete(id);
    }

    @PutMapping("/change-active/{id}")
    public ResponseDto<ProducerResponse> changeActive(@PathVariable Long id){
        return producerService.changeActive(id);
    }
}
