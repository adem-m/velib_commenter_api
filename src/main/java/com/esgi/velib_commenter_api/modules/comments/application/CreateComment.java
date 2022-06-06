package com.esgi.velib_commenter_api.modules.comments.application;

public record CreateComment(String userId, String content, String stationId, String image) {
}
