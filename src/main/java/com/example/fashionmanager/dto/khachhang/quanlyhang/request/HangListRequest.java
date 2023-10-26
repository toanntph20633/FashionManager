package com.example.fashionmanager.dto.khachhang.quanlyhang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HangListRequest {
    private Integer page;
    private Integer size;
    private String code;
    private String name;
    private boolean active = true;
}
