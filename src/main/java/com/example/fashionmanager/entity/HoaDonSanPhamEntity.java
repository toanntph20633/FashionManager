package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "hoa_don_san_pham_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HoaDonSanPhamEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDonEntity hoaDonEntity;

//    @ManyToOne
//    @JoinColumn(name = "chi_tiet_san_pham_id")
//    private ChiTietSanPhamEntity chiTietSanPhamEntity;
    @ManyToOne
    @JoinColumn(name = "dat_may_id")
    private DatMayEntity datMayEntity;

    @Column(name = "gia_ban_dau")
    private BigDecimal giaBanDau;

    @Column(name = "gia_duoc_giam")
    private BigDecimal giaDuocGiam;

    @Column(name = "gia_cuoi_cung")
    private BigDecimal giaCuoiCung;

    @Column(name = "so_luong")
    private BigDecimal soLuong;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "trang_thai")
    @Enumerated(EnumType.STRING)
    private OrderStatus trangThai;

    @Column(name = "hang_dat_may")
    private Boolean hangDatMay;

}
