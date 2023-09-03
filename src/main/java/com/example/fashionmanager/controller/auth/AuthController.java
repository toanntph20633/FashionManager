package com.example.fashionmanager.controller.auth;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;
import com.example.fashionmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/authenticate")
    public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
        return iUserService.authenticate(loginRequest);
    }

    @GetMapping("/admin")
    public String hihi() throws Exception {
        return "hihihi";
    }
}
