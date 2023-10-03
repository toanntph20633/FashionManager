package com.example.fashionmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SanPhamApDungEntity {
    @Column(name = "ten_san_pham_ap_dung")
    private String tenSanPhamApDung;
    @Column(name = "so_luong_tang")
    private String soLuongTang;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private HangEntity voucherEntity;
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private HangEntity sanPhamEntity;
}
