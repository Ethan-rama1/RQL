/*
 * RamaLexer.java
 * Author: Ethan Rama
 *
 * Version History: v1.0
 * 6/18/2024: v1.0
 * - Fixed tokenize to skip whitespace
 * - Implemented match/pattern methods
 * - Implemented tokenize method
 * - Created file
 *
 * 6/18/2024
 *
 */

package store;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the RQL lexer
 * @version 1.0
 * @author Ethan Rama
 */
public class RamaLexer {
    /**
     * Pattern for keywords
     */
    private static final String KEYWORD_PATTERN = "\\b(SELECT|FROM|WHERE|INSERT|INTO|VALUES|UPDATE|SET|DELETE|CREATE|DROP|TABLE|DATABASE|NOT NULL|PRIMARY KEY|UNIQUE|CHECK|DEFAULT|BOOL|INT|FLOAT|DATE|CHAR|VARCHAR)\\b";;

    /**
     * Pattern for identifiers
     */
    private static final String IDENTIFIER_PATTERN = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";

    /**
     * Pattern for strings
     */
    private static final String STRING_PATTERN = "'([^']*)'";

    /**
     * Pattern for numbers
     */
    private static final String NUMBER_PATTERN = "-?\\d+(\\.\\d+)?";

    /**
     * Pattern for booleans
     */
    private static final String BOOLEAN_PATTERN = "\\b(TRUE|FALSE)\\b";

    /**
     * Pattern for dates
     */
    private static final String DATE_PATTERN = "'\\d{4}-\\d{2}-\\d{2}'";

    /**
     * Pattern for comparison operators
     */
    private static final String COMP_OPERATOR_PATTERN = "(!=|<>|<=|>=|<|=|>)";

    /**
     * Pattern for logical operators
     */
    private static final String LOG_OPERATOR_PATTERN = "\\b(AND|OR)\\b";

    /**
     * Pattern for commas
     */
    private static final String COMMA_PATTERN = ",";

    /**
     * Pattern for open parenthesis
     */
    private static final String PARENTHESIS_OPEN_PATTERN = "\\(";

    /**
     * Pattern for closed parenthesis
     */
    private static final String PARENTHESIS_CLOSE_PATTERN = "\\)";

    /**
     * Pattern for null
     */
    private static final String NULL_PATTERN = "\\bNULL\\b";

    /**
     * Pattern for whitespace
     */
    private static final String WHITESPACE_PATTERN = "\\s+";

    /**
     * Pattern for EOF
     */
    private static final String TERMINAL_PATTERN = ";";

    /**
     * Input RQL query
     */
    private final String input;

    /**
     * Current position of input string
     */
    private int position;

    /**
     * Constructs a lexer using a query to be tokenized
     * @param input RQL query
     * @author Ethan Rama
     */
    public RamaLexer(String input) {
        this.input = input;
        this.position = 0;
    }

    /**
     * Divides the query into tokens
     * @return list of tokens from query
     * @author Ethan Rama
     */
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) { //TODO: Ensure priority handles all cases correctly
            skipWhitespace();
            if (position >= input.length()) {
                break;
            }
            if (matchPattern(KEYWORD_PATTERN)) {
                tokens.add(new Token(Token.TokenType.KEYWORD, getMatch(KEYWORD_PATTERN)));
            } else if (matchPattern(BOOLEAN_PATTERN)) {
                tokens.add(new Token(Token.TokenType.BOOLEAN_LITERAL, getMatch(BOOLEAN_PATTERN)));
            } else if (matchPattern(DATE_PATTERN)) {
                tokens.add(new Token(Token.TokenType.DATE_LITERAL, getMatch(DATE_PATTERN)));
            } else if (matchPattern(NUMBER_PATTERN)) {
                tokens.add(new Token(Token.TokenType.NUMBER_LITERAL, getMatch(NUMBER_PATTERN)));
            } else if (matchPattern(STRING_PATTERN)) {
                tokens.add(new Token(Token.TokenType.STRING_LITERAL, getMatch(STRING_PATTERN)));
            } else if (matchPattern(COMP_OPERATOR_PATTERN)) {
                tokens.add(new Token(Token.TokenType.COMP_OPERATOR, getMatch(COMP_OPERATOR_PATTERN)));
            } else if (matchPattern(LOG_OPERATOR_PATTERN)) {
                tokens.add(new Token(Token.TokenType.LOG_OPERATOR, getMatch(LOG_OPERATOR_PATTERN)));
            } else if (matchPattern(COMMA_PATTERN)) {
                tokens.add(new Token(Token.TokenType.COMMA, getMatch(COMMA_PATTERN)));
            } else if (matchPattern(PARENTHESIS_OPEN_PATTERN)) {
                tokens.add(new Token(Token.TokenType.PARENTHESIS_OPEN, getMatch(PARENTHESIS_OPEN_PATTERN)));
            } else if (matchPattern(PARENTHESIS_CLOSE_PATTERN)) {
                tokens.add(new Token(Token.TokenType.PARENTHESIS_CLOSE, getMatch(PARENTHESIS_CLOSE_PATTERN)));
            } else if (matchPattern(NULL_PATTERN)) {
                tokens.add(new Token(Token.TokenType.NULL_LITERAL, getMatch(NULL_PATTERN)));
            } else if (matchPattern(TERMINAL_PATTERN)) {
                tokens.add(new Token(Token.TokenType.TERMINAL, getMatch(TERMINAL_PATTERN)));
            } else if (matchPattern(IDENTIFIER_PATTERN)) {
                tokens.add(new Token(Token.TokenType.IDENTIFIER, getMatch(IDENTIFIER_PATTERN)));
            } else {
                throw new RuntimeException("Unexpected character: " + input.charAt(position));
            }
        }

        tokens.add(new Token(Token.TokenType.EOF, ""));
        return tokens;
    }

    private void skipWhitespace() {
        Pattern p = Pattern.compile(WHITESPACE_PATTERN);
        Matcher m = p.matcher(input.substring(position));
        if (m.lookingAt()) {
            position += m.end();
        }
    }

    /**
     * Checks if the pattern matches the token found
     * @param pattern token pattern
     * @return true if token matches pattern, false otherwise
     * @author Ethan Rama
     */
    private boolean matchPattern(String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input.substring(position));
        return m.lookingAt();
    }

    /**
     * Returns the value at the position matching the pattern
     * @param pattern pattern used agianst token
     * @return token value
     * @author Ethan Rama
     */
    private String getMatch(String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input.substring(position));
        if (m.lookingAt()) {
            String match = m.group();
            position += match.length();
            return match;
        }
        return null;
    }

}
