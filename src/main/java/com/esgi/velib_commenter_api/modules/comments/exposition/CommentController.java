package com.esgi.velib_commenter_api.modules.comments.exposition;

import com.esgi.velib_commenter_api.modules.authentification.domain.Token;
import com.esgi.velib_commenter_api.modules.authentification.domain.TokenService;
import com.esgi.velib_commenter_api.modules.comments.application.CreateComment;
import com.esgi.velib_commenter_api.modules.comments.domain.Comment;
import com.esgi.velib_commenter_api.modules.comments.domain.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final TokenService tokenService;

    public CommentController(CommentService commentService, TokenService tokenService) {
        this.commentService = commentService;
        this.tokenService = tokenService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<CommentResponse>> getCommentsByStationId(@PathVariable String id) {
        List<Comment> comments = commentService.getCommentsByStationId(id);
        List<CommentResponse> response = comments
                .stream().map(comment ->
                        new CommentResponse(
                                comment.id(),
                                comment.userId(),
                                comment.content(),
                                comment.stationId(),
                                comment.image(),
                                comment.createdAt().toString())
                ).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> addComment(
            @RequestHeader("authorization") String token,
            @RequestPart(required = false) MultipartFile image,
            @RequestParam String content,
            @RequestParam String stationId
    ) throws IOException {
        String userId = tokenService.getUserId(new Token(token));
        String encodedImage = image == null ? null : Base64.getEncoder().encodeToString(image.getBytes());
        CreateComment createComment = new CreateComment(
                userId,
                content,
                stationId,
                encodedImage
        );
        commentService.createComment(createComment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(
            @RequestHeader("authorization") String token,
            @PathVariable String id,
            @RequestBody @Valid UpdateCommentRequest request
    ) {
        String userId = tokenService.getUserId(new Token(token));
        commentService.updateComment(userId, id, request.content);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @RequestHeader("authorization") String token,
            @PathVariable String id) {
        String userId = tokenService.getUserId(new Token(token));
        commentService.deleteComment(userId, id);
        return ResponseEntity.noContent().build();
    }
}

