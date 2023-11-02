package com.example.fashionmanager.controller.admin.khachhang.quan_ly_hang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangListRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.responst.HangResponse;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.impl.module_khach_hang.hang.HangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/admin/rank-manager")
@CrossOrigin(origins = "*", maxAge = -1)
public class HangController {
    @Autowired
    private HangService hangService;
    @GetMapping("list")
    public ListReponseDto<HangResponse> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "active", defaultValue = "true") Boolean active,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "code", required = false) String code) {
        HangListRequest request = HangListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();
        return hangService.getList(request);
    }

    @PostMapping("create")
    public ResponseDto<HangResponse> create(@RequestBody @Valid HangCreateResquest request, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        return hangService.create(request);
    }


    @GetMapping("detail/{id}")
    public ResponseDto<HangResponse> detail(@PathVariable Long id) {
        return hangService.getByID(id);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDto<HangResponse> delete(@PathVariable Long id) {
        return hangService.delete(id);
    }
    @PutMapping("change-active/{id}")
    public ResponseDto<HangResponse> changeActive(@PathVariable Long id) {
        return hangService.changeActive(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<HangResponse> update(@PathVariable Long id, @RequestBody @Valid HangUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        request.setId(id);
        return hangService.update(request);
    }
}
