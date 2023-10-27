package com.example.fashionmanager.controller.admin.dotgiamgia;

import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaListRequest;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.service.impl.module_san_pham.dot_giam_gia.DotGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("admin/quan-ly-dot-giam-gia")
public class DotGiamGiaController {
    @Autowired
    private DotGiamGiaService dotGiamGiaService;

    @GetMapping("list")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                     @RequestParam(value = "tenDotGiamGia", required = false) String tenDotGiamGia,
                                     @RequestParam(value = "ngayBatDau", required = false) LocalDate ngayBatDau,
                                     @RequestParam(value = "ngayKetThuc", required = false) LocalDate ngayKetThuc,
                                     @RequestParam(value = "status", required = false) DotGiamGiaStatus status) {

        DotGiamGiaListRequest dotGiamGiaListRequest =
                DotGiamGiaListRequest.builder()
                        .dotGiamGiaStatus(status)
                        .ngayKetThuc(ngayKetThuc)
                        .ngayBatDau(ngayBatDau)
                        .page(page)
                        .size(size)
                        .tenDotGiamGia(tenDotGiamGia)
                        .build();
        return dotGiamGiaService.getList(dotGiamGiaListRequest);

    }

}
