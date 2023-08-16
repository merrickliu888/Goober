package com.goober;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Goober {
    public static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: goober <script>");
            System.exit(-1);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    // For running from a file
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        // On error exit
        if (hadError) System.exit(-1);
    }

    // For running interactively (reads line of code from user)
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (true) {
            System.out.print(">>> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);

            // On error don't kill session
            hadError = false;
        }
    }

    // For running actual code from a source
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanSource();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    // Error handling (could delegate to interface)
    public static void error(int line, String message) {
        report(line, "", message);
        hadError = true;
    }

    private static void report(int line, String where, String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message
        );
    }
}
