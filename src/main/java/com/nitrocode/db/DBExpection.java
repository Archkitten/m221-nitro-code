package com.nitrocode.db;

public class DBExpection extends Exception {
    private String errorMessage;
    DBExpection(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
