package com.hafiz.githubrepositorysearch.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDTO implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
