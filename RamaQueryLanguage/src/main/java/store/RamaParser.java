/*
 * RamaParser.java
 * Author: Ethan Rama
 *
 * Version History: v1.0
 * 6/18/2024: v1.0
 * - Created file
 *
 * 6/18/2024
 *
 */

package store;

import java.util.ArrayList;
import java.util.List;

//TODO: Transfer all grammars defined on iPad into statements

/**
 * Represents the RQL Parser
 */
public class RamaParser {
    private final List<Token> tokens;
    private int position;
    public RamaParser(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    public RQLStatement parse() {
        Token currentToken = tokens.get(position);
        if (currentToken.getType() == Token.TokenType.KEYWORD) {
            switch (currentToken.getValue().toUpperCase()) {
                case "CREATE":
                    return parseCreateTableStatement();
                case "INSERT":
                    return parseInsertIntoStatement();
                default:
                    throw new RuntimeException("Unsupported statement: " + currentToken.getValue());
            }
        }
        throw new RuntimeException("Invalid SQL statement");
    }

    private CreateTableStatement parseCreateTableStatement() {
        expectToken("CREATE");
        expectToken("TABLE");
        String tableName = expectToken(Token.TokenType.IDENTIFIER).getValue();
        expectToken(Token.TokenType.PARENTHESIS_OPEN);
        List<ColumnDefinition> columns = new ArrayList<>();
        do {
            columns.add(parseColumnDefinition());
        } while (matchToken(Token.TokenType.COMMA));
        expectToken(Token.TokenType.PARENTHESIS_CLOSE);

        return new CreateTableStatement(tableName, columns);
    }

    //TODO: Work on logic
    private InsertIntoStatement parseInsertIntoStatement() {
        expectToken("INSERT");
        expectToken("INTO");
        String tableName = expectToken(Token.TokenType.IDENTIFIER).getValue();
        expectToken(Token.TokenType.PARENTHESIS_OPEN);
        List<String> columns = new ArrayList<>();
        do {
            // TODO: Column List
            columns.add(null);
        } while (matchToken(Token.TokenType.COMMA));
        expectToken(Token.TokenType.PARENTHESIS_OPEN);
        expectToken(Token.TokenType.KEYWORD);
        expectToken(Token.TokenType.PARENTHESIS_OPEN);
        do {
            // TODO: Constraints
        } while (matchToken(Token.TokenType.COMMA));
        expectToken(Token.TokenType.PARENTHESIS_OPEN);
        return new InsertIntoStatement();
    }

    private ColumnDefinition parseColumnDefinition() {
        String columnName = expectToken(Token.TokenType.IDENTIFIER).getValue();
        String dataType = expectToken(Token.TokenType.KEYWORD).getValue();
        List<String> constraints = new ArrayList<>();
        while (matchToken(Token.TokenType.KEYWORD)) {
            constraints.add(expectToken(Token.TokenType.KEYWORD).getValue());
        }
        return new ColumnDefinition(columnName, dataType, constraints);
    }

    private boolean matchToken(Token.TokenType type) {
        if (position < tokens.size() && tokens.get(position).getType() == type) {
            position++;
            return true;
        }
        return false;
    }

    private Token expectToken(String value) {
        Token token = tokens.get(position);
        if (token.getValue().equalsIgnoreCase(value)) {
            position++;
            return token;
        }
        throw new RuntimeException("Expected token: " + value + " but found: " + token.getValue());
    }

    private Token expectToken(Token.TokenType type) {
        Token token = tokens.get(position);
        if (token.getType() == type) {
            position++;
            return token;
        }
        throw new RuntimeException("Expected token of type: " + type + " but found: " + token.getType());
    }
}
