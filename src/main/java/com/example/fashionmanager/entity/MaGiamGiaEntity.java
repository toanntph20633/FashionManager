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

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "voucher_entity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class MaGiamGiaEntity extends CommonEntity {
    @Column(name = "ma_voucher")
    private String maVoucher;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "mo_ta")
    private String mota;

    @Column(name = "so_tien_yeu_cau")
    private BigDecimal soTienYeuCau;

    @Column(name = "hinh_thuc_khuyen_mai")
    private String hinhThuckhuyenmai;

    @Column(name = "hinh_thuc_ap_dung")
    private String hinhThucApDung;

    @Column(name = "gia_tri_duoc_giam")
    private int giaTriDuocGiam;
    @ManyToOne
    @JoinColumn(name = "hang_id")
    private HangEntity hangEntity;


}
