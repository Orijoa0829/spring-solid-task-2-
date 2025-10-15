package com.puzzlix.solid_task.domain.notification;

import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSender implements NotificationSender {

    @Override
    public void send(String message) {
        // 외부 API 연동 문자 발송 처리 (오늘에 도전)
        System.out.println("[SMS 발송]" + message);
    }

    @Override
    public boolean supports(String type) {
        return "SMS".equalsIgnoreCase(type);
    }
}
