package com.example.fashionmanager.dto.khachhang.quanlykhachhang.request;

import com.example.fashionmanager.entity.HangEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangCreateResquest {
    @NotNull(message = "Không để trống mã khách hàng")
    @NotBlank(message = "Không để trống mã khách hàng")
    private String maKhachHang;
    @NotNull(message = "Không để trống tên khách hàng")
    @NotBlank(message = "Không để trống tên khách hàng")
    private String tenKhachHang;
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại phải là số")
    private String soDienThoai;
    @Email(message = "Email không hợp lệ")
    private String email;
    @Past(message = "Ngày sinh phải trước ngày hiện tại")
    private LocalDate ngaySinh;
    @Min(value = 0, message = "Điểm tích luỹ không được âm")
    private Long diemTichLuy;
    private Long hangId;
}
