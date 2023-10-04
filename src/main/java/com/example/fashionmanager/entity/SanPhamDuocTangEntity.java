package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.HangEntity;
import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SanPhamDuocTangEntity extends CommonEntity {
    @Column(name = "ten_san_pham_tang")
    private String tenSanPham;
    @Column(name = "so_luong_tang")
    private String soLuongTang;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private HangEntity voucherEntity;
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private HangEntity samPhamEntity;
}
