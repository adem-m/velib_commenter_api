package com.esgi.velib_commenter_api.modules.comments.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {
    Comment findById(String id);

    void save(Comment comment);

    void delete(String id);

    List<Comment> getByStationId(String stationId);
}
