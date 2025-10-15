package com.puzzlix.solid_task.domain.notification;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.HexFormat;
import java.util.UUID;

public class SolapiAuth {

    //	"""HMAC-SHA256 시그니처 생성"""
    public static String generateSignature(String apiSecret, String dateTime, String salt)
            throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256"));
        byte[] hash = mac.doFinal((dateTime + salt).getBytes());
        return HexFormat.of().formatHex(hash);
    }

    //	"""Authorization 헤더 생성"""
    public static String createAuthHeader(String apiKey, String apiSecret) throws Exception {
        String dateTime = Instant.now().toString();
        String salt = UUID.randomUUID().toString().replace("-", "");
        String signature = generateSignature(apiSecret, dateTime, salt);

        return "HMAC-SHA256 apiKey=%s, date=%s, salt=%s, signature=%s"
                .formatted(apiKey, dateTime, salt, signature);
    }
}