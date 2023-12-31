package com.example.fashionmanager.service.impl.auth;

import com.example.fashionmanager.dto.auth.request.LoginRequest;
import com.example.fashionmanager.dto.auth.response.LoginResponse;
import com.example.fashionmanager.dto.user.request.UserCreateRequest;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import com.example.fashionmanager.security.CustomUserDetail;
import com.example.fashionmanager.jwt.IJwtService;
import com.example.fashionmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public void add(UserEntity userEntity, UserRoleEntity userRoleEntity) {
        // Lưu UserEntity vào cơ sở dữ liệu
        userRepository.save(userEntity);

        // Lưu UserRoleEntity vào cơ sở dữ liệu
        userRoleRepository.save(userRoleEntity);
    }

    public String getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        return jwtService.generateToken(Map.of("role", roles), username);
    }

    private UserEntity convertEntity(UserCreateRequest userRequest){
        return UserEntity.builder()
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .userRoleEntities(userRequest.getUserRoleEntities())
                .build();
    }
}



