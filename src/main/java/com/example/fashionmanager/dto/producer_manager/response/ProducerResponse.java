package com.example.fashionmanager.dto.producer_manager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProducerResponse {

    private Long id;

    private String producerCode;

    private String producerName;

    private Boolean active;
}
