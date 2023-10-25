package com.example.fashionmanager.service.impl;

import com.example.fashionmanager.controller.admin.sanpham.quanlykieutui.KieuTuiController;
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
    private final KieuTuiRepository kieuTuiRepository;
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
        Set<KieuTuiEntity> kieuTuiEntities = new HashSet<>();
        kieuTuiEntities.add(KieuTuiEntity.builder()
                .tenKieuTui("Flap Pockets (Túi nắp)")
                .moTa("""
                        Đây là kiểu túi phổ biến nhất trên suit jacket. Chúng có nắp hoặc nắp che đi một phần hoặc toàn 
                        bộ túi. Tùy theo phong cách, túi có thể được may vào áo ở bên ngoài (flap pockets nằm ở bên 
                        ngoài) hoặc bên trong (flap pockets nằm ẩn bên trong).
                        """)
                .build());
        kieuTuiEntities.add(KieuTuiEntity.builder()
                .tenKieuTui("Welt Pockets (Túi mắt cáo)")
                .moTa("""
                       Đây là kiểu túi dạng rãnh được may bên trong suit jacket. Chúng thường không có nắp và tạo ra một
                        vẻ gọn gàng và thanh lịch.
                        """)
                .build());
        kieuTuiEntities.add(KieuTuiEntity.builder()
                .tenKieuTui("Ticket Pocket (Túi vé)")
                .moTa("""
                        Túi vé là một túi nhỏ, thường được đặt bên trên một trong túi flap hoặc welt pockets. Ban đầu, 
                        chúng được sử dụng để đựng vé máy bay hoặc vé tàu, nhưng ngày nay chúng thường chỉ mang tính 
                        chất thẩm mỹ.
                        """)
                .build());
        kieuTuiEntities.add(KieuTuiEntity.builder()
                .tenKieuTui("Patch Pockets (Túi gắn ngoài)")
                .moTa("""
                        Đây là túi nằm ở bên ngoài suit jacket và được gắn thường bằng việc may thêm một lớp vải trên 
                        bề mặt áo. Chúng tạo ra một vẻ phóng khoáng và thường xuất hiện trên các suit jacket không phải 
                        dự tiệc hoặc không phải suit cổ điển.
                        """)
                .build());
        kieuTuiEntities.add(KieuTuiEntity.builder()
                .tenKieuTui("Interior Pockets (Túi bên trong)")
                .moTa("""
                        Suit jacket cũng có thể đi kèm với các túi bên trong được thiết kế để đựng điện thoại di động, 
                        ví, bút và các vật dụng cá nhân khác.
                        """)
                .build());
        kieuTuiRepository.saveAll(kieuTuiEntities);
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
