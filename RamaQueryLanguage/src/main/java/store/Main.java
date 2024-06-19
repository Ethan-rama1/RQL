package store;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String sql = "CREATE TABLE employees (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, birth_date DATE, is_active BOOL DEFAULT TRUE, salary FLOAT, position CHAR(50) NOT NULL, CHECK (salary >= 0));";
        RamaLexer lexer = new RamaLexer(sql);
        List<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
