package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.DiscountType;
import com.example.fashionmanager.enums.TrangThaiCTSPEnums;
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

import java.math.BigDecimal;

@Entity
@Table(name = "chi_tiet_san_pham_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ChiTietSanPhamEntity extends CommonEntity {
    @Column(name = "so_luong_ban")
    private Integer soLuongBan;
    @Column(name = "so_luong_tang")
    private Integer soLuongTang;

    @Column(name = "hinh_thuc_giam_gia")
    @Enumerated(EnumType.STRING)
    private DiscountType hinhThucGiamGia; // Hình thức giảm giá( nếu có)
    @Column(name = "gia_tri_duoc_giam")
    private BigDecimal giaTriDuocGiam;// Giá trị được giảm

    @Column(name = "gia_ban_cuoi_cung")
    private BigDecimal giaBanCuoiCung; //Giá cuối cùng
    @Column(name = "so_mi_phu_hop", columnDefinition = "LONGTEXT")
    private String soMiPhuHop;
    @Column(name = "giay_phu_hop", columnDefinition = "LONGTEXT")
    private String giayPhuHop;
    @Column(name = "trang_thai_ctsp")
    private TrangThaiCTSPEnums trangThaiCTSP;

    @ManyToOne
    @JoinColumn(name = "kich_thuoc_san_pham_id")
    private SanPhamKichThuocEntity sanPhamKichThuocEntity;

    @ManyToOne
    @JoinColumn(name = "mau_sac_san_pham_id")
    private SanPhamMauSacEntity sanPhamMauSacEntity;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPhamEntity sanPhamEntity;


}
