package com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.respones;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhieuGiaoHangRespones {


    private Long id;

    private String tenNguoiNhan;

    private String sdtnguoiNhan;

    private String diaChiChiTiet;

    private BigDecimal soTienThanhToan;

    private String tenNguoiGiao;

    private String sdtNguoiGiao;

    private Date ngayGiao;

    private Date ngayNhan;

    private String trangThai;

    private String ghiChu;

}
