package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;

public interface IUserService {
    LoginResponse authenticate(LoginRequest request) throws Exception;
}
