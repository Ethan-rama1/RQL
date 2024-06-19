/*
 * ASTNode.java
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

import java.util.List;

/**
 * Represents a node in the syntax tree
 * @version 1.0
 * @author Ethan Rama
 */
public abstract class ASTNode {}

/**
 * Represents an RQL statement
 * @version 1.0
 * @author Ethan Rama
 */
class RQLStatement extends ASTNode {}

/**
 * Represents a CREATE TABLE statement
 * @version 1.0
 * @author Ethan Rama
 */
class CreateTableStatement extends RQLStatement {
    /**
     * Name of the table
     */
    private String tableName;

    /**
     * List of attribute definitions
     */
    private List<ColumnDefinition> columns;

    /**
     * Constructs a CreateTableStatement object
     * @param tableName name of table
     * @param columns list of attributes
     * @author Ethan Rama
     */
    public CreateTableStatement(String tableName, List<ColumnDefinition> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    /**
     * Get the table name
     * @return name of table
     * @author Ethan Rama
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Set the table name
     * @param tableName name of table
     * @author Ethan Rama
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get the list of attribute definitions
     * @return list of attribute definitions
     * @author Ethan Rama
     */
    public List<ColumnDefinition> getColumns() {
        return columns;
    }

    /**
     * Set the list of attribute definitions
     * @param columns list of attribute definitions
     * @author Ethan Rama
     */
    public void setColumns(List<ColumnDefinition> columns) {
        this.columns = columns;
    }
}

/**
 * Represents a INSERT INTO statement
 * @version 1.0
 * @author Ethan Rama
 */
class InsertIntoStatement extends RQLStatement {
    private String tableName;
    private List<String> columnList;
    private List<Row> valueList;
}

/**
 * Represents a column definition node
 * @version 1.0
 * @author Ethan Rama
 */
class ColumnDefinition extends ASTNode {
    /**
     * Name of the attribute
     */
    private String columnName;

    /**
     * Datatype of the attribute
     */
    private String dataType;

    /**
     * List of table constraints
     */
    private List<String> constraints;

    /**
     * Construct the definition of an attribute
     * @param columnName attribute name
     * @param dataType attribute type
     * @param constraints list of attribute constraints
     * author Ethan Rama
     */
    public ColumnDefinition(String columnName, String dataType, List<String> constraints) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.constraints = constraints;
    }

    /**
     * Get the attribute name
     * @return attribute name
     * author Ethan Rama
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Set the attribute name
     * @param columnName attribute name
     * author Ethan Rama
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Get the attribute type
     * @return attribute type
     * author Ethan Rama
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Set the attribute type
     * @param dataType attribute type
     * author Ethan Rama
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Get the list of attribute constraints
     * @return list of attribute constraints
     * author Ethan Rama
     */
    public List<String> getConstraints() {
        return constraints;
    }

    /**
     * Set the list of attribute constraints
     * @param constraints list of attribute constraints
     * @author Ethan Rama
     */
    public void setConstraints(List<String> constraints) {
        this.constraints = constraints;
    }
}

/**
 * Represents a row node
 * @version 1.0
 * @author Ethan Rama
 */
class Row extends ASTNode {
    private List<Value> valueList;
    // Constructor, getters, and setters
}

class Value extends ASTNode {

}


