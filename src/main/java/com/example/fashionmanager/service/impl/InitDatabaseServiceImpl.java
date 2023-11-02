package com.example.fashionmanager.service.impl;

import com.example.fashionmanager.controller.admin.sanpham.quanlykieutui.KieuTuiController;
import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.enums.DiscountType;
import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import com.example.fashionmanager.repository.*;
import com.example.fashionmanager.entity.*;
import com.example.fashionmanager.repository.*;
import com.example.fashionmanager.service.InitDatabaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InitDatabaseServiceImpl implements InitDatabaseService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final KieuDangRepository kieuDangRepository;
    private final CauTrucKhuyRepository cauTrucKhuyRepository;
    private final KieuTuiRepository kieuTuiRepository;
    private final ChatLieuRepository chatLieuRepository;

    private final HoaTietRepository hoaTietRepository;
    private final KieuDetResponsitory kieuDetResponsitory;
    private final MauSacRepository mauSacRepository;
    private final VeAoRepository veAoRepository;

    private final XeTaRepository xeTaRepository;
    private final DotGiamGiaRepository dotGiamGiaRepository;


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
        Set<XetaEntity> xetaEntities = new HashSet<>();
        xetaEntities.add(XetaEntity.builder()
                .tenXeTa("Xẻ tà kép (Double Vent)")
                .moTa("""
                        Loại xẻ tà này có hai xẻ tà bên hông áo, mỗi xẻ ở một bên. Xẻ tà kép thường tạo ra dáng 
                        vẻ thoải mái khi bạn đi hoặc ngồi và giúp áo suit giữ dáng.
                        """)
                .build());
        xetaEntities.add(XetaEntity.builder()
                .tenXeTa("Xẻ tà đơn (Single Vent)")
                .moTa("""
                        Loại xẻ tà này chỉ có một xẻ tà ở giữa lưng áo. Xẻ tà đơn phổ biến trong áo suit cổ điển và 
                        thường tạo ra phong cách truyền thống.
                        """)
                .build());
        xetaEntities.add(XetaEntity.builder()
                .tenXeTa("Không có xẻ tà (No Vent)")
                .moTa("""
                        Một số áo suit, đặc biệt là các bộ suit dự tiệc hoặc thời trang, có thể không có xẻ tà. Áo suit 
                        này thường có dáng ôm sát và phong cách hiện đại.
                        """)
                .build());
        xeTaRepository.saveAll(xetaEntities);
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
        Set<MauSacEntity> mauSacEntities = new HashSet<>();
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#000000")
                .tenMau("Màu Đen (Black)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#808080")
                .tenMau("Màu Xám (Gray)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#000080")
                .tenMau("Màu Xanh Navy (Navy Blue)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#0047AB")
                .tenMau("Màu Xanh Cobalt (Cobalt Blue)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#964B00")
                .tenMau("Màu Nâu (Brown)")
                .build());
        mauSacEntities.add(MauSacEntity.builder()
                .maMau("#36454F")
                .tenMau("Màu Xám Charcoal (Charcoal Gray)")
                .build());
        mauSacRepository.saveAll(mauSacEntities);
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
        Set<VeAoEntity> veAoEntities = new HashSet<>();
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Notch (Notch Lapel)")
                .moTa("""
                        Loại ve này là phổ biến và truyền thống. Nó có hình tam giác hoặc ve đứng ở góc 
                        cạnh của áo blazer hoặc áo vest
                         """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Peak (Peak Lapel)")
                .moTa("""
                        Ve Peak có hình núi cao và được sử dụng để tạo ra một phong cách thanh lịch và sang trọng. 
                        Thường thấy trên áo suit vest và áo blazer đắt tiền.
                         """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Shawl (Shawl Lapel)")
                .moTa("""
                        Ve Shawl là loại ve tròn, mềm mại và thường thấy trên áo tuxedo hoặc áo suit dự tiệc.
                         """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve kép (Double-Breasted)")
                .moTa("""
                        Loại ve này thường đi kèm với áo suit kép nút hoặc áo blazer kép nút. Nó có hai dãy ve và thường 
                        tạo điểm nhấn mạnh vùng ngực.
                         """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Gorge (Gorge)")
                .moTa("""
                        Ve Gorge là vùng gập của áo suit ở phía trên đầu ve áo. Độ cao và rộng của ve Gorge có thể thay 
                        đổi để tạo ra phong cách khác nhau.                       
                        """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Patch (Patch Pockets)")
                .moTa("""
                        Loại ve này không phải là vét được may thành một lớp vải riêng biệt mà thường được may trực tiếp
                         lên áo suit. Nó thường thấy trên áo blazer thể thao hoặc áo vest không chính thống.                       
                        """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Jetted (Jetted Pockets)")
                .moTa("""
                        Ve Jetted là loại ve ẩn bên trong áo suit. Đây là loại ve phổ biến trên áo suit cổ điển.                       
                        """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve Flap (Flap Pockets)")
                .moTa("""
                        Loại ve này là loại ve được che kín bằng nắp. Nó thường thấy trên áo suit thể thao và áo vest.                       
                        """)
                .build());
        veAoEntities.add(VeAoEntity.builder()
                .tenVeAo("Ve besom (Besom Pockets)")
                .moTa("""
                        Ve besom là loại ve phẳng và không có nắp. Nó thường thấy trên áo suit cổ điển và áo suit dự tiệc.                        """)
                .build());
        veAoRepository.saveAll(veAoEntities);
    }

    @Override
    public void initDotGiamGia() {
        List<DotGiamGiaEntity> dotGiamGiaEntities = new ArrayList<>();
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện khai trương")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2023, 11, 20))
                .ngayKetThuc(LocalDate.of(2023, 11, 25))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(DiscountType.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện mùa thu")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2023, 7, 20))
                .ngayKetThuc(LocalDate.of(2023, 7, 25))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(DiscountType.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện mùa đông")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2023, 11, 29))
                .ngayKetThuc(LocalDate.of(2023, 12, 31))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(DiscountType.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaEntities.add(DotGiamGiaEntity.builder()
                .tenDotGiamGia("Sự kện tết")
                .dotGiamGiaStatus(DotGiamGiaStatus.HOAT_DONG)
                .ngayBatDau(LocalDate.of(2024, 2, 10))
                .ngayKetThuc(LocalDate.of(2024, 2, 21))
                .loaiUuDaiDDG(LoaiUuDaiDDG.HOA_DON)
                .loaiGiamGiaHD(DiscountType.PERCENT)
                .giaTriGiamHD(new BigDecimal(20))
                .soTienHoaDonYeuCau(new BigDecimal(0))
                .build());
        dotGiamGiaRepository.saveAll(dotGiamGiaEntities);
    }


}
