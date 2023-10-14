package com.example.fashionmanager.dto.sanpham.quanlykieudang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateKieuDangRequest {
 private String tenKieuDang;
 private String moTa;
}
