package com.example.fashionmanager.service.impl.auth;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import com.example.fashionmanager.security.JwtUtilities;
import com.example.fashionmanager.security.model.CustomUserDetail;
import com.example.fashionmanager.security.service.IJwtService;
import com.example.fashionmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    IJwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public LoginResponse authenticate(LoginRequest request) throws Exception {
        final var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails userDetails = userRepository
                .findByEmail(request.getUserName())
                .map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user name: " + request.getUserName()));
        String token = getToken(userDetails);

        return LoginResponse.builder().user((CustomUserDetail) userDetails).token(token).build();
    }

    public String getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        return jwtService.generateToken(Map.of("role", roles), username);
    }

}



