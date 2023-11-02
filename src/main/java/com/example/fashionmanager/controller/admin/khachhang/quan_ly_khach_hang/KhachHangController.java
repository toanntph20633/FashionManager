package com.example.fashionmanager.controller.admin.khachhang.quan_ly_khach_hang;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.responst.HangResponse;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangListRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.responst.KhachHangResponse;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.impl.module_khach_hang.khach_hang.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/customer-manager")
@CrossOrigin(origins = "*", maxAge = -1)
public class KhachHangController {
    @Autowired
    private KhachHangService service;

    @GetMapping("list")
    public ResponseEntity<ListReponseDto<KhachHangResponse>> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                                     @RequestParam(value = "active", defaultValue = "true") Boolean active,
                                                                     @RequestParam(value = "name", required = false) String name,
                                                                     @RequestParam(value = "code", required = false) String code){
        KhachHangListRequest request = KhachHangListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .size(size)
                .page(page)
                .build();
        ListReponseDto<KhachHangResponse> khachHangList = service.getList(request);
        return ResponseEntity.ok(khachHangList);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDto<KhachHangResponse>> create(@RequestBody @Valid KhachHangCreateResquest request, BindingResult bindingResult) {
                if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        // Gọi service để tạo khách hàng và nhận kết quả trả về
        ResponseDto<KhachHangResponse> responseDto = service.create(request);

        if (responseDto.getStatus() == ResponseStatus.SUCCESS) {
            // Trường hợp thành công, đặt thông báo thành công vào message
            responseDto.setMessage("Thêm thành công");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } else {
            // Trường hợp lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @GetMapping("detail/{id}")
    public ResponseDto<KhachHangResponse> detail(@PathVariable Long id) {
        return service.getByID(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<KhachHangResponse> delete(@PathVariable Long id) {
        return service.delete(id);
    }
    @PutMapping("change-active/{id}")
    public ResponseDto<KhachHangResponse> changeActive(@PathVariable Long id) {
        return service.changeActive(id);
    }
    @PutMapping("update/{id}")
    public ResponseDto<KhachHangResponse> update(@PathVariable Long id, @RequestBody @Valid KhachHangUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        request.setId(id);
        return service.update(request);
    }
}
