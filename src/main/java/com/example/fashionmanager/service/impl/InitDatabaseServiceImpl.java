//package com.example.fashionmanager.service.impl;
//
//import com.example.fashionmanager.entity.RoleEntity;
//import com.example.fashionmanager.entity.UserEntity;
//import com.example.fashionmanager.entity.UserRoleEntity;
//import com.example.fashionmanager.repository.RoleRepository;
//import com.example.fashionmanager.repository.UserRepository;
//import com.example.fashionmanager.repository.UserRoleRepository;
//import com.example.fashionmanager.service.InitDatabaseService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//@RequiredArgsConstructor
//public class InitDatabaseServiceImpl implements InitDatabaseService {
//    private final UserRepository userRepository;
//
//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void initData() {
//        long count = userRepository.count();
//        if (count > 0) {
//            return;
//        }
//        RoleEntity roleEntityAdmin = RoleEntity.builder().roleName("ROLE_ADMIN").build();//nhan vien
//        RoleEntity roleEntitySuperAdmin = RoleEntity.builder().roleName("ROLE_SUPER_ADMIN").build();// quan ly,chu cua hang
//        RoleEntity roleEntityUser = RoleEntity.builder().roleName("ROLE_USER").build();
//        roleRepository.save(roleEntityAdmin);
//        roleRepository.save(roleEntitySuperAdmin);
//        roleRepository.save(roleEntityUser);
//        UserEntity userEntity = UserEntity.builder()
//                .userName("admin@fashion")
//                .email("admin.fashion@gmail.com")
//                .password(passwordEncoder.encode("123@123"))
//                .build();
//        UserRoleEntity userRoleEntityAdmin = UserRoleEntity.builder().roleEntity(roleEntityAdmin).userEntity(userEntity).build();
//        UserRoleEntity userRoleEntitySuperAdmin = UserRoleEntity.builder().roleEntity(roleEntitySuperAdmin).userEntity(userEntity).build();
//        UserRoleEntity userRoleEntityUser = UserRoleEntity.builder().roleEntity(roleEntityUser).userEntity(userEntity).build();
//        Set<UserRoleEntity> userRoleEntities = new HashSet<>();
//        userRoleEntities.add(userRoleEntityAdmin);
//        userRoleEntities.add(userRoleEntitySuperAdmin);
//        userRoleEntities.add(userRoleEntityUser);
//        userEntity.setUserRoleEntities(userRoleEntities);
//        userRepository.save(userEntity);
//
//    }
//
//    private void initKieuDang(){
//
//    }
//    private void initXeTa(){
//
//    }
//    private void initKieuDet(){
//
//    }
//    private void initKieuTui(){
//
//    }
//    private void initDanhMuc(){
//
//    }
//
//    private void initChatLieu(){
//
//    }
//    private void initSanPham(){
//
//    }
//    private void initMauSac(){
//
//    }
//    private void initKichThuoc(){
//
//    }
//    private void initCauTrucKhuy(){
//
//    }
//    private void  initBinhLuan(){
//
//    }
//    private void initLopLot(){
//
//    }
//    private void initHoaTiet(){
//
//    }
//    private void initVeAo(){
//
//    }
//}
