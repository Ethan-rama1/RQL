/*
 * RamaQueryParser.java
 * Author: Ethan Rama
 *
 * Version History: v1.01
 * 6/18/2024: v1.01
 * -
 * 6/17/2024: v1.0
 * - Created file
 *
 * 6/18/2024
 *
 */

package store;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines a query parser
 * @version 1.0
 * @author Ethan Rama
 */
class RamaQueryParser {
    /**
     * Divides query into tokens and handles specific CRUD operation
     * @param rdb selected RQL database
     * @param query RQL query
     * @author Ethan Rama
     */
    public static void parseAndExecute(RamaDatabase rdb, String query) {
        String[] tokens = query.split("\\s+");

        if (tokens[0].equalsIgnoreCase("CREATE")) {
            handleCreate(rdb, tokens);
        } else if (tokens[0].equalsIgnoreCase("DROP")) {
            handleDrop(rdb, tokens);
        } else if (tokens[0].equalsIgnoreCase("INSERT")) {
            handleInsert(rdb, tokens);
        } else if (tokens[0].equalsIgnoreCase("SELECT")) {
            handleSelect(rdb, tokens);
        } else {
            throw new IllegalArgumentException("Unknown query: " + query);
        }
    }

    /**
     * Performs CREATE operation on database
     * @param rdb selected RQL database
     * @param tokens tokens from query
     * @author Ethan Rama
     */
    private static void handleCreate(RamaDatabase rdb, String[] tokens) {
        String tableName = tokens[2];
        String[] columns = tokens[3].replace("(", "").replace(")", "").split(",");
        rdb.createTable(tableName, Arrays.asList(columns));
    }

    /**
     * Performs DROP operation on database
     * @param rdb selected RQL database
     * @param tokens tokens from query
     * @author Ethan Rama
     */
    private static void handleDrop(RamaDatabase rdb, String[] tokens) {
        String tableName = tokens[2];
        rdb.drop(tableName);
    }

    /**
     * Performs INSERT operation on database
     * @param rdb selected RQL database
     * @param tokens tokens from query
     * @author Ethan Rama
     */
    private static void handleInsert(RamaDatabase rdb, String[] tokens) {
        String tableName = tokens[2];
        String[] values = tokens[4].replace("(", "").replace(")", "").split(",");
        RamaTable table = rdb.tables.get(tableName);

        if (table == null) {
            throw new IllegalArgumentException("Table " + tableName + " does not exist.");
        }

        List<String> columns = table.getColumns();
        if (columns.size() != values.length) {
            throw new IllegalArgumentException("Column count does not match value count.");
        }

        Map<String, String> row = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            row.put(columns.get(i), values[i]);
        }
        rdb.insert(tableName, row);
    }

    /**
     * Performs SELECT operation on database
     * @param rdb selected RQL database
     * @param tokens tokens from query
     * @author Ethan Rama
     */
    private static void handleSelect(RamaDatabase rdb, String[] tokens) {
        String tableName = tokens[3];
        List<Map<String, String>> rows = rdb.select(tableName);

        for (Map<String, String> row : rows) {
            System.out.println(row);
        }
    }
}
