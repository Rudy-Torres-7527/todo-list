package com.progress.todoList.repository;

import lombok.NonNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import static com.progress.todoList.constants.QueryConstants.CREATE_TODO_ITEM;
import static com.progress.todoList.constants.QueryConstants.CREATE_TODO_LIST;
import static com.progress.todoList.constants.QueryConstants.DROP_TODO_ITEM;
import static com.progress.todoList.constants.QueryConstants.DROP_TODO_LIST;

@Component
public class H2Repo {

    Connection conn = null;
    Statement stmt = null;

    public H2Repo(){
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test", "", "");
            stmt = conn.createStatement();

            updateDB(DROP_TODO_LIST);
            updateDB(DROP_TODO_ITEM);
            updateDB(CREATE_TODO_LIST);
            updateDB(CREATE_TODO_ITEM);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet queryDB(@NonNull String query) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void updateDB(@NonNull String query){
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            if(e.getMessage().contains("Unique index or primary key violation")){
                throw new DuplicateKeyException("Name provided for List or Item is already in DB");
            } else {
                e.printStackTrace();
            }
        }
    }

}
