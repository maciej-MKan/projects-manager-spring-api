package pl.zajavka.project_manager.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;

import java.util.List;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Integer> {

    //    List<ProjectEntity> findAllProjects();
    List<ProjectEntity> findByUsers(UserEntity user);

//    ProjectEntity save(ProjectEntity project);
}
