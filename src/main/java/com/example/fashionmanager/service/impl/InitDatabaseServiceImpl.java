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
    private final KieuDetResponsitory kieuDetResponsitory;
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
        Set<KieuDetEntity> kieuDetEntities = new HashSet<>();
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("LV2025")
                .moTa("""
                        Đây là kiểu dệt đơn sắc, nghĩa là áo suit được làm từ một màu duy nhất mà không có hoa văn hay 
                        kết cấu khác. Màu sắc phổ biến cho áo suit màu đơn bao gồm xám, đen, xanh navy và xám tro.
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Pinstripe (Áo suit vằn tàu)")
                .moTa("""
                        Loại dệt này có sợi sợi mỏng đứng dọc trên bề mặt áo, tạo ra vẻ nổi bật và sang trọng. Vẻ nổi 
                        bật này có thể tạo thêm chiều cao và gầy dáng cho người mặc
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Herringbone (Áo suit chéo ốc)")
                .moTa("""
                        Kiểu dệt herringbone tạo ra các đường cheo chéo trên bề mặt áo, tương tự như các bộ xếp cổ cáo. 
                        Đây là kiểu dệt phổ biến và mang tính biểu tượng trong thời trang nam.
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Birdseye (Áo suit họa tiết điểm chim)")
                .moTa("""
                        Birdseye là một loại họa tiết nhỏ giọt, tạo ra một mặt đứng đứng giống như điểm chim. Nó thường
                         được thực hiện trên nền màu sáng và tạo ra một vẻ ngoại hình độc đáo.
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Houndstooth (Áo suit họa tiết chó săn)")
                .moTa("""
                        Houndstooth là một loại họa tiết tương tự như các hình chó săn, với các hình vuông nhỏ xen kẽ 
                        với nhau. Nó tạo ra một vẻ ngoại hình phô trương và thường được thực hiện trên nền màu sáng
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Check (Áo suit họa tiết caro)")
                .moTa("""
                        Kiểu dệt caro tạo ra các hình vuông lớn hoặc nhỏ trên bề mặt áo. Kiểu dệt này có thể biến đổi 
                        từ caro nhỏ đến caro lớn và có thể tạo ra một vẻ ngoại hình phong cách và lôi cuốn.
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Tweed (Áo suit tweed)")
                .moTa("""
                        Tweed là một loại vải dày và đầy kết cấu, thường có họa tiết và màu sắc đa dạng. Nó thường được 
                        sử dụng trong các bộ suit dành cho thời tiết lạnh hoặc trong các bộ suit phong cách cổ điển.
                        """)
                .build());
        kieuDetEntities.add(KieuDetEntity.builder()
                .tenKieuDet("Glen Plaid (Áo suit vằn cao cấp)")
                .moTa("""
                        Loại họa tiết này kết hợp giữa các đường vuông và đường kẻ nhỏ, tạo ra một vẻ ngoại hình tinh tế và đẳng cấp.
                        """)
                .build());
        kieuDetResponsitory.saveAll(kieuDetEntities);
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
