package com.example.fashionmanager.dto.size_manager.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SizeListRequest {
    private Integer page;

    private Integer sizePage;

    private String sizeCode;

    private String sizeName;

    private Boolean active = true;
}
