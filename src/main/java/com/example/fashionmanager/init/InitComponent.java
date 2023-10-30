package com.example.fashionmanager.init;

import com.example.fashionmanager.repository.*;
import com.example.fashionmanager.repository.ChatLieuRepository;
import com.example.fashionmanager.repository.DotGiamGiaRepository;
import com.example.fashionmanager.repository.HoaTietRepository;
import com.example.fashionmanager.repository.CauTrucKhuyRepository;
import com.example.fashionmanager.repository.KieuDangRepository;
import com.example.fashionmanager.repository.KieuDetResponsitory;
import com.example.fashionmanager.repository.MauSacRepository;
import com.example.fashionmanager.repository.SanPhamRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.VeAoRepository;
import com.example.fashionmanager.repository.XeTaRepository;
import com.example.fashionmanager.service.InitDatabaseService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitComponent {
    @Value("${fashion.app.init.database}")
    private boolean isInitDatabase;
    private final UserRepository userRepository;
    private final KieuDangRepository kieuDangRepository;
    private final CauTrucKhuyRepository cauTrucKhuyRepository;
    private final LopLotRepository lopLotRepository;
    private final ChatLieuRepository chatLieuRepository;
    private final KieuTuiRepository kieuTuiRepository;
    private final KieuDetResponsitory kieuDetResponsitory;

    private final HoaTietRepository hoaTietRepository;

    private final SanPhamRepository sanPhamRepository;

    private final DotGiamGiaRepository dotGiamGiaRepository;

    private final InitDatabaseService initDatabaseService;
    private final MauSacRepository mauSacRepository;
    private final VeAoRepository veAoRepository;
    private final XeTaRepository xeTaRepository;
    private final DanhMucRepository danhMucRepository;
    @PostConstruct
    private void initDatabase() {
        if (!isInitDatabase) {
            return;
        }
        if (userRepository.count() == 0) {
            initDatabaseService.initData();
        }
        if (kieuDangRepository.count() == 0) {
            initDatabaseService.initKieuDang();
        }
        if (cauTrucKhuyRepository.count() == 0) {
            initDatabaseService.initCauTrucKhuy();
        }
        if (chatLieuRepository.count() == 0) {
            initDatabaseService.initChatLieu();
        }
        if (hoaTietRepository.count() == 0) {
            initDatabaseService.initHoaTiet();
        }
        if (dotGiamGiaRepository.count() == 0){
            initDatabaseService.initDotGiamGia();
        }
        if (kieuDetResponsitory.count() == 0) {
            initDatabaseService.initKieuDet();
        }

        if (mauSacRepository.count() == 0) {
            initDatabaseService.initMauSac();
        }

        if (veAoRepository.count() == 0) {
            initDatabaseService.initVeAo();
        }

        if (xeTaRepository.count() == 0) {
            initDatabaseService.initXeTa();
        }
        if (danhMucRepository.count() == 0) {
            initDatabaseService.initDanhMuc();
        }
        if (kieuTuiRepository.count() == 0) {
            initDatabaseService.initKieuTui();
        }
    }
}
