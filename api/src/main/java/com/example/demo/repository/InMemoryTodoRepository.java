package com.example.demo.repository;

import com.example.demo.model.TodoItem;
import com.example.demo.model.builder.TodoItemBuilder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.model.builder.TodoItemBuilder.fromItem;

@Repository
public class InMemoryTodoRepository implements TodoRepository {
    private Long idCounter = 1L;
    Map<Long, TodoItem> idToItem;
    Map<String, TodoItem> externalIdToItem;

    public InMemoryTodoRepository() {
        this.externalIdToItem = new HashMap<>();
        this.idToItem = new HashMap<>();
    }

    @Override
    public Optional<TodoItem> getTodoById(Long id) {
        return Optional.ofNullable(idToItem.get(id));
    }

    @Override
    public Optional<TodoItem> getTodoByExternalId(String externalId) {
        return Optional.ofNullable(externalIdToItem.get(externalId));
    }

    @Override
    public void createTodoItem(TodoItem todoItem) {
        TodoItem itemToPersist = fromItem(todoItem)
                .withId(idCounter)
                .build();

        idToItem.put(itemToPersist.getId(), itemToPersist);
        externalIdToItem.put(itemToPersist.getExternalId(), itemToPersist);

        idCounter += 1;
    }

    @Override
    public void updateTodoItem(TodoItem todoItem) {
        TodoItem persistedItem = idToItem.get(todoItem.getId());
        TodoItem updatedItem = TodoItemBuilder.fromItem(persistedItem)
                .withNotes(todoItem.getNotes())
                .withTitle(todoItem.getTitle())
                .build();

        idToItem.put(persistedItem.getId(), updatedItem);
        externalIdToItem.put(persistedItem.getExternalId(), updatedItem);
    }
}
