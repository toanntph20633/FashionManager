package com.example.fashionmanager.dto.khachhang.quanlykhachhang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class KhachHangListRequest {
    private Integer page;
    private Integer size;
    private String code;
    private String name;
    private Boolean active = true;
}
