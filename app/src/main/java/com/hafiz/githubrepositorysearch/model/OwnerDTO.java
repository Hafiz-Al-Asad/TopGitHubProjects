package com.hafiz.githubrepositorysearch.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OwnerDTO implements Serializable {

    @SerializedName("id")
    private Long id;
    @SerializedName("login")
    private String ownerName;
    @SerializedName("description")
    private String description;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
