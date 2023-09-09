package com.example.fashionmanager.dto.size_manager.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SizeCreateRequest {
    private String sizeCode;
    private String sizeName; // có sắn 2 cái name với code nhá
}
