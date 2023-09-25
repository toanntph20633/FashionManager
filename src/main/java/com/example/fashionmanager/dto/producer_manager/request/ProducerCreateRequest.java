package com.example.fashionmanager.dto.producer_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducerCreateRequest {

    @NotNull(message = "Không để trống producerCode")
    @NotBlank(message = "Không để trống producerCode")
    private String producerCode;

    @NotNull(message = "Không để trống producerName")
    @NotBlank(message = "Không để trống producerName")
    private String producerName;
}
