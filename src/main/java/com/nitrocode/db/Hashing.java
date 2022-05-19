package com.nitrocode.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    private static final String pepper = "i use arch btw";

    public static String putSalt() {
        return java.util.UUID.randomUUID().toString();
    }

    public static String getHash(String password) {
        String salt = putSalt();
        return salt + ":" + hash(salt + password + pepper);
    }

    public static boolean checkPassword(String password, String hash) {
        System.out.println("fuck" + hash);

        String[] passwordHashes = hash.split(":");
        String salt = passwordHashes[0];
        hash = passwordHashes[1];

        return hash(salt + password + pepper).equals(hash);
    }

    // hash using sha256
    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (Math.random() * 26 + 97));
        }
        return sb.toString();
    }
}
