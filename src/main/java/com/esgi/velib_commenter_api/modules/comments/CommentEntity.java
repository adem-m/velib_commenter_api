package com.esgi.velib_commenter_api.modules.comments;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Lob
    @Column
    private String image;

    public CommentEntity(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public CommentEntity() {
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
