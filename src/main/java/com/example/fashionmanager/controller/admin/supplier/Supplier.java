package com.example.fashionmanager.controller.admin.supplier;


import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.Supplier.request.SuppliearCreatRequest;
import com.example.fashionmanager.dto.Supplier.request.SupplierListRequest;
import com.example.fashionmanager.dto.Supplier.request.SupplierUpdateRequest;
import com.example.fashionmanager.dto.Supplier.response.SupplierResponse;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.ISupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/supplier")
public class Supplier {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping("list")
    public ListReponseDto<SupplierResponse> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "active", defaultValue = "true") Boolean active,
            @RequestParam(value = "name", required = false ) String name,
            @RequestParam(value = "code", required = false) String code){

        SupplierListRequest request = SupplierListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();
        return supplierService.getList(request);
    }

    @PostMapping("creat")
    public ResponseDto<SupplierResponse> create(@RequestBody @Valid SuppliearCreatRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException("ok");
        }
        return supplierService.save(request);
    }

    @GetMapping("detail/{id}")
    public ResponseDto<SupplierResponse> detail(@PathVariable Long id){
        return supplierService.detail(id);
    }

    @PutMapping("update/{id}")
    ResponseDto<SupplierResponse> update(@PathVariable Long id, @RequestBody @Valid SupplierUpdateRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new FashionManagerException(
                    ErrorResponse.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(bindingResult.getAllErrors().stream()
                                    .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                            .build()
            );
        }
        request.setId(id);
        return supplierService.update(request);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<SupplierResponse> delete(@PathVariable Long id){
        return supplierService.delete(id);
    }
    @PutMapping("change-active/{id}")
    public ResponseDto<SupplierResponse> changeActive(@PathVariable Long id){
        return supplierService.changeActive(id);
    }
}
