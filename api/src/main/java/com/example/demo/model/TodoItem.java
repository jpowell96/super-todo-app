package com.example.demo.model;

import java.util.Objects;

public class TodoItem {
    private Long id;
    private String externalId;
    private String title;

    private String notes;

    public TodoItem(Long id, String externalId, String title, String notes) {
        this.id = id;
        this.externalId = externalId;
        this.title = title;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(id, todoItem.id) && Objects.equals(externalId, todoItem.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", title='" + title + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
