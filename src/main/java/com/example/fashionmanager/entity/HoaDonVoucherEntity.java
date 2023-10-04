package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.BuyingMethod;
import com.example.fashionmanager.enums.OrderStatus;
import com.example.fashionmanager.enums.PaymentMethod;
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
import java.util.Date;

@Entity
@Table(name = "hoa_don_voucher_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HoaDonVoucherEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private VoucherEntity voucherEntity;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDonEntity hoaDonEntity;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "ngay_lap")
    private Date ngayLap;

    @Column(name = "tong_tien_tam_tinh")
    private BigDecimal tongTienTamTinh;

    @Column(name = "tong_tien_duoc_giam")
    private BigDecimal tongTienDuocGiam;

    @Column(name = "tong_tien_thanh_toan")
    private BigDecimal tongTienThanhToan;

    @Column(name = "hinh_thuc_mua_hang")
    @Enumerated(EnumType.STRING)
    private BuyingMethod hinhThucMuaHang;

    @Column(name = "hinh_thuc_thanh_toan")
    @Enumerated(EnumType.STRING)
    private PaymentMethod hinhThucThanhToan;

    @Column(name = "trang_thai_don_hang")
    @Enumerated(EnumType.STRING)
    private OrderStatus trangThaiDonHang;


}
