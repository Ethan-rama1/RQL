/*
 * Token.java
 * Author: Ethan Rama
 *
 * Version History: v1.0
 * 6/18/2024: v1.01
 * - Created file
 *
 * 6/18/2024
 *
 */


package store;

import java.util.Objects;

/**
 * Defines the structure of a token
 * @version 1.0
 * @author Ethan Rama
 */
public class Token {
    /**
     * Defines the types of tokens to be obtained from input query
     */
    public enum TokenType {
        KEYWORD, IDENTIFIER, STRING_LITERAL, NUMBER_LITERAL, BOOLEAN_LITERAL, DATE_LITERAL,
        COMP_OPERATOR, LOG_OPERATOR, COMMA, PARENTHESIS_OPEN, PARENTHESIS_CLOSE, NULL_LITERAL, TERMINAL, EOF
    }

    /**
     * Type of token to be found
     */
    private TokenType type;

    /**
     * String value under the token
     */
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Get the type of token
     * @return token type
     * @author Ethan Rama
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Get the value under the token
     * @return string representing token
     * @author Ethan Rama
     */
    public String getValue() {
        return value;
    }

    /**
     * Compares two Token objects by testing for reflexivity, if
     * the compared object is null, and if the two object's attributes are
     * the same.
     * @param o Some object to be compared with this Token
     * @return result of comparison of Token object with object o
     * @author Ethan Rama
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type && Objects.equals(value, token.value);
    }

    /**
     * Creates a hash of this Token object's attributes.
     * @return The hash of this Token object
     * @author Ethan Rama
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    /**
     * Gets a string representation of a token
     *
     * @return string representation of a token
     * @author Ethan Rama
     */
    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + "'}";
    }
}

