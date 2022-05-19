package com.nitrocode.sessions;

import java.util.HashMap;

import com.nitrocode.db.*;

public class sessionToken {

    // HashMap<Username, SessionToken>
    private static HashMap<String, String> sessionTokens = new HashMap<String, String>();

    public static String get(String username) {
        return sessionTokens.get(username);
    }

    public static void set(String username) throws DBExpection {
        // session token is a hash of 32 random characters and user role
        sessionTokens.put(
            username, 
            Hashing.hash(getSessionToken() + Database.get("user_profiles", username, "role"))
        );
    }

    public static void set(String username, String token) throws DBExpection {
        // session token is a hash of 32 random characters and user role
        sessionTokens.put(
            username, 
            Hashing.hash(token + Database.get("user_profiles", username, "role"))
        );
    }

    public static void remove(String username) {
        sessionTokens.remove(username);
    }

    public static boolean isValid(String username, String sessionToken) {
        return sessionToken.equals(sessionTokens.get(username));
    }

    public static String getSessionToken() {
        return Hashing.randomString(32);
    }
}
