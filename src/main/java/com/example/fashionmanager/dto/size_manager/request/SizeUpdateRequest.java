package com.example.fashionmanager.dto.size_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeUpdateRequest extends SizeCreateRequest{
    @NotNull(message = "Không để trống id")
    private Long id;
}
