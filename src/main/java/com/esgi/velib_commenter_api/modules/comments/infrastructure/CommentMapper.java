package com.esgi.velib_commenter_api.modules.comments.infrastructure;

import com.esgi.velib_commenter_api.modules.comments.domain.Comment;

public class CommentMapper {
    public static CommentEntity toEntity(Comment comment) {
        return new CommentEntity(
                comment.id(),
                comment.content(),
                comment.stationId(),
                comment.userId(),
                comment.createdAt(),
                comment.image()
        );
    }

    public static Comment toDomain(CommentEntity commentEntity) {
        return new Comment(
                commentEntity.getId(),
                commentEntity.getUserId(),
                commentEntity.getContent(),
                commentEntity.getStationId(),
                commentEntity.getImage(),
                commentEntity.getCreatedAt()
        );
    }
}
