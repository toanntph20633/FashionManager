package com.example.fashionmanager.dto.producer_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducerUpdateRequest extends ProducerCreateRequest{

    @NotNull(message = "Không để trống id")
    private Long id;
}
