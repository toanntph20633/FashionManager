package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.TrangThaiBinhLuanEnum;
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

@Entity
@Table(name = "binh_luan_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BinhLuanEntity extends CommonEntity implements Serializable {
    @Column(name = "ten_nguoi_binh_luan")
    private String tenNguoiDanhGia;

    @Column(name = "so_diem_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "noi_dung", columnDefinition = "LONGTEXT")
    private String noiDung;

    @Column(name = "trang_thai_binh_luan")
    @Enumerated(EnumType.STRING)
    private TrangThaiBinhLuanEnum trangThaiBinhLuan;
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPhamEntity sanPhamEntity;
}
