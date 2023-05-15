package com.example.demo.model.builder;

import com.example.demo.model.TodoItem;

public class TodoItemBuilder {
    private String notes;
    private String title;
    private Long id;
    private String externalId;

    private TodoItemBuilder() {}

    public TodoItem build() {
        // TODO: Add validation for null fields and stuff
        return new TodoItem(this.id, this.externalId, this.notes, this.title);
    }

    public static TodoItemBuilder Builder() {
        return new TodoItemBuilder();
    }

    public static TodoItemBuilder fromItem(TodoItem todoItem) {
        TodoItemBuilder builder = new TodoItemBuilder();

        return builder
                .withId(todoItem.getId())
                .withExternalId(todoItem.getExternalId())
                .withNotes(todoItem.getNotes())
                .withTitle(todoItem.getTitle());
    }

    public TodoItemBuilder withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public TodoItemBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TodoItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TodoItemBuilder withExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }


}
