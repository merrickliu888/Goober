package com.goober;

public class RuntimeError extends RuntimeException {
    final Token token;

    RuntimeError(Token token, String message) {
        super("Runtime Error: " + message);
        this.token = token;
    }
}
