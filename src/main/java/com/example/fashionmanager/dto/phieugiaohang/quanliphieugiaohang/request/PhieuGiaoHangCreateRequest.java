package com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Builder
public class PhieuGiaoHangCreateRequest {
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String tenNguoiNhan;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    @Pattern(regexp = "^(05|03|09|08)[0-9]*$", message = "Số điện thoại không hợp lệ")
    @Size(min = 10, max = 12, message = "Số điện thoại phải có từ 10 đến 12 số")
    private String sdtnguoiNhan;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String diaChiChiTiet;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    @Positive(message = "Số tiền phải lớn hơn 0")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Số tiền không hợp lệ")
    private BigDecimal soTienThanhToan;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String tenNguoiGiao;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    @Pattern(regexp = "^(05|03|09|08)[0-9]*$", message = "Số điện thoại không hợp lệ")
    @Size(min = 10, max = 12, message = "Số điện thoại phải có từ 10 đến 12 số")
    private String sdtNguoiGiao;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private Date ngayGiao;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private Date ngayNhan;


    private String ghiChu;

    private String trangThai;
}
