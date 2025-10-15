package com.puzzlix.solid_task.domain.notification;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import com.puzzlix.solid_task.domain.notification.SolapiAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// SolapiAuth 클래스는 별도로 구현되어 있다고 가정합니다. (API Key와 Secret을 이용해 인증 헤더 생성)

@Service
public class SolapiClient {

    private final String apiKey;
    private final String apiSecret;
    private final HttpClient client;

    // 인증 정보는 application.properties 등에서 주입받습니다.
    public SolapiClient(@Value("${solapi.api.key}") String apiKey,
                        @Value("${solapi.api.secret}") String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.client = HttpClient.newHttpClient(); // 재사용 가능한 HttpClient 인스턴스
    }

    // (기존 static 메서드를 인스턴스 메서드로 변경)
    public String sendMessage(String messageJson) throws Exception {
        String authHeader = SolapiAuth.createAuthHeader(this.apiKey, this.apiSecret);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.solapi.com/messages/v4/send-many/detail"))
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(messageJson))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}