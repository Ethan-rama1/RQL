/*
 * StoreTests.java
 * Author: Ethan Rama
 *
 * Version History: v1.01
 * 6/18/2024: v1.01
 * - Created token tests
 * 6/17/2024: v1.0
 * - Created file
 *
 * 6/18/2024
 *
 */

import org.junit.jupiter.api.*;
import store.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Store Tests")
public class StoreTests {
    private static final List<Token> KEY_TOKENS = new ArrayList<>();
    private static final List<Token> IDEN_TOKENS = new ArrayList<>();
    private static final List<Token> STR_TOKENS = new ArrayList<>();
    private static final List<Token> NUM_TOKENS = new ArrayList<>();
    private static final List<Token> BOOL_TOKENS = new ArrayList<>();
    private static final List<Token> DATE_TOKENS = new ArrayList<>();
    private static final List<Token> COMP_TOKENS = new ArrayList<>();
    private static final List<Token> LOG_TOKENS = new ArrayList<>();
    private static final Token COMMA_TOKEN = new Token(Token.TokenType.COMMA, ",");
    private static final Token P_OPEN_TOKEN = new Token(Token.TokenType.PARENTHESIS_OPEN, "(");
    private static final Token P_CLOSE_TOKEN = new Token(Token.TokenType.PARENTHESIS_CLOSE, ")");
    private static final Token NULL_TOKEN = new Token(Token.TokenType.NULL_LITERAL, "NULL");
    private static final Token TERMINAL_TOKEN = new Token(Token.TokenType.TERMINAL, ";");
    private static final Token EOF_TOKEN = new Token(Token.TokenType.EOF, "");
    private static final String[] KEYS = {"SELECT", "FROM", "WHERE", "INSERT", "INTO", "VALUES", "UPDATE", "SET", "DELETE", "CREATE", "DROP", "TABLE", "DATABASE", "NOT NULL", "PRIMARY KEY", "UNIQUE", "CHECK", "DEFAULT", "BOOL", "INT", "FLOAT", "DATE", "CHAR", "VARCHAR"};
    private static final String[] IDENS = {"testcase", "_a_b_c", "abc123", "z__z", "____"};
    private static final String[] STRS = {"'test_string'", "'1str2str3'", "'65'", "'-_-'", "'l'", "'[something]'"};
    private static final String[] NUMS = {"1", "0", "0.0", "-1", "1.00000001", "-0.0000001"};
    private static final String[] BOOLS = {"TRUE", "FALSE"};
    private static final String[] DATES = {"'1492-02-09'", "'0001-01-01'", "'9999-12-31'", "'2024-06-18'", "'1234-08-24'"};
    private static final String[] COMPS = {">=", "<=", "!=", "<>", "<", "=", ">"};
    private static final String[] LOGS = {"AND", "OR"};

    // SELECT attr1 FROM table;
    private static final Token[] test1 = {
            new Token(Token.TokenType.KEYWORD, "SELECT"),
            new Token(Token.TokenType.IDENTIFIER, "attr1"),
            new Token(Token.TokenType.KEYWORD, "FROM"),
            new Token(Token.TokenType.IDENTIFIER, "table"),
            new Token(Token.TokenType.TERMINAL, ";"),
            new Token(Token.TokenType.EOF, ""),
    };

    // CREATE TABLE t1 (id INT PRIMARY KEY, name VARCHAR(255) NOT NULL);
    private static final Token[] test2 = {
            new Token(Token.TokenType.KEYWORD, "CREATE"),
            new Token(Token.TokenType.KEYWORD, "TABLE"),
            new Token(Token.TokenType.IDENTIFIER, "t1"),
            new Token(Token.TokenType.PARENTHESIS_OPEN, "("),
            new Token(Token.TokenType.IDENTIFIER, "id"),
            new Token(Token.TokenType.KEYWORD, "INT"),
            new Token(Token.TokenType.KEYWORD, "PRIMARY KEY"),
            new Token(Token.TokenType.COMMA, ","),
            new Token(Token.TokenType.IDENTIFIER, "name"),
            new Token(Token.TokenType.KEYWORD, "VARCHAR"),
            new Token(Token.TokenType.PARENTHESIS_OPEN, "("),
            new Token(Token.TokenType.NUMBER_LITERAL, "255"),
            new Token(Token.TokenType.PARENTHESIS_CLOSE, ")"),
            new Token(Token.TokenType.KEYWORD, "NOT NULL"),
            new Token(Token.TokenType.PARENTHESIS_CLOSE, ")"),
            new Token(Token.TokenType.TERMINAL, ";"),
            new Token(Token.TokenType.EOF, ""),
    };

