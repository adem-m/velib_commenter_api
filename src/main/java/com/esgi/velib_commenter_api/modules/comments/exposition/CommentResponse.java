package com.esgi.velib_commenter_api.modules.comments.exposition;

public record CommentResponse(
        String id,
        String userId,
        String content,
        String stationId,
        String image,
        String createdAt) {
}
