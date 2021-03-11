package com.progress.todoList.constants;

public class UrlConstants {
    private UrlConstants(){
    }

    public static final String ENHANCED_CONTROLLER_BASE_URL = "/enhanced/todo";
    public static final String BASIC_CONTROLLER_BASE_URL = "/todo";

    public static final String GET_LIST_URI = "/getList/{name}";
    public static final String CREATE_LIST_URI = "/createList/{name}/{description}";
    public static final String ADD_ITEM_URI = "/updateList/addItem/{name}";
    public static final String DELETE_ITEM_URI = "/updateList/deleteItem/{listName}/{itemName}";
}
