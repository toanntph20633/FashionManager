package com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
public class PhieuGiaoHangListRequest {
    private int page;

    private int size;

    private  String maPhieuGiao;

    private String tenNguoiNhan;

    private String sdtnguoiNhan;

    private String tenNguoiGiao;

    private String sdtNguoiGiao;

    private Date ngayGiao;

    private Date ngayNhan;

    private boolean active = true;
}
