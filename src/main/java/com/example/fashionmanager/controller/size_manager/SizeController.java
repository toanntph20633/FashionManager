package com.example.fashionmanager.controller.size_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeListRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.dto.size_manager.response.SizeResponse;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.ISizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/size-manager")
public class SizeController {
    @Autowired
    private ISizeService sizeService;

    @GetMapping("list")
    public ListReponseDto<SizeResponse> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "sizePage", defaultValue = "10") int sizePage,
                                                @RequestParam(value = "active", defaultValue = "true") Boolean active,
                                                @RequestParam(value = "sizeName", required = false) String sizeName,
                                                @RequestParam(value = "sizeCode", required = false) String sizeCode){
        SizeListRequest request = SizeListRequest.builder()
                .active(active)
                .sizeCode(sizeCode)
                .sizeName(sizeName)
                .sizePage(sizePage)
                .page(page)
                .build();
        return sizeService.getList(request);
    }

    @PostMapping("create")
    public ResponseDto<SizeResponse> create(@RequestBody @Valid SizeCreateRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new FashionManagerException(
                    ErrorResponse.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString()).build()
            );
        }
        return sizeService.save(request);
    }
    @GetMapping("detail/{id}")
    public ResponseDto<SizeResponse> detail(@PathVariable Long id){
        return sizeService.delete(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<SizeResponse> update(@PathVariable Long id, @RequestBody @Valid SizeUpdateRequest request,
                                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new FashionManagerException(ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString()).build());
        }
        request.setId(id);
        return sizeService.update(request);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDto<SizeResponse> delete(@PathVariable Long id){
        return sizeService.delete(id);
    }

    @PutMapping("change-active/{id}")
    public ResponseDto<SizeResponse> changeActive(@PathVariable Long id){
        return sizeService.changeActive(id);
    }

}
