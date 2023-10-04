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

@Entity
@Table(name = "dat_may_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DatMayEntity extends CommonEntity implements Serializable {
//    @ManyToOne
//    @JoinColumn(name = "chi_tiet_san_pham_id")
//    private ChiTietSanPhamEntity chiTietSanPhamEntity;
    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "tinh")
    private String tinh;

    @Column(name = "huyen")
    private String huyen;

    @Column(name = "dia_chi_chi_tiet")
    private String diaChiChiTiet;

    @Column(name = "vong_nguc")
    private String vongNguc;

    @Column(name = "vong_eo")
    private String vongEo;

    @Column(name = "do_dai_vai")
    private String doDaiVai;

    @Column(name = "do_dai_canh_tay")
    private String doDaiCanhTay;

    @Column(name = "so_do_bap_tay")
    private String soDoBapTay;

    @Column(name = "vong_nach")
    private String vongNach;

    @Column(name = "vong_bung")
    private String vongBung;

    @Column(name = "vong_co")
    private String vongCo;

    @Column(name = "so_do_co_tay")
    private String soDoCoTay;

    @Column(name = "trang_thai_dat_may")
    private String trangThaiDatMay;
}
