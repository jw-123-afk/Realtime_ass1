package org.example;

import java.io.File;
import java.util.Scanner;

/**
 * Main application class to handle user interaction and system flow.
 */
public class Main {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("0_0 Directory Analysis System 0_0");

        // 1. Prompt the user
        System.out.print("Halo,can you enter the directory path to analyze: ");
        String pathInput = inputScanner.nextLine();
        System.out.print("Thanks you for your cooperation!\n");

        // 2. Create the analyzer object (OOP Design)
        DirectoryAnalyzer analyzer = new DirectoryAnalyzer(pathInput);

        // 3. Perform analysis and Handle Errors
        if (analyzer.isValidDirectory()) {
            analyzer.analyze();

            // 4. Display results
            System.out.println("\n Q_Q Analysis Results Q_Q");
            System.out.println("Number of Java Files = " + analyzer.getJavaFileCount());
            System.out.println("Number of Issues     = " + analyzer.getIssueCount());
        } else {
            System.err.println("Error: The path provided is invalid or is not a directory.");
        }

        inputScanner.close();
    }
}

/**
 * Helper class responsible for the logic of scanning files
 * and identifying specific file types.
 */
class DirectoryAnalyzer {
    private File directory;
    private int javaFileCount;
    private int issueCount;

    // Constructor
    public DirectoryAnalyzer(String path) {
        this.directory = new File(path);
        this.javaFileCount = 0;
        this.issueCount = 0;
    }

    /**
     * Checks if the provided path is a valid existing directory.
     * @return true if valid, false otherwise.
     */
    public boolean isValidDirectory() {
        return directory.exists() && directory.isDirectory();
    }

    /**
     * Iterates through the directory and counts files based on criteria.
     */
    public void analyze() {
        File[] filesList = directory.listFiles();

        if (filesList != null) {
            for (File file : filesList) {
                // Ensure we are checking files, not sub-directories
                if (file.isFile()) {
                    String filename = file.getName();

                    if (isJavaFile(filename)) {
                        javaFileCount++;

                        // We only check for issues if it is already a Java file
                        if (isSolvedIssue(filename)) {
                            issueCount++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Criteria 1: Checks if file ends with .java
     */
    private boolean isJavaFile(String name) {
        return name.toLowerCase().endsWith(".java");
    }

    /**
     * Criteria 2: Identifying a "Solved Issue".
     * Logic: Checks if the filename contains "Issue" or "Problem".
     */
    private boolean isSolvedIssue(String name) {
        String lowerName = name.toLowerCase();
        return lowerName.contains("issue") || lowerName.contains("problem");
    }

    // Getters for the results
    public int getJavaFileCount() {
        return javaFileCount;
    }

    public int getIssueCount() {
        return issueCount;
    }
}
