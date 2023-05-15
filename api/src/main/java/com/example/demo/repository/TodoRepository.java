package com.example.demo.repository;

import com.example.demo.model.TodoItem;

import java.util.Optional;

public interface TodoRepository {

    Optional<TodoItem> getTodoById(Long id);

    Optional<TodoItem> getTodoByExternalId(String externalId);
    void createTodoItem(TodoItem todoItem);

    void updateTodoItem(TodoItem todoItem);



}
