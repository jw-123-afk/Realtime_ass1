package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.util.Scanner;

class DirectoryAnalyzer {
    private File directory;

    public DirectoryAnalyzer(String path) {
        this.directory = new File(path);
    }

    // Check if directory is valid
    public boolean isValidDirectory() {
        return directory.exists() && directory.isDirectory();
    }

    // Count .java files
    public int countJavaFiles() {
        int count = 0;
        File[] files = directory.listFiles();

        if (files == null) return 0;

        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".java")) {
                count++;
            }
        }
        return count;
    }

    // Count solved issues
    // Rule: file name contains the word "SOLVED"
    public int countSolvedIssues() {
        int count = 0;
        File[] files = directory.listFiles();

        if (files == null) return 0;

        for (File f : files) {
            if (f.isFile() && f.getName().toUpperCase().contains("SOLVED")) {
                count++;
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Directory Analysis System ===");
        System.out.print("Enter directory path: ");
        String path = input.nextLine();

        DirectoryAnalyzer analyzer = new DirectoryAnalyzer(path);

        // Error handling
        if (!analyzer.isValidDirectory()) {
            System.out.println("Error: Invalid directory path. Please try again.");
            return;
        }

        // Process results
        int javaCount = analyzer.countJavaFiles();
        int issueCount = analyzer.countSolvedIssues();

        // Display output
        System.out.println("=================================");
        System.out.println("Number of Java Files = " + javaCount);
        System.out.println("Number of Issues = " + issueCount);
        System.out.println("=================================");
    }
}
