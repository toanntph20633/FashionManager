package com.example.fashionmanager.dto.rank_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankCreateRequest {
    @NotNull(message = "Không để trống rankCode")
    @NotBlank(message = "Không để trống rankCode")
    private String rankCode;
    @NotNull(message = "Không để trống rankName")
    @NotBlank(message = "Không để trống rankName")
    private String rankName;
}
