package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "khach_hang_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KhachHangEntity extends CommonEntity implements Serializable {
    @Column(name = "ten_khach_hang")
    private String tenKhachHang;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Column(name = "thanh_pho")
    private String thanhPho;
    @Column(name = "quan_huyen")
    private String quanHuyen;
    @Column(name = "dia_chi_chi_tiet")
    private String diaChiChiTiet;
    @Column(name = "email")
    private String email;
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;
    @Column(name = "diem_tich_luy")
    private Long diemTichLuy;
    @ManyToOne
    @JoinColumn(name = "hang_id")
    private HangEntity hangEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
