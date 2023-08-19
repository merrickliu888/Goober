package com.goober;

public enum TokenType {
    // Single character tokens
    LEFT_PAREN, RIGHT_PAREN, LEFT_CURLY_BRACE, RIGHT_CURLY_BRACE, COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, ASTERISK,

    // One or two character tokens
    NOT, NOT_EQUAL, EQUAL, EQUAL_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,

    IDENTIFIER,

    // Literals
    STRING, NUMBER,

    // Keywords
    AND, CLASS, ELSE, FALSE, FUNCTION, FOR, IF, NULL, OR, PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,

    EOF
}
