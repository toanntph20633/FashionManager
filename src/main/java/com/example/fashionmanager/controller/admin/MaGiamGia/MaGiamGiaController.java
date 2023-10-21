package com.example.fashionmanager.controller.admin.MaGiamGia;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaReponse;
import com.example.fashionmanager.dto.MaGiamGia.Request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.impl.module_phieu_giam_gia.MaGiamGia.MaGiamGiaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/ma-giam-gia-manager")
public class MaGiamGiaController {
    @Autowired
    MaGiamGiaServiceImpl maGiamGiaService;
    @GetMapping("list")
    public ListReponseDto<MaGiamGiaReponse> getList(
                                                    @RequestParam(value = "active", required = false) Boolean active,
                                                    @RequestParam(value = "maVoucher", required = false) String maVoucher,
                                                    @RequestParam(value = "ngayBatDau", required = false) LocalDate ngayBatDau,
                                                    @RequestParam(value = "ngayKetThuc", required = false) LocalDate ngayKetThuc,
                                                    @RequestParam(value = "mota", required = false) String mota,
                                                    @RequestParam(value = "soTienYeuCau", required = false) BigDecimal soTienYeuCau,
                                                    @RequestParam(value = "hinhThuckhuyenmai", required = false) String hinhThuckhuyenmai,
                                                    @RequestParam(value = "hinhThucApDung", required = false) String hinhThucApDung,
                                                    @RequestParam(value = "giaTriDuocGiam", required = false) Integer giaTriDuocGiam,
                                                    @RequestParam(value = "soLuongMaGianGia", required = false) Integer soLuongMaGianGia) {
        ListMaGiamGiaRequest request = ListMaGiamGiaRequest.builder()
                .active(active)
                .maVoucher(maVoucher)
                .ngayBatDau(ngayBatDau)
                .ngayKetThuc(ngayKetThuc)
                .mota(mota)
                .soTienYeuCau(soTienYeuCau)
                .hinhThuckhuyenmai(hinhThuckhuyenmai)
                .hinhThucApDung(hinhThucApDung)
                .giaTriDuocGiam(giaTriDuocGiam)
                .soLuongMaGianGia(soLuongMaGianGia)
                .size(size)
                .page(page)
                .build();

        return maGiamGiaService.getList(request);
    }

    @PostMapping("create")
    public ResponseDto<MaGiamGiaReponse> create(@RequestBody @Valid CreateMaGiamGiaRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        return maGiamGiaService.save(request);
    }

    @GetMapping("detail/{id}")
    public ResponseDto<MaGiamGiaReponse> detail(@PathVariable Long id) {
        return maGiamGiaService.detail(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<MaGiamGiaReponse> update(@PathVariable Long id, @RequestBody @Valid UpdateMaGiamGiaRequest request, BindingResult bindingResult) {


        request.setId(id);
        return maGiamGiaService.update(request);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<MaGiamGiaReponse> delete(@PathVariable Long id) {
        return maGiamGiaService.delete(id);
    }

    @PutMapping("change-active/{id}")
    public ResponseDto<MaGiamGiaReponse> changeActive(@PathVariable Long id) {
        return maGiamGiaService.changeActive(id);
    }
}
