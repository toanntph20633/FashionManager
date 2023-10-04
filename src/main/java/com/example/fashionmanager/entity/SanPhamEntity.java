package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.DiscountType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "san_pham_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SanPhamEntity extends CommonEntity implements Serializable {
    @Column(name = "ma_san_pham")
    private String maSanPham;
    @Column(name = "tan_san_pham")
    private String teSanPham;
    @Column(name = "so_luong_ban")
    private Integer soLuongBan;
    @Column(name = "so_luong_tang")
    private Integer soLuongTang;

    @Column(name = "gia_ban_khoi_diem")
    private BigDecimal giaBanKhoiDiem; // giá bán khởi điểm
    @Column(name = "hinh_thuc_giam_gia")
    @Enumerated(EnumType.STRING)
    private DiscountType hinhThucGiamGia; // Hình thức giảm giá
    @Column(name = "gia_tri_duoc_giam")
    private BigDecimal giaTriDuocGiam;// Giá trị được giảm

    @Column(name = "gia_ban_cuoi_cung")
    private BigDecimal giaBanCuoiCung; //Giá cuối cùng

    @Column(name = "cau_truc_khuy", columnDefinition = "LONGTEXT")
    private String cauTrucKhuy;

    @Column(name = "kieu_dang", columnDefinition = "LONGTEXT")
    private String kieuDang;

    @Column(name = "hoa_tiet", columnDefinition = "LONGTEXT")
    private String hoaTiet;
    @Column(name = "chat_lieu", columnDefinition = "LONGTEXT")
    private String chatLieu;

    @Column(name = "kieu_det", columnDefinition = "LONGTEXT")
    private String kieuDet;

    @Column(name = "so_mi_phu_hop", columnDefinition = "LONGTEXT")
    private String soMiPhuHop;
    @Column(name = "giay_phu_hop", columnDefinition = "LONGTEXT")
    private String giayPhuHop;


    @Column(name = "mo_ta_chi_tiet", columnDefinition = "LONGTEXT")
    private String  moTaChiTiet;

    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    private LoaiSanPhamEntity loaiSanPhamEntity;

    @ManyToOne
    @JoinColumn(name = "nha_san_xuat_id")
    private NhaSanXuatEntity nhaSanXuatEntity;

    @ManyToOne
    @JoinColumn(name = "nha_cung_cap_id")
    private NhaCungCapEntity nhaCungCapEntity;

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SanPhamDanhMucEntity> sanPhamDanhMucEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SanPhamMauSacEntity> sanPhamMauSacEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SanPhamKichThuocEntity> sanPhamKichThuocEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HinhAnhEntity> hinhAnhEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DanhGiaEntity> danhGiaEntities = new HashSet<>();


}
