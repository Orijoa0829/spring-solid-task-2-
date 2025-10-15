package com.puzzlix.solid_task.domain.notification;


import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSender implements NotificationSender {

    private final SolapiClient solapiClient;

    public SmsNotificationSender(SolapiClient solapiClient) {
        this.solapiClient = solapiClient;
    }

    @Override
    public void send(String message) {

        try {
            String messageJson = createSmsMessageJson(message);
            String apiResponse = solapiClient.sendMessage(messageJson);
            System.out.println("솔라피 SMS 발송 응답: " + apiResponse);
        } catch (Exception e) {
            System.err.println("솔라피발송실패");
        }
    }


    private String createSmsMessageJson(String content) {
        return "{\"messages\": [{\"to\": \"01056198677\", \"from\": \"01056198677\", \"text\": \"" + content + "\"}]}";
    }

    @Override
    public boolean supports(String type) {
        return "SMS".equalsIgnoreCase(type);
    }
}