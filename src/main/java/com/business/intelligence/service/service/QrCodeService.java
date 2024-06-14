package com.business.intelligence.service.service;

import com.business.intelligence.service.model.QrEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Random;

@Service
public class QrCodeService {

    private static String PASSWORD = "";

    public QrCodeService(){
        buildNewPassword();
    }

    public QrEntity getQrCode(int projectId) {
        final QrEntity qr = new QrEntity();
        qr.setProjectId(projectId);
        qr.setPassword(PASSWORD);
        qr.setTime(Instant.now());

        return qr;
    };

    public String getPassword(){
        return PASSWORD;
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    private void buildNewPassword(){
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        PASSWORD = new String(array, StandardCharsets.UTF_8);
    }
}
