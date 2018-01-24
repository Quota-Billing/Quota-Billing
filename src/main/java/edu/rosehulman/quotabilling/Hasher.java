package edu.rosehulman.quotabilling;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

public class Hasher {
  public static String hash(String password, String salt) throws Exception {
    return new String(MessageDigest.getInstance("SHA-256").digest((salt + password).getBytes(StandardCharsets.UTF_8)));
  }

  public static String getRandomSalt() {
    String acceptableChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random random = new SecureRandom();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 50; i++) {
      stringBuilder.append(acceptableChars.charAt(random.nextInt(acceptableChars.length())));
    }
    return stringBuilder.toString();
  }
}
