package com.example.fashionmanager.dto.size_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SizeCreateRequest {
    @NotNull(message = "Không để trống sizeCode")
    @NotBlank(message = "Không để trống sizeCode")
    private String sizeCode;
    @NotNull(message = "Không để trống sizeName")
    @NotBlank(message = "Không để trống sizeName")
    private String sizeName;
}
