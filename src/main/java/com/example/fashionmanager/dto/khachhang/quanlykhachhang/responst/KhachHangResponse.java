package com.example.fashionmanager.dto.khachhang.quanlykhachhang.responst;
import com.example.fashionmanager.entity.HangEntity;
//import com.example.fashionmanager.entity.UserEntity;
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
public class KhachHangResponse {
    private Long id;
    private String maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String email;
    private LocalDate ngaySinh;
    private Long diemTichLuy;
    private String tenHang;
    private Boolean active;
}
