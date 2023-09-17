package com.example.fashionmanager.dto.color_manager.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ColorListRequest {
    private Integer page;

    private Integer size;

    private String code;

    private String name;

    private Boolean active = true;
}
