package pl.zajavka.project_manager.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.project_manager.business.dao.CommentsDAO;
import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.infrastructure.database.entity.CommentEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.CommentJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.mapper.CommentEntityMapper;
import pl.zajavka.project_manager.infrastructure.database.repository.mapper.ProjectEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class CommentRepository implements CommentsDAO {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentEntityMapper commentEntityMapper;
    private final ProjectEntityMapper projectEntityMapper;

    @Override
    public List<Comment> findCommentsByProject(Project project) {
        ProjectEntity projectEntity = projectEntityMapper.mapToEntity(project);
        return commentJpaRepository.findByProject(projectEntity);
    }

    @Override
    public Comment saveComment(Comment comment) {
        CommentEntity commentEntity = commentEntityMapper.mapToEntity(comment);
        CommentEntity newComment = commentJpaRepository.save(commentEntity);
        return commentEntityMapper.mapFromEntity(newComment);
    }
}
