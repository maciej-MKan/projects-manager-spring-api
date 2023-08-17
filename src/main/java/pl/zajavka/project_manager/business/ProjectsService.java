package pl.zajavka.project_manager.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.project_manager.business.dao.ProjectsDAO;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectsService {

    private final ProjectsDAO projectsDAO;

    @Transactional
    public List<Project> findAllProjects(){
        List<Project> allProjects = projectsDAO.findAllProjects();
        log.info("Found and return [{}] projects", allProjects.size());
        return allProjects;
    }

    @Transactional
    public List<Project> findProjectsByUser(User user){
        return Optional.ofNullable(projectsDAO.findProjectsByUser(user))
                .orElseThrow();
    }

    @Transactional
    public Project saveProject(Project project){
        return projectsDAO.saveProject(project);
    }

    public Project updateProject(Project project){
        if (project.getProjectId() == null){
            throw new RuntimeException();
        }
        return projectsDAO.saveProject(project);
    }

    public void deleteProject(Project project){
        projectsDAO.deleteProject(project);
    }
}
