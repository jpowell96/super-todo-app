package com.example.demo.model;

import java.util.Objects;

public class ApplicationUser {
    private String id;

    // External ID will be a UUID
    private String externalId;
    private String username;
    private String password;

    public ApplicationUser(String id, String externalId, String username, String password) {
        this.id = id;
        this.externalId = externalId;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUser that = (ApplicationUser) o;
        return Objects.equals(id, that.id) && Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId);
    }
}
