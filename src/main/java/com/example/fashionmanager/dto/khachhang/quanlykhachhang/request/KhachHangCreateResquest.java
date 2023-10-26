package com.example.fashionmanager.dto.khachhang.quanlykhachhang.request;

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

    private String makhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String email;
    private LocalDate ngaySinh;
    private Long diemTichLuy;
}
