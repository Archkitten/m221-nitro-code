package com.nitrocode.sessions;

import java.util.HashMap;

import com.nitrocode.db.*;

public class sessionToken {

    // HashMap<Username, SessionToken>
    private static HashMap<String, String> sessionTokens = 
                                        new HashMap<String, String>();

    public static String get(String username) {
        return sessionTokens.get(username);
    }

    public static void set(String username) throws DBExpection {
        // session token is a hash of 32 random characters and user role
        String token = getSessionToken();

        sessionTokens.put(
            username, 
            token
        );

        // session token is made by hash(token + userRole)
    }

    public static void set(String username, String token) throws DBExpection {
        // session token is a hash of 32 random characters and user role
        sessionTokens.put(
            username, 
            Hashing.hash(
                token + Database.get("user_profiles", 
                username, "role"
            ))
        );
    }

    public static void remove(String username) {
        sessionTokens.remove(username);
    }

    public static boolean isValid(
        String username, 
        String sessionTokenHash
    ) {

        // there will only be 2 types of roles
        // admin and user
        if(isUser(username, sessionTokenHash)) return true;
        return isAdmin(username, sessionTokenHash);

    }

    public static boolean isUser(
        String username, 
        String sessionTokenHash
    ) {
        return sessionTokenHash.equals(
            Hashing.hash(
                sessionTokens.get(username) + "user"
            )
        );
    }

    public static boolean isAdmin(
        String username,
        String sessionTokenHash
    ) {
        return sessionTokenHash.equals(
            Hashing.hash(
                sessionTokens.get(username) + "admin"
            )
        );
    }

    public static String getSessionToken() {
        return Hashing.randomString(32);
    }
}