    // INSERT INTO table (name, age) VALUES ('ethan_rama', 22);
    private static final Token[] test3 = {
            new Token(Token.TokenType.KEYWORD, "INSERT"),
            new Token(Token.TokenType.KEYWORD, "INTO"),
            new Token(Token.TokenType.IDENTIFIER, "table"),
            new Token(Token.TokenType.PARENTHESIS_OPEN, "("),
            new Token(Token.TokenType.IDENTIFIER, "name"),
            new Token(Token.TokenType.COMMA, ","),
            new Token(Token.TokenType.IDENTIFIER, "age"),
            new Token(Token.TokenType.PARENTHESIS_CLOSE, ")"),
            new Token(Token.TokenType.KEYWORD, "VALUES"),
            new Token(Token.TokenType.PARENTHESIS_OPEN, "("),
            new Token(Token.TokenType.STRING_LITERAL, "'ethan_rama'"),
            new Token(Token.TokenType.COMMA, ","),
            new Token(Token.TokenType.NUMBER_LITERAL, "22"),
            new Token(Token.TokenType.PARENTHESIS_CLOSE, ")"),
            new Token(Token.TokenType.TERMINAL, ";"),
            new Token(Token.TokenType.EOF, ""),
    };

    // UPDATE t1 SET name = 'ethan', age = 23 WHERE dob = '2002-01-29';
    private static final Token[] test4 = {
            new Token(Token.TokenType.KEYWORD, "UPDATE"),
            new Token(Token.TokenType.IDENTIFIER, "t1"),
            new Token(Token.TokenType.KEYWORD, "SET"),
            new Token(Token.TokenType.IDENTIFIER, "name"),
            new Token(Token.TokenType.COMP_OPERATOR, "="),
            new Token(Token.TokenType.STRING_LITERAL, "'ethan'"),
            new Token(Token.TokenType.COMMA, ","),
            new Token(Token.TokenType.IDENTIFIER, "age"),
            new Token(Token.TokenType.COMP_OPERATOR, "="),
            new Token(Token.TokenType.NUMBER_LITERAL, "23"),
            new Token(Token.TokenType.KEYWORD, "WHERE"),
            new Token(Token.TokenType.IDENTIFIER, "dob"),
            new Token(Token.TokenType.COMP_OPERATOR, "="),
            new Token(Token.TokenType.DATE_LITERAL, "'2002-01-29'"),
            new Token(Token.TokenType.TERMINAL, ";"),
            new Token(Token.TokenType.EOF, ""),
    };

