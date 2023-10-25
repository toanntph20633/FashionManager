package com.example.fashionmanager.init;

import com.example.fashionmanager.repository.LopLotRepository;
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
    private final LopLotRepository lopLotRepository;
    private final InitDatabaseService initDatabaseService;
    @PostConstruct
    private void initDatabase() {
        if (!isInitDatabase) {
            return;
        }

        long userCount = userRepository.count();

        if (userCount > 0) {
            return;
        }

        initDatabaseService.initData();

    }
}
