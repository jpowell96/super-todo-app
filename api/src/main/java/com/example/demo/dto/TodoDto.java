package com.example.demo.dto;

import com.example.demo.model.TodoItem;

public class TodoDto {
    private String externalId;
    private String title;
    private String notes;

    public TodoDto() {}
    public TodoDto(String externalId, String title, String notes) {
        this.externalId = externalId;
        this.title = title;
        this.notes = notes;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static TodoDto fromTodoItem(TodoItem todoItem) {
        TodoDto dto = new TodoDto();
        dto.setNotes(todoItem.getNotes());
        dto.setTitle(todoItem.getTitle());
        dto.setExternalId(todoItem.getExternalId());

        return dto;
    }
}
