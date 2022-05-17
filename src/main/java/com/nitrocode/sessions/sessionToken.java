package com.nitrocode.sessions;

import com.nitrocode.db.Hashing;

public class sessionToken {

    // public static String generateSessionToken(String username) {
    //     return Hashing.hash(username + System.currentTimeMillis());
    // }

    public static void init() {

    }

    public static String getSessionToken() {
        return Hashing.randomString(32);
    }
    
}