    static {
        try {
            for (String s : KEYS) {
                Token t = new Token(Token.TokenType.KEYWORD, s);
                KEY_TOKENS.add(t);
            }
            for (String s : IDENS) {
                Token t = new Token(Token.TokenType.IDENTIFIER, s);
                IDEN_TOKENS.add(t);
            }
            for (String s : BOOLS) {
                Token t = new Token(Token.TokenType.BOOLEAN_LITERAL, s);
                BOOL_TOKENS.add(t);
            }
            for (String s : STRS) {
                Token t = new Token(Token.TokenType.STRING_LITERAL, s);
                STR_TOKENS.add(t);
            }
            for (String s : NUMS) {
                Token t = new Token(Token.TokenType.NUMBER_LITERAL, s);
                NUM_TOKENS.add(t);
            }
            for (String s : DATES) {
                Token t = new Token(Token.TokenType.DATE_LITERAL, s);
                DATE_TOKENS.add(t);
            }
            for (String s : COMPS) {
                Token t = new Token(Token.TokenType.COMP_OPERATOR, s);
                COMP_TOKENS.add(t);
            }
            for (String s : LOGS) {
                Token t = new Token(Token.TokenType.LOG_OPERATOR, s);
                LOG_TOKENS.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lexer tests
     */
    @Nested
    @DisplayName("Lexer Tests")
    public class LexerTests {
        @Nested
        @DisplayName("Token Tests")
        public class TokenTests {
            @Test
            @DisplayName("KEYWORD Test")
            public void keywordTest() {
                RamaLexer rl = new RamaLexer("SELECT FROM WHERE INSERT INTO VALUES UPDATE SET DELETE CREATE DROP TABLE DATABASE NOT NULL PRIMARY KEY UNIQUE CHECK DEFAULT BOOL INT FLOAT DATE CHAR VARCHAR");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(KEY_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("IDENTIFIER Test")
            public void identifierTest() {
                RamaLexer rl = new RamaLexer("testcase _a_b_c abc123 z__z ____");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(IDEN_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("STRING_LITERAL Test")
            public void stringTest() {
                RamaLexer rl = new RamaLexer("'test_string' '1str2str3' '65' '-_-' 'l' '[something]'");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(STR_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("NUMBER_LITERAL Test")
            public void numberTest() {
                RamaLexer rl = new RamaLexer("1 0 0.0 -1 1.00000001 -0.0000001");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(NUM_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("BOOLEAN_LITERAL Test")
            public void booleanTest() {
                RamaLexer rl = new RamaLexer("TRUE FALSE");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(BOOL_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("DATE_LITERAL Test")
            public void dateTest() {
                RamaLexer rl = new RamaLexer("'1492-02-09' '0001-01-01' '9999-12-31' '2024-06-18' '1234-08-24'");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(DATE_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("COMP_OPERATOR Test")
            public void comparisonTest() {
                RamaLexer rl = new RamaLexer(">= <= != <> < = >");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(COMP_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("LOG_OPERATOR Test")
            public void logicalTest() {
                RamaLexer rl = new RamaLexer("AND OR");
                List<Token> act_tokens = rl.tokenize();
                for (int i = 0; i < act_tokens.size() - 1; i++) {
                    assertEquals(LOG_TOKENS.get(i), act_tokens.get(i));
                }
            }
            @Test
            @DisplayName("COMMA Test")
            public void commaTest() {
                RamaLexer rl = new RamaLexer(",");
                List<Token> act_tokens = rl.tokenize();
                assertEquals(COMMA_TOKEN, act_tokens.get(0));
            }
            @Test
            @DisplayName("PARENTHESIS_OPEN Test")
            public void pOpenTest() {
                RamaLexer rl = new RamaLexer("(");
                List<Token> act_tokens = rl.tokenize();
                assertEquals(P_OPEN_TOKEN, act_tokens.get(0));
            }
            @Test
            @DisplayName("PARENTHESIS_CLOSE Test")
            public void pCloseTest() {
                RamaLexer rl = new RamaLexer(")");
                List<Token> act_tokens = rl.tokenize();
                assertEquals(P_CLOSE_TOKEN, act_tokens.get(0));
            }
            @Test
            @DisplayName("NULL_LITERAL Test")
            public void nullTest() {
                RamaLexer rl = new RamaLexer("NULL");
                List<Token> act_tokens = rl.tokenize();
                assertEquals(NULL_TOKEN, act_tokens.get(0));
            }
            @Test
            @DisplayName("TERMINAL Test")
            public void terminalTest() {
                RamaLexer rl = new RamaLexer(";");
                List<Token> act_tokens = rl.tokenize();
                assertEquals(TERMINAL_TOKEN, act_tokens.get(0));
            }
            @Test
            @DisplayName("EOF Test")
            public void eofTest() {
                RamaLexer rl = new RamaLexer("");
                List<Token> act_tokens = rl.tokenize();
                assertEquals(EOF_TOKEN, act_tokens.get(0));
            }
        }
        @Nested
        @DisplayName("Tokenize Tests")
        public class TokenizeTests {
            @Test
            @DisplayName("Test Case I")
            public void test1(){
                RamaLexer rl = new RamaLexer("SELECT attr1 FROM table;");

                List<Token> tokens = rl.tokenize();
                for (int i = 0; i < tokens.size(); i++) {
                    assertEquals(test1[i], tokens.get(i));
                }
            }
            @Test
            @DisplayName("Test Case II")
            public void test2(){
                RamaLexer rl = new RamaLexer("CREATE TABLE t1 (id INT PRIMARY KEY, name VARCHAR(255) NOT NULL);");
                List<Token> tokens = rl.tokenize();
                for (int i = 0; i < tokens.size(); i++) {
                    assertEquals(test2[i], tokens.get(i));
                }
            }
            @Test
            @DisplayName("Test Case III")
            public void test3(){
                RamaLexer rl = new RamaLexer("INSERT INTO table (name, age) VALUES ('ethan_rama', 22);");
                List<Token> tokens = rl.tokenize();
                for (int i = 0; i < tokens.size(); i++) {
                    assertEquals(test3[i], tokens.get(i));
                }
            }

            @Test
            @DisplayName("Test Case IV")
            public void test4(){
                RamaLexer rl = new RamaLexer("UPDATE t1 SET name = 'ethan', age = 23 WHERE dob = '2002-01-29';");
                List<Token> tokens = rl.tokenize();
                for (int i = 0; i < tokens.size(); i++) {
                    assertEquals(test4[i], tokens.get(i));
                }
            }
        }
    }

    /**
     * Parser tests
     */
    @Nested
    @DisplayName("Parser Tests")
    public class ParserTests {

    }
}
