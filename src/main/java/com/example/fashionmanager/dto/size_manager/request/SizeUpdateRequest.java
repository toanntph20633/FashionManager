package com.example.fashionmanager.dto.size_manager.request;

import ch.qos.logback.core.property.CanonicalHostNamePropertyDefiner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeUpdateRequest extends SizeCreateRequest{
    private Long id;
}
