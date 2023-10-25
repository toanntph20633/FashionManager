package com.example.fashionmanager.service.impl;

import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.repository.*;
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
    private final CauTrucKhuyRepository cauTrucKhuyRepository;
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
        Set<CauTrucKhuyEntity> cauTrucKhuyEntities = new HashSet<>();
        cauTrucKhuyEntities.add(CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy("1 Khuy (One-Button)")
                .moTa("""
                        Áo vest có một nút duy nhất ở phía trên giữa. Đây thường là loại khuy phù hợp với áo vest một 
                        hàng cúc (single-breasted) và tạo sự đơn giản và thanh lịch.
                        """)
                .build());
        cauTrucKhuyEntities.add(CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy("2 Khuy (Two-Button)")
                .moTa("""
                        Áo vest có hai nút ở phía trên, với nút trên ở phía trên và nút dưới ở phía dưới. Đây là loại 
                        cấu trúc khuy phổ biến và tạo ra vẻ thanh thoát, phù hợp cho áo vest một hàng cúc.
                        """)
                .build());
        cauTrucKhuyEntities.add(CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy("3 Khuy (Three-Button)")
                .moTa("""
                        Cấu trúc áo vest có ba nút ở phía trên, với hai nút trên cùng một khoảng và một nút phía dưới. 
                        Áo vest ba hàng cúc thường sử dụng cấu trúc này, và nó có thể giúp tạo ra một vẻ nghiêm túc và lịch lãm.
                        """)
                .build());
        cauTrucKhuyEntities.add(CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy("4 Khuy (Four-Button)")
                .moTa("""
                       Loại cấu trúc này có bốn nút ở phía trên áo vest. Nó thường xuất hiện trên các áo vest nhiều hàng 
                       cúc và thể hiện một phong cách sang trọng và độc đáo.
                        """)
                .build());
        cauTrucKhuyEntities.add(CauTrucKhuyEntity.builder()
                .tenCauTrucKhuy("5 Khuy (Double-Breasted)")
                .moTa("""
                       Áo vest kép cúc có hai hàng nút xếp chéo và thường đi kèm với sáu hoặc tám nút, tạo nên một vẻ 
                       lịch lãm và độc đáo.
                        """)
                .build());
        cauTrucKhuyRepository.saveAll(cauTrucKhuyEntities);
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
