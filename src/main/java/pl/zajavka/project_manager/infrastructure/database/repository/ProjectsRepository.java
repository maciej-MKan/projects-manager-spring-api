package pl.zajavka.project_manager.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.project_manager.business.dao.ProjectsDAO;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.ProjectJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.mapper.ProjectEntityMapper;
import pl.zajavka.project_manager.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProjectsRepository implements ProjectsDAO {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectEntityMapper projectEntityMapper;
    private final UserEntityMapper userEntityMapper;


    @Override
    public List<Project> findAllProjects() {
        return projectJpaRepository.findAll().stream()
                .map(projectEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Project> findProjectsByUser(User user) {
        UserEntity userEntity = userEntityMapper.mapToEntity(user);
        return projectJpaRepository.findByUsers(userEntity).stream()
                .map(projectEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Project saveProject(Project project) {
        ProjectEntity projectEntity = projectEntityMapper.mapToEntity(project);
        ProjectEntity savedEntity = projectJpaRepository.save(projectEntity);
        return projectEntityMapper.mapFromEntity(savedEntity);
    }

    @Override
    public void deleteProject(Project project) {
        projectJpaRepository.deleteById(project.getProjectId());

    }
}
