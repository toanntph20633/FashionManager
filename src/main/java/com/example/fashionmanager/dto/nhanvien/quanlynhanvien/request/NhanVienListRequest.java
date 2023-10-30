package com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request;

import com.example.fashionmanager.dto.user.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NhanVienListRequest {
    private int page;

    private int size;

    private String tenNhanVien;

    private String cccd;

    private String soDienThoai;

    private String diaChi;

    private boolean gioiTinh ;

    private UserResponse userResponse;

    private boolean active = true;
}
