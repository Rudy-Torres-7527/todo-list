package com.progress.todoList.constants;

public class QueryConstants {

    private QueryConstants(){

    }

    public static final String CREATE_TODO_LIST = "CREATE TABLE Todo_List(ID int NOT NULL AUTO_INCREMENT, Name varchar(100) NOT NULL PRIMARY KEY, Description varchar(100) NOT NULL);";
    public static final String CREATE_TODO_ITEM = "CREATE TABLE Todo_Item(ID int NOT NULL AUTO_INCREMENT, Name varchar(100) NOT NULL PRIMARY KEY, Details varchar(100) NOT NULL, Effective_Date Date, Completed bool, ListID int NOT NULL, FOREIGN KEY (ListID) REFERENCES Todo_List(ID));";

    public static final String DROP_TODO_LIST = "DROP TABLE IF EXISTS TODO_LIST";
    public static final String DROP_TODO_ITEM = "DROP TABLE IF EXISTS TODO_ITEM";

    public static final String FETCH_TODO_LIST = "SELECT * FROM TODO_LIST";
    public static final String FETCH_TODO_ITEM = "SELECT * FROM TODO_ITEM";

    public static final String INSERT_TODO_LIST = "INSERT INTO TODO_LIST";
    public static final String INSERT_TODO_ITEM = "INSERT INTO TODO_ITEM";

    public static final String DELETE_TODO_ITEM = "DELETE FROM TODO_ITEM WHERE NAME = '%s' AND LISTID = '%s'";

    public static final String FETCH_TODO_LIST_MODEL = "SELECT A.ID as ListID, A.NAME, A.DESCRIPTION, B.NAME as ItemName, B.DETAILS FROM TODO_LIST A LEFT JOIN TODO_ITEM  B ON A.ID = B.LISTID WHERE A.NAME = '%s'";
    public static final String FETCH_ALL_ENHANCED_TODO_LISTS = "SELECT A.ID as ListID, A.NAME, A.DESCRIPTION, B.ID as ItemID, B.NAME as ItemName, B.DETAILS as ItemDetails, B.EFFECTIVE_DATE, B.COMPLETED FROM TODO_LIST A LEFT JOIN TODO_ITEM  B ON A.ID = B.LISTID ORDER BY A.NAME";

}
