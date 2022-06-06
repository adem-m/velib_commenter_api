package com.esgi.velib_commenter_api.modules.comments.infrastructure;

import com.esgi.velib_commenter_api.modules.comments.domain.Comment;
import com.esgi.velib_commenter_api.modules.comments.domain.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SpringDataCommentRepository implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;

    public SpringDataCommentRepository(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    @Override
    public Comment findById(String id) {
        Optional<CommentEntity> comment = jpaCommentRepository.findById(id);
        return comment.map(CommentMapper::toDomain).orElse(null);
    }

    @Override
    public void save(Comment comment) {
        jpaCommentRepository.save(CommentMapper.toEntity(comment));
    }

    @Override
    public void delete(String id) {
        jpaCommentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getByStationId(String stationId) {
        return jpaCommentRepository
                .findAllByStationId(stationId)
                .stream().map(CommentMapper::toDomain)
                .toList();
    }
}

@Repository
interface JpaCommentRepository extends JpaRepository<CommentEntity, String> {
    List<CommentEntity> findAllByStationId(String stationId);
}
