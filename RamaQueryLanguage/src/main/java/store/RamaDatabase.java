/*
 * RamaDatabase.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the structure of the database
 * @version 1.0
 * @author Ethan Rama
 */
public class RamaDatabase {
    /**
     * List of unique tables in database
     */
    protected final Map<String, RamaTable> tables;

    /**
     * Constructs an empty database
     * @author Ethan Rama
     */
    public RamaDatabase() {
        tables = new HashMap<>();
    }

    /**
     * Creates a new table in database
     * @param name name of table
     * @param columns list of attributes
     * @author Ethan Rama
     */
    public void createTable(String name, List<String> columns) {
        tables.put(name, new RamaTable(name, columns));
    }

    /**
     * Inserts a data entry into a table
     * @param tableName name of table
     * @param row data entry
     * @author Ethan Rama
     */
    public void insert(String tableName, Map<String, String> row) {
        RamaTable table = tables.get(tableName);
        if (table != null) {
            table.insert(row);
        } else {
            throw new IllegalArgumentException("Table " + tableName + " does not exist.");
        }
    }

    /**
     * Gets data from a table
     * @param tableName name of table
     * @return list of data entries
     * @author Ethan Rama
     */
    public List<Map<String, String>> select(String tableName) {
        RamaTable table = tables.get(tableName);
        if (table != null) {
            return table.getRows();
        } else {
            throw new IllegalArgumentException("Table " + tableName + " does not exist.");
        }
    }

    /**
     * Drops table from the database
     * @param tableName name of table
     * @author Ethan Rama
     */
    public void drop(String tableName) {
        this.tables.remove(tableName);
    }
}
