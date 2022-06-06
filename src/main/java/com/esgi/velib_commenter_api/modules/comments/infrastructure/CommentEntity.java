package com.esgi.velib_commenter_api.modules.comments.infrastructure;

import com.esgi.velib_commenter_api.modules.users.infrastructure.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String stationId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Lob
    @Column
    private String image;

    public CommentEntity() {
    }

    public CommentEntity(String id, String content, String stationId, String userId, LocalDateTime createdAt, String image) {
        this.id = id;
        this.content = content;
        this.stationId = stationId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getStationId() {
        return stationId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getImage() {
        return image;
    }
}
