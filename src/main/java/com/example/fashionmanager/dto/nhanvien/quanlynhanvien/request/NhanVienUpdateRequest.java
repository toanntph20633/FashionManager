package com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NhanVienUpdateRequest {
    @NotNull(message = "Không để trống id")
    private Long id;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String tenNhanVien;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String cccd;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    @Pattern(regexp = "^(05|03|09|08)[0-9]*$", message = "Số điện thoại không hợp lệ")
    @Size(min = 10, max = 12, message = "Số điện thoại phải có từ 10 đến 12 số")
    private String soDienThoai;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String diaChi;
    @NotNull(message = "Không để trống ")
    private boolean gioiTinh;

    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String userName;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String password;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String email;
    private boolean active;
}
