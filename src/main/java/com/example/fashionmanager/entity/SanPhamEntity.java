package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "san_pham_entity")

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SanPhamEntity extends CommonEntity implements Serializable {
    @Column(name = "ma_san_pham")
    private String maSanPham;
    @Column(name = "ten_san_pham")
    private String tenSanPham;
    @ManyToOne
    @JoinColumn(name = "chat_lieu_id")
    private ChatLieuEntity chatLieuEntity;

    @ManyToOne
    @JoinColumn(name = "cau_truc_khuy_id")
    private CauTrucKhuyEntity cauTrucKhuyEntity;
    @ManyToOne
    @JoinColumn(name = "hoa_tiet_id")
    private HoaTietEntity hoaTietEntity;

    @ManyToOne
    @JoinColumn(name = "kieu_dang_id")
    private KieuDangEntity kieuDangEntity;

    @ManyToOne
    @JoinColumn(name = "kieu_det_id")
    private KieuDetEntity kieuDetEntity;

    @ManyToOne
    @JoinColumn(name = "kieu_tui_id")
    private KieuTuiEntity kieuTuiEntity;

    @ManyToOne
    @JoinColumn(name = "lop_lot_id")
    private LopLotEntity lopLotEntity;

    @ManyToOne
    @JoinColumn(name = "ve_ao_id")
    private VeAoEntity veAoEntity;

    @ManyToOne
    @JoinColumn(name = "xe_ta_id")
    private XetaEntity xetaEntity;

    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    private LoaiSanPhamEntity loaiSanPhamEntity;

    @Column(name = "mo_ta_chi_tiet", columnDefinition = "LONGTEXT")
    private String moTaChiTiet;
    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SanPhamDanhMucEntity> sanPhamDanhMucEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HinhAnhEntity> hinhAnhEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BinhLuanEntity> binhLuanEntities = new HashSet<>();
    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ChiTietSanPhamEntity> chiTietSanPhamEntities = new HashSet<>();

}
