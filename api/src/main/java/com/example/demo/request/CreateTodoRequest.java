package com.example.demo.request;

public class CreateTodoRequest {

    private String title;
    private String notes;

    public CreateTodoRequest(String title, String notes) {
        this.title = title;
        this.notes = notes;
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
}
