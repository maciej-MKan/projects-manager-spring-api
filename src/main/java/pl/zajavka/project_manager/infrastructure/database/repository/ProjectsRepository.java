package pl.zajavka.project_manager.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.project_manager.business.dao.ProjectsDAO;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.ProjectJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.mapper.ProjectEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProjectsRepository implements ProjectsDAO {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectEntityMapper projectEntityMapper;


    @Override
    public List<Project> findAllProjects() {
        return projectJpaRepository.findAllProjects().stream()
                .map(projectEntityMapper::mapFromEntity)
                .toList();
    }
}
