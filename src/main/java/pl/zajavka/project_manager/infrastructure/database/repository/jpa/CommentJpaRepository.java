package pl.zajavka.project_manager.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.infrastructure.database.entity.CommentEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {

    List<Comment> findByProject(ProjectEntity projectEntity);
}
