package com.example.fashionmanager.init;

import com.example.fashionmanager.repository.ChatLieuRepository;
import com.example.fashionmanager.repository.HoaTietRepository;
import com.example.fashionmanager.repository.KieuDangRepository;
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
    private final HoaTietRepository hoaTietRepository;
    private final InitDatabaseService initDatabaseService;
    private final ChatLieuRepository chatLieuRepository;
    private final VeAoRepository veAoRepository;
    private final XeTaRepository xeTaRepository;
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
        if (chatLieuRepository.count() == 0) {
            initDatabaseService.initChatLieu();
        }
        if (hoaTietRepository.count() == 0) {
            initDatabaseService.initHoaTiet();
        }
        if (veAoRepository.count() == 0) {
            initDatabaseService.initVeAo();
        }

        if (xeTaRepository.count() == 0) {
            initDatabaseService.initXeTa();
        }
    }
}
