package com.example.demo.request;

public class UpdateTodoRequest {
    private String externalId;
    private String title;
    private String notes;

    public UpdateTodoRequest(String externalId, String title, String notes) {
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
}
