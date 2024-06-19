/*
 * RamaTable.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a table in the database
 * @version 1.0
 * @author Ethan Rama
 */
public class RamaTable {
    /**
     * Name of the table
     */
    private String name;

    /**
     * Attributes in the table
     */
    private List<String> columns;

    /**
     * Data in the table
     */
    private List<Map<String, String>> rows;

    /**
     * Constructs a table with defined attributes
     * @param name table name
     * @param columns list of table attributes
     * @author Ethan Rama
     */
    public RamaTable(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
        this.rows = new ArrayList<>();
    }

    /**
     * Inserts an entry of data into table
     * @param row data entry
     * @author Ethan Rama
     */
    public void insert(Map<String, String> row) {
        rows.add(row);
    }

    /**
     * Get list of data
     * @return list of data
     * @author Ethan Rama
     */
    public List<Map<String, String>> getRows() {
        return rows;
    }

    /**
     * Get list of attributes
     * @return list of attributes
     * @author Ethan Rama
     */
    public List<String> getColumns() {
        return columns;
    }

    /**
     * Get table name
     * @return table name
     * @author Ethan Rama
     */
    public String getName() {
        return name;
    }

    /**
     * Sets table name
     * @param name table name
     * @author Ethan Rama
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets attributes in table
     * @param columns list of attributes
     * @author Ethan Rama
     */
    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    /**
     * Sets data in table
     * @param rows list of data
     * @author Ethan Rama
     */
    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }

    /**
     * Returns a string representation <br><br>
     *
     *
     * @return string representation of a RamaTable object
     * @author Ethan Rama
     */
    @Override
    public String toString() {
        return "RamaTable{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", rows=" + rows +
                '}';
    }

    /**
     * Compares two RamaTable objects by testing for reflexivity, if
     * the compared object is null, and if the two object's attributes are
     * the same.
     * @param o Some object to be compared with this RamaTable
     * @return result of comparison of RamaTable object with object o
     * @author Ethan Rama
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RamaTable ramaTable = (RamaTable) o;
        return Objects.equals(name, ramaTable.name) && Objects.equals(columns, ramaTable.columns) && Objects.equals(rows, ramaTable.rows);
    }

    /**
     * Creates a hash of this RamaTable object's attributes.
     * @return The hash of this RamaTable object
     * @author Ethan Rama
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, columns, rows);
    }
}
