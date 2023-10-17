package com.example.fashionmanager.init;

import com.example.fashionmanager.repository.KieuDangRepository;
import com.example.fashionmanager.repository.KieuDetResponsitory;
import com.example.fashionmanager.repository.UserRepository;
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
    private final KieuDetResponsitory kieuDetResponsitory;

    private final InitDatabaseService initDatabaseService;

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
        if (kieuDetResponsitory.count() == 0) {
            initDatabaseService.initKieuDet();
        }

    }
}
