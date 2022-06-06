package com.esgi.velib_commenter_api.modules.comments.domain;

import com.esgi.velib_commenter_api.kernel.Clock;
import com.esgi.velib_commenter_api.kernel.ForbiddenOperationException;
import com.esgi.velib_commenter_api.kernel.NoSuchEntityException;
import com.esgi.velib_commenter_api.modules.comments.application.CreateComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final Clock clock;

    public CommentService(CommentRepository commentRepository, Clock clock) {
        this.commentRepository = commentRepository;
        this.clock = clock;
    }

    public void createComment(CreateComment createComment) {
        log.info("Create comment");
        Comment comment = new Comment(
                UUID.randomUUID().toString(),
                createComment.userId(),
                createComment.content(),
                createComment.stationId(),
                createComment.image(),
                clock.now()
        );
        commentRepository.save(comment);
    }

    public void deleteComment(String userId, String id) {
        Comment comment = commentRepository.findById(id);
        if (comment == null) {
            log.error("Comment {} not found", id);
            throw new NoSuchEntityException(Comment.class);
        }
        if (!comment.userId().equals(userId)) {
            log.error("User {} is not allowed to delete comment {}", userId, id);
            throw new ForbiddenOperationException("User is not allowed to delete comment");
        }
        log.info("Delete comment {}", id);
        commentRepository.delete(id);
    }

    public void updateComment(String userId, String id, String content) {
        log.info("Update comment {}", id);
        Comment comment = commentRepository.findById(id);
        if (comment == null) {
            log.error("Comment {} not found", id);
            throw new NoSuchEntityException(Comment.class);
        }
        if (!comment.userId().equals(userId)) {
            log.error("User {} is not allowed to update comment {}", userId, id);
            throw new ForbiddenOperationException("User id does not match");
        }
        comment.setContent(content);
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByStationId(String stationId) {
        log.info("Get comment by station id {}", stationId);
        return commentRepository.getByStationId(stationId);
    }
}
