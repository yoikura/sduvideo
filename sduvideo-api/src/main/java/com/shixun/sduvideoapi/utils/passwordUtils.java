package com.shixun.sduvideoapi.utils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class passwordUtils {
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;

    public static boolean verifyPassword(String inputPassword, String encryptedPassword, String salt) {
        String hashedInputPassword = hashPassword(inputPassword, salt);
        return hashedInputPassword.equals(encryptedPassword);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

            // 将盐值和密码组合
            String combinedString = salt + password;

            byte[] hashedBytes = md.digest(combinedString.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 处理算法不支持的异常
            e.printStackTrace();
        }

        return null;
    }

    public static String generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[length];
        random.nextBytes(saltBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
