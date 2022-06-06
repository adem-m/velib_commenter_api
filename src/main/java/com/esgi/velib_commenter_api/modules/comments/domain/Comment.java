package com.esgi.velib_commenter_api.modules.comments.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Comment {
    private final String id;
    private final String userId;
    private String content;
    private final String stationId;
    private final String image;
    private final LocalDateTime createdAt;

    public Comment(
            String id,
            String userId,
            String content,
            String stationId,
            String image,
            LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.stationId = stationId;
        this.image = image;
        this.createdAt = createdAt;
    }

    public String id() {
        return id;
    }

    public String userId() {
        return userId;
    }

    public String content() {
        return content;
    }

    public String stationId() {
        return stationId;
    }

    public String image() {
        return image;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Comment) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.content, that.content) &&
                Objects.equals(this.stationId, that.stationId) &&
                Objects.equals(this.image, that.image) &&
                Objects.equals(this.createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, content, stationId, image, createdAt);
    }

    @Override
    public String toString() {
        return "Comment[" +
                "id=" + id + ", " +
                "userId=" + userId + ", " +
                "content=" + content + ", " +
                "stationId=" + stationId + ", " +
                "image=" + image + ", " +
                "createdAt=" + createdAt + ']';
    }

}
