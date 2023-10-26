package com.example.fashionmanager.dto.khachhang.quanlykhachhang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class KhachHangListRequest {
    private String makhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String email;
    private LocalDate ngaySinh;
    private Long diemTichLuy;
    private Integer page;
    private Integer size;
    private String code;
    private String name;
    private Boolean active = true;
}
