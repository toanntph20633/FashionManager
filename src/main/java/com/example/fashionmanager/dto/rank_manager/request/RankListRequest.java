package com.example.fashionmanager.dto.rank_manager.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RankListRequest {
    private int page;

    private int size;

    private String code;

    private String name;

    private boolean active = true;
}
