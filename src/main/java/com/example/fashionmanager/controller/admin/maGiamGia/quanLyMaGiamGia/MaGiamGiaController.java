package com.example.fashionmanager.controller.admin.maGiamGia.quanLyMaGiamGia;

import com.example.fashionmanager.dto.maGiamGia.request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.service.impl.module_Ma_Giam_Gia.maGiamGia.MaGiamGiaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/admin/ma-giam-gia")
public class MaGiamGiaController {
    @Autowired
    MaGiamGiaServiceImpl maGiamGiaService;

    @GetMapping("/list")
    public ResponseEntity<?> getListMaGiamGia(
            @RequestParam(name = "maVoucher", required = false) String maVoucher,
            @RequestParam(name = "ngayBatDau", required = false) LocalDate ngayBatDau,
            @RequestParam(name = "ngayKetThuc", required = false) LocalDate ngayKetThuc,
            @RequestParam(name = "giaTriDuocGiam", required = false) BigDecimal giaTriDuocGiam,
            @RequestParam(name = "soLuongMaGianGia", required = false) Integer soLuongMaGianGia,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        ListMaGiamGiaRequest request = ListMaGiamGiaRequest.builder()
                .maVoucher(maVoucher)
                .ngayBatDau(ngayBatDau)
                .ngayKetThuc(ngayKetThuc)
                .giaTriDuocGiam(giaTriDuocGiam)
                .soLuongMaGianGia(soLuongMaGianGia)
                .size(size)
                .page(page)
                .build();
        return maGiamGiaService.getList(request);
    }
@PostMapping("create")
public ResponseEntity<?> create(@RequestBody @Valid CreateMaGiamGiaRequest request, BindingResult bindingResult) {
    return maGiamGiaService.create(request);
}
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateMaGiamGiaRequest request) {

        return maGiamGiaService.update(request);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return maGiamGiaService.delete(id);
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return maGiamGiaService.getById(id);
    }
}
