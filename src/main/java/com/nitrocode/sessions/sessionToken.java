package com.nitrocode.sessions;

import java.util.HashMap;

import com.nitrocode.db.DBExpection;
import com.nitrocode.db.Database;
import com.nitrocode.db.Hashing;

public class sessionToken {

    // HashMap<Username, SessionToken>
    private static HashMap<String, String> sessionTokens = 
                                        new HashMap<String, String>();

    public static String getSecret(String username) {
        return sessionTokens.get(username);
    }

    public static String get(String username) {
        try {
            String token = sessionTokens.get(username);
            if(token == null) return null;
            return Hashing.hash(
                token + Database.get("user_profiles", username, "role"));
        } catch(DBExpection e) {
            return null;
        }
    }

    public static String set(String username) {
        // session token is a hash of 32 random characters and user role
        String token = getSessionToken();

        // sessionTokens.put(
        //     username, 
        //     Hashing.hash(
        //         token + Database.get("user_profiles", 
        //         username, "role"
        //     ))
        // );

        sessionTokens.put(
            username, 
            token
        );

        return get(username);

        // session token is made by hash(token + userRole)
    }

    public static String set(String username, String token) {
        // session token is a hash of 32 random characters and user role
        // sessionTokens.put(
        //     username, 
        //     Hashing.hash(
        //         token + Database.get("user_profiles", 
        //         username, "role"
        //     ))
        // );

        sessionTokens.put(
            username, 
            token
        );

        return get(username);
    }

    public static void remove(String username) {
        sessionTokens.remove(username);
    }

    public static boolean compare(String username, String hashedToken) {
        // compare session token with the one in the database
        try { // returns true if the token is correct
            return Hashing.hash(sessionTokens.get(username) + Database.get("user_profiles", 
                username, "role"
            )).equals(hashedToken);
        } catch(DBExpection e) {
            return false;
        }
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
