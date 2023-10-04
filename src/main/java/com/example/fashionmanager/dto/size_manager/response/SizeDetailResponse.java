package com.example.fashionmanager.dto.size_manager.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SizeDetailResponse {
    private Long id;

    private String sizeCode;

    private String sizeName;
}
