package com.example.fashionmanager.service.impl;

import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.RoleEntity;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.repository.KieuDangRepository;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import com.example.fashionmanager.service.InitDatabaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InitDatabaseServiceImpl implements InitDatabaseService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final KieuDangRepository kieuDangRepository;

    @Override
    @Transactional
    public void initData() {
        long count = userRepository.count();
        if (count > 0) {
            return;
        }
        RoleEntity roleEntityAdmin = RoleEntity.builder().roleName("ROLE_ADMIN").build();//nhan vien
        RoleEntity roleEntitySuperAdmin = RoleEntity.builder().roleName("ROLE_SUPER_ADMIN").build();// quan ly,chu cua hang
        RoleEntity roleEntityUser = RoleEntity.builder().roleName("ROLE_USER").build();
        roleRepository.save(roleEntityAdmin);
        roleRepository.save(roleEntitySuperAdmin);
        roleRepository.save(roleEntityUser);
        UserEntity userEntity = UserEntity.builder()
                .userName("admin@fashion")
                .email("admin.fashion@gmail.com")
                .password(passwordEncoder.encode("123@123"))
                .build();
        UserRoleEntity userRoleEntityAdmin = UserRoleEntity.builder().roleEntity(roleEntityAdmin).userEntity(userEntity).build();
        UserRoleEntity userRoleEntitySuperAdmin = UserRoleEntity.builder().roleEntity(roleEntitySuperAdmin).userEntity(userEntity).build();
        UserRoleEntity userRoleEntityUser = UserRoleEntity.builder().roleEntity(roleEntityUser).userEntity(userEntity).build();
        Set<UserRoleEntity> userRoleEntities = new HashSet<>();
        userRoleEntities.add(userRoleEntityAdmin);
        userRoleEntities.add(userRoleEntitySuperAdmin);
        userRoleEntities.add(userRoleEntityUser);
        userEntity.setUserRoleEntities(userRoleEntities);
        userRepository.save(userEntity);

    }

    @Override
    @Transactional
    public void initKieuDang() {
        Set<KieuDangEntity> kieuDangEntities = new HashSet<>();
        kieuDangEntities.add(KieuDangEntity.builder()
                .tenKieuDang("2-button Single-Breasted")
                .moTa("""
                        Đây là kiểu dáng phổ biến, với 2 nút phía trước.
                         Nó thích hợp cho hầu hết các dịp và vóc dáng.
                        """)
                .build());
        kieuDangEntities.add(KieuDangEntity.builder()
                .tenKieuDang("3-button Single-Breasted")
                .moTa("""
                        Áo suit 3 nút thường tạo điểm tập trung cao hơn
                         và thích hợp cho người cao vóc.
                        """)
                .build());
        kieuDangEntities.add(KieuDangEntity.builder()
                .tenKieuDang("Double-Breasted Suit")
                .moTa("""
                        Áo suit cổ điển này có hai hàng nút hoặc nhiều hơn.
                         Nó thường mang lại vẻ lịch lãm và thường được mặc
                          trong các tình huống thương mại hoặc quý phái.
                        """)
                .build());
        kieuDangEntities.add(KieuDangEntity.builder()
                .tenKieuDang("Slim Fit Suit")
                .moTa("""
                        Áo suit slim fit có kiểu dáng gọn gàng, ôm sát cơ thể. 
                        Thích hợp cho những người có vóc dáng mảnh mai
                         hoặc muốn tạo ấn tượng thời trang.
                         """)
                .build());
        kieuDangEntities.add(KieuDangEntity.builder()
                .tenKieuDang("Classic Fit Suit")
                .moTa("""
                        Kiểu dáng classic fit thoải mái, không quá ôm sát,
                         và thường dành cho người muốn cảm giác thoải mái
                          và truyền thống.
                         """)
                .build());
        kieuDangRepository.saveAll(kieuDangEntities);
    }

    @Override
    public void initXeTa() {

    }

    @Override
    public void initKieuDet() {

    }

    @Override
    public void initKieuTui() {

    }

    @Override
    public void initDanhMuc() {

    }

    @Override
    public void initChatLieu() {

    }

    @Override
    public void initSanPham() {

    }

    @Override
    public void initMauSac() {

    }

    @Override
    public void initKichThuoc() {

    }

    @Override
    public void initCauTrucKhuy() {

    }

    @Override
    public void initBinhLuan() {

    }

    @Override
    public void initLopLot() {

    }

    @Override
    public void initHoaTiet() {

    }

    @Override
    public void initVeAo() {

    }


}
