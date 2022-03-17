package com.example.sping_portfolio.MichaelMinilabs;

import java.io.File;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

import java.time.Duration;
import java.time.Instant;

public class checkPasswordInputStream extends _checkPassword {
    private String password;
    private String methodName = "Stream";
    public checkPasswordInputStream(String password) {
        this.password = password;
    }

    public String getName() {
        return methodName;
    }

    @Override
    public String getWordlistPath() {
        return "src/main/resources/static/wordlists";
    } 

    @Override
    public boolean isInWordlists() {
        File[] lists = getWordlists();

        for(File wordlist : lists) {
            try {
                FileInputStream inputStream = new FileInputStream(wordlist);

                int ch;
                String builder = new String();
                while ((ch = inputStream.read()) != -1) {
                    builder += ((char) ch);
                }

                String[] words = builder.split("\n");
                for(String word : words) {
                    if (word.equals(password)) return true;
                }
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();;
            }
            
        }

        return false;
    }
}
