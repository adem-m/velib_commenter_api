package com.esgi.velib_commenter_api.modules.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public void addComment(@RequestPart MultipartFile image) throws IOException {
        CommentEntity comment = new CommentEntity(UUID.randomUUID().toString(), Base64.getEncoder().encodeToString(image.getBytes()));
        repository.save(comment);
    }

    @GetMapping
    public List<CommentEntity> getComments() {
        return repository.findAll();
    }
}

@Repository
interface CommentRepository extends JpaRepository<CommentEntity, String> {
}
