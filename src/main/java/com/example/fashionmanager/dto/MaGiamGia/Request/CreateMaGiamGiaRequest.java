package com.example.fashionmanager.dto.MaGiamGia.Request;

import com.example.fashionmanager.entity.HangEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
public class CreateMaGiamGiaRequest {
    @NotNull(message = "Không để trống rankName")
    @NotBlank(message = "Không để trống rankName")
    String maVoucher;

    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    @NotNull(message = "Không để trống mô tả")
    @NotBlank(message = "Không để trống Mô tả")
    String mota;
    @NotNull(message = "Không để trống số tiền yêu cầu")
    @NotBlank(message = "Không để trống số tiền yêu cầu")
    BigDecimal soTienYeuCau;
    @NotNull(message = "Không để trống hình thức khuyến mại")
    @NotBlank(message = "Không để trống hình thức khuyến mại")
    String hinhThuckhuyenmai;
    @NotNull(message = "Không để trống hình thức khuyến mại")
    @NotBlank(message = "Không để trống hình thức khuyến mại")
    String hinhThucApDung;
    @NotNull(message = "Không để trống giá trị được giảm")
    @NotBlank(message = "Không để trống giá trị được giảm")
    Integer giaTriDuocGiam;
    @NotNull(message = "Không để trống số lượng mã giảm giá")
    @NotBlank(message = "Không để trống số lượng mã giảm giá")
    Integer soLuongMaGianGia;
//    HangEntity hangEntity;
}
