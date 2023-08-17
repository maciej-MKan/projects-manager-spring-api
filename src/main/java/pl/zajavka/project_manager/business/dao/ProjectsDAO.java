package pl.zajavka.project_manager.business.dao;

import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;

import java.util.List;

public interface ProjectsDAO {

    List<Project> findAllProjects();

    List<Project> findProjectsByUser(User user);

    Project saveProject(Project project);

    void deleteProject(Project project);
}
