package com.example.fashionmanager.controller.admin.product_type_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeCreateRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeListRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeUpdateRequest;
import com.example.fashionmanager.dto.product_type_manager.response.ProductTypeRespones;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.IProductTypeService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/product-type-manager")
public class ProductTypeController {

    @Autowired
    private IProductTypeService service;

    @GetMapping("/list")
    public ListReponseDto<ProductTypeRespones> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "active", defaultValue = "true") Boolean active,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code) {
        ProductTypeListRequest request = ProductTypeListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();
        return service.getList(request);
    }

    @PostMapping("/create")
    public ResponseDto<ProductTypeRespones> createProductType(@RequestBody @Valid
                                                                      ProductTypeCreateRequest request,
                                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(
                    ErrorResponse.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(bindingResult.getAllErrors().stream()
                                    .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString()).build()
            );
        }
        return service.save(request);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<ProductTypeRespones> updateProductType(@PathVariable Long id, @RequestBody @Valid
            ProductTypeUpdateRequest request,
                                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
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
        return service.update(request);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<ProductTypeRespones> detail(@PathVariable Long id) {
        return service.details(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<ProductTypeRespones> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/change-active/{id}")
    public ResponseDto<ProductTypeRespones> changeActive(@PathVariable Long id) {
        return service.changeActive(id);
    }
}
