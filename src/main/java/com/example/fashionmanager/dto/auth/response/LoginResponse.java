package com.example.fashionmanager.dto.auth.response;

import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.security.model.CustomUserDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private CustomUserDetail user;

    private String token;
}
