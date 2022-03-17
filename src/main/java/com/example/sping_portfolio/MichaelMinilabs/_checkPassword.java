package com.example.sping_portfolio.MichaelMinilabs;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public abstract class _checkPassword {
    public abstract String getWordlistPath();
    public abstract boolean isInWordlists();

    public File[] getWordlists() {
        File directoryPath = new File(getWordlistPath());
        //List of all files and directories
        return directoryPath.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                String name = pathname.getName().toLowerCase();
                return name.endsWith(".txt") && pathname.isFile();
            }
        });
        // System.out.println("List of files and directories in the specified directory:");
        // for(File file : filesList) {
        //     System.out.println("File name: "+file.getName());
        //     System.out.println("File path: "+file.getAbsolutePath());
        //     System.out.println("Size :"+file.getTotalSpace());
        //     System.out.println(" ");
        // }
    }

    public String test() {
        return getWordlistPath();
    }
}
