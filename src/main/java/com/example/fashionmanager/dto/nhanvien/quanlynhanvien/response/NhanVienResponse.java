package com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienResponse {
    private Long id;

    private String tenNhanVien;

    private String cccd;

    private String soDienThoai;

    private String diaChi;

    private boolean gioiTinh;

    private Boolean active ;

    private String email;

    private String userName;

    private String password;

}
