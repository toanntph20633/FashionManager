package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "phieu_giao_hang_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PhieuGiaoHangEntity extends CommonEntity implements Serializable {

    @Column(name = "ma_phieu_giao")
    private String maPhieuGiao;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi_chi_tiet")
    private String diaChiChiTiet;

    @Column(name = "so_tien_thanh_toan")
    private BigDecimal soTienThanhToan;

    @Column(name = "ten_nguoi_giao")
    private String tenNguoiGiao;

    @Column(name = "ngay_giao")
    private Date ngayGiao;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "trang_thai")
    private String maPhieuGiao;

    @Column(name = "ghi_chu")
    private String maPhieuGiao;

    @ManyToOne
    @JoinColumn("khach_hang_id")
    private KhachHangEntity khachHangEntity;

    @ManyToOne
    @JoinColumn("hoa_don_id")
    private HoaDonEntity hoaDonEntity;

}
