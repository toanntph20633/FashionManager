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
    private final ChatLieuRepository chatLieuRepository;

    private final HoaTietRepository hoaTietRepository;
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
        Set<ChatLieuEntity> chatLieuEntities = new HashSet<>();
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Laine (Wool)")
                .moTa("""
                        Laine là loại chất liệu phổ biến nhất cho áo suit. Nó có nhiều biến thể, bao gồm laine worsted 
                        (mềm, mịn), laine tweed (dày, có kết cấu), và laine flannel (được làm từ sợi mỏng và có cảm nhận
                         mịn). Laine thích hợp cho mọi mùa và có thể tạo ra các bộ suit cổ điển hoặc hiện đại.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Linen")
                .moTa("""
                        Là một loại chất liệu tự nhiên, linen thích hợp cho mùa hè vì nó thoáng mát và hút ẩm tốt. 
                        Tuy nhiên, linen có thể nhăn và nhàu, nên bạn cần phải sử dụng nó một cách cẩn thận.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Cotton")
                .moTa("""
                        Cotton là một chất liệu phổ biến cho áo suit mùa hè. Nó thoáng mát, nhẹ, và dễ chăm sóc. 
                        Cotton có thể có nhiều biến thể, bao gồm chino (mịn, không sáng bóng) và khaki (được xử lý để 
                        sáng bóng hơn).                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Silk (Lụa)")
                .moTa("""
                        Lụa thường được sử dụng cho áo suit cổ điển và trong các bữa tiệc hoặc dịp đặc biệt khác. 
                        Nó có cảm nhận mịn và sáng bóng, tạo ra một vẻ ngoại hình sang trọng.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Mohair")
                .moTa("""
                        Mohair là một loại sợi tạo từ sợi lông của dê Angora. Chất liệu này thường sáng bóng và bóng 
                        loáng, và thích hợp cho bộ suit dành cho mùa hè hoặc các sự kiện quan trọng.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Velvet (Nhung)")
                .moTa("""
                        Velvet là một loại vải mịn và mềm, thường được sử dụng cho áo suit cho các sự kiện hoặc trong 
                        mùa đông. Nó có cảm nhận mượt mà và sang trọng.                                      
                        """)
                .build());
        chatLieuEntities.add(ChatLieuEntity.builder()
                .tenChatLieu("Tweed")
                .moTa("""
                        Tweed là loại vải dày, chứa nhiều kết cấu và thường được sử dụng cho áo suit cổ điển hoặc áo 
                        blazer. Nó thích hợp cho mùa đông và tạo ra vẻ ngoại hình lịch lãm.                                      
                        """)
                .build());
        chatLieuRepository.saveAll(chatLieuEntities);
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
        Set<HoaTietEntity> hoaTietEntities = new HashSet<>();
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Solid Color(Màu đơn)")
                .moTa("""
                        Áo suit một màu là một trong những lựa chọn phổ biến nhất.
                        Áo suit này có màu đơn và không có họa tiết hoặc hoa văn nào.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Herringbone")
                .moTa("""
                        Họa tiết herringbone là một loại mô hình zigzag tạo ra từ việc kết hợp các 
                        dải màu tương phản nhau. Điều này tạo ra một mô hình hình xương cá trên áo.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Pinstripe (Vạt ngang nhỏ)")
                .moTa("""
                        Pinstripe là một họa tiết được tạo ra bằng các dải ngang nhỏ và 
                        thường có khoảng cách đều đặn. Nó thường tạo ra một diện mạo chuyên nghiệp và 
                        thích hợp cho môi trường công sở.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Check (Kẻ ô)")
                .moTa("""
                        Họa tiết kẻ ô là một loại họa tiết có các dải ngang và dọc tạo thành các ô vuông 
                        nhỏ hoặc lớn. Có nhiều loại kẻ ô khác nhau, bao gồm kẻ ô nhỏ (micro check), kẻ ô lớn 
                        (windowpane check), và nhiều họa tiết kẻ khác.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Houndstooth (Họa tiết chó săn)")
                .moTa("""
                        Houndstooth là một họa tiết thường có dạng ô vuông nhỏ
                         tạo nên một mô hình sọc xéo hoặc zigzag. Nó thường xuất hiện trong hai màu tương phản.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Bird's Eye")
                .moTa("""
                        Họa tiết bird's eye tạo ra một mô hình nhỏ, 
                        giống như các điểm đen hoặc màu tương tự trên nền áo.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Glen Plaid (Kẻ sọc nhỏ)")
                .moTa("""
                        Họa tiết glen plaid có các dải sọc mỏng xen kẽ với các dải sọc khác tạo
                         ra một họa tiết phức tạp. Nó thường xuất hiện trong các tông màu tối.
                        """)
                .build());
        hoaTietEntities.add(HoaTietEntity.builder()
                .tenHoaTiet("Prince of Wales Check (Kẻ hoàng gia xứ Wales)")
                .moTa("""
                        Họa tiết này tạo ra một mô hình lớn với các dải sọc và kẻ sọc nhỏ hơn xen kẽ. Nó thường có màu
                         trung tính và thanh lịch.
                        """)
                .build());
        hoaTietRepository.saveAll(hoaTietEntities);
    }

    @Override
    public void initVeAo() {

    }


}
