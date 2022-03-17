package com.example.sping_portfolio.MichaelMinilabs;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.time.Duration;
import java.time.Instant;

public class checkPasswordFor extends _checkPassword {
    private String password;
    private String methodName = "For";
    public checkPasswordFor(String password) {
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
               for(String word; myReader.hasNextLine();) {
                   word = myReader.nextLine();
                   if(word.equals(this.password)) return true;
               }
               myReader.close();
           } catch (FileNotFoundException e) {
               System.out.println("An error occurred.");
               e.printStackTrace();
           }
       }
       return false;
    }
}
