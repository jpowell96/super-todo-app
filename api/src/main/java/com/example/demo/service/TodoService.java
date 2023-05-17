package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.TodoItem;
import com.example.demo.model.builder.TodoItemBuilder;
import com.example.demo.repository.TodoRepository;
import com.example.demo.request.CreateTodoRequest;
import com.example.demo.request.UpdateTodoRequest;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoItem> items() {
        return List.of(new TodoItem(5L, "abc-def", "My Todo", "This is the message."));
    }

    @WithSpan
    public TodoItem createTodoItem(CreateTodoRequest request) {
        String uuid = UUID.randomUUID().toString();
        TodoItem toCreate = new TodoItem(null, uuid, request.getTitle(), request.getNotes());
        todoRepository.createTodoItem(toCreate);
        return getTodo(uuid);
    }

    @WithSpan
    public TodoItem getTodo(String externalId) {
        return todoRepository
                .getTodoByExternalId(externalId)
                .orElseThrow(() -> new NotFoundException("No item found for id: " + externalId));
    }

    public TodoItem updateTodo(UpdateTodoRequest updateTodoRequest) {
        // 1. Get the existing item
        TodoItem persistedTodoItem = getTodo(updateTodoRequest.getExternalId());

        // 2. Override the notes and details
        TodoItem toUpdate = TodoItemBuilder.fromItem(persistedTodoItem)
                .withTitle(updateTodoRequest.getTitle())
                .withNotes(updateTodoRequest.getNotes())
                .build();

        // 3. Update the todo item
        todoRepository.updateTodoItem(toUpdate);

        // 4. Return the updated Todo
        return getTodo(toUpdate.getExternalId());
    }
}
