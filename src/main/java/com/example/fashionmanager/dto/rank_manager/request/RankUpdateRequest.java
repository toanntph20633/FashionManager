package com.example.fashionmanager.dto.rank_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankUpdateRequest extends RankCreateRequest {
    @NotNull(message = "Không để trống id")
    private Long id;
}
