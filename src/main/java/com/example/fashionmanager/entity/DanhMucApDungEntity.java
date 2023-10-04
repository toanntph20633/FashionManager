package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DanhMucApDungEntity extends CommonEntity {
    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;
    @Column(name = "so_luong_tang")
    private String soLuongTang;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private HangEntity voucherEntity;
    @ManyToOne
    @JoinColumn(name = "danh_muc_id")
    private HangEntity danhMucEntity;
}
