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




    @Column(name = "mo_ta_chi_tiet", columnDefinition = "LONGTEXT")
    private String  moTaChiTiet;

    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    private LoaiSanPhamEntity loaiSanPhamEntity;


    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SanPhamDanhMucEntity> sanPhamDanhMucEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HinhAnhEntity> hinhAnhEntities = new HashSet<>();

    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BinhLuanEntity> danhGiaEntities = new HashSet<>();
    @OneToMany(mappedBy = "sanPhamEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ChiTietSanPhamEntity> chiTietSanPhamEntities = new HashSet<>();

}
