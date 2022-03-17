package com.example.sping_portfolio.MichaelMinilabs;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.time.Duration;
import java.time.Instant;

public class checkPasswordRecursive extends _checkPassword {
    private String password;
    private String methodName = "Recursive";
    public checkPasswordRecursive(String password) {
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
                Scanner myReader = new Scanner(wordlist);
                boolean temp = lineHasWord(myReader);
                if(temp) {
                    return true;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean lineHasWord(Scanner myReader) {
        if (!myReader.hasNext()) return false;
        if (password.equals(myReader.nextLine())) return true;
        return lineHasWord(myReader);
    }
}
